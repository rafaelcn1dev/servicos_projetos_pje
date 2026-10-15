package br.jus.cnj.pje.criminal.error;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class PjeEntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PjeEntityNotFoundException(Class<?> entidade, Serializable id) {
		super("Não foi possivel encontrar "+ entidade.getSimpleName()+" de ID: "+id);
	}
}