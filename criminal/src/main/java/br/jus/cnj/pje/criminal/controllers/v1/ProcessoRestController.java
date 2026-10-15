package br.jus.cnj.pje.criminal.controllers.v1;

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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.enums.TipoProcessoEnum;
import br.jus.cnj.pje.criminal.error.CriminalBusinessException;
import br.jus.cnj.pje.criminal.model.dto.v1.ProcessoDTO;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.model.entities.ProcessoProcedimentoOrigem;
import br.jus.cnj.pje.criminal.services.ProcessoService;

@RestController
public class ProcessoRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProcessoRestController.class);

	@Autowired
	private ProcessoService processoService;
	
    @Autowired
	private ModelMapper modelMapper;


	@RequestMapping(method = RequestMethod.POST, path = "/processos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ProcessoDTO criarNovoProcesso(@Valid @RequestBody ProcessoDTO processo) throws Exception {

		this.logPostDataBody(processo);

		if (processo != null && processo.getDtLocalFato() != null && processo.getDtLocalFato().after(new Date())) {
			throw new Exception("A data do fato não pode ser posterior a data de hoje!");
		}
		Processo proc = this.processoService.recuperarProcessoPorNumero(processo.getNrProcesso());
		// Aqui cabe uma observação. Caso o processo exista e o usuário não possua acesso a este processo, será retornado NULL. Isso pode indicar que o processo não exista.
		if(proc == null) {
			try {
				proc = dtoToEntity(processo);
				proc.setTipoProcesso(TipoProcessoEnum.CRI);				
				processoService.validarNovoProcesso(proc);
				proc = processoService.criarNovoProcesso(proc);
			} catch (CriminalBusinessException e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}

  		return modelMapper.map(proc, ProcessoDTO.class);
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

	
	@RequestMapping(method = RequestMethod.PUT, path = "/processos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ProcessoDTO alterarProcesso(@Valid @RequestBody Processo processo) {
		if (processo.getId() != null) {
			Processo processoAlterar = processoService.recuperarProcessoPorId(processo.getId());
			if (processoAlterar != null) {				
				if( processoAlterar.getTipoProcesso() == null  ) {
					processoAlterar.setTipoProcesso(TipoProcessoEnum.CRI);
				}				
				Processo proc = processoService.updateProcesso(processo);
				return  modelMapper.map(proc, ProcessoDTO.class);
			}
		}
		return new ProcessoDTO();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/processos", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	@Deprecated
	public List<Processo> recuperarProcessos() {
		throw new UnsupportedOperationException("Operação desabilitada.");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/processos/{idProcesso}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ProcessoDTO recuperarProcessoPorId(@PathVariable("idProcesso") Long idProcesso) {
		return convertToDto(processoService.recuperarProcessoPorId(idProcesso));
	}

	@RequestMapping(method = RequestMethod.POST, path = "/processos/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<ProcessoDTO> recuperarProcessosPorPagina(Pageable pagina) {
		Page<Processo> page = processoService.recuperarProcessosPorPagina(pagina);
		return page.map(this::convertToDto);		 
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/processos/pesquisar/numero/{numeroProcesso}/", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<ProcessoDTO> recuperarProcessosPorNumero(@PathVariable("numeroProcesso") String numeroProcesso) throws UnsupportedEncodingException {
		List<Processo> listaProcesso = processoService.recuperarProcessosPorNumero(numeroProcesso);
		return listaProcesso.stream().map(this::convertToDto).collect(Collectors.toList());		 
	}

	@RequestMapping(method = RequestMethod.GET, path = "/processos/numero/{numeroProcesso:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ProcessoDTO recuperarProcessoPorNumero(@PathVariable("numeroProcesso") String numeroProcesso) throws UnsupportedEncodingException {
		Processo processo = processoService.recuperarProcessoPorNumero(numeroProcesso);
		return convertToDto(processo);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/processos/pesquisar/procedimentoOrigem/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<String> recuperarProcedimentoOrigem(@RequestBody ProcessoProcedimentoOrigem processoProcedimentoOrigem) {
		List<String> listProcessoProcedimentoOrigem = processoService.recuperarNumeroProcessoPorProcedimentoOrigem(processoProcedimentoOrigem);
		return listProcessoProcedimentoOrigem;		
	}
	
	
	private Processo dtoToEntity(ProcessoDTO dto) {
	    return modelMapper.map(dto, Processo.class);
	}
	
	private ProcessoDTO convertToDto(Processo processo) {
		if(processo == null) {
			return null;
		}
	    return modelMapper.map(processo, ProcessoDTO.class);
	}
}