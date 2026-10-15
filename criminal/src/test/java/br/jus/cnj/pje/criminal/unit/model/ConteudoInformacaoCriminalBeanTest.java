package br.jus.cnj.pje.criminal.unit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import br.jus.cnj.pje.criminal.enums.TipoEventoCriminalEnum;
import br.jus.cnj.pje.criminal.model.beans.ConteudoInformacaoCriminalBean;
import br.jus.cnj.pje.criminal.model.beans.EventoCriminalBean;
import br.jus.cnj.pje.criminal.model.entities.InformacaoCriminal;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;

public class ConteudoInformacaoCriminalBeanTest {

    @Test
    public void deveRotearInicioEFimDaInstrucaoParaListasProprias() {
        ConteudoInformacaoCriminalBean conteudo = new ConteudoInformacaoCriminalBean();
        EventoCriminalBean inicio = evento(TipoEventoCriminalEnum.IIP);
        EventoCriminalBean fim = evento(TipoEventoCriminalEnum.FIP);
        Date dataInicio = new Date();
        Date dataFim = new Date(dataInicio.getTime() + 1000L);
        inicio.setData(dataInicio);
        inicio.setObservacao("Instrução processual iniciada");
        fim.setData(dataFim);
        fim.setObservacao("Instrução processual encerrada");

        conteudo.addEventoCriminal(inicio);
        conteudo.addEventoCriminal(fim);

        assertEquals(1, conteudo.getInicioInstrucaoProcessual().size());
        assertEquals(1, conteudo.getFimInstrucaoProcessual().size());
        assertTrue(conteudo.getEventosCriminaisPorTipo(TipoEventoCriminalEnum.IIP).contains(inicio));
        assertTrue(conteudo.getEventosCriminaisPorTipo(TipoEventoCriminalEnum.FIP).contains(fim));
        assertEquals(dataInicio, inicio.getData());
        assertEquals("Instrução processual iniciada", inicio.getObservacao());
        assertEquals(dataFim, fim.getData());
        assertEquals("Instrução processual encerrada", fim.getObservacao());

        InformacaoCriminal informacao = new InformacaoCriminal();
        informacao.setConteudo(conteudo);
        assertEquals(2, informacao.getEventosCriminais().size());
    }

    private EventoCriminalBean evento(TipoEventoCriminalEnum codigo) {
        TipoEventoCriminal tipo = new TipoEventoCriminal();
        tipo.setCodTipoIc(codigo);
        EventoCriminalBean evento = new EventoCriminalBean();
        evento.setTipoInformacaoCriminal(tipo);
        return evento;
    }
}
