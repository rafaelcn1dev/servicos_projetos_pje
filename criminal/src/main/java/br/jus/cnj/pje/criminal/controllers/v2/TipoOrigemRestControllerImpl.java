package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;
import br.jus.cnj.pje.criminal.services.TipoOrigemService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + TipoOrigemRestControllerImpl.PATH)
public class TipoOrigemRestControllerImpl extends AbstractRestController<TipoOrigem, Long>{

    public static final String PATH = "/tipos-origens";

    @Autowired
    private TipoOrigemService tipoOrigemService;

    @Override
    protected BaseService<TipoOrigem, Long> getService() {
        return this.tipoOrigemService;
    }
}
