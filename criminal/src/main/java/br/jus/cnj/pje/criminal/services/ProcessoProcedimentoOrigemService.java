package br.jus.cnj.pje.criminal.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.ProcessoProcedimentoOrigem;

public interface ProcessoProcedimentoOrigemService {

	ProcessoProcedimentoOrigem salvarProcessoProcedimentoOrigem(ProcessoProcedimentoOrigem processoProcedimentoOrigem) throws UnsupportedEncodingException ;

	ProcessoProcedimentoOrigem inativarProcessoProcedimentoOrigem(ProcessoProcedimentoOrigem processoProcedimentoOrigem);

	List<ProcessoProcedimentoOrigem> recuperarProcedimentosOrigemPorProcesso(String nrProcesso);

	List<ProcessoProcedimentoOrigem> recuperarTodosProcedimentosOrigem();

	ProcessoProcedimentoOrigem recuperarProcedimentosPorId(Long id);

	void deletarProcessoProcedimentoOrigem(Long idProcedimento);

	List<ProcessoProcedimentoOrigem> recuperarProcessoProcedimentoOrigem(ProcessoProcedimentoOrigem ppo);

}
