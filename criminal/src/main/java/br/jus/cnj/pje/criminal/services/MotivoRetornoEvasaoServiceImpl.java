package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.base.AbstractBaseService;
import br.jus.cnj.pje.criminal.model.entities.MotivoRetornoEvasao;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.MotivoRetornoEvasaoRepository;

@Service
public class MotivoRetornoEvasaoServiceImpl extends AbstractBaseService<MotivoRetornoEvasao, Long> implements MotivoRetornoEvasaoService {

	@Autowired
	private MotivoRetornoEvasaoRepository repository;
	
	@Override
	public List<MotivoRetornoEvasao> recuperarMotivosRetornoEvasaoAtivos() {
		return repository.findAllByAtivoTrue();
	}

	@Override
	public Page<MotivoRetornoEvasao> recuperarPorPagina(MotivoRetornoEvasao motivoRetornoEvasao, Pageable pagina) {
		return repository.findAll(getExemploMotivoRetornoEvasao(motivoRetornoEvasao), pagina);
	}

	@Override
	public Long count() {
		return repository.count();
	}

	@Override
	public Long count(MotivoRetornoEvasao motivoRetornoEvasao) {
		return repository.count(getExemploMotivoRetornoEvasao(motivoRetornoEvasao));
	}

	private Example<MotivoRetornoEvasao> getExemploMotivoRetornoEvasao(MotivoRetornoEvasao motivoRetornoEvasao) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return Example.of(motivoRetornoEvasao, matcher);
	}

	@Override
	protected CustomRepository<MotivoRetornoEvasao, Long> getRepository() {
		return repository;
	}
	
}
