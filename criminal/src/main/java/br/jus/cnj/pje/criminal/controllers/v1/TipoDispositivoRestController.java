package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.model.entities.TipoDispositivo;
import br.jus.cnj.pje.criminal.services.TipoDispositivoService;

@RestController
public class TipoDispositivoRestController {

	@Autowired
	private TipoDispositivoService tpDispositivoService;

	@RequestMapping(method = RequestMethod.GET, path = "/tiposDispositivos", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<TipoDispositivo> recuperarTiposDispositivos(){
		return this.tpDispositivoService.recuperarTiposDispositivos();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/tiposDispositivos/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<TipoDispositivo> recuperarTiposDispositivos(@PathVariable("nome") String nome){
		return this.tpDispositivoService.recuperarTiposDispositivosPorNome(nome);
	}
}