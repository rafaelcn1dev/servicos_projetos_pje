package br.jus.cnj.pje.criminal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;
import br.jus.cnj.pje.criminal.services.TipoEventoCriminalService;

@RestController
public class TipoEventoCriminalRestController {

	@Autowired
	private TipoEventoCriminalService tipoEventoCriminalService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/tiposEventoCriminal", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<TipoEventoCriminal>> recuperarTiposEventoCriminal(){
		ResponseEntity<List<TipoEventoCriminal>> res = ResponseEntity.noContent().build();
		List<TipoEventoCriminal> lista = this.tipoEventoCriminalService.recuperarTiposEventoCriminalAtivos();
		if(!CollectionUtils.isEmpty(lista)) {
			res = ResponseEntity.ok(lista);
		}
		return res;
	}
	
}
