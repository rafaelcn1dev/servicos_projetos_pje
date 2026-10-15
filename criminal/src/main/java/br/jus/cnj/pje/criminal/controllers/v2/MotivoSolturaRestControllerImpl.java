package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.MotivoSoltura;
import br.jus.cnj.pje.criminal.services.MotivoSolturaService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + MotivoSolturaRestControllerImpl.PATH)
public class MotivoSolturaRestControllerImpl extends AbstractRestController<MotivoSoltura, Long>{
	
	public static final String PATH = "/motivos-solturas";

	@Autowired
	private MotivoSolturaService motivoSolturaService;
	
	@Override
	protected BaseService<MotivoSoltura, Long> getService() {
		return this.motivoSolturaService;
	}

}
