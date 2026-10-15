package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.error.PjeEntityNotFoundException;
import br.jus.cnj.pje.criminal.model.entities.TipoRecurso;
import br.jus.cnj.pje.criminal.services.TipoRecursoService;

@RestController
public class TipoRecursoRestController {

	@Autowired
	private TipoRecursoService tipoRecursoService;


	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST, path = "/tiposRecurso", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoRecurso criarTipoRecurso(@Valid @RequestBody TipoRecurso tipoRecurso, Errors erros) {		
		return tipoRecursoService.criarTipoRecurso(tipoRecurso);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/tiposRecurso", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<TipoRecurso>> recuperarTiposRecursos() {
		return ResponseEntity.ok(tipoRecursoService.recuperarTiposRecursosAtivos());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/tiposRecurso/{idTipoRecurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoRecurso recuperarTipoRecurso(@PathVariable("idTipoRecurso") Long idTipoRecurso) {
		verificaSeEntidadeExiste(idTipoRecurso);
		return this.tipoRecursoService.recuperarTipoRecurso(idTipoRecurso);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/tiposRecurso/count", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countTiposRecurso() {
		return tipoRecursoService.countTiposRecurso();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/tiposRecurso/count", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countTiposRecurso(@RequestBody TipoRecurso tipoRecurso) {
		return tipoRecursoService.countTiposRecurso(tipoRecurso);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/tiposRecurso/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<TipoRecurso> recuperarTiposRecursoPorPagina(Pageable pagina, @RequestBody TipoRecurso tipoRecurso) {
		return tipoRecursoService.recuperarTiposRecursoPorPagina(tipoRecurso, pagina);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/tiposRecurso/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<TipoRecurso> recuperarTiposRecursoPorPagina(Pageable pagina) {
		return tipoRecursoService.recuperarTiposRecursoPorPagina(pagina);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/tiposRecurso", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoRecurso alterarTipoRecurso(@Valid @RequestBody TipoRecurso tipoRecurso) {
		if (tipoRecurso != null) {
			verificaSeEntidadeExiste(tipoRecurso.getId());
			return tipoRecursoService.alterarTipoRecurso(tipoRecurso);
		}
		return new TipoRecurso();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.DELETE, path = "/tiposRecurso/{idTipoRecurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoRecurso inativarTipoRecurso(@PathVariable("idTipoRecurso") Long idTipoRecurso) {
		verificaSeEntidadeExiste(idTipoRecurso);
		TipoRecurso tipoRecursoAlterar = tipoRecursoService.recuperarTipoRecurso(idTipoRecurso);
		if (tipoRecursoAlterar != null) {
			return tipoRecursoService.inativarTipoRecurso(tipoRecursoAlterar);
		}
		return null;
	}
	
	private void verificaSeEntidadeExiste(Long id) {
		TipoRecurso tipoRecurso = tipoRecursoService.recuperarTipoRecurso(id);
		if(tipoRecurso == null) {
			throw new PjeEntityNotFoundException(TipoRecurso.class, id);
		}
	}
}