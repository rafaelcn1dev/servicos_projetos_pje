package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.base.AbstractBaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoRemissao;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.TipoRemissaoRepository;

@Service
public class TipoRemissaoServiceImpl extends AbstractBaseService<TipoRemissao, Long> implements TipoRemissaoService {

	@Autowired
	private TipoRemissaoRepository repository;
	
	@Override
	public List<TipoRemissao> recuperarTiposRemissaoAtivos() {
		return repository.findAllByAtivoTrue();
	}

	@Override
	protected CustomRepository<TipoRemissao, Long> getRepository() {
		return repository;
	}

}