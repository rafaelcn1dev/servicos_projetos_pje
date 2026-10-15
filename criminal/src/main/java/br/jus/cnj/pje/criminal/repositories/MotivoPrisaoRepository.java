package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.MotivoPrisao;

public interface MotivoPrisaoRepository extends CustomRepository<MotivoPrisao, Long>{
	public List<MotivoPrisao> findAllByAtivoTrue();
}
