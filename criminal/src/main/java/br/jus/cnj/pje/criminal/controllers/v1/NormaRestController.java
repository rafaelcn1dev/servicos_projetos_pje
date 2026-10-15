package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.error.PjeEntityNotFoundException;
import br.jus.cnj.pje.criminal.model.entities.Norma;
import br.jus.cnj.pje.criminal.services.NormaService;

@RestController
public class NormaRestController {

	@Autowired
	private NormaService normaService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/normas", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<Norma> recuperarNormas(){
		return this.normaService.recuperarNormasAtivas();
	}
	
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(method = RequestMethod.POST, path = "/normas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Norma criarNorma(@Valid @RequestBody Norma norma, Errors erros) {		
		return normaService.criarNorma(norma);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/normas/count", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countNormas() {
		return normaService.countNormas();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/normas/count", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countNormas(@RequestBody Norma norma) {
		return normaService.countNormas(norma);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/normas/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<Norma> recuperarNormasPorPagina(Pageable pagina, @RequestBody Norma norma) {
		Sort sort = Sort.by(Sort.Order.asc("tipoNorma"),Sort.Order.asc("numeroNorma"),Sort.Order.asc("norma"));
		pagina = PageRequest.of(pagina.getPageNumber(), pagina.getPageSize(), sort);
		return normaService.recuperarNormasPorPagina(norma, pagina);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/normas/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<Norma> recuperarNormasPorPagina(Pageable pagina) {
		return normaService.recuperarNormasPorPagina(pagina);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/normas/{idNorma}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Norma recuperarNorma(@PathVariable("idNorma") Long idNorma) {
		return normaService.recuperarNorma(idNorma);
	}
	
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/normas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Norma alterarNorma(@Valid @RequestBody Norma norma) {
		if (norma != null) {
			verificaSeEntidadeExiste(norma.getId());
			return normaService.alterarNorma(norma);
		}
		return new Norma();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.DELETE, path = "/normas/{idNorma}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Norma inativarNorma(@PathVariable("idNorma") Long idNorma) {
		verificaSeEntidadeExiste(idNorma);
		Norma normaAlterar = normaService.recuperarNorma(idNorma);
		if (normaAlterar != null) {
			return normaService.inativarNorma(normaAlterar);
		}
		return null;
	}
	
	private void verificaSeEntidadeExiste(Long id) {
		Norma norma = normaService.recuperarNorma(id);
		if(norma == null) {
			throw new PjeEntityNotFoundException(Norma.class, id);
		}
	}
}