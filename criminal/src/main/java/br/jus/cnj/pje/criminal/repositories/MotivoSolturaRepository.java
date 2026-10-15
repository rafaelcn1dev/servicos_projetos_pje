package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.MotivoSoltura;

public interface MotivoSolturaRepository extends CustomRepository<MotivoSoltura, Long>{
	
	public List<MotivoSoltura> findAllByAtivoTrue();

}
