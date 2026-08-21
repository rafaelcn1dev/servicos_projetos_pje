package br.jus.cnj.pje.storage.jcr.server.support;

import java.io.IOException;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Classe responsável por registrar a Sessão JCR no início da requisição Http
 * 
 * @author leoneparise
 */
public class JCRSessionLoginFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(JCRSessionLoginFilter.class);
	private FilterConfig filterConfig;
	
	/**
	 * Obtém o repositório JCR instalado pelo container Spring
	 * 
	 * @return repositório JCR
	 */
	private Repository getReporitoy() {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		return context.getBean(Repository.class);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("Iniciando JCRSessionLoginFilter...");
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String auth = request.getHeader("Authorization");
		if (auth != null) {
            int index = auth.indexOf(' ');
            if (index > 0) {
                String[] credentials = StringUtils.split(new String(Base64.decodeBase64(auth.substring(index).getBytes())), ':');
                if(credentials.length == 2) {

                	registerSession(credentials[0], credentials[1]);
                	if(JCRSessionHolder.hashSession()){
            			chain.doFilter(request, response);
            			releaseSession();
            			return;
                    }
                }
            }
        }
		
		response.setHeader("WWW-Authenticate", "Basic realm=\"Storage\"");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
	
	/**
	 * Registra sessão JCR na aplicação
	 *  
	 * @param username usuário JCR
	 * @param pass senha JCR
	 * @throws ServletException
	 */
	private void registerSession(String username, String pass) throws ServletException {
		log.debug("Obtem sessão JCR...");
		
		try {
			Session session = getReporitoy().login(new SimpleCredentials(username, pass.toCharArray()));
			if(session != null)
				JCRSessionHolder.setSession(new JCRSessionWrapper(session));
			
		} catch (RepositoryException e) {
			throw new ServletException(e);
		}
	}
	
	/**
	 * Libera a sessão JCR da aplicação salvando as alterações caso não tenha ocorrido erro.
	 */
	private void releaseSession() {
		log.debug("Liberando sessão JCR");
		
		Session session = ((JCRSessionWrapper)JCRSessionHolder.getSession()).getInnerSession();
		
		if(session != null && session.isLive()) {
			try{
				if(!JCRSessionHolder.hashException()) {
					session.save();
				}
			} catch(RepositoryException ex) {
				log.error("Falha ao salvar alteracoes no JCR", ex);
			} finally {
				session.logout();
				JCRSessionHolder.clear();
			}
		}
	}
	
	@Override
	public void destroy() {
	}
}
