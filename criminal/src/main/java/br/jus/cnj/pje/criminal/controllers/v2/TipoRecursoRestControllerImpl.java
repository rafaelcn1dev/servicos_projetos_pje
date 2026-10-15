package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoRecurso;
import br.jus.cnj.pje.criminal.services.TipoRecursoService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + TipoRecursoRestControllerImpl.PATH)
public class TipoRecursoRestControllerImpl extends AbstractRestController<TipoRecurso, Long>{

	public static final String PATH = "/tipos-recursos";

	@Autowired
	private TipoRecursoService tipoRecursoService;
	
	@Override
	protected BaseService<TipoRecurso, Long> getService() {
		return this.tipoRecursoService;
	}
	
}
