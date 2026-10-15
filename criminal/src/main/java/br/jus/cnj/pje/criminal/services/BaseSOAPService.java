package br.jus.cnj.pje.criminal.services;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementa métodos comuns às classes que manipulam webservices do tipo SOAP, como aquelas baseados no MNI.
 * @author adriano.silva
 *
 */
public class BaseSOAPService {
	
	/**
	 * Deve ser chamado para permitir a impressão do XML de requisição e de resposta das chanadas SOAP.
	 * @param bindingProvider A interface que representa o endpoint do serviço SOAP.
	 * Por exemplo, no caso do MNI, a interface Intercomunicacao, devidamente convertida para BindingProvider. Ex:
	 * <pre>
	 * {@code
	 * Intercomunicacao endPoint = null;
	 * URL url = new URL(enderecoWSDL);
	 * QName qname = new QName(Intercomunicacao.class.getAnnotation(WebService.class).targetNamespace(), "IntercomunicacaoService");
	 * endPoint = javax.xml.ws.Service.create(url, qname).getPort(Intercomunicacao.class);
	 * attachSOAPHandler((BindingProvider)endPoint, enderecoWSDL);
	 * }
	 * @param wsdlUrl
	 */
	protected void attachSOAPHandler(BindingProvider bindingProvider, String wsdlUrl) {
		SOAPBinding soapBinding = (SOAPBinding) bindingProvider.getBinding();
		soapBinding.setMTOMEnabled(Boolean.TRUE);
		soapBinding.setHandlerChain(Arrays.asList(new MessageSOAPHandler()));
		bindingProvider.getRequestContext().put("javax.xml.ws.client.connectionTimeout", "60000");
		bindingProvider.getRequestContext().put("javax.xml.ws.client.receiveTimeout", "600000");
		bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl.replace("?wsdl", StringUtils.EMPTY));
	}
		
	/**
	 * Classe que imprime no console o XML de ida e de resposta das chamadas SOAP.
	 */
	private class MessageSOAPHandler implements SOAPHandler<SOAPMessageContext> {
	
		private Logger logger = LoggerFactory.getLogger(this.getClass());
	
		@Override
		public boolean handleMessage(SOAPMessageContext context) {
			StringBuilder log = new StringBuilder();
			SOAPMessage message = context.getMessage();
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				message.writeTo(baos);
				baos.flush();
				baos.close();
				
				log.append(new String(baos.toByteArray()));
				logger.info("<< ------------------------------INÍCIO---------------------------------- >>");
				logger.info(this.formatar(log.toString()));
				logger.info("<< ------------------------------ FIM ----------------------------------- >>");
			} catch (Exception e) {	}
	
			return Boolean.TRUE;
		}
	
		@Override
		public boolean handleFault(SOAPMessageContext context) {
			return Boolean.FALSE;
		}
	
		@Override
		public void close(MessageContext context) {
			
		}
	
		@Override
		public Set<QName> getHeaders() {
			return null;
		}
		
		private String formatar(String xml) {
			try {
				Document doc = DocumentHelper.parseText(xml);
				StringWriter out = new StringWriter();
				XMLWriter writer = new XMLWriter(out, OutputFormat.createPrettyPrint());
				writer.write(doc);
				writer.close();
				
				xml = out.toString();
			} catch (Exception e) {} 
			
			return xml;
	    }

	} //end nested class
}
