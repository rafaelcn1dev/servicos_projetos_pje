package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.model.entities.MotivoSuspensaoProcesso;
import br.jus.cnj.pje.criminal.repositories.MotivoSuspensaoProcessoRepository;

@Service
public class MotivoSuspensaoProcessoServiceImpl implements MotivoSuspensaoProcessoService {

	@Autowired
	private MotivoSuspensaoProcessoRepository repository;

	@Override
	public MotivoSuspensaoProcesso criarMotivoSuspensaoProcesso(MotivoSuspensaoProcesso motivoSuspensaoProcesso) {
		return repository.save(motivoSuspensaoProcesso);
	}

	@Override
	public List<MotivoSuspensaoProcesso> recuperarMotivosSuspensaoProcessoAtivos() {
		return repository.findAllByAtivoTrue();
	}

	@Override
	public Long countMotivosSuspensaoProcesso() {
		return repository.count();
	}

	@Override
	public Long countMotivosSuspensaoProcesso(MotivoSuspensaoProcesso motivoSuspensaoProcesso) {
		return repository.count(getExemploMotivoSuspensaoProcesso(motivoSuspensaoProcesso));
	}

	@Override
	public Page<MotivoSuspensaoProcesso> recuperarMotivosSuspensaoProcessoPorPagina(
			MotivoSuspensaoProcesso motivoSuspensaoProcesso, Pageable pagina) {
		return repository.findAll(getExemploMotivoSuspensaoProcesso(motivoSuspensaoProcesso), pagina);
	}

	@Override
	public Page<MotivoSuspensaoProcesso> recuperarMotivosSuspensaoProcessoPorPagina(Pageable pagina) {
		return repository.findAll(pagina);
	}

	private Example<MotivoSuspensaoProcesso> getExemploMotivoSuspensaoProcesso(
			MotivoSuspensaoProcesso motivoSuspensaoProcesso) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
				.withStringMatcher(StringMatcher.CONTAINING);
		return Example.of(motivoSuspensaoProcesso, matcher);
	}

	@Override
	public MotivoSuspensaoProcesso recuperarMotivoSuspensaoProcesso(Long idMotivoSuspensaoProcesso) {
		return repository.getById(idMotivoSuspensaoProcesso).get();
	}

	@Override
	public MotivoSuspensaoProcesso alterarMotivoSuspensaoProcesso(MotivoSuspensaoProcesso motivoSuspensaoProcesso) {
		return repository.save(motivoSuspensaoProcesso);
	}

	@Override
	public MotivoSuspensaoProcesso inativarMotivoSuspensaoProcesso(MotivoSuspensaoProcesso motivoSuspensaoProcesso) {
		motivoSuspensaoProcesso.setAtivo(false);
		return alterarMotivoSuspensaoProcesso(motivoSuspensaoProcesso);
	}
	
}
