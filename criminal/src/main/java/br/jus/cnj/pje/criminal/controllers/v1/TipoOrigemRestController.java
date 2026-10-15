package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.error.CriminalBusinessException;
import br.jus.cnj.pje.criminal.error.PjeEntityNotFoundException;
import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;
import br.jus.cnj.pje.criminal.services.TipoOrigemService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TipoOrigemRestController {

	@Autowired
	private TipoOrigemService tipoOrigemService;

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST, path = "/tiposOrigem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoOrigem criarTipoOrigem(@Valid @RequestBody TipoOrigem tipoOrigem, Errors erros) throws CriminalBusinessException {		
		return this.tipoOrigemService.criarTipoOrigem(tipoOrigem);
	}

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/tiposOrigem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoOrigem alterarTipoOrigem(@Valid @RequestBody TipoOrigem tipoOrigem) throws CriminalBusinessException {
		if (tipoOrigem != null) {
			verificaSeEntidadeExiste(tipoOrigem.getId());
			tipoOrigem = this.tipoOrigemService.alterarTipoOrigem(tipoOrigem);
		}
		return tipoOrigem;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/tiposOrigem/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<TipoOrigem> recuperarTiposOrigemPorPagina(Pageable pagina, @RequestBody TipoOrigem tipoOrigem) {
		return this.tipoOrigemService.recuperarTiposOrigemPorPagina(tipoOrigem, pagina);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/tiposOrigem/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<TipoOrigem> recuperarTiposOrigemPorPagina(Pageable pagina) {
		return this.tipoOrigemService.recuperarTiposOrigemPorPagina(pagina);
	}	

	@RequestMapping(method = RequestMethod.GET, path = "/tiposOrigem", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<TipoOrigem> recuperarTiposOrigem() {
		return this.tipoOrigemService.recuperarTiposOrigem();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/tiposOrigem/{idTipoOrigem}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoOrigem recuperarTipoOrigem(@PathVariable("idTipoOrigem") Long idTipoOrigem) {
		verificaSeEntidadeExiste(idTipoOrigem);
		return this.tipoOrigemService.recuperarTipoOrigem(idTipoOrigem);
	}

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.DELETE, path = "/tiposOrigem/{idTipoOrigem}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoOrigem inativarTipoOrigem(@PathVariable("idTipoOrigem") Long idTipoOrigem) throws CriminalBusinessException {
		verificaSeEntidadeExiste(idTipoOrigem);
		TipoOrigem tipoOrigemAlterar = tipoOrigemService.recuperarTipoOrigem(idTipoOrigem);
		if (tipoOrigemAlterar != null) {
			return tipoOrigemService.inativarTipoOrigem(tipoOrigemAlterar);
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/tiposOrigem/count", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countTiposOrigem() {
		return this.tipoOrigemService.countTipoOrigem();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/tiposOrigem/count", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countTiposOrigem(@RequestBody TipoOrigem tipoOrigem) {
		return this.tipoOrigemService.countTipoOrigem(tipoOrigem);
	}	
	
	private void verificaSeEntidadeExiste(Long id) {
		TipoOrigem tipoOrigem = tipoOrigemService.recuperarTipoOrigem(id);
		if(tipoOrigem == null) {
			throw new PjeEntityNotFoundException(TipoOrigem.class, id);
		}
	}
}