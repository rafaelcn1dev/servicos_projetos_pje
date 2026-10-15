package br.jus.cnj.pje.criminal.controllers.v2;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.error.CriminalBusinessException;
import br.jus.cnj.pje.criminal.model.dto.v2.ProcessoDTO;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.services.ProcessoService;
import br.jus.cnj.pje.criminal.util.ApiVersions;


@RestController("ProcessoRestControllerV2")
@RequestMapping(ApiVersions.V2 + ProcessoRestController.PATH)
public class ProcessoRestController {

	private static final Logger logger = LoggerFactory.getLogger(ProcessoRestController.class);
	public static final String PATH = "/processos";
	
	@Autowired
	private ProcessoService processoService;
	
    @Autowired
	private ModelMapper modelMapper;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<ProcessoDTO> criarNovoProcesso(@Valid @RequestBody ProcessoDTO processo) throws Exception {
		this.logPostDataBody(processo);

		if(processo.getNrProcesso() == null) {
			throw new Exception("Processo não informado.");
		}
		if (processo.getDtLocalFato() != null && processo.getDtLocalFato().after(new Date())) {
			throw new Exception("A data do fato não pode ser posterior a data de hoje!");
		}
		Processo novoProcesso = this.processoService.recuperarProcessoPorNumero(processo.getNrProcesso(), true);
		if(novoProcesso == null) {
			try {
				novoProcesso = dtoToEntity(processo);
				processoService.validarNovoProcesso(novoProcesso);
				novoProcesso = processoService.criarNovoProcesso(novoProcesso);
			} catch (CriminalBusinessException e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		} else {
			novoProcesso = this.processoService.recuperarProcessoPorNumero(processo.getNrProcesso());
			if(novoProcesso == null) {	
				return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
			}			
		}		
		return new ResponseEntity<>(convertToDto(novoProcesso), HttpStatus.OK);
	}
	

	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ProcessoDTO alterarProcesso(@Valid @RequestBody ProcessoDTO processo) {
		if (processo.getId() != null) {
			Processo processoAlterar = processoService.recuperarProcessoPorId(processo.getId());
			if (processoAlterar != null) {
				Processo proc = dtoToEntity(processo);
				proc = processoService.updateProcesso(proc);
				return  convertToDto(proc);
			}
		}
		return new ProcessoDTO();
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	@Deprecated
	public List<Processo> recuperarProcessos() {
		throw new UnsupportedOperationException("Operação desabilitada.");
	}

	@GetMapping(path = "/{idProcesso}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ProcessoDTO recuperarProcessoPorId(@PathVariable("idProcesso") Long idProcesso) {
		return convertToDto(processoService.recuperarProcessoPorId(idProcesso));
	}

	@PostMapping(path = "/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<ProcessoDTO> recuperarProcessosPorPagina(Pageable pagina) {
		Page<Processo> page = processoService.recuperarProcessosPorPagina(pagina);
		return page.map(this::convertToDto);		 
	}
	
	@GetMapping(path = "/pesquisar/numero/{numeroProcesso}/", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<ProcessoDTO> recuperarProcessosPorNumero(@PathVariable("numeroProcesso") String numeroProcesso) throws UnsupportedEncodingException {
		List<Processo> listaProcesso = processoService.recuperarProcessosPorNumero(numeroProcesso);
		return listaProcesso.stream().map(this::convertToDto).collect(Collectors.toList());		 
	}

	@GetMapping(path = "/numero/{numeroProcesso:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ProcessoDTO recuperarProcessoPorNumero(@PathVariable("numeroProcesso") String numeroProcesso) throws UnsupportedEncodingException {
		Processo processo = processoService.recuperarProcessoPorNumero(numeroProcesso);
		return convertToDto(processo);
	}
	
	private ProcessoDTO convertToDto(Processo processo) {
		if(processo == null) {
			return null;
		}
	    return modelMapper.map(processo, ProcessoDTO.class);
	}
	
	private Processo dtoToEntity(ProcessoDTO dto) {
	    return modelMapper.map(dto, Processo.class);
	}
	
	private void logPostDataBody(ProcessoDTO processo) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Dados de entrada (JSON): {}", mapper.writeValueAsString(processo));
			}
		} catch (JsonProcessingException ex) {
			logger.error(ex.getLocalizedMessage(), ex);
		}
	}
}