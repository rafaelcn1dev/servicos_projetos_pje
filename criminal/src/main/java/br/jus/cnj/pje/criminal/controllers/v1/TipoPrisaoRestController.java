package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.model.entities.TipoPrisao;
import br.jus.cnj.pje.criminal.services.TipoPrisaoService;

@RestController
public class TipoPrisaoRestController {

	@Autowired
	private TipoPrisaoService tipoPrisaoService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/tiposPrisao", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<TipoPrisao>> recuperarTiposPrisao(){
		ResponseEntity<List<TipoPrisao>> res = ResponseEntity.noContent().build();
		
		res = ResponseEntity.ok(this.tipoPrisaoService.recuperarTiposPrisaoAtivos());
		
		return res;
	}	
	
}
