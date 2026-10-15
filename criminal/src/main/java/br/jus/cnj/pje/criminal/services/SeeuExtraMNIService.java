package br.jus.cnj.pje.criminal.services;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.clients.soap.ListarServicoSeeuClient;
import br.jus.cnj.pje.criminal.clients.soap.DominioWebServiceClient;
//import br.jus.cnj.pje.criminal.seeu.extramni.ListaServicoSeeu;
import br.jus.cnj.pje.criminal.seeu.extramni.ListarSentenciadoAtivoPorFiltro;
import br.jus.cnj.pje.criminal.seeu.extramni.ListarSentenciadoAtivoPorFiltroResponse;
import br.jus.cnj.pje.criminal.seeu.extramni.ObjectFactory;

@Service
public class SeeuExtraMNIService extends BaseSOAPService {
	
	@Autowired
	private ListarServicoSeeuClient listarServicoSeeuClient;

	/**
	 * Retorna a lista de execuções a partir de um conjunto de filtros de consulta.<br>
	 * Os campos obrigatórios da requisição são <b>login</b> e <b>senha</b> (usados para a validação da requisição, vide https://docs.seeu.pje.jus.br/tecnica/requisito-acesso.html),
	 * e <b>nome</b>. Obs: A consulta é fonética, de tal modo que são retornados registros com nomes parecidos ao informado, e não uma coincidência exata.
	 * @param request objeto do tipo ListarSentenciadoAtivoPorFiltro.
	 * @return Lista do tipo SentenciadoSeeu.
	 */
	
	public ListarSentenciadoAtivoPorFiltroResponse listarSentenciadoAtivoPorFiltro(ListarSentenciadoAtivoPorFiltro request) {
        ObjectFactory objectFactory = new ObjectFactory();

        ListarSentenciadoAtivoPorFiltro sentenciado = new ListarSentenciadoAtivoPorFiltro();

        sentenciado.setUsuario(request.getUsuario());
        sentenciado.setSenha(request.getSenha());
        sentenciado.setCodTribunal(request.getCodTribunal()); 
        sentenciado.setNome(request.getNome());
        sentenciado.setNomeMae(request.getNomeMae());
        sentenciado.setNomePai(request.getNomePai());
        sentenciado.setDataNascimento(request.getDataNascimento());
        sentenciado.setSexo(request.getSexo());
        sentenciado.setRgParte(request.getRgParte());
        sentenciado.setSiglaUfRg(request.getSiglaUfRg());
        sentenciado.setCpf(request.getCpf());
        sentenciado.setPagina(request.getPagina());
        
        JAXBElement<ListarSentenciadoAtivoPorFiltro> jaxb = objectFactory.createListarSentenciadoAtivoPorFiltro(sentenciado);
        ListarSentenciadoAtivoPorFiltroResponse response = listarServicoSeeuClient.getSentenciadoAtivoPorFiltro(jaxb);

        return response;
    }	
	
}
