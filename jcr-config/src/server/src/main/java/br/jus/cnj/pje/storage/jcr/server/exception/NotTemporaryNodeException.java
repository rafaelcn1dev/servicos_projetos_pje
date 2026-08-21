package br.jus.cnj.pje.storage.jcr.server.exception;

/**
 * Lancado caso o documento não seja temporário
 * 
 * @author leoneparise
 */
public class NotTemporaryNodeException extends StorageException {
	private static final long serialVersionUID = 1L;

	public NotTemporaryNodeException(String hash) {
		super(String.format("O nó '%s' não é temporário", hash));
	}
}
