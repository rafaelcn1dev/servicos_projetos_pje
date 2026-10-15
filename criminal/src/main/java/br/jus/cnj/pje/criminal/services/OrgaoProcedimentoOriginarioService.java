package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.OrgaoProcedimentoOriginario;
import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;

public interface OrgaoProcedimentoOriginarioService extends BaseService<OrgaoProcedimentoOriginario, Long>{

	OrgaoProcedimentoOriginario saveOrgaoProcedimentoOriginario(OrgaoProcedimentoOriginario orgaoProcedimentoOriginario);
	Page<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginario(Pageable pagina);
	Page<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginario(OrgaoProcedimentoOriginario opo, Pageable pagina);
	List<OrgaoProcedimentoOriginario> recuperarOrgaoProcedimentoOriginario();
	OrgaoProcedimentoOriginario recuperarOrgaoProcedimentoOriginario(Long idOrgaoProcedimentoOriginario);
	OrgaoProcedimentoOriginario inativarOrgaoProcedimentoOriginario(OrgaoProcedimentoOriginario orgaoProcedimentoOriginario);
	List<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginarioPorNome(String nomeOrgaoProcedimento);
	List<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginarioPorTipoOrigem(TipoOrigem idTipoOrigem);
	Long countOrgaoProcedimentoOriginario(OrgaoProcedimentoOriginario orgaoProcedimentoOriginario);
	Long countOrgaoProcedimentoOriginario();
	List<OrgaoProcedimentoOriginario> recuperarOrgaosPorTipoOrigemAndUf(Long idTipoOrigem, String uf);

}
