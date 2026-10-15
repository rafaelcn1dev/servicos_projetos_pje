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
import br.jus.cnj.pje.criminal.model.entities.TipoRecurso;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.TipoRecursoRepository;

@Service
public class TipoRecursoServiceImpl extends AbstractBaseService<TipoRecurso, Long> implements TipoRecursoService {

	@Autowired
	private TipoRecursoRepository repository;

	@Override
	public TipoRecurso criarTipoRecurso(TipoRecurso tipoRecurso) {
		return repository.save(tipoRecurso);
	}
	
	@Override
	protected CustomRepository<TipoRecurso, Long> getRepository() {
		return this.repository;
	}
	
	@Override
	public Long countTiposRecurso() { 
		return repository.count();
	}

	@Override
	public Long countTiposRecurso(TipoRecurso tipoRecurso) {
		return repository.count(getExemploTipoRecurso(tipoRecurso));
	}

	@Override
	public Page<TipoRecurso> recuperarTiposRecursoPorPagina(TipoRecurso tipoRecurso, Pageable pagina) {
		return repository.findAll(getExemploTipoRecurso(tipoRecurso), pagina);
	}

	@Override
	public Page<TipoRecurso> recuperarTiposRecursoPorPagina(Pageable pagina) {
		return repository.findAll(pagina);
	}
	
	private Example<TipoRecurso> getExemploTipoRecurso(TipoRecurso tipoRecurso) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
				.withStringMatcher(StringMatcher.CONTAINING);
		return Example.of(tipoRecurso, matcher);
	}

	@Override
	public TipoRecurso recuperarTipoRecurso(Long idTipoRecurso) {
		return repository.getById(idTipoRecurso).get();
	}

	@Override
	public TipoRecurso alterarTipoRecurso(TipoRecurso tipoRecurso) {
		return repository.save(tipoRecurso);
	}

	@Override
	public TipoRecurso inativarTipoRecurso(TipoRecurso tipoRecurso) {
		tipoRecurso.setAtivo(false);
		return alterarTipoRecurso(tipoRecurso);
	}
	
	@Override
	public List<TipoRecurso> recuperarTiposRecursosAtivos() {
		return repository.findAllByAtivoTrue();
	}

}
