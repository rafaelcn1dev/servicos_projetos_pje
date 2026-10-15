package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.MedidaSocioeducativa;

public interface MedidaSocioeducativaRepository extends CustomRepository<MedidaSocioeducativa, Long> {
	
	public List<MedidaSocioeducativa> findAllByAtivoTrue();

}
