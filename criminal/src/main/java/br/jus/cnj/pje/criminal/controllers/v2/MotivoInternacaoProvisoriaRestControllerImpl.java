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
import br.jus.cnj.pje.criminal.model.entities.MotivoInternacaoProvisoria;
import br.jus.cnj.pje.criminal.services.MotivoInternacaoProvisoriaService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + MotivoInternacaoProvisoriaRestControllerImpl.PATH)
public class MotivoInternacaoProvisoriaRestControllerImpl extends AbstractRestController<MotivoInternacaoProvisoria, Long> {

	@Autowired
	private MotivoInternacaoProvisoriaService service;
	
	public static final String PATH = "/motivos-internacao-provisoria";
	private static final String PATH_PESQUISAR = "/pesquisar";
	private static final String PATH_COUNT = "/count";
	
	@PostMapping(path = PATH_PESQUISAR, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Page<MotivoInternacaoProvisoria> recuperarMotivosInternacaoProvisoriaPorPagina(Pageable pagina, @RequestBody MotivoInternacaoProvisoria motivoInternacaoProvisoria) {
		return service.recuperarPorPagina(motivoInternacaoProvisoria, pagina);
	}
	
	@GetMapping(path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countMotivosInternacaoProvisoria() {
		return service.count();
	}
	
	@PostMapping(path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long countMotivosInternacaoProvisoria(@RequestBody MotivoInternacaoProvisoria motivoInternacaoProvisoria) {
		return service.count(motivoInternacaoProvisoria);
	}

	@Override
	protected BaseService<MotivoInternacaoProvisoria, Long> getService() {
		return service;
	}
	
}
