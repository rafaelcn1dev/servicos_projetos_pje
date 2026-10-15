package br.jus.cnj.pje.criminal.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.enums.TipoEventoCriminalEnum;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;
import br.jus.cnj.pje.criminal.repositories.TipoEventoCriminalRepository;

@Service
public class TipoInformacaoCriminalServiceImpl implements TipoInformacaoCriminalService {

	@Autowired
	private TipoEventoCriminalRepository repository;

	@Override
	public List<TipoEventoCriminal> recuperaTiposInformacaoCriminal() {
		TipoEventoCriminal tipoEventoCriminal = new TipoEventoCriminal();
		tipoEventoCriminal.setAtivo(true);
		
		return repository.findAll(this.getExemploTipoEventoCriminal(tipoEventoCriminal));
	}
	
	@Override
	public List<TipoEventoCriminal> recuperaTipoInformacaoCriminalPorMovimento(String codigoMovimento) {
		return repository.findByTipoEventoCriminalCodigoMovimento(codigoMovimento);
	}

	@Override
	public List<String> recuperaTiposInformacaoCriminalMovimento() {
		return repository.findAllTiposEventoCriminalMovimento();
	}
	
	@Override
	public TipoEventoCriminal recuperaTipoInformacaoCriminalInicioProcesso() {
		return repository.findByCodTipoIc(TipoEventoCriminalEnum.IND);
	}
	
	private Example<TipoEventoCriminal> getExemploTipoEventoCriminal(TipoEventoCriminal tipoEventoCriminal) throws NoSuchElementException{
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(StringMatcher.CONTAINING);
		Example<TipoEventoCriminal> exemploTipoEventoCriminal = Example.of(tipoEventoCriminal, matcher);
		
		return exemploTipoEventoCriminal;
	}
	
	@Override
	public TipoEventoCriminal recuperaTipoInformacaoCriminal(TipoEventoCriminalEnum eventoCriminalEnum) {
		return repository.findByCodTipoIc(eventoCriminalEnum);
	}

}
