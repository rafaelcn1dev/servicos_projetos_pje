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
import br.jus.cnj.pje.criminal.model.entities.MotivoRetornoEvasao;
import br.jus.cnj.pje.criminal.services.MotivoRetornoEvasaoService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + MotivoRetornoEvasaoRestControllerImpl.PATH)
public class MotivoRetornoEvasaoRestControllerImpl extends AbstractRestController<MotivoRetornoEvasao, Long> {

	@Autowired
	private MotivoRetornoEvasaoService service;
	
	public static final String PATH = "/motivos-retorno-evasao";
	private static final String PATH_PESQUISAR = "/pesquisar";
	private static final String PATH_COUNT = "/count";
	
	@PostMapping(path = PATH_PESQUISAR, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Page<MotivoRetornoEvasao> recuperarMotivosRetornoEvasaoPorPagina(Pageable pagina, @RequestBody MotivoRetornoEvasao motivoRetornoEvasao) {
		return service.recuperarPorPagina(motivoRetornoEvasao, pagina);
	}
	
	@GetMapping(path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countMotivosRetornoEvasao() {
		return service.count();
	}
	
	@PostMapping(path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long countMotivosRetornoEvasao(@RequestBody MotivoRetornoEvasao motivoRetornoEvasao) {
		return service.count(motivoRetornoEvasao);
	}

	@Override
	protected BaseService<MotivoRetornoEvasao, Long> getService() {
		return service;
	}
	
}
