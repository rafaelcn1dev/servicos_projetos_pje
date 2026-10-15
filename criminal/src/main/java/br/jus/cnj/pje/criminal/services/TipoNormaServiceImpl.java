package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.model.entities.TipoNorma;
import br.jus.cnj.pje.criminal.repositories.TipoNormaRepository;

@Service
public class TipoNormaServiceImpl implements TipoNormaService {

	@Autowired
	private TipoNormaRepository tipoNormaRepository;  
	
	@Override
	public List<TipoNorma> recuperarTiposNorma() {
		return tipoNormaRepository.findAll();
	}

	@Override
	public Long countTipoNorma(TipoNorma tipoNorma) {
		return tipoNormaRepository.count(this.getExemploTipoNorma(tipoNorma));
	}
	
	@Override
	public Long countTipoNorma() {
		return this.tipoNormaRepository.count();
	}
	
	private Example<TipoNorma> getExemploTipoNorma(TipoNorma tipoNorma){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(StringMatcher.CONTAINING);
		Example<TipoNorma> exemploTipoNorma = Example.of(tipoNorma, matcher);
		return exemploTipoNorma;
	}

}
