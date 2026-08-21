package br.jus.cnj.pje.storage.jcr.server;

import static org.apache.jackrabbit.JcrConstants.JCR_CONTENT;
import static org.apache.jackrabbit.JcrConstants.JCR_DATA;

import java.io.InputStream;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

import br.jus.cnj.pje.storage.jcr.server.exception.NodeNotFoundException;
import br.jus.cnj.pje.storage.jcr.server.exception.PropertyNotFoundException;
import br.jus.cnj.pje.storage.jcr.server.exception.StorageError;

/**
 * Recurso JCR
 * 
 * @author leoneparise
 */
public class Resource {
	private final Attributes attributes;
	private final InputStream data;
	private Binary bin;
	
	public Resource(Node node) {
		validate(node);
		
		try {
			Node content = node.getNode(JCR_CONTENT);
			this.attributes = new Attributes(node);
			this.bin = content.getProperty(JCR_DATA).getBinary();
			this.data = bin.getStream();
		} catch (RepositoryException e) {
			throw new StorageError(e);
		}
	}
	
	private void validate(Node node) {
		try {
			if(!node.hasNode(JCR_CONTENT))
				throw new NodeNotFoundException(node.getName(), JCR_CONTENT);

			Node content = node.getNode(JCR_CONTENT);
			if(!content.hasProperty(JCR_DATA))
				throw new PropertyNotFoundException(node.getName(), JCR_DATA);
		} catch(RepositoryException e) {
			throw new StorageError(e);
		}
	}
	
	public InputStream getData() {
		return data;
	}
	
	public Attributes getAttributes() {
		return attributes;
	}
	
	public void close() {
		try {
			data.close();
			bin.dispose();
		} catch(Exception e) {
			throw new StorageError(e);
		}
	}
}
