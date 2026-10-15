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
import br.jus.cnj.pje.criminal.model.entities.MotivoExtincaoMedidaSocioeducativa;
import br.jus.cnj.pje.criminal.services.MotivoExtincaoMedidaSocioeducativaService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + MotivoExtincaoMedidaSocioeducativaRestControllerImpl.PATH)
public class MotivoExtincaoMedidaSocioeducativaRestControllerImpl extends AbstractRestController<MotivoExtincaoMedidaSocioeducativa, Long> {

	@Autowired
	private MotivoExtincaoMedidaSocioeducativaService service;
	
	public static final String PATH = "/motivos-extincao-medida-socioeducativa";
	private static final String PATH_PESQUISAR = "/pesquisar";
	private static final String PATH_COUNT = "/count";
	
	@PostMapping(path = PATH_PESQUISAR, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Page<MotivoExtincaoMedidaSocioeducativa> recuperarMotivosExtincaoMedidaSocioeducativaPorPagina(Pageable pagina, @RequestBody MotivoExtincaoMedidaSocioeducativa motivoExtincaoMedidaSocioeducativa) {
		return service.recuperarPorPagina(motivoExtincaoMedidaSocioeducativa, pagina);
	}
	
	@GetMapping(path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countMotivosExtincaoMedidaSocioeducativa() {
		return service.count();
	}
	
	@PostMapping(path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long countMotivosExtincaoMedidaSocioeducativa(@RequestBody MotivoExtincaoMedidaSocioeducativa motivoExtincaoMedidaSocioeducativa) {
		return service.count(motivoExtincaoMedidaSocioeducativa);
	}

	@Override
	protected BaseService<MotivoExtincaoMedidaSocioeducativa, Long> getService() {
		return service;
	}
	
}
