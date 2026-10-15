package br.jus.cnj.pje.criminal.controllers.v2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.enums.TipoProcessoEnum;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;
import br.jus.cnj.pje.criminal.services.ProcessoService;
import br.jus.cnj.pje.criminal.services.TipoEventoCriminalService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + TipoEventoCriminalRestControllerImpl.PATH)
public class TipoEventoCriminalRestControllerImpl extends AbstractRestController<TipoEventoCriminal, Long>{

	public static final String PATH = "/tipos-eventos-criminais";
	private static final String PATH_PESQUISAR = "/pesquisar";
	private static final String PATH_COUNT = "/count";
	
	@Autowired
	private TipoEventoCriminalService service;
	
	@Autowired
	private ProcessoService processoService;
	
	@Override
	protected BaseService<TipoEventoCriminal, Long> getService() {
		return this.service;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = PATH_PESQUISAR, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Page<TipoEventoCriminal> recuperarTiposPorPagina(Pageable pagina, @RequestBody TipoEventoCriminal tipoEventoCriminal) {
		return service.recuperarPorPagina(tipoEventoCriminal, pagina);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countTipos() {
		return service.count();
	}

	@RequestMapping(method = RequestMethod.POST, path = PATH_COUNT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long countTipos(@RequestBody TipoEventoCriminal tipoEventoCriminal) {
		return service.count(tipoEventoCriminal);
	}
	
	@GetMapping(path = "/processo/{idProcesso}")
	public ResponseEntity<List<TipoEventoCriminal>> recuperarTiposEventosCriminaisPorProcesso(
			@PathVariable("idProcesso") Long idProcesso, @RequestParam(required = false, name = "ativo") Boolean ativo) {
		ResponseEntity<List<TipoEventoCriminal>> ret = ResponseEntity.noContent().build();
		Processo processo = processoService.recuperarProcessoPorId(idProcesso);
		List<TipoEventoCriminal> tiposInformacaoCriminal;
		if (ativo != null) {
			if (ativo) {
				tiposInformacaoCriminal = service.recuperaTiposEventoCriminalAtivosPorTipoProcesso(processo.getTipoProcesso());		
			} else {
				tiposInformacaoCriminal = service.recuperaTiposEventoCriminalInativosPorTipoProcesso(processo.getTipoProcesso());
			}
		} else {
			tiposInformacaoCriminal = service.recuperaTiposEventoCriminalPorTipoProcesso(processo.getTipoProcesso());
		}
		if (!CollectionUtils.isEmpty(tiposInformacaoCriminal)) {
			ret = ResponseEntity.ok(tiposInformacaoCriminal);
		}
		return ret;
	}
	
	@GetMapping(path = "/tipo-processo/{tipoProcesso}")
	public ResponseEntity<List<TipoEventoCriminal>> recuperarTiposEventosCriminaisPorTipoProcesso(@PathVariable("tipoProcesso") TipoProcessoEnum tipoProcesso){
		ResponseEntity<List<TipoEventoCriminal>> ret = ResponseEntity.noContent().build();
		
		List<TipoEventoCriminal> listaTipoInformacaoCriminal = 
				service.recuperaTiposEventoCriminalPorTipoProcesso(tipoProcesso);
		
		if(!CollectionUtils.isEmpty(listaTipoInformacaoCriminal)) {
			ret = ResponseEntity.ok(listaTipoInformacaoCriminal.stream().filter(tipoEventoCriminal -> tipoEventoCriminal.getAtivo() == true).collect(Collectors.toList()));
		}
		
		return ret;
	}

}
