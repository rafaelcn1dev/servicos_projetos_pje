package br.jus.cnj.pje.criminal.controllers.v2;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.enums.EstadosBrasileirosEnum;
import br.jus.cnj.pje.criminal.util.ApiVersions;
import br.jus.cnj.pje.pjecommons.model.services.PjeResponse;
import br.jus.cnj.pje.pjecommons.model.services.PjeResponseStatus;

@RestController
@RequestMapping(ApiVersions.V2)
public class EnderecoRestControllerImpl {

	@RequestMapping(method = RequestMethod.GET, path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public PjeResponse<EstadosBrasileirosEnum[]> recuperarEstados(){
		return new PjeResponse<>(PjeResponseStatus.OK, HttpStatus.OK.toString(), null, EstadosBrasileirosEnum.class.getEnumConstants());
	}
	
}
