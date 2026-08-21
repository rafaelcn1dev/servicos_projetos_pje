package br.jus.cnj.pje.storage.jcr.server.support;

import javax.jcr.Session;

import br.jus.cnj.pje.storage.jcr.server.exception.StorageError;
import br.jus.cnj.pje.storage.jcr.server.exception.StorageException;

/**
 * Template responsável por executar comandos em uma transação JCR 
 * 
 * @author leoneparise
 */
public abstract class JCRTemplate {
	public abstract void doWithouReturn(Session session) throws Exception;
	
	public void execute() {
		Session session = JCRSessionHolder.getSession();
		try {
			doWithouReturn(session);
		} catch(StorageException ex) {
			throw ex;
		} catch(StorageError ex) {
			JCRSessionHolder.setException(ex);
			throw ex;
		} catch (Exception ex) {
			JCRSessionHolder.setException(ex);
			throw new StorageError(ex);
		}
	}
}
