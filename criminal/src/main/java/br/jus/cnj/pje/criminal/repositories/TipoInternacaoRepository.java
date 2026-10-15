package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.TipoInternacao;

public interface TipoInternacaoRepository extends CustomRepository<TipoInternacao, Long> {
	
	public List<TipoInternacao> findAllByAtivoTrue();

}
