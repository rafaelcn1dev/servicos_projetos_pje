package br.jus.cnj.pje.criminal.security;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.jus.cnj.pje.criminal.clients.PjeLegacyClient;
import br.jus.cnj.pje.criminal.model.beans.PjeUser;
import br.jus.cnj.pje.criminal.util.PjeLegacyClientUtil;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UsuarioCacheProvider {
	private Map<String, PjeUser> userCache = new ConcurrentHashMap<String, PjeUser>();
	private Map<String, Date> cacheTTL = new ConcurrentHashMap<String, Date>();
	private long timeToRefreshAuthentication = TimeUnit.MINUTES.toMillis(10);

	@Autowired
	private PjeLegacyClientUtil pjeLegacyClientUtil;
	
		
	public PjeUser recuperarPJeUser(String username, boolean cacheWasUsed) {
		PjeUser user =  this.userCache.get(username);
		Date ultimoRefresh =   this.cacheTTL.get(username);
    	
		if(user == null || 
				(user != null && ultimoRefresh != null && 
					((System.currentTimeMillis() - ultimoRefresh.getTime()) > this.timeToRefreshAuthentication))) {
			cacheWasUsed = false;
			user = recuperarUsuarioAutenticado();
			if(user != null && user.getUsername() != null && !user.getUsername().isEmpty()) {
				username = user.getUsername();
			}
		}
		
		if(!cacheWasUsed && username != null && user != null) {
			this.userCache.put(username, user);
			this.cacheTTL.put(username, new Date());
		}
		return user;
	}
	
	public PjeUser recuperarUsuarioAutenticado() {
		PjeUser usuarioAutenticado = null;

    	HttpServletRequest request = 
    	        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	
		if(request != null) {
			PjeLegacyClient client = this.pjeLegacyClientUtil.createPjeLegacyClient(request);
			usuarioAutenticado = client.recuperarUsuarioPjeLegacy();
			if (usuarioAutenticado.getPapeis()== null) {
				//buscar no client os papeis
				Set<String> roles = (Set<String>) client.recuperarPapeisByUsuarioPjeLegacy(usuarioAutenticado.getUsername());
				usuarioAutenticado.setPapeis(roles);
			}
		}
		return usuarioAutenticado;
	}
}
