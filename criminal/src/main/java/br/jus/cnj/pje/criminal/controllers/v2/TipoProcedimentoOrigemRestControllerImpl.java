package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoProcedimentoOrigem;
import br.jus.cnj.pje.criminal.services.TipoProcedimentoOrigemService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + TipoProcedimentoOrigemRestControllerImpl.PATH)
public class TipoProcedimentoOrigemRestControllerImpl extends AbstractRestController<TipoProcedimentoOrigem, Long>{

	public static final String PATH = "/tipos-procedimentos-origens";
	
	@Autowired
	private TipoProcedimentoOrigemService tipoProcedimentoOrigemService;
	
	@Override
	protected BaseService<TipoProcedimentoOrigem, Long> getService() {
		return this.tipoProcedimentoOrigemService;
	}

}
