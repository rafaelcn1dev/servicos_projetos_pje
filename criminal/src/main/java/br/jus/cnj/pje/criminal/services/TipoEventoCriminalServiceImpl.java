package br.jus.cnj.pje.criminal.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.base.AbstractBaseService;
import br.jus.cnj.pje.criminal.enums.TipoProcessoEnum;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.TipoEventoCriminalRepository;

@Service
public class TipoEventoCriminalServiceImpl extends AbstractBaseService<TipoEventoCriminal, Long> implements TipoEventoCriminalService{

	@Autowired
	private TipoEventoCriminalRepository repository;
	
	@Override
	protected CustomRepository<TipoEventoCriminal, Long> getRepository() {
		return this.repository;
	}
	
	public List<TipoEventoCriminal> recuperarTiposEventoCriminalAtivos() {
		return repository.findByAtivoTrue();
	}

	@Override
	public List<TipoEventoCriminal> recuperaTiposEventoCriminalPorTipoProcesso(TipoProcessoEnum tipoProcesso) {
		return repository.findByTipoProcessoIn(Arrays.asList(tipoProcesso, TipoProcessoEnum.T));
	}
	
	@Override
	public List<TipoEventoCriminal> recuperaTiposEventoCriminalAtivosPorTipoProcesso(TipoProcessoEnum tipoProcesso) {
		return repository.findByAtivoTrueAndTipoProcessoIn(Arrays.asList(tipoProcesso, TipoProcessoEnum.T));
	}
	
	@Override
	public List<TipoEventoCriminal> recuperaTiposEventoCriminalInativosPorTipoProcesso(TipoProcessoEnum tipoProcesso) {
		return repository.findByAtivoFalseAndTipoProcessoIn(Arrays.asList(tipoProcesso, TipoProcessoEnum.T));
	}
	
	@Override
	public Page<TipoEventoCriminal> recuperarPorPagina(TipoEventoCriminal tipoEventoCriminal, Pageable pagina) {
		return repository.findAll(getExemploTipoEventoCriminal(tipoEventoCriminal), pagina);
	}

	@Override
	public Long count() {
		return repository.count();
	}

	@Override
	public Long count(TipoEventoCriminal tipoEventoCriminal) {
		return repository.count(getExemploTipoEventoCriminal(tipoEventoCriminal));
	}

	private Example<TipoEventoCriminal> getExemploTipoEventoCriminal(TipoEventoCriminal tipoEventoCriminal) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return Example.of(tipoEventoCriminal, matcher);
	}
	
}
