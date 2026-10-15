package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.TipoDispositivo;

public interface TipoDispositivoRepository extends CustomRepository<TipoDispositivo, Integer>{
	public List<TipoDispositivo> findAllByTipoDispositivoLikeIgnoreCaseAndAtivoTrue(String nome);
}
