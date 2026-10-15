package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.OrgaoProcedimentoOriginario;
import br.jus.cnj.pje.criminal.services.OrgaoProcedimentoOriginarioService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + OrgaoProcedimentoOriginarioRestControllerImpl.PATH)
public class OrgaoProcedimentoOriginarioRestControllerImpl extends AbstractRestController<OrgaoProcedimentoOriginario, Long>{

    public static final String PATH = "/orgaos-procedimentos-origens";
    
    @Autowired
    private OrgaoProcedimentoOriginarioService orgaoProcedimentoOriginarioService;
	
	@Override
	protected BaseService<OrgaoProcedimentoOriginario, Long> getService() {
		return this.orgaoProcedimentoOriginarioService;
	}

}
