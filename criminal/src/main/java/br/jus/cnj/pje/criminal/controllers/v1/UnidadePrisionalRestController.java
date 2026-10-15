package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.model.beans.UnidadePrisionalBean;
import br.jus.cnj.pje.criminal.model.entities.UnidadePrisional;
import br.jus.cnj.pje.criminal.services.UnidadePrisionalService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class UnidadePrisionalRestController {

	@Autowired
	private UnidadePrisionalService unidadePrisionalService;
	private static final String PATH = "/unidadesPrisionais";
	private static final String PATH_PESQUISAR = "/unidadesPrisionais/pesquisar";
	private static final String PATH_COUNT = "/unidadesPrisionais/count";

	@Transactional(rollbackFor=Exception.class) 
	@RequestMapping(method = RequestMethod.POST, path = PATH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public UnidadePrisional criarUnidadePrisional(@Valid @RequestBody UnidadePrisional unidadePrisional) {
		return this.unidadePrisionalService.persistUnidadePrisional(unidadePrisional);
	}

	@Transactional(rollbackFor=Exception.class) 
	@RequestMapping(method = RequestMethod.PUT, path = PATH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public UnidadePrisional alterarUnidadePrisional(@Valid @RequestBody UnidadePrisional unidadePrisional) {
		if (unidadePrisional.getId() != null) {
			UnidadePrisional unidadePrisionalAlterar = this.unidadePrisionalService.recuperarUnidadePrisional(unidadePrisional.getId());
			if (unidadePrisionalAlterar != null) {
				return this.unidadePrisionalService.persistUnidadePrisional(unidadePrisional);
			}
		}
		return new UnidadePrisional();
	}
	
	@Transactional(rollbackFor=Exception.class) 
	@RequestMapping(method = RequestMethod.DELETE, path = PATH+"/{idUnidadePrisional}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public UnidadePrisional removerUnidadePrisional(@PathVariable("idUnidadePrisional") Long idUnidadePrisional) {
		if (idUnidadePrisional != null) {
			UnidadePrisional unidadePrisional = unidadePrisionalService.recuperarUnidadePrisional(idUnidadePrisional);
			if (unidadePrisional != null) {
				unidadePrisionalService.removerUnidadePrisional(unidadePrisional);
				return unidadePrisional;
			}
		}
		return new UnidadePrisional();
	}

	@RequestMapping(method = RequestMethod.POST, path = PATH_PESQUISAR, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<UnidadePrisional> recuperarUnidadesPrisionaisPorPagina(Pageable pagina, @RequestBody UnidadePrisional unidadePrisional) {
		return this.unidadePrisionalService.recuperarUnidadesPrisionaisPorPagina(unidadePrisional, pagina);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = PATH_PESQUISAR, produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<UnidadePrisional> recuperarUnidadesPrisionaisPorPagina(Pageable pagina) {
		return this.unidadePrisionalService.recuperarUnidadesPrisionaisPorPagina(pagina);
	}	

	@RequestMapping(method = RequestMethod.GET, path = PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<UnidadePrisional> recuperarUnidadesPrisionais() {
		return this.unidadePrisionalService.recuperarUnidadesPrisionais();
	}

	@RequestMapping(method = RequestMethod.GET, path = PATH+"/{idUnidadePrisional}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public UnidadePrisional recuperarUnidadePrisional(@PathVariable("idUnidadePrisional") Long idUnidadePrisional) {
		return this.unidadePrisionalService.recuperarUnidadePrisional(idUnidadePrisional);
	}

	
	@RequestMapping(method = RequestMethod.GET, path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countUnidadesPrisionais() {
		return this.unidadePrisionalService.countUnidadePrisional();
	}

	@RequestMapping(method = RequestMethod.POST, path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countUnidadesPrisionais(@RequestBody UnidadePrisional unidadePrisional) {
		return this.unidadePrisionalService.countUnidadePrisional(unidadePrisional);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = PATH + "/estados/{siglaUf}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<UnidadePrisional>> recuperarUnidadesPrisionaisPorSiglaUf(@PathVariable("siglaUf") String siglaUf) {
		ResponseEntity <List<UnidadePrisional>> res = ResponseEntity.noContent().build();
		
		List<UnidadePrisional> unidadesPrisionais = this.unidadePrisionalService.recuperarUnidadePrisionalPorSiglaUf(siglaUf);
		
		if(!CollectionUtils.isEmpty(unidadesPrisionais)) {
			res = ResponseEntity.ok(unidadesPrisionais);
		}
		
		return res;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = PATH + "/filter/estados/{siglaUf}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<UnidadePrisionalBean>> recuperarUnidadesPrisionaisBeanPorSiglaUf(
			@PathVariable("siglaUf") String siglaUf) {
		ResponseEntity<List<UnidadePrisionalBean>> res = ResponseEntity.noContent().build();
		List<UnidadePrisional> unidadesPrisionais = unidadePrisionalService
				.recuperarUnidadePrisionalPorSiglaUf(siglaUf);
		if (!CollectionUtils.isEmpty(unidadesPrisionais)) {
			List<UnidadePrisionalBean> unidadesPrisionaisBean = new ArrayList<>();
			unidadesPrisionais.forEach(up -> unidadesPrisionaisBean.add(new UnidadePrisionalBean(up)));
			res = ResponseEntity.ok(unidadesPrisionaisBean);
		}
		return res;
	}
}