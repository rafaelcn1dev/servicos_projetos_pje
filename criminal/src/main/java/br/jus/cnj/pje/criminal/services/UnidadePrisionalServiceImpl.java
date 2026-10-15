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
import br.jus.cnj.pje.criminal.model.entities.Municipio;
import br.jus.cnj.pje.criminal.model.entities.UnidadePrisional;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.MunicipioRepository;
import br.jus.cnj.pje.criminal.repositories.UnidadePrisionalRepository;

@Service
public class UnidadePrisionalServiceImpl extends AbstractBaseService<UnidadePrisional, Long> implements UnidadePrisionalService {

	@Autowired
	private UnidadePrisionalRepository unidadePrisionalRepository;  
	
	@Autowired
	private MunicipioRepository municipioRepository;
	
	@Override
	public UnidadePrisional persistUnidadePrisional(UnidadePrisional unidadePrisional) {
		Municipio municipio = null;
		if (unidadePrisional.getMunicipio().getCodigoIbge() != null) {
			municipio = municipioRepository
					.findByCodigoIbge(unidadePrisional.getMunicipio().getCodigoIbge());
		}
		if (municipio == null) {
			municipio = municipioRepository.findByMunicipioAndUf(
					unidadePrisional.getMunicipio().getMunicipio(),
					unidadePrisional.getMunicipio().getUf());
			if (municipio == null) {
				municipio = municipioRepository.save(unidadePrisional.getMunicipio());
				unidadePrisional.setMunicipio(municipio);
			} else {
				unidadePrisional.setMunicipio(municipio);
			}
		} else {
			unidadePrisional.setMunicipio(municipio);
		}
		return unidadePrisionalRepository.save(unidadePrisional);
	}

	@Override
	public List<UnidadePrisional> recuperarUnidadesPrisionais() {
		return unidadePrisionalRepository.findAll();
	}

	@Override
	public UnidadePrisional recuperarUnidadePrisional(Long idUnidadePrisional) {
		return unidadePrisionalRepository.getById(idUnidadePrisional).get();
	}

	@Override
	public void removerUnidadePrisional(UnidadePrisional unidadePrisional) {
		unidadePrisionalRepository.delete(unidadePrisional);
	}

	@Override
	public Page<UnidadePrisional> recuperarUnidadesPrisionaisPorPagina(UnidadePrisional unidadePrisional, Pageable pagina) {
		return unidadePrisionalRepository.findAll(this.getExemploUnidadePrisional(unidadePrisional), pagina);
	}
	
	@Override
	public Page<UnidadePrisional> recuperarUnidadesPrisionaisPorPagina(Pageable pagina) {
		return unidadePrisionalRepository.findAll(pagina);
	}
	
	@Override
	public Long countUnidadePrisional(UnidadePrisional unidadePrisional) {
		return unidadePrisionalRepository.count(this.getExemploUnidadePrisional(unidadePrisional));
	}
	
	@Override
	public Long countUnidadePrisional() {
		return this.unidadePrisionalRepository.count();
	}
	
	@Override
	public List<UnidadePrisional> recuperarUnidadePrisionalPorSiglaUf(String uf) {
		return this.unidadePrisionalRepository.findByMunicipioUf(uf);
	}

	@Override
	public UnidadePrisional inativarUnidadePrisional(UnidadePrisional unidadePrisional) {

		unidadePrisional = this.unidadePrisionalRepository.getById(unidadePrisional.getId()).get();
		unidadePrisional.setAtivo(false);
		unidadePrisional = this.unidadePrisionalRepository.save(unidadePrisional);

		return unidadePrisional;
	}

	private Example<UnidadePrisional> getExemploUnidadePrisional(UnidadePrisional unidadePrisional){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<UnidadePrisional> exemploUnidadePrisional = Example.of(unidadePrisional, matcher);
		
		return exemploUnidadePrisional;
	}


	@Override
	protected CustomRepository<UnidadePrisional, Long> getRepository() {
		return this.unidadePrisionalRepository;
	}
}
