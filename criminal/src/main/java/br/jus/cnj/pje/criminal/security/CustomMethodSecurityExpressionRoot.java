package br.jus.cnj.pje.criminal.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.CollectionUtils;

import br.jus.cnj.pje.criminal.model.beans.PjeUser;
import br.jus.cnj.pje.criminal.util.PapeisUtil;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

	private Object filterObject;
    private Object returnObject;
    private Object target;
	private UsuarioCacheProvider usuarioCacheProvider;
	private Authentication authentication;
	    
	public CustomMethodSecurityExpressionRoot(Authentication authentication, UsuarioCacheProvider usuarioCacheProvider) {
		super(authentication);
		this.authentication = authentication;
		this.usuarioCacheProvider = usuarioCacheProvider;
	}

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    void setThis(Object target) {
        this.target = target;
    }

    @Override
    public Object getThis() {
        return target;
    }

	public boolean customHasAnyAuthority(String... roles) {
		updateRoles();
		Set<String> roleSet = getAuthority();
		return Arrays.asList(roles).stream().anyMatch(roleSet::contains);
	}

	private Set<String> getAuthority() {
		Set<String> roleSet = new HashSet<>();
		Collection<? extends GrantedAuthority>  authorities =  authentication.getAuthorities();
		
		authorities.forEach(a -> roleSet.add(a.getAuthority()));
		
		return roleSet;
	}
	
	public void updateRoles() {
		if(authentication instanceof KeycloakAuthenticationToken) {
			PjeUser pjeUser = recuperarUsuarioAutenticado(); 
			if(pjeUser != null) {
				loadNewAuthorities(pjeUser.getPapeis(), authentication.getAuthorities());	
			}
		}	
	}
	
	public PjeUser recuperarUsuarioAutenticado() {
		return this.usuarioCacheProvider.recuperarPJeUser(authentication.getName(), false);
	}
	
	private void loadNewAuthorities(Collection<String> roles, Collection<? extends GrantedAuthority> rolesCriminal ){		
		List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
		rolesCriminal.forEach( rC -> updatedAuthorities.add( new SimpleGrantedAuthority(rC.getAuthority() ) ) );
		roles.forEach(r -> updatedAuthorities.add(new SimpleGrantedAuthority(PapeisUtil.ROLE_PREFIXO.concat(r))));		
		
		authentication =  new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), updatedAuthorities);
	}
}
