package br.jus.cnj.pje.storage.jcr.server;

import javax.jcr.Session;

import org.apache.jackrabbit.core.SessionImpl;
import org.apache.jackrabbit.core.data.GarbageCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Task responsável por executar a coleta de lixo no repositório JCR
 * 
 * @author leoneparise
 */
public class GarbageCollectorTask implements Runnable {
	private Session session;
	Logger log = LoggerFactory.getLogger(GarbageCollectorTask.class);
	
	public GarbageCollectorTask(Session session) {
		this.session = session;
	}
	
	@Override
	public void run() {
		GarbageCollector gc = null;
		
		try {
			gc = ((SessionImpl)session).createDataStoreGarbageCollector();

			gc.mark();
			gc.sweep();
		} catch (Exception e) {
			log.error("Falha ao executar a coleta de lixo:", e);
		} finally {
			if(gc != null) gc.close();
			if(session != null && session.isLive()) session.logout();
		}
	}
}
