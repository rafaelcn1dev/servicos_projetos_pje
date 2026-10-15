package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.MotivoExtincaoMedidaSocioeducativa;

public interface MotivoExtincaoMedidaSocioeducativaRepository extends CustomRepository<MotivoExtincaoMedidaSocioeducativa, Long> {
	
	public List<MotivoExtincaoMedidaSocioeducativa> findAllByAtivoTrue();

}
