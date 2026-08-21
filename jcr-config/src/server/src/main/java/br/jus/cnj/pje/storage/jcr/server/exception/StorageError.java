package br.jus.cnj.pje.storage.jcr.server.exception;

import br.jus.cnj.pje.storage.jcr.server.support.JCRTemplateWithReturn;

/**
 * Lancado caso ocorra erro em algumas das operações dentro {@link JCRTemplateWithReturn}
 * 
 * @author leoneparise
 */
public class StorageError extends RuntimeException {
	private static final long serialVersionUID = 6336541503657099356L;

	public StorageError() {
		super();
	}

	public StorageError(String message, Throwable cause) {
		super(message, cause);
	}

	public StorageError(String message) {
		super(message);
	}

	public StorageError(Throwable cause) {
		super(cause);
	}
}
