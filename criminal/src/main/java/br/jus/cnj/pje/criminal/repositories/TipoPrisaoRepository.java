package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.TipoPrisao;

public interface TipoPrisaoRepository extends CustomRepository<TipoPrisao, Long> {
	
	public List<TipoPrisao> findAllByAtivoTrue();

}
