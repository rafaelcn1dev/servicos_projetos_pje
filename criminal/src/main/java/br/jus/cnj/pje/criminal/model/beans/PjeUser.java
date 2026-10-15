package br.jus.cnj.pje.criminal.model.beans;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PjeUser implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean enabled;
	private Set<String> papeis;
	@JsonIgnore
	private Collection<? extends GrantedAuthority> authorities;
	
	public PjeUser() {
		super();
	}

	public PjeUser(String username, String password, boolean enabled,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	public PjeUser(String username, String password, boolean enabled,
			Set<String> papeis) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.papeis = papeis;
	}	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public Set<String> getPapeis() {
		return papeis;
	}
	
	public void setPapeis(Set<String> papeis) {
		this.papeis = papeis;
	}
}
