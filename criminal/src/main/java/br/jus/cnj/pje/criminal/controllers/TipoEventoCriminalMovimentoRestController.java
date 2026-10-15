package br.jus.cnj.pje.criminal.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.error.PjeEntityNotFoundException;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminalMovimento;
import br.jus.cnj.pje.criminal.services.TipoEventoCriminalMovimentoService;

@RestController
public class TipoEventoCriminalMovimentoRestController {

	@Autowired
	private TipoEventoCriminalMovimentoService tipoEventoCriminalMovimentoService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/tiposEventoCriminalMovimento/evento/{codMovimento}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<TipoEventoCriminalMovimento>> recuperarPorCodMovimento(@PathVariable("codMovimento") String codMovimento){
		ResponseEntity<List<TipoEventoCriminalMovimento>> res = ResponseEntity.noContent().build();
		List<TipoEventoCriminalMovimento> lista = this.tipoEventoCriminalMovimentoService.recuperarPorCodigoMovimento(codMovimento);
		if(!CollectionUtils.isEmpty(lista)) {
			res = ResponseEntity.ok(lista);
		}
		return res;
	}

	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(method = RequestMethod.POST, path = "/tiposEventoCriminalMovimento", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoEventoCriminalMovimento criarTipoEventoCriminalMovimento(@Valid @RequestBody TipoEventoCriminalMovimento tipoEventoCriminalMovimento, Errors erros) {		
		return tipoEventoCriminalMovimentoService.criarTipoEventoCriminalMovimento(tipoEventoCriminalMovimento);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.DELETE, path = "/tiposEventoCriminalMovimento/{idTipoEventoCriminalMovimento}")
	@InboundRequest
	public ResponseEntity<Void> removeTipoEventoCriminalMovimento(@PathVariable("idTipoEventoCriminalMovimento") Integer idTipoEventoCriminalMovimento) {
		verificaSeEntidadeExiste(idTipoEventoCriminalMovimento);
		TipoEventoCriminalMovimento tipoEventoCriminalMovimento = tipoEventoCriminalMovimentoService.recuperarTipoEventoCriminalMovimentoPorId(idTipoEventoCriminalMovimento);
		if (tipoEventoCriminalMovimento != null) {
			tipoEventoCriminalMovimentoService.removerTipoEventoCriminalMovimento(tipoEventoCriminalMovimento);
		}
		return ResponseEntity.ok().build();
	}
	
	private void verificaSeEntidadeExiste(Integer id) {
		TipoEventoCriminalMovimento tipoEventoCriminalMovimento = tipoEventoCriminalMovimentoService.recuperarTipoEventoCriminalMovimentoPorId(id);
		if(tipoEventoCriminalMovimento == null) {
			throw new PjeEntityNotFoundException(TipoEventoCriminalMovimento.class, id);
		}
	}
}