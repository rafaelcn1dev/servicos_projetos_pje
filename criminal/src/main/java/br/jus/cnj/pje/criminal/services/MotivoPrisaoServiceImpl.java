package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.base.AbstractBaseService;
import br.jus.cnj.pje.criminal.model.entities.MotivoPrisao;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.MotivoPrisaoRepository;

@Service
public class MotivoPrisaoServiceImpl extends AbstractBaseService<MotivoPrisao, Long> implements MotivoPrisaoService{

	@Autowired
	private MotivoPrisaoRepository repository;
	
	@Override
	public List<MotivoPrisao> recuperarMotivosPrisaoAtivos() {
		return repository.findAllByAtivoTrue();
	}

	@Override
	protected CustomRepository<MotivoPrisao, Long> getRepository() {
		return this.repository;
	}
}
