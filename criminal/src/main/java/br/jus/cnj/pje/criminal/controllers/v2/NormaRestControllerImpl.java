package br.jus.cnj.pje.criminal.controllers.v2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.Dispositivo;
import br.jus.cnj.pje.criminal.model.entities.Norma;
import br.jus.cnj.pje.criminal.services.DispositivoService;
import br.jus.cnj.pje.criminal.services.NormaService;
import br.jus.cnj.pje.criminal.util.ApiVersions;
import br.jus.cnj.pje.pjecommons.model.services.PjeResponse;
import br.jus.cnj.pje.pjecommons.model.services.PjeResponseStatus;

@RestController
@RequestMapping(ApiVersions.V2 + NormaRestControllerImpl.PATH)
public class NormaRestControllerImpl extends AbstractRestController<Norma, Long>{

	public static final String PATH = "/normas";

	@Autowired
	private NormaService normaService;
	
	@Autowired
	private DispositivoService dispositivoService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/{idNorma}/dispositivos", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public PjeResponse<List<Dispositivo>> recuperarDispositivosPorIdNorma(
			@PathVariable("idNorma") Long idNorma) {
		PjeResponse<List<Dispositivo>> res = null;

		List<Dispositivo> lista = this.dispositivoService.recuperarDispositivosPorIdNorma(idNorma);

		if (!CollectionUtils.isEmpty(lista)) {
			res = new PjeResponse<>(PjeResponseStatus.OK, HttpStatus.OK.toString(), this.getResponseMessages(), lista);
		} else {
			this.addResponseMessage("Nenhum dispositivo encontrado para a norma informada.");
			res = new PjeResponse<>(PjeResponseStatus.OK, HttpStatus.OK.toString(), this.getResponseMessages(), new ArrayList<>(0));
		}

		return res;		
	}	
	
	@Override
	protected BaseService<Norma, Long> getService() {
		return this.normaService;
	}
	
}
