package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.base.AbstractBaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoInternacao;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.TipoInternacaoRepository;

@Service
public class TipoInternacaoServiceImpl extends AbstractBaseService<TipoInternacao, Long> implements TipoInternacaoService {

	@Autowired
	private TipoInternacaoRepository repository;
	
	@Override
	public List<TipoInternacao> recuperarTiposInternacaoAtivos() {
		return repository.findAllByAtivoTrue();
	}

	@Override
	protected CustomRepository<TipoInternacao, Long> getRepository() {
		return repository;
	}

}
