package br.jus.cnj.pje.storage.jcr.server.exception;

/**
 * Lancado caso o hash informado seja diferente do hash do conteúdo.
 * 
 * @author leoneparise
 */
public class InconsistentHashException extends StorageException {
	private static final long serialVersionUID = 3385442883537342987L;

	public InconsistentHashException(String given, String content) {
		super(String.format("O hash informado '%s' é diferente do hash do conteúdo '%s'", given, content));
	}
}
