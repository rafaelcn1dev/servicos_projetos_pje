package br.jus.cnj.pje.criminal.controllers.v1;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.enums.EstadosBrasileirosEnum;

@RestController
public class EnderecoRestController {

	@RequestMapping(method = RequestMethod.GET, path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<EstadosBrasileirosEnum[]> recuperarEstadosBrasileiros(){
		return ResponseEntity.ok(EstadosBrasileirosEnum.class.getEnumConstants());
	}
	
}
