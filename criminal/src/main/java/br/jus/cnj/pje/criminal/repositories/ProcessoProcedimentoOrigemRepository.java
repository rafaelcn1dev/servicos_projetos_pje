package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.jus.cnj.pje.criminal.model.entities.ProcessoProcedimentoOrigem;

public interface ProcessoProcedimentoOrigemRepository extends CustomRepository<ProcessoProcedimentoOrigem, Long>{

	@Query("select ppo from ProcessoProcedimentoOrigem ppo where ppo.processo.nrProcesso = :nrProcesso")
	List<ProcessoProcedimentoOrigem> findProcedimentoByNumeroProcesso(@Param("nrProcesso") String nrProcesso);
	
}
