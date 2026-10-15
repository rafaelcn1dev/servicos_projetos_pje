package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.base.AbstractBaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoSoltura;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.TipoSolturaRepository;

@Service
public class TipoSolturaServiceImpl extends AbstractBaseService<TipoSoltura, Long> implements TipoSolturaService{

	@Autowired
	private TipoSolturaRepository repository;

	@Override
	public List<TipoSoltura> recuperarTiposSolturaAtivos() {
		return repository.findAllByAtivoTrue();
	}

	@Override
	protected CustomRepository<TipoSoltura, Long> getRepository() {
		return repository;
	}

}
