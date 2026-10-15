package br.jus.cnj.pje.criminal.clients.soap;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import br.jus.cnj.pje.criminal.seeu.extramni.ListarSentenciadoAtivoPorFiltroResponse;

public class ListarServicoSeeuClient extends WebServiceGatewaySupport {

	@Value("${seeu.wsdlExtraMni}")
    private String URL2;

    public ListarSentenciadoAtivoPorFiltroResponse getSentenciadoAtivoPorFiltro(Object request) {
        JAXBElement res = (JAXBElement) getWebServiceTemplate().marshalSendAndReceive(URL2, request);
        return (ListarSentenciadoAtivoPorFiltroResponse) res.getValue();
    }
}