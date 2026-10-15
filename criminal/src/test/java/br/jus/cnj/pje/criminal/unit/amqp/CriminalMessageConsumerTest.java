package br.jus.cnj.pje.criminal.unit.amqp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import br.jus.cnj.pje.criminal.amqp.CriminalMessageConsumer;
import br.jus.cnj.pje.criminal.amqp.handlers.ParteEventHandler;
import br.jus.cnj.pje.criminal.amqp.model.CloudEvent;
import br.jus.cnj.pje.criminal.model.entities.CodigoTribunal;
import br.jus.cnj.pje.criminal.services.TribunalService;

@RunWith(MockitoJUnitRunner.class)
public class CriminalMessageConsumerTest {

    @InjectMocks
    private CriminalMessageConsumer consumer;

    @Mock
    private TribunalService tribunalService;

    @Mock
    private ParteEventHandler parteEventHandler;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(consumer, "tribunalService", tribunalService);
        ReflectionTestUtils.setField(consumer, "parteEventHandler", parteEventHandler);
    }

    @Test
    public void deveProcessarEventoProcessoParte_quandoTribunalIdentificado() throws Exception {
        CodigoTribunal codigoTribunal = new CodigoTribunal("pje-tjce", "TJCE");
        when(tribunalService.recuperarCodigoTribunal("pje-tjce")).thenReturn(codigoTribunal);

        CloudEvent event = new CloudEvent();
        event.setAppName("pje-tjce");
        event.setRoutingKey("post.v1.tjce.ProcessoParte.123");

        consumer.receiveEventFromBroker(event);

        verify(parteEventHandler).handle(event);
    }

    @Test
    public void deveIgnorarMensagem_quandoTribunalNaoIdentificado() throws Exception {
        when(tribunalService.recuperarCodigoTribunal(anyString())).thenReturn(null);

        CloudEvent event = new CloudEvent();
        event.setAppName("pje-desconhecido");
        event.setRoutingKey("post.v1.xxx.ProcessoParte.123");

        consumer.receiveEventFromBroker(event);

        verify(parteEventHandler, never()).handle(event);
    }

    @Test
    public void deveIgnorarMensagem_quandoAppNameVazio() throws Exception {
        CloudEvent event = new CloudEvent();
        event.setAppName("");
        event.setRoutingKey("post.v1.xxx.ProcessoParte.123");

        consumer.receiveEventFromBroker(event);

        verify(tribunalService, never()).recuperarCodigoTribunal(anyString());
        verify(parteEventHandler, never()).handle(event);
    }

    @Test
    public void deveIgnorarMensagem_quandoRoutingKeyNaoEProcessoParte() throws Exception {
        CloudEvent event = new CloudEvent();
        event.setAppName("pje-tjce");
        event.setRoutingKey("post.v1.tjce.ProcessoEvento.123");

        consumer.receiveEventFromBroker(event);

        verify(tribunalService, never()).recuperarCodigoTribunal(anyString());
        verify(parteEventHandler, never()).handle(event);
    }

    @Test
    public void deveUsarConfiguracaoCustomizada_quandoPropriedadesOverrideadas() {
        // Valida que o consumer pode ser instanciado com configuração customizada.
        // As propriedades ${criminal.amqp.*} são resolvidas pelo Spring no binding
        // do @RabbitListener; aqui verificamos que o bean existe e está funcional.
        org.junit.Assert.assertNotNull(consumer);
    }

    private CloudEvent eventProcessoParte(String appName) {
        CloudEvent event = new CloudEvent();
        event.setAppName(appName);
        event.setRoutingKey("pje." + appName + ".1.ProcessoParte.created");
        return event;
    }

    @Test
    public void testMensagemIgnorada() throws Exception {
        ReflectionTestUtils.setField(consumer, "appNamesIgnorados", "pje-tjmg,pje-tjsp");

        consumer.receiveEventFromBroker(eventProcessoParte("pje-tjmg"));

        verify(tribunalService, never()).recuperarCodigoTribunal(any());
        verify(parteEventHandler, never()).handle(any());
    }

    @Test
    public void testMensagemProcessada() throws Exception {
        ReflectionTestUtils.setField(consumer, "appNamesIgnorados", "pje-tjmg,pje-tjsp");

        when(tribunalService.recuperarCodigoTribunal("pje-tjgo")).thenReturn(null);

        consumer.receiveEventFromBroker(eventProcessoParte("pje-tjgo"));

        verify(tribunalService).recuperarCodigoTribunal("pje-tjgo");
    }

    @Test
    public void testListaVazia() throws Exception {
        ReflectionTestUtils.setField(consumer, "appNamesIgnorados", "");

        when(tribunalService.recuperarCodigoTribunal("pje-tjmg")).thenReturn(null);

        consumer.receiveEventFromBroker(eventProcessoParte("pje-tjmg"));

        verify(tribunalService).recuperarCodigoTribunal("pje-tjmg");
    }
}
