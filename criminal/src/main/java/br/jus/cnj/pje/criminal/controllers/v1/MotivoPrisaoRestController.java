package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.model.entities.MotivoPrisao;
import br.jus.cnj.pje.criminal.services.MotivoPrisaoService;

@RestController
public class MotivoPrisaoRestController {

	@Autowired
	private MotivoPrisaoService motivoPrisaoService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/motivosPrisao", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<MotivoPrisao>> recuperarMotivosPrisao(){
		ResponseEntity<List<MotivoPrisao>> res = ResponseEntity.noContent().build();
		
		res = ResponseEntity.ok(this.motivoPrisaoService.recuperarMotivosPrisaoAtivos());
		
		return res;
	}
}
