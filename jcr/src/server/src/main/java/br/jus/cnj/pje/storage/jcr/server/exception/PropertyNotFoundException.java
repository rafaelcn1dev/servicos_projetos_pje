package br.jus.cnj.pje.storage.jcr.server.exception;

/**
 * Lancado caso uma propriedade não exista no documento
 * 
 * @author leoneparise
 */
public class PropertyNotFoundException extends StorageException{
	private static final long serialVersionUID = -5703362474418478523L;

	public PropertyNotFoundException(String nodeId, String property) {
		super(String.format("Propriedade '%s' não encontada em '%s'", property, nodeId));
	}
}
