package br.jus.cnj.pje.criminal.amqp;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.jus.cnj.pje.criminal.amqp.handlers.ParteEventHandler;
import br.jus.cnj.pje.criminal.amqp.model.CloudEvent;
import br.jus.cnj.pje.criminal.aop.TribunalContext;
import br.jus.cnj.pje.criminal.model.entities.CodigoTribunal;
import br.jus.cnj.pje.criminal.services.TribunalService;

@Component
public class CriminalMessageConsumer {

	private static final Logger logger = LoggerFactory.getLogger(CriminalMessageConsumer.class);

	public final static String CRIMINAL_QUEUE = "pje.criminal";
	public final static String EXCHANGE_NAME = "pje.exchange";
	
	public final static String PROCESSO_PARTE_ROUTING_KEY = "*.*.*.ProcessoParte.*";
	public final static String PROCESSO_EVENTO_ROUTING_KEY = "*.*.*.ProcessoEvento.*";

	@Value("${criminal.amqp.appnames-ignorados:}")
	private String appNamesIgnorados;

    @Autowired
    TribunalService tribunalService;

	@Autowired
	private ParteEventHandler parteEventHandler;

	@RabbitListener(
		bindings = @QueueBinding(
			value = @Queue(value = "${criminal.amqp.queue}", durable = "true", autoDelete = "false", exclusive = "false"),
			exchange = @Exchange(value = "${criminal.amqp.exchange}", type = ExchangeTypes.TOPIC),
			key = {"${criminal.amqp.routing-key.processo-parte}"})
	)
	public void receiveEventFromBroker(CloudEvent event) throws Exception {
		logger.debug("Limpando tribunal context!");
		TribunalContext.clear();

		try {
			logger.info("[PJE-CLOUD-EVENT] " + new String(event.toString()));
			if(event != null) {
				String appName = event.getAppName();
				if (StringUtils.isNotBlank(appNamesIgnorados) && Arrays.asList(appNamesIgnorados.split(",")).contains(appName)) {
				logger.info("Mensagem do appName '{}' está na lista de appNames ignorados. Descartando mensagem.", appName);
					return;
				}

				if(event.getRoutingKey().toLowerCase().contains("processoparte")) {
					logger.debug("Identificando appName do tribunal da mensagem");
					CodigoTribunal codigoTribunal = null;
					if(StringUtils.isNotBlank(appName)) {
						logger.debug("Buscando codigoTribunal pelo appName!");
						codigoTribunal = tribunalService.recuperarCodigoTribunal(appName);
					}

					if(codigoTribunal != null && codigoTribunal.valido()) {
					logger.debug("Identificando o tribunal atual na variável de Thread local");
						TribunalContext.setCodigoTribunal(codigoTribunal);
						this.parteEventHandler.handle(event);
					}else {
					logger.info("Não foi possível identificar o tribunal a partir do appName: " + appName + " ignorando a mensagem.");
					}
				}
			}
		} finally {
			logger.debug("Limpando tribunal context ao final do processamento da mensagem!");
			TribunalContext.clear();
		}
	}
	
}
