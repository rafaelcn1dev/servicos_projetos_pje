package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.TipoRemissao;

public interface TipoRemissaoRepository extends CustomRepository<TipoRemissao, Long> {
	
	public List<TipoRemissao> findAllByAtivoTrue();

}
