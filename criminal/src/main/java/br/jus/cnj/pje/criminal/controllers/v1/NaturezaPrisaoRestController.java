package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.model.entities.NaturezaPrisao;
import br.jus.cnj.pje.criminal.services.NaturezaPrisaoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NaturezaPrisaoRestController {

	@Autowired
	private NaturezaPrisaoService naturezaPrisaoService;
	private static final String PATH = "/naturezasPrisao";
	private static final String PATH_PESQUISAR = "/naturezasPrisao/pesquisar";
	private static final String PATH_COUNT = "/naturezasPrisao/count";

	@Transactional(rollbackFor=Exception.class) 
	@RequestMapping(method = RequestMethod.POST, path = PATH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public NaturezaPrisao criarNaturezaPrisao(@Valid @RequestBody NaturezaPrisao naturezaPrisao) {
		return this.naturezaPrisaoService.criarNaturezaPrisao(naturezaPrisao);
	}

	@Transactional(rollbackFor=Exception.class) 
	@RequestMapping(method = RequestMethod.PUT, path = PATH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public NaturezaPrisao alterarNaturezaPrisao(@Valid @RequestBody NaturezaPrisao naturezaPrisao) {
		if (naturezaPrisao.getId() != null) {
			NaturezaPrisao naturezaPrisaoAlterar = this.naturezaPrisaoService.recuperarNaturezaPrisao(naturezaPrisao.getId());
			if (naturezaPrisaoAlterar != null) {
				return this.naturezaPrisaoService.alterarNaturezaPrisao(naturezaPrisao);
			}
		}
		return new NaturezaPrisao();
	}
	
	@Transactional(rollbackFor=Exception.class) 
	@RequestMapping(method = RequestMethod.DELETE, path = PATH+"/{idNaturezaPrisao}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public NaturezaPrisao removerNaturezaPrisao(@PathVariable("idNaturezaPrisao") Long idNaturezaPrisao) {
		if (idNaturezaPrisao != null) {
			NaturezaPrisao naturezaPrisao = naturezaPrisaoService.recuperarNaturezaPrisao(idNaturezaPrisao);
			if (naturezaPrisao != null) {
				naturezaPrisaoService.inativarNaturezaPrisao(naturezaPrisao);
				return naturezaPrisao;
			}
		}
		return new NaturezaPrisao();
	}

	@RequestMapping(method = RequestMethod.POST, path = PATH_PESQUISAR, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<NaturezaPrisao> recuperarNaturezasPrisaoPorPagina(Pageable pagina, @RequestBody NaturezaPrisao naturezaPrisao) {
		return this.naturezaPrisaoService.recuperarNaturezasPrisaoPorPagina(naturezaPrisao, pagina);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = PATH_PESQUISAR, produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<NaturezaPrisao> recuperarNaturezasPrisaoPorPagina(Pageable pagina) {
		return this.naturezaPrisaoService.recuperarNaturezasPrisaoPorPagina(pagina);
	}	

	@RequestMapping(method = RequestMethod.GET, path = PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<NaturezaPrisao> recuperarNaturezasPrisao() {
		return this.naturezaPrisaoService.recuperarNaturezasPrisao();
	}

	@RequestMapping(method = RequestMethod.GET, path = PATH+"/{idNaturezaPrisao}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public NaturezaPrisao recuperarNaturezaPrisao(@PathVariable("idNaturezaPrisao") Long idNaturezaPrisao) {
		return this.naturezaPrisaoService.recuperarNaturezaPrisao(idNaturezaPrisao);
	}

	
	@RequestMapping(method = RequestMethod.GET, path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countNaturezasPrisao() {
		return this.naturezaPrisaoService.countNaturezaPrisao();
	}

	@RequestMapping(method = RequestMethod.POST, path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countNaturezasPrisao(@RequestBody NaturezaPrisao naturezaPrisao) {
		return this.naturezaPrisaoService.countNaturezaPrisao(naturezaPrisao);
	}
}