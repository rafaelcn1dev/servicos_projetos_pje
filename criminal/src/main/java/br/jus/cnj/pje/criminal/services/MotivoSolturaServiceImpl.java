package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.base.AbstractBaseService;
import br.jus.cnj.pje.criminal.model.entities.MotivoSoltura;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.MotivoSolturaRepository;

@Service
public class MotivoSolturaServiceImpl extends AbstractBaseService<MotivoSoltura, Long> implements MotivoSolturaService{

	@Autowired
	private MotivoSolturaRepository repository;
	
	@Override
	public List<MotivoSoltura> recuperarMotivosSolturaAtivos() {
		return repository.findAllByAtivoTrue();
	}

	@Override
	protected CustomRepository<MotivoSoltura, Long> getRepository() {
		return this.repository;
	}
}
