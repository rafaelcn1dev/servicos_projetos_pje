package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.MotivoPrisao;
import br.jus.cnj.pje.criminal.services.MotivoPrisaoService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + MotivoPrisaoRestControllerImpl.PATH)
public class MotivoPrisaoRestControllerImpl extends AbstractRestController<MotivoPrisao, Long>{

    public static final String PATH = "/motivos-prisoes";

    @Autowired
    private MotivoPrisaoService motivoPrisaoService;

    @Override
    protected BaseService<MotivoPrisao, Long> getService() {
        return this.motivoPrisaoService;
    }

}
