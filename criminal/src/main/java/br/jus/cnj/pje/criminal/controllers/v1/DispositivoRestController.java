package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.error.PjeEntityNotFoundException;
import br.jus.cnj.pje.criminal.model.entities.Dispositivo;
import br.jus.cnj.pje.criminal.services.DispositivoService;

@RestController
public class DispositivoRestController {

	@Autowired
	private DispositivoService dispositivoService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/dispositivos/{idNorma}/{idTipoDispositivo}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<Dispositivo>> recuperarDispositivosPorIdNormaEhIdTipoDispositivo(
			@PathVariable("idNorma") Long idNorma, @PathVariable("idTipoDispositivo") Long idTipoDispositivo) {
		ResponseEntity<List<Dispositivo>> res = ResponseEntity.noContent().build();

		List<Dispositivo> lista = this.dispositivoService.recuperarDispositivosPorIdNormaEhTipo(idNorma, idTipoDispositivo);

		if (!CollectionUtils.isEmpty(lista)) {
			res = ResponseEntity.ok(lista);
		}

		return res;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/dispositivos/{idNorma}/{idTipoDispositivo}/{texto}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<Dispositivo>> recuperarDispositivosPorIdNormaEhIdTipoDispositivoEhTexto(
			@PathVariable("idNorma") Long idNorma, 
			@PathVariable("idTipoDispositivo") Long idTipoDispositivo,
			@PathVariable("texto") String texto) {
		ResponseEntity<List<Dispositivo>> res = ResponseEntity.noContent().build();

		List<Dispositivo> lista = this.dispositivoService.recuperarDispositivosPorIdNormaEhTipoEhTexto(idNorma, idTipoDispositivo, texto);

		if (!CollectionUtils.isEmpty(lista)) {
			res = ResponseEntity.ok(lista);
		}

		return res;
	}	
	
	@RequestMapping(method = RequestMethod.GET, path = "/dispositivos/norma/{idNorma}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<Dispositivo>> recuperarDispositivosPorIdNorma(
			@PathVariable("idNorma") Long idNorma) {
		ResponseEntity<List<Dispositivo>> res = ResponseEntity.noContent().build();

		List<Dispositivo> lista = this.dispositivoService.recuperarDispositivosPorIdNorma(idNorma);

		if (!CollectionUtils.isEmpty(lista)) {
			res = ResponseEntity.ok(lista);
		}

		return res;		
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/dispositivos/{idDispositivo}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Dispositivo> recuperarDispositivoPorId(
			@PathVariable("idDispositivo") Long idDispositivo) {
		ResponseEntity<Dispositivo> res = ResponseEntity.noContent().build();

		Dispositivo dispositivo = this.dispositivoService.recuperarDispositivoPorId(idDispositivo);

		if (dispositivo != null) {
			res = ResponseEntity.ok(dispositivo);
		}

		return res;		
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/dispositivos/dispositivoPai/{idDispositivoPai}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<Dispositivo>> recuperarDispositivosPorIdDispositivoPai(@PathVariable("idDispositivoPai") Long idDispositivoPai) {
		ResponseEntity<List<Dispositivo>> res = ResponseEntity.noContent().build();

		List<Dispositivo> lista = this.dispositivoService.recuperarDispositivosPorIdDispositivoPai(idDispositivoPai);

		if (!CollectionUtils.isEmpty(lista)) {
			res = ResponseEntity.ok(lista);
		}

		return res;
	}	
	
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(method = RequestMethod.POST, path = "/dispositivos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Dispositivo criarDispositivo(@Valid @RequestBody Dispositivo dispositivo, Errors erros) {		
		return dispositivoService.criarDispositivo(dispositivo);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/dispositivos/count", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countDispositivos() {
		return dispositivoService.countDispositivos();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/dispositivos/count", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countDispositivos(@RequestBody Dispositivo dispositivo) {
		return dispositivoService.countDispositivos(dispositivo);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/dispositivos/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<Dispositivo> recuperarDispositivosPorPagina(Pageable pagina, @RequestBody Dispositivo dispositivo) {
		Sort sort = Sort.by(Sort.Order.asc("norma"),Sort.Order.asc("tipoDispositivo"));
		pagina = PageRequest.of(pagina.getPageNumber(), pagina.getPageSize(), sort);
		return dispositivoService.recuperarDispositivosPorPagina(dispositivo, pagina);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/dispositivos/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<Dispositivo> recuperarDispositivosPorPagina(Pageable pagina) {
		return dispositivoService.recuperarDispositivosPorPagina(pagina);
	}
	
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/dispositivos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Dispositivo alterarDispositivo(@Valid @RequestBody Dispositivo dispositivo) {
		if (dispositivo != null) {
			verificaSeEntidadeExiste(dispositivo.getId());
			return dispositivoService.alterarDispositivo(dispositivo);
		}
		return new Dispositivo();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.DELETE, path = "/dispositivos/{idDispositivo}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Dispositivo inativarDispositivo(@PathVariable("idDispositivo") Long idDispositivo) {
		verificaSeEntidadeExiste(idDispositivo);
		Dispositivo dispositivoAlterar = dispositivoService.recuperarDispositivoPorId(idDispositivo);
		if (dispositivoAlterar != null) {
			return dispositivoService.inativarDispositivo(dispositivoAlterar);
		}
		return null;
	}
	
	private void verificaSeEntidadeExiste(Long id) {
		Dispositivo dispositivo = dispositivoService.recuperarDispositivoPorId(id);
		if(dispositivo == null) {
			throw new PjeEntityNotFoundException(Dispositivo.class, id);
		}
	}
	
}
