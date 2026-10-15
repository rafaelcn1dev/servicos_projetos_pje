package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.jus.cnj.pje.criminal.model.entities.TipoProcedimentoOrigem;

public interface TipoProcedimentoOrigemRepository extends CustomRepository<TipoProcedimentoOrigem, Long> {

	public List<TipoProcedimentoOrigem> findByDsTipoProcedimentoLikeIgnoreCase(String tipoProcedimentoOrigem);

	@Query("select o from TipoProcedimentoOrigem o join o.tipoOrigemList tol where tol.id = :idTipoOrigem and o.ativo = true")
	public List<TipoProcedimentoOrigem> findByTipoOrigem(@Param("idTipoOrigem") Long idTipoOrigem);

}


