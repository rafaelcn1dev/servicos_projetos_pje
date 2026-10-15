package br.jus.cnj.pje.criminal.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.model.entities.ProcessoProcedimentoOrigem;
import br.jus.cnj.pje.criminal.repositories.ProcessoProcedimentoOrigemRepository;

@Service
public class ProcessoProcedimentoOrigemServiceImpl implements ProcessoProcedimentoOrigemService {

	@Autowired
	private ProcessoProcedimentoOrigemRepository procedimentoOrigemRepository;

	@Autowired
	private ProcessoService processoService;

	@Override
	public ProcessoProcedimentoOrigem salvarProcessoProcedimentoOrigem(
			ProcessoProcedimentoOrigem processoProcedimentoOrigem) throws UnsupportedEncodingException {
		Processo processo = processoService
				.recuperarProcessoPorNumero(processoProcedimentoOrigem.getProcesso().getNrProcesso());
		processoProcedimentoOrigem.setProcesso(processo);
		return procedimentoOrigemRepository.save(processoProcedimentoOrigem);
	}

	@Override
	public ProcessoProcedimentoOrigem inativarProcessoProcedimentoOrigem(
			ProcessoProcedimentoOrigem processoProcedimentoOrigem) {
		processoProcedimentoOrigem.setAtivo(false);
		return procedimentoOrigemRepository.save(processoProcedimentoOrigem);
	}

	@Override
	public List<ProcessoProcedimentoOrigem> recuperarProcedimentosOrigemPorProcesso(String nrProcesso) {
		return procedimentoOrigemRepository.findProcedimentoByNumeroProcesso(nrProcesso);
	}

	@Override
	public List<ProcessoProcedimentoOrigem> recuperarTodosProcedimentosOrigem() {
		return procedimentoOrigemRepository.findAll();
	}

	@Override
	public ProcessoProcedimentoOrigem recuperarProcedimentosPorId(Long id) {
		return procedimentoOrigemRepository.getById(id).get();
	}

	@Override
	public void deletarProcessoProcedimentoOrigem(Long idProcedimento) {
		procedimentoOrigemRepository.deleteById(idProcedimento);
	}
	
	@Override
	public List<ProcessoProcedimentoOrigem> recuperarProcessoProcedimentoOrigem(ProcessoProcedimentoOrigem ppo) {
		return procedimentoOrigemRepository.findAll(this.getExemploProcessoProcedimentoOrigem(ppo));
	}

	private Example<ProcessoProcedimentoOrigem> getExemploProcessoProcedimentoOrigem(ProcessoProcedimentoOrigem ppo){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(StringMatcher.CONTAINING);
		Example<ProcessoProcedimentoOrigem> exemploProcessoProcedimentoOrigem = Example.of(ppo, matcher);
		
		return exemploProcessoProcedimentoOrigem;
	}

}
