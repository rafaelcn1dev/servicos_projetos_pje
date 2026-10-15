package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.TipoSoltura;

public interface TipoSolturaRepository extends CustomRepository<TipoSoltura, Long>{
	
	public List<TipoSoltura> findAllByAtivoTrue();

}
