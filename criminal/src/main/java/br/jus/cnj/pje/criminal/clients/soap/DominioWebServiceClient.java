package br.jus.cnj.pje.criminal.clients.soap;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import br.jus.cnj.pje.criminal.seeu.extramni2.TodasAreaDeVaraResponse;
import br.jus.cnj.pje.criminal.seeu.extramni2.TodasComarcasResponse;

public class DominioWebServiceClient extends WebServiceGatewaySupport {

	@Value("${seeu.url}")
    private String URL;

    public TodasComarcasResponse getComarcas(Object request){
        JAXBElement res = (JAXBElement) getWebServiceTemplate().marshalSendAndReceive(URL, request);
        return (TodasComarcasResponse) res.getValue();
    }

    public TodasAreaDeVaraResponse listarAreasDeVara(Object request){
        JAXBElement res = (JAXBElement) getWebServiceTemplate().marshalSendAndReceive(URL, request);
        return (TodasAreaDeVaraResponse) res.getValue();
    }
    
    
}