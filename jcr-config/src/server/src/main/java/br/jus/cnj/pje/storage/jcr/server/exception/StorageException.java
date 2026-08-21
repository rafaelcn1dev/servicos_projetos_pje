package br.jus.cnj.pje.storage.jcr.server.exception;

/**
 * Lancado no caso de erro de estado ou estrutura do repositório e seus
 * documentos.
 * 
 * @author leoneparise
 */
public class StorageException extends RuntimeException {
	private static final long serialVersionUID = -1592472973346879957L;

	public StorageException() {
		super();
	}

	public StorageException(String msg, Throwable ex) {
		super(msg, ex);
	}

	public StorageException(String msg) {
		super(msg);
	}

	public StorageException(Throwable ex) {
		super(ex);
	}
}
