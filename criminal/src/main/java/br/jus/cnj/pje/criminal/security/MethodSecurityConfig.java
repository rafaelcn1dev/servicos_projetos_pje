package br.jus.cnj.pje.criminal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	@Autowired
	private UsuarioCacheProvider usuarioCacheProvider;

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		return new CustomMethodSecurityExpressionHandler(usuarioCacheProvider);
	}

}
