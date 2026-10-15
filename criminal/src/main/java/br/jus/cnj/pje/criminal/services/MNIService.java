package br.jus.cnj.pje.criminal.services;

import java.net.URL;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.jus.cnj.intercomunicacao.beans.ManifestacaoProcessual;
import br.jus.cnj.intercomunicacao.beans.RequisicaoConsultaProcesso;
import br.jus.cnj.intercomunicacao.beans.RespostaConsultaProcesso;
import br.jus.cnj.intercomunicacao.beans.RespostaManifestacaoProcessual;
import br.jus.cnj.intercomunicacao.servico.Intercomunicacao;


@Service
public class MNIService extends BaseSOAPService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public RespostaManifestacaoProcessual entregaManifestacao(ManifestacaoProcessual manifestacaoProcessual, String Wsdl) throws Exception {
		
		logger.info("================> Inicio de entrega de manifestação processual");

		Intercomunicacao intercomunicacao = this.obtemIntercomunicacao(Wsdl);
		RespostaManifestacaoProcessual resposta = intercomunicacao.entregarManifestacaoProcessual(manifestacaoProcessual);

		logger.info("================> Resposta: {}", resposta.getMensagem());

		if (!resposta.isSucesso()) {
			throw new Exception(resposta.getMensagem());
		}

		return resposta;
	}

	private Intercomunicacao obtemIntercomunicacao(String enderecoWSDL) throws Exception {
		Intercomunicacao endPoint = null;

		URL url = new URL(enderecoWSDL);
		QName qname = new QName(Intercomunicacao.class.getAnnotation(WebService.class).targetNamespace(), "IntercomunicacaoService");

		endPoint = javax.xml.ws.Service.create(url, qname).getPort(Intercomunicacao.class);
		
		//Para imprimir no console o XML sendo enviado e recebido
	    attachSOAPHandler((BindingProvider)endPoint, enderecoWSDL);

		return endPoint;
	}

	public RespostaConsultaProcesso consultarProcesso(RequisicaoConsultaProcesso requisiicao, String Wsdl)
			throws Exception {
		Intercomunicacao intercomunicacao = this.obtemIntercomunicacao(Wsdl);
		RespostaConsultaProcesso resposta = intercomunicacao.consultarProcesso(requisiicao);
		if (!resposta.isSucesso()) {
			throw new Exception(resposta.getMensagem());
		}
		return resposta;
	}


}
