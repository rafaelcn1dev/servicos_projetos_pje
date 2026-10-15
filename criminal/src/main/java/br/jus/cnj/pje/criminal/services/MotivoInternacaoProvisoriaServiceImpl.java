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
import br.jus.cnj.pje.criminal.model.entities.MotivoInternacaoProvisoria;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.MotivoInternacaoProvisoriaRepository;

@Service
public class MotivoInternacaoProvisoriaServiceImpl extends AbstractBaseService<MotivoInternacaoProvisoria, Long> implements MotivoInternacaoProvisoriaService {

	@Autowired
	private MotivoInternacaoProvisoriaRepository repository;
	
	@Override
	public List<MotivoInternacaoProvisoria> recuperarMotivosInternacaoProvisoriaAtivos() {
		return repository.findAllByAtivoTrue();
	}

	@Override
	public Page<MotivoInternacaoProvisoria> recuperarPorPagina(MotivoInternacaoProvisoria motivoInternacaoProvisoria, Pageable pagina) {
		return repository.findAll(getExemploMotivoInternacaoProvisoria(motivoInternacaoProvisoria), pagina);
	}

	@Override
	public Long count() {
		return repository.count();
	}

	@Override
	public Long count(MotivoInternacaoProvisoria motivoInternacaoProvisoria) {
		return repository.count(getExemploMotivoInternacaoProvisoria(motivoInternacaoProvisoria));
	}

	private Example<MotivoInternacaoProvisoria> getExemploMotivoInternacaoProvisoria(MotivoInternacaoProvisoria motivoInternacaoProvisoria) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return Example.of(motivoInternacaoProvisoria, matcher);
	}

	@Override
	protected CustomRepository<MotivoInternacaoProvisoria, Long> getRepository() {
		return repository;
	}
	
}
