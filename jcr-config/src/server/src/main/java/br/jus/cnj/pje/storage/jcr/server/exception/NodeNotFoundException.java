package br.jus.cnj.pje.storage.jcr.server.exception;

/**
 * Lancado caso não exista um nó no documento
 * 
 * @author leoneparise
 *
 */
public class NodeNotFoundException extends StorageException {
	private static final long serialVersionUID = 1241808389033813569L;

	public NodeNotFoundException(String nodeId, String path) {
		super(String.format("Nó '%s' não encontrado em '%s'", path, nodeId));
	}
}
