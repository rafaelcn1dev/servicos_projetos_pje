package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.error.PjeEntityNotFoundException;
import br.jus.cnj.pje.criminal.model.entities.MotivoSuspensaoProcesso;
import br.jus.cnj.pje.criminal.services.MotivoSuspensaoProcessoService;

@RestController
public class MotivoSuspensaoProcessoRestController {

	@Autowired
	private MotivoSuspensaoProcessoService motivoSuspensaoProcessoService;

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST, path = "/motivosSuspensaoProcesso", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public MotivoSuspensaoProcesso criarMotivoSuspensaoProcesso(
			@Valid @RequestBody MotivoSuspensaoProcesso motivoSuspensaoProcesso, Errors erros) {
		return motivoSuspensaoProcessoService.criarMotivoSuspensaoProcesso(motivoSuspensaoProcesso);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/motivosSuspensaoProcesso", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<MotivoSuspensaoProcesso>> recuperarMotivosSuspensaoProcesso() {
		return ResponseEntity.ok(motivoSuspensaoProcessoService.recuperarMotivosSuspensaoProcessoAtivos());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/motivosSuspensaoProcesso/{idMotivoSuspensaoProcesso}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public MotivoSuspensaoProcesso recuperarMotivoSuspensaoProcesso(
			@PathVariable("idMotivoSuspensaoProcesso") Long idMotivoSuspensaoProcesso) {
		verificaSeEntidadeExiste(idMotivoSuspensaoProcesso);
		return this.motivoSuspensaoProcessoService.recuperarMotivoSuspensaoProcesso(idMotivoSuspensaoProcesso);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/motivosSuspensaoProcesso/count", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countMotivosSuspensao() {
		return motivoSuspensaoProcessoService.countMotivosSuspensaoProcesso();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/motivosSuspensaoProcesso/count", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countMotivosSuspensao(@RequestBody MotivoSuspensaoProcesso motivoSuspensaoProcesso) {
		return motivoSuspensaoProcessoService.countMotivosSuspensaoProcesso(motivoSuspensaoProcesso);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/motivosSuspensaoProcesso/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<MotivoSuspensaoProcesso> recuperarMotivosSuspensaoPorPagina(Pageable pagina,
			@RequestBody MotivoSuspensaoProcesso motivoSuspensaoProcesso) {
		return motivoSuspensaoProcessoService.recuperarMotivosSuspensaoProcessoPorPagina(motivoSuspensaoProcesso,
				pagina);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/motivosSuspensaoProcesso/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<MotivoSuspensaoProcesso> recuperarMotivosSuspensaoPorPagina(Pageable pagina) {
		return motivoSuspensaoProcessoService.recuperarMotivosSuspensaoProcessoPorPagina(pagina);
	}

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/motivosSuspensaoProcesso", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public MotivoSuspensaoProcesso alterarMotivoSuspensaoProcesso(
			@Valid @RequestBody MotivoSuspensaoProcesso motivoSuspensaoProcesso) {
		if (motivoSuspensaoProcesso != null) {
			verificaSeEntidadeExiste(motivoSuspensaoProcesso.getId());
			return motivoSuspensaoProcessoService.alterarMotivoSuspensaoProcesso(motivoSuspensaoProcesso);
		}
		return new MotivoSuspensaoProcesso();
	}

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.DELETE, path = "/motivosSuspensaoProcesso/{idMotivoSuspensaoProcesso}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public MotivoSuspensaoProcesso inativarMotivoSuspensaoProcesso(
			@PathVariable("idMotivoSuspensaoProcesso") Long idMotivoSuspensaoProcesso) {
		verificaSeEntidadeExiste(idMotivoSuspensaoProcesso);
		MotivoSuspensaoProcesso motivoSuspensaoProcessoAlterar = motivoSuspensaoProcessoService
				.recuperarMotivoSuspensaoProcesso(idMotivoSuspensaoProcesso);
		if (motivoSuspensaoProcessoAlterar != null) {
			return motivoSuspensaoProcessoService.inativarMotivoSuspensaoProcesso(motivoSuspensaoProcessoAlterar);
		}
		return null;
	}

	private void verificaSeEntidadeExiste(Long id) {
		MotivoSuspensaoProcesso motivoSuspensaoProcesso = motivoSuspensaoProcessoService
				.recuperarMotivoSuspensaoProcesso(id);
		if (motivoSuspensaoProcesso == null) {
			throw new PjeEntityNotFoundException(MotivoSuspensaoProcesso.class, id);
		}
	}
	
}