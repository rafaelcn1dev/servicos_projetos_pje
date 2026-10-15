package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.MotivoRetornoEvasao;

public interface MotivoRetornoEvasaoRepository extends CustomRepository<MotivoRetornoEvasao, Long> {
	
	public List<MotivoRetornoEvasao> findAllByAtivoTrue();

}
