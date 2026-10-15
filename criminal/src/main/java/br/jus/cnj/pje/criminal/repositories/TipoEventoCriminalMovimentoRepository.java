package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminalMovimento;

public interface TipoEventoCriminalMovimentoRepository extends CustomRepository<TipoEventoCriminalMovimento, Integer> {
	
	List<TipoEventoCriminalMovimento> findByCodMovimento(String codMovimento);

}
