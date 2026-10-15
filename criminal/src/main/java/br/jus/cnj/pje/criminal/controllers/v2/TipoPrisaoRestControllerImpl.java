package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.base.AbstractRestController;
import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoPrisao;
import br.jus.cnj.pje.criminal.services.TipoPrisaoService;
import br.jus.cnj.pje.criminal.util.ApiVersions;

@RestController
@RequestMapping(ApiVersions.V2 + TipoPrisaoRestControllerImpl.PATH)
public class TipoPrisaoRestControllerImpl extends AbstractRestController<TipoPrisao, Long>{

    public static final String PATH = "/tipos-prisoes";

    @Autowired
    private TipoPrisaoService tipoPrisaoService;

    @Override
    protected BaseService<TipoPrisao, Long> getService() {
        return this.tipoPrisaoService;
    }
}


