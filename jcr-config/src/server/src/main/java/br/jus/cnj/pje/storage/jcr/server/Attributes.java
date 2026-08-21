package br.jus.cnj.pje.storage.jcr.server;

import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.JCR_CONTENT_SIZE;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.JCR_TEMPORARY;
import static br.jus.cnj.pje.storage.jcr.common.StorageConstants.lastModifiedFormat;
import static org.apache.jackrabbit.JcrConstants.JCR_CONTENT;
import static org.apache.jackrabbit.JcrConstants.JCR_LASTMODIFIED;
import static org.apache.jackrabbit.JcrConstants.JCR_MIMETYPE;

import java.util.Date;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import br.jus.cnj.pje.storage.jcr.server.exception.NodeNotFoundException;
import br.jus.cnj.pje.storage.jcr.server.exception.PropertyNotFoundException;
import br.jus.cnj.pje.storage.jcr.server.exception.StorageError;

/**
 * Atributos do recurso.
 * 
 * @author leoneparise
 */
public class Attributes {
	private final String id;
	private final String path;
	private final String mimetype;
	private final Date lastModified;
	private final long size;
	private final boolean temporary;
	
	public Attributes(Node node) {
		validate(node);

		try {
			Node content = node.getNode(JCR_CONTENT);
			this.id = node.getName();
			this.path = node.getPath();
			this.temporary = content.getProperty(JCR_TEMPORARY).getBoolean();
			this.mimetype = content.getProperty(JCR_MIMETYPE).getString();
			this.lastModified = content.getProperty(JCR_LASTMODIFIED).getDate().getTime();
			this.size = content.getProperty(JCR_CONTENT_SIZE).getLong();
		} catch (RepositoryException e) {
			throw new StorageError(e);
		}
	}
	
	private void validate(Node node) {
		try {
			if(!node.hasNode(JCR_CONTENT))
				throw new NodeNotFoundException(node.getName(), JCR_CONTENT);
			
			Node content = node.getNode(JCR_CONTENT);
			if(!content.hasProperty(JCR_TEMPORARY))
				throw new PropertyNotFoundException(node.getName(), JCR_TEMPORARY);
			if(!content.hasProperty(JCR_MIMETYPE))
				throw new PropertyNotFoundException(node.getName(), JCR_MIMETYPE);
			if(!content.hasProperty(JCR_LASTMODIFIED))
				throw new PropertyNotFoundException(node.getName(), JCR_LASTMODIFIED);
			if(!content.hasProperty(JCR_CONTENT_SIZE))
				throw new PropertyNotFoundException(node.getName(), JCR_CONTENT_SIZE);
		} catch (RepositoryException e) {
			throw new StorageError(e);
		}
	}
	
	public String getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public String getMimetype() {
		return mimetype;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public String getLasModifiedFmt() {
		return lastModifiedFormat().format(lastModified);
	}
	
	public long getSize() {
		return size;
	}

	public boolean isTemporary() {
		return temporary;
	}
}
