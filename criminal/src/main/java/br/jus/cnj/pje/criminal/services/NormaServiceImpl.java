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
import br.jus.cnj.pje.criminal.model.entities.Norma;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.NormaRepository;

@Service
public class NormaServiceImpl extends AbstractBaseService<Norma, Long>  implements NormaService {

	@Autowired
	private NormaRepository repository;

	@Override
	public List<Norma> recuperarNormasAtivas() {
		return repository.findByAtivoTrueOrderByNormaAsc();
	}

	@Override
	protected CustomRepository<Norma, Long> getRepository() {
		return this.repository;
	}
	
	public Norma criarNorma(Norma norma) {
		return repository.save(norma);
	}
	
	@Override
	public Norma recuperarNorma(Long idNorma) {
		return repository.getById(idNorma).get();
	}


	@Override
	public Long countNormas() {
		return repository.count();
	}

	@Override
	public Long countNormas(Norma norma) {
		return repository.count(getExemploNorma(norma));
	}

	@Override
	public Page<Norma> recuperarNormasPorPagina(Norma norma, Pageable pagina) {
		return repository.findAll(getExemploNorma(norma), pagina);
	}

	@Override
	public Page<Norma> recuperarNormasPorPagina(Pageable pagina) {
		return repository.findAll(pagina);
	}


	@Override
	public Norma recuperarNormaPorNumero(String numeroNorma) {
		return repository.findByNumeroNorma(numeroNorma);
	}

	public Norma alterarNorma(Norma norma) {
		return repository.save(norma);
	}

	@Override
	public Norma inativarNorma(Norma norma) {
		norma.setAtivo(false);
		return alterarNorma(norma);
	}
	
	private Example<Norma> getExemploNorma(Norma norma) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
				.withStringMatcher(StringMatcher.CONTAINING);
		return Example.of(norma, matcher);
	}

}
