package br.jus.cnj.pje.criminal.services;

import java.net.MalformedURLException;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.clients.soap.DominioWebServiceClient;
import br.jus.cnj.pje.criminal.error.CriminalBusinessException;
import br.jus.cnj.pje.criminal.seeu.extramni2.ObjectFactory;
import br.jus.cnj.pje.criminal.seeu.extramni2.TodasAreaDeVara;
import br.jus.cnj.pje.criminal.seeu.extramni2.TodasAreaDeVaraResponse;
import br.jus.cnj.pje.criminal.seeu.extramni2.TodasComarcas;
import br.jus.cnj.pje.criminal.seeu.extramni2.TodasComarcasResponse;

@Service
public class SeeuExtraMNI2Service extends BaseSOAPService {
	
	@Value("${seeu.wsdlDominioWs}")
	private String wsdl;
	
	@Autowired
	private DominioWebServiceClient dominioWebServiceClient;
				
	/**
	 * <p>Retorna a lista de comarcas cadastradas no SEEU para o tribunal informado.</p>
	 * <p>O código da comarca é imprescindível para filtrar a lista de "áreas de vara" do SEEU.</p> 
	 * @param request
	 * @return
	 * @throws CriminalBusinessException
	 */
	
	public TodasComarcasResponse listarComarcas(TodasComarcas request) {
        ObjectFactory objectFactory = new ObjectFactory();

        TodasComarcas comarca = new TodasComarcas();

        comarca.setCodigoTribunal(request.getCodigoTribunal());
        comarca.setSistemaTribunal(request.getSistemaTribunal());
        comarca.setSystemPass(request.getSystemPass());
 
        JAXBElement<TodasComarcas> jaxb = objectFactory.createTodasComarcas(comarca);
        TodasComarcasResponse response = dominioWebServiceClient.getComarcas(jaxb);

        return response;
    }																															 
	
	/**
	 * <p>Retorna a lista de "áreas de vara" do SEEU (para onde o processo/guia será distribuído/encaminhada).<br>
	 * Os campos obrigatórios da requisição são <b>sistemaTribunal (login)</b>, <b>systemPass (senha)</b> (usados para a validação da requisição, vide <a href='https://docs.seeu.pje.jus.br/tecnica/requisito-acesso.html'>https://docs.seeu.pje.jus.br/tecnica/requisito-acesso.html</a>),
	 * e <b>codigoTribunal</b> (parte numérica do login, ex: se sistemaTribunal for igual a T10, codigoTribunal será 10).<br></p>
	 * <p>Obs: o SEEU, no momento desta implementação (11.08.2020), não permite a distribuição de guias/processos para áreas de 
	 * varas de tribunal distinto do usuário que está fazendo a requisição. Há expectativa de que essa regra seja flexibilizada no futuro.</p> 
	 * @param request objeto do tipo TodasAreaDeVara.
	 * @return Lista do tipo WsAreaDeVara.
	 * @throws MalformedURLException 
	 */
	
    public TodasAreaDeVaraResponse listarAreasDeVara(TodasAreaDeVara request) {
        ObjectFactory objectFactory = new ObjectFactory();

        TodasAreaDeVara areasDeVara = new TodasAreaDeVara();

       
        areasDeVara.setCodigoTribunal(request.getCodigoTribunal());
        areasDeVara.setSistemaTribunal(request.getSistemaTribunal());
        areasDeVara.setSystemPass(request.getSystemPass());
        areasDeVara.setCodigoComarca(request.getCodigoComarca());
        
        JAXBElement<TodasAreaDeVara> jaxb = objectFactory.createTodasAreaDeVara(areasDeVara);
        TodasAreaDeVaraResponse response = dominioWebServiceClient.listarAreasDeVara(jaxb);

        return response;
    }
}
