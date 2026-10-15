package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.base.AbstractBaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoPrisao;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.TipoPrisaoRepository;

@Service
public class TipoPrisaoServiceImpl extends AbstractBaseService<TipoPrisao, Long> implements TipoPrisaoService{

	@Autowired
	private TipoPrisaoRepository repository;
	
	@Override
	public List<TipoPrisao> recuperarTiposPrisaoAtivos() {
		return repository.findAllByAtivoTrue();
	}

	@Override
	protected CustomRepository<TipoPrisao, Long> getRepository() {
		return this.repository;
	}
}
