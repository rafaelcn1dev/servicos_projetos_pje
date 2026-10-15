package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.model.beans.PjeUser;
import br.jus.cnj.pje.criminal.services.UsuarioAutenticadoService;
import br.jus.cnj.pje.criminal.util.ApiVersions;
import br.jus.cnj.pje.pjecommons.model.services.PjeResponse;
import br.jus.cnj.pje.pjecommons.model.services.PjeResponseStatus;

@RestController
@RequestMapping(ApiVersions.V2 + UsuarioAutenticadoRestControllerImpl.PATH)
public class UsuarioAutenticadoRestControllerImpl{
	public static final String PATH = "/usuario-autenticado";

	@Autowired
	private UsuarioAutenticadoService usuarioAutenticadoService;
	
    @RequestMapping(method = RequestMethod.GET, path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @InboundRequest
    public PjeResponse<PjeUser> recuperarUsuarioAutenticado() {
        PjeUser usuarioAutenticado = this.usuarioAutenticadoService.recuperarUsuarioAutenticado();

        PjeResponse<PjeUser> res = new PjeResponse<PjeUser>(PjeResponseStatus.OK, HttpStatus.OK.toString(), null, usuarioAutenticado);

        return res;
    }
}
