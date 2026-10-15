package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoInternacao;
import br.jus.cnj.pje.criminal.services.TipoInternacaoService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + TipoInternacaoRestControllerImpl.PATH)
public class TipoInternacaoRestControllerImpl extends AbstractRestController<TipoInternacao, Long> {

	public static final String PATH = "/tipos-internacao";
	
	@Autowired
	private TipoInternacaoService service;
	
	@Override
	protected BaseService<TipoInternacao, Long> getService() {
		return service;
	}	
	
}
