package br.jus.cnj.pje.criminal.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.error.CriminalBusinessException;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.model.entities.ProcessoProcedimentoOrigem;

public interface ProcessoService {

	Processo criarNovoProcesso(Processo processo) throws CriminalBusinessException;

	Processo updateProcesso(Processo processo);

	List<Processo> recuperarProcessos();

	Processo recuperarProcessoPorId(Long idProcesso);

	Page<Processo> recuperarProcessosPorPagina(Pageable pageable);
	
	List<Processo> recuperarProcessosPorNumero(String numeroProcesso) throws UnsupportedEncodingException;
	
	Processo recuperarProcessoPorNumero(String numeroProcesso) throws UnsupportedEncodingException;
	
	Processo recuperarProcessoPorNumero(String numeroProcesso, boolean sistema) throws UnsupportedEncodingException;

	List<String> recuperarNumeroProcessoPorProcedimentoOrigem(ProcessoProcedimentoOrigem processoProcedimentoOrigem);
	
	void validarNovoProcesso(@Valid Processo processo);


}
