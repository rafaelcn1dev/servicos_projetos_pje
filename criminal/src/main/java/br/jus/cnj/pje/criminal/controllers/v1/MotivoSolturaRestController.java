package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.model.entities.MotivoSoltura;
import br.jus.cnj.pje.criminal.services.MotivoSolturaService;

@RestController
public class MotivoSolturaRestController {

	@Autowired
	private MotivoSolturaService motivoSolturaService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/motivosSoltura", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<MotivoSoltura>> recuperarMotivosSoltura(){
		ResponseEntity<List<MotivoSoltura>> res = ResponseEntity.noContent().build();
		
		res = ResponseEntity.ok(this.motivoSolturaService.recuperarMotivosSolturaAtivos());
		
		return res;
	}	
}
