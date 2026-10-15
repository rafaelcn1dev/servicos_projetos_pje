package br.jus.cnj.pje.criminal.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientProperties.FeignClientConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.jus.cnj.pje.criminal.model.beans.PjeUser;
import br.jus.cnj.pje.criminal.util.PapeisUtil;

@Component
@Import(FeignClientConfiguration.class)
public class PJeAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UsuarioCacheProvider usuarioCacheProvider;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		PjeUser user = this.usuarioCacheProvider.recuperarPJeUser(authentication.getName(), false);

		if (user != null) {
			return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
					this.loadAuthorities(user.getPapeis()));
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private List<GrantedAuthority> loadAuthorities(Collection<String> roles){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(String role : roles) {
			if(role != null) {
				authorities.add(new SimpleGrantedAuthority(PapeisUtil.ROLE_PREFIXO.concat(role)));
			}
		}
		return authorities;
	}

}
