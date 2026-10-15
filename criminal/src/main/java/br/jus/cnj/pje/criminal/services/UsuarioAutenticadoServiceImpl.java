package br.jus.cnj.pje.criminal.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.jus.cnj.pje.criminal.aop.TribunalContext;
import br.jus.cnj.pje.criminal.clients.PjeLegacyClient;
import br.jus.cnj.pje.criminal.model.beans.PjeUser;
import br.jus.cnj.pje.criminal.model.entities.CodigoTribunal;
import br.jus.cnj.pje.criminal.util.PapeisUtil;
import br.jus.cnj.pje.criminal.util.PjeLegacyClientUtil;

@Service
public class UsuarioAutenticadoServiceImpl implements UsuarioAutenticadoService{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private PjeLegacyClientUtil pjeLegacyClientUtil;

	@Override
	public PjeUser recuperarUsuarioAutenticado() {
		PjeUser usuarioAutenticado = null;
		if(this.request != null) {
			PjeLegacyClient client = this.pjeLegacyClientUtil.createPjeLegacyClient(this.request);
			usuarioAutenticado = client.recuperarUsuarioPjeLegacy();
		}
		return usuarioAutenticado;
	}
	
	@Override
    public CodigoTribunal codigoTribunal() {
		return TribunalContext.codigoTribunal();
    }

	@Override
	public Boolean usuarioSistema() {
		return this.hasRole(PapeisUtil.ROLE_SISTEMA_PROCESSUAL);
	}
	
	@Override
	public Boolean permissaoTribunal(CodigoTribunal codigoTribunal) {
		return this.hasRole(PapeisUtil.ROLE_APPNAME_PREFIXO + codigoTribunal.appName());
	}

	private Boolean hasRole(String role) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		boolean hasRole = false;
		
		if(!CollectionUtils.isEmpty(authentication.getAuthorities())) {
			hasRole = authentication.getAuthorities().stream()
					.anyMatch(r -> r.getAuthority().equals(role));
		}
		
		return hasRole;
	}

}
