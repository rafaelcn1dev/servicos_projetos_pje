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
import br.jus.cnj.pje.criminal.error.CriminalBusinessException;
import br.jus.cnj.pje.criminal.model.entities.TipoProcedimentoOrigem;
import br.jus.cnj.pje.criminal.services.TipoProcedimentoOrigemService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TipoProcedimentoOrigemRestController {

	@Autowired
	private TipoProcedimentoOrigemService tipoProcedimentoOrigemService;

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST, path = "/tiposProcedimentoOrigem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoProcedimentoOrigem criarNovoTipoProcedimentoOrigem(@Valid
			@RequestBody TipoProcedimentoOrigem tipoProcedimentoOrigem) throws CriminalBusinessException {
		return tipoProcedimentoOrigemService.criarNovoTipoProcedimentoOrigem(tipoProcedimentoOrigem);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/tiposProcedimentoOrigem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoProcedimentoOrigem alterarTipoProcedimentoOrigem(@Valid
			@RequestBody TipoProcedimentoOrigem tipoProcedimentoOrigem) throws CriminalBusinessException {
		if (tipoProcedimentoOrigem.getId() != null) {
			TipoProcedimentoOrigem tipoProcedimentoOrigemAlterar = tipoProcedimentoOrigemService
					.recuperarTipoProcedimentoOrigemPorId(tipoProcedimentoOrigem.getId());
			if (tipoProcedimentoOrigemAlterar != null) {
				return tipoProcedimentoOrigemService.alterarTipoProcedimentoOrigem(tipoProcedimentoOrigem);
			}
		}
		return new TipoProcedimentoOrigem();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/tiposProcedimentoOrigem", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigem() {
		return tipoProcedimentoOrigemService.recuperarTiposProcedimentoOrigem();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/tiposProcedimentoOrigem/{idTipoProcedimentoOrigem}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoProcedimentoOrigem recuperarTipoProcedimentoOrigemPorId(
			@PathVariable("idTipoProcedimentoOrigem") Long idTipoProcedimentoOrigem) {
		return tipoProcedimentoOrigemService.recuperarTipoProcedimentoOrigemPorId(idTipoProcedimentoOrigem);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/tiposProcedimentoOrigem/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorPagina(Pageable pagina) {
		return tipoProcedimentoOrigemService.recuperarTiposProcedimentoOrigemPorPagina(pagina);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/tiposProcedimentoOrigem/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorPagina(Pageable pagina, @RequestBody TipoProcedimentoOrigem tipoProcedimentoOrigem) {
		return tipoProcedimentoOrigemService.recuperarTiposProcedimentoOrigemPorPagina(tipoProcedimentoOrigem, pagina);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/tiposProcedimentoOrigem/nome/{nomeTipoProcedimentoOrigem}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorNome(
			@PathVariable("nomeTipoProcedimentoOrigem") String nomeTipoProcedimentoOrigem) {
		return tipoProcedimentoOrigemService.recuperarTiposProcedimentoOrigemPorNome(nomeTipoProcedimentoOrigem);
	}

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.DELETE, path = "/tiposProcedimentoOrigem/{idTipoProcedimentoOrigem}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public TipoProcedimentoOrigem inativarTipoProcedimentoOrigem(
			@PathVariable("idTipoProcedimentoOrigem") Long idTipoProcedimentoOrigem) throws CriminalBusinessException {
		if (idTipoProcedimentoOrigem != null) {
			TipoProcedimentoOrigem tipoProcedimentoOrigemAlterar = tipoProcedimentoOrigemService
					.recuperarTipoProcedimentoOrigemPorId(idTipoProcedimentoOrigem);
			if (tipoProcedimentoOrigemAlterar != null) {
				return tipoProcedimentoOrigemService.inativarTipoProcedimentoOrigem(tipoProcedimentoOrigemAlterar);
			}
		}
		return new TipoProcedimentoOrigem();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/tiposOrigem/{idTipoOrigem}/tiposProcedimentoOrigem", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorTipoOrigem(
			@PathVariable("idTipoOrigem") Long idTipoOrigem) {
		return tipoProcedimentoOrigemService.recuperarTiposProcedimentoOrigemPorTipoOrigem(idTipoOrigem);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/tiposProcedimentoOrigem/count", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countTiposProcedimentoOrigem() {
		return this.tipoProcedimentoOrigemService.countTipoProcedimentoOrigem();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/tiposProcedimentoOrigem/count", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countTiposProcedimentoOrigem(@RequestBody TipoProcedimentoOrigem tipoProcedimentoOrigem) {
		return this.tipoProcedimentoOrigemService.countTipoProcedimentoOrigem(tipoProcedimentoOrigem);
	}
}