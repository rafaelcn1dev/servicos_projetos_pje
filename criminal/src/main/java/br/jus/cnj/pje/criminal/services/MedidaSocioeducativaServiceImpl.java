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
import br.jus.cnj.pje.criminal.model.entities.MedidaSocioeducativa;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.MedidaSocioeducativaRepository;

@Service
public class MedidaSocioeducativaServiceImpl extends AbstractBaseService<MedidaSocioeducativa, Long> implements MedidaSocioeducativaService {

	@Autowired
	private MedidaSocioeducativaRepository repository;
	
	@Override
	public List<MedidaSocioeducativa> recuperarMedidasSocioeducativasAtivas() {
		return repository.findAllByAtivoTrue();
	}

	@Override
	public Page<MedidaSocioeducativa> recuperarPorPagina(MedidaSocioeducativa medidaSocioeducativa, Pageable pagina) {
		return pesquisarRecurso(getExemploMedidaSocioeducativa(medidaSocioeducativa), pagina);
	}

	@Override
	public Long count() {
		return repository.count();
	}

	@Override
	public Long count(MedidaSocioeducativa medidaSocioeducativa) {
		return repository.count(getExemploMedidaSocioeducativa(medidaSocioeducativa));
	}

	private Example<MedidaSocioeducativa> getExemploMedidaSocioeducativa(MedidaSocioeducativa medidaSocioeducativa) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return Example.of(medidaSocioeducativa, matcher);
	}

	@Override
	protected CustomRepository<MedidaSocioeducativa, Long> getRepository() {
		return repository;
	}
	
}
