package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.Norma;

public interface NormaRepository extends CustomRepository<Norma, Long> {

	List<Norma> findByAtivoTrueOrderByNormaAsc();
	Norma findByNumeroNorma(String numeroNorma);

}
