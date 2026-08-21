package br.jus.cnj.pje.storage.jcr.server.support;

import javax.jcr.Session;

import org.springframework.stereotype.Service;

/**
 * Classe responsável por disponibilizar para a aplicação a Sessão JCR armazenada 
 * no ThreadLocal. Deve ser utilizada em conjunto com o JCRSessionRegister
 * para o gerenciamento correto das sessões JCR.
 * 
 * @author leoneparise
 */
@Service
public class JCRSessionHolder {
	private static final ThreadLocal<Session> SESSIONS = new ThreadLocal<Session>();
	private static final ThreadLocal<Exception> EXCEPTIONS = new ThreadLocal<Exception>();
	
	public static Session getSession() {
		return SESSIONS.get();
	}
	
	public static Exception getException() {
		return EXCEPTIONS.get();
	}
	
	public static void setSession(Session session) {
		SESSIONS.set(session);
	}
	
	public static void setException(Exception ex) {
		EXCEPTIONS.set(ex);
	}
	
	public static boolean hashException() {
		return EXCEPTIONS.get() != null;
	}
	
	public static boolean hashSession() {
		return SESSIONS.get() != null;
	}
	
	public static void clear() {
		SESSIONS.remove();
		EXCEPTIONS.remove();
	}
}
