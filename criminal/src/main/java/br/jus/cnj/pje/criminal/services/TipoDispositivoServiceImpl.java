package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.model.entities.TipoDispositivo;
import br.jus.cnj.pje.criminal.repositories.TipoDispositivoRepository;

@Service
public class TipoDispositivoServiceImpl implements TipoDispositivoService{

	@Autowired
	private TipoDispositivoRepository repository;
	
	@Override
	public List<TipoDispositivo> recuperarTiposDispositivos() {
		return this.repository.findAll();
	}
	
	public List<TipoDispositivo> recuperarTiposDispositivosPorNome(String nome){
		return this.repository.findAllByTipoDispositivoLikeIgnoreCaseAndAtivoTrue("%" + nome + "%");
	}
	

}
