package br.jus.cnj.pje.criminal.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.error.CriminalBusinessException;
import br.jus.cnj.pje.criminal.model.beans.RemessaSeeuBean;
import br.jus.cnj.pje.criminal.model.beans.Resposta;
import br.jus.cnj.pje.criminal.seeu.extramni.ListarSentenciadoAtivoPorFiltroResponse;
import br.jus.cnj.pje.criminal.seeu.extramni2.TodasAreaDeVaraResponse;
import br.jus.cnj.pje.criminal.seeu.extramni2.TodasComarcasResponse;
import br.jus.cnj.pje.criminal.services.SeeuService;

@RestController
public class SeeuRestController {
	
	@Autowired
	private SeeuService seeuService;
	
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(
	path = "/processos/{idProcesso}/remessa-seeu", 
	method = RequestMethod.POST, 
	produces = MediaType.APPLICATION_JSON_VALUE, 
	consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Resposta> remeterAoSeeu(
			@PathVariable("idProcesso") Long idProcesso, 
			@RequestBody RemessaSeeuBean remessa) throws CriminalBusinessException {
		return ResponseEntity.ok(seeuService.entregarManifestacoes(remessa));
	}
	
	@RequestMapping(
	path = "/seeu/processos/{nome}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<ListarSentenciadoAtivoPorFiltroResponse> listarSentenciadoAtivoPorFiltro(@PathVariable("nome") String nome) throws CriminalBusinessException {
		return ResponseEntity.ok(seeuService.listarSentenciadoAtivoPorFiltro(nome));
	}
	
	@RequestMapping(
	path = "/seeu/comarcas/{idComarca}/areas-de-vara", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<TodasAreaDeVaraResponse> listarAreasDeVara(@PathVariable("idComarca") Long idComarca) throws CriminalBusinessException {
		return ResponseEntity.ok(seeuService.listarAreasDeVara(idComarca));
	}
	
	@RequestMapping(
	path = "/seeu/comarcas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<TodasComarcasResponse> listarComarcas() throws CriminalBusinessException {
		return ResponseEntity.ok(seeuService.listarComarcas());
	}
	
}
