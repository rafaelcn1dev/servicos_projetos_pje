package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoRemissao;
import br.jus.cnj.pje.criminal.services.TipoRemissaoService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + TipoRemissaoControllerImpl.PATH)
public class TipoRemissaoControllerImpl extends AbstractRestController<TipoRemissao, Long> {

	public static final String PATH = "/tipos-remissao";
	
	@Autowired
	private TipoRemissaoService service;
	
	@Override
	protected BaseService<TipoRemissao, Long> getService() {
		return service;
	}	
	
}
