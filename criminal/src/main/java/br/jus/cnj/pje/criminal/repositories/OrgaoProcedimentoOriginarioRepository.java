package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.jus.cnj.pje.criminal.model.entities.OrgaoProcedimentoOriginario;
import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;

public interface OrgaoProcedimentoOriginarioRepository extends CustomRepository<OrgaoProcedimentoOriginario, Long> {

	List<OrgaoProcedimentoOriginario> findByDsNomeOrgaoLikeIgnoreCase(String string);

	List<OrgaoProcedimentoOriginario> findByTipoOrigem(TipoOrigem tipoOrigem);

	@Query("select opo from OrgaoProcedimentoOriginario opo where opo.tipoOrigem.id = :idTipoOrigem and opo.municipio.uf = :uf")
	List<OrgaoProcedimentoOriginario> findByTipoOrigemAndUf(@Param("idTipoOrigem") Long idTipoOrigem,
			@Param("uf") String uf);

}
