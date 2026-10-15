package br.jus.cnj.pje.criminal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import br.jus.cnj.pje.criminal.clients.soap.ListarServicoSeeuClient;
import br.jus.cnj.pje.criminal.clients.soap.DominioWebServiceClient;



@Configuration
public class SoapClientConfig extends WebServiceGatewaySupport {
	
    @Bean
    public Jaxb2Marshaller marshaller1() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths("br.jus.cnj.pje.criminal.seeu.extramni2", "br.jus.cnj.pje.criminal.seeu.extramni");
        return marshaller;
    }

    @Bean
    public DominioWebServiceClient soapConnector(Jaxb2Marshaller marshaller) {
        DominioWebServiceClient client = new DominioWebServiceClient();
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
       return client;
    }
    
    @Bean
    public ListarServicoSeeuClient soapConnector2(Jaxb2Marshaller marshaller) {
        ListarServicoSeeuClient client2 = new ListarServicoSeeuClient();
        client2.setMarshaller(marshaller);
        client2.setUnmarshaller(marshaller);
       return client2;
    }
}
