package br.jus.cnj.pje.criminal.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioSemPermissaoNoTribunalSolicitadoException extends CriminalBusinessException {

	private static final long serialVersionUID = 1L;

	public UsuarioSemPermissaoNoTribunalSolicitadoException() {
		super("O usuário não possui permissão no tribunal indicado."
				+"");
    }
}