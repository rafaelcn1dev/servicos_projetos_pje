package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.TipoRecurso;

public interface TipoRecursoRepository extends CustomRepository<TipoRecurso, Long> {
	public List<TipoRecurso> findAllByAtivoTrue();
}
