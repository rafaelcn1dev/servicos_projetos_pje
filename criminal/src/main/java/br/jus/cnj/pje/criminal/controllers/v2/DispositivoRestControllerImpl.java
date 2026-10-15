package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.Dispositivo;
import br.jus.cnj.pje.criminal.services.DispositivoService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + DispositivoRestControllerImpl.PATH)
public class DispositivoRestControllerImpl extends AbstractRestController<Dispositivo, Long>{
	public static final String PATH = "/dispositivos";

	@Autowired
	private DispositivoService dispositivoService;
	
	
	@Override
	protected BaseService<Dispositivo, Long> getService() {
		return this.dispositivoService;
	} 
}
