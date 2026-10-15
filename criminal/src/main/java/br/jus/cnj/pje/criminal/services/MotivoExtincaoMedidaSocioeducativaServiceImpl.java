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
import br.jus.cnj.pje.criminal.model.entities.MotivoExtincaoMedidaSocioeducativa;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.MotivoExtincaoMedidaSocioeducativaRepository;

@Service
public class MotivoExtincaoMedidaSocioeducativaServiceImpl extends AbstractBaseService<MotivoExtincaoMedidaSocioeducativa, Long> implements MotivoExtincaoMedidaSocioeducativaService {

	@Autowired
	private MotivoExtincaoMedidaSocioeducativaRepository repository;
	
	@Override
	public List<MotivoExtincaoMedidaSocioeducativa> recuperarMotivosExtincaoMedidaSocioeducativaAtivas() {
		return repository.findAllByAtivoTrue();
	}

	@Override
	public Page<MotivoExtincaoMedidaSocioeducativa> recuperarPorPagina(MotivoExtincaoMedidaSocioeducativa motivoExtincaoMedidaSocioeducativa, Pageable pagina) {
		return repository.findAll(getExemploMotivoExtincaoMedidaSocioeducativa(motivoExtincaoMedidaSocioeducativa), pagina);
	}

	@Override
	public Long count() {
		return repository.count();
	}

	@Override
	public Long count(MotivoExtincaoMedidaSocioeducativa motivoExtincaoMedidaSocioeducativa) {
		return repository.count(getExemploMotivoExtincaoMedidaSocioeducativa(motivoExtincaoMedidaSocioeducativa));
	}

	private Example<MotivoExtincaoMedidaSocioeducativa> getExemploMotivoExtincaoMedidaSocioeducativa(MotivoExtincaoMedidaSocioeducativa motivoExtincaoMedidaSocioeducativa) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return Example.of(motivoExtincaoMedidaSocioeducativa, matcher);
	}

	@Override
	protected CustomRepository<MotivoExtincaoMedidaSocioeducativa, Long> getRepository() {
		return repository;
	}
	
}
