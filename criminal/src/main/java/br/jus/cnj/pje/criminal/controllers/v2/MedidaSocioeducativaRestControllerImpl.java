package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.MedidaSocioeducativa;
import br.jus.cnj.pje.criminal.services.MedidaSocioeducativaService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + MedidaSocioeducativaRestControllerImpl.PATH)
public class MedidaSocioeducativaRestControllerImpl extends AbstractRestController<MedidaSocioeducativa, Long> {

	@Autowired
	private MedidaSocioeducativaService service;
	
	public static final String PATH = "/medidas-socioeducativas";
	private static final String PATH_PESQUISAR = "/pesquisar";
	private static final String PATH_COUNT = "/count";
	
	@PostMapping(path = PATH_PESQUISAR, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Page<MedidaSocioeducativa> recuperarMedidasSocioeducativasPorPagina(Pageable pagina, @RequestBody MedidaSocioeducativa medidaSocioeducativa) {
		return service.recuperarPorPagina(medidaSocioeducativa, pagina);
	}
	
	@GetMapping(path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countMedidasSocioeducativas() {
		return service.count();
	}

	@PostMapping(path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long countMedidasSocioeducativas(@RequestBody MedidaSocioeducativa medidaSocioeducativa) {
		return service.count(medidaSocioeducativa);
	}

	@Override
	protected BaseService<MedidaSocioeducativa, Long> getService() {
		return service;
	}
	
}
