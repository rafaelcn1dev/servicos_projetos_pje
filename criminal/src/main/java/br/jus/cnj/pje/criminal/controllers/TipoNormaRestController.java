package br.jus.cnj.pje.criminal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.model.entities.TipoNorma;
import br.jus.cnj.pje.criminal.services.TipoNormaService;

@RestController
public class TipoNormaRestController {

	@Autowired
	private TipoNormaService tipoNormaService;

	@RequestMapping(method = RequestMethod.GET, path = "/tiposNorma", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<TipoNorma> recuperarTiposNorma() {
		return this.tipoNormaService.recuperarTiposNorma();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/tiposNorma/count", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countTiposOrigem() {
		return this.tipoNormaService.countTipoNorma();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/tiposNorma/count", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countTiposNorma(@RequestBody TipoNorma tipoNorma) {
		return this.tipoNormaService.countTipoNorma(tipoNorma);
	}	
}