package br.jus.cnj.pje.storage.jcr.server.support;

import javax.jcr.Session;

import br.jus.cnj.pje.storage.jcr.server.exception.StorageError;
import br.jus.cnj.pje.storage.jcr.server.exception.StorageException;

/**
 * Template responsável por executar comandos em uma transação JCR 
 * 
 * @author leoneparise
 * @param <T> retorno do método {@link JCRTemplateWithReturn#executeAndReturn()}
 */
public abstract class JCRTemplateWithReturn<T> {
	public abstract T doAndReturn(Session session) throws Exception;
	
	public T execute() {
		Session session = JCRSessionHolder.getSession();
		try {
			return doAndReturn(session);
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
