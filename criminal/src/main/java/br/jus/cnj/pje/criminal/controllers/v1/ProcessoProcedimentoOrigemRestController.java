package br.jus.cnj.pje.criminal.controllers.v1;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.model.entities.ProcessoProcedimentoOrigem;
import br.jus.cnj.pje.criminal.services.ProcessoProcedimentoOrigemService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProcessoProcedimentoOrigemRestController {

	@Autowired
	private ProcessoProcedimentoOrigemService processoProcedimentoOrigemService;

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST, path = "/processosProcedimentosOrigem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ProcessoProcedimentoOrigem criarProcessoProcedimentoOrigem(@Valid
			@RequestBody ProcessoProcedimentoOrigem processoProcedimentoOrigem) throws UnsupportedEncodingException {
		return processoProcedimentoOrigemService.salvarProcessoProcedimentoOrigem(processoProcedimentoOrigem);
	}

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.PUT, path = "/processosProcedimentosOrigem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ProcessoProcedimentoOrigem alterarProcessoProcedimentoOrigem(@Valid
			@RequestBody ProcessoProcedimentoOrigem processoProcedimentoOrigem) throws UnsupportedEncodingException {
		if (processoProcedimentoOrigem.getId() != null) {
			ProcessoProcedimentoOrigem processoProcedimentoOrigemAlterar = processoProcedimentoOrigemService
					.recuperarProcedimentosPorId(processoProcedimentoOrigem.getId());
			if (processoProcedimentoOrigemAlterar != null) {
				return processoProcedimentoOrigemService.salvarProcessoProcedimentoOrigem(processoProcedimentoOrigem);
			}
		}
		return new ProcessoProcedimentoOrigem();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/processosProcedimentosOrigem/{idProcedimento}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ProcessoProcedimentoOrigem recuperarProcessoProcedimentoOrigem(
			@PathVariable("idProcedimento") Long idProcedimento) {
		return processoProcedimentoOrigemService.recuperarProcedimentosPorId(idProcedimento);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/processosProcedimentosOrigem/processo/{nrProcesso}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<ProcessoProcedimentoOrigem> recuperarProcessoProcedimentoOrigem(
			@PathVariable("nrProcesso") String nrProcesso) {
		return processoProcedimentoOrigemService.recuperarProcedimentosOrigemPorProcesso(nrProcesso);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/processosProcedimentosOrigem", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<ProcessoProcedimentoOrigem> recuperarProcessoProcedimentoOrigem() {
		return processoProcedimentoOrigemService.recuperarTodosProcedimentosOrigem();
	}
	
	@DeleteMapping(path = "/processosProcedimentosOrigem/{idProcedimento}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ProcessoProcedimentoOrigem inativarProcessoProcedimentoOrigem(
			@PathVariable("idProcedimento") Long idProcedimento) {
		ProcessoProcedimentoOrigem processoProcedimentoOrigem = processoProcedimentoOrigemService
				.recuperarProcedimentosPorId(idProcedimento);
		if (processoProcedimentoOrigem != null) {
			return processoProcedimentoOrigemService.inativarProcessoProcedimentoOrigem(processoProcedimentoOrigem);
		}
		return new ProcessoProcedimentoOrigem();
	}
}