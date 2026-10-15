package br.jus.cnj.pje.criminal.security;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationEntryPoint;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.filter.QueryParamPresenceRequestMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
@Configuration
@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
public class CriminalSecurityConfig extends KeycloakWebSecurityConfigurerAdapter{
	
	@Autowired
	private PJeAuthenticationProvider pjeAuthProvider;

	@Autowired
	private CriminalEntryPoint entryPoint;

	@Value("${criminal.security.bypass-enabled:false}")
	private boolean bypassSegurancaHabilitado;
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
		keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
		auth.authenticationProvider(pjeAuthProvider);
		auth.authenticationProvider(keycloakAuthenticationProvider);
	}
	
	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
	}  
	
	@Bean
	public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}
	
    @Bean
    @Override
    protected KeycloakAuthenticationProcessingFilter keycloakAuthenticationProcessingFilter() throws Exception {
        RequestMatcher requestMatcher =
                new OrRequestMatcher(
                        new AntPathRequestMatcher(KeycloakAuthenticationEntryPoint.DEFAULT_LOGIN_URI),
                        new QueryParamPresenceRequestMatcher(OAuth2Constants.ACCESS_TOKEN),
                        new IgnoreKeycloakProcessingFilterRequestMatcher()
                );
        return new KeycloakAuthenticationProcessingFilter(authenticationManagerBean(), requestMatcher);
    }

    private class IgnoreKeycloakProcessingFilterRequestMatcher implements RequestMatcher {
        IgnoreKeycloakProcessingFilterRequestMatcher() {
        }

        public boolean matches(HttpServletRequest request) {
            String authorizationHeaderValue = request.getHeader("Authorization");
            return authorizationHeaderValue != null && !authorizationHeaderValue.startsWith("Basic ");
        }
    }		
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (isModoDesenvolvimentoAtivo()) {
			http
				.csrf().disable()
				.authorizeRequests()
				.anyRequest().permitAll();
			return;
		}

		super.configure(http);
		http.csrf().disable().httpBasic().authenticationEntryPoint(entryPoint);
		CriminalSecurityConfig.getDefaultHttpSecurityRules(http);
	}

	private boolean isModoDesenvolvimentoAtivo() {
		return this.bypassSegurancaHabilitado;
	}
		
	public static void getDefaultHttpSecurityRules(HttpSecurity http) throws Exception {
		http
			// Regras de acesso aos recursos da API V1
			.authorizeRequests()
				.antMatchers("/swagger-resources").permitAll()
				.antMatchers("/actuator/**").permitAll()
				// TipoOrigem
				.antMatchers(HttpMethod.GET, "/tiposOrigem/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/tiposOrigem/**").authenticated()
				.antMatchers(HttpMethod.POST, "/tiposOrigem/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/tiposOrigem/**").authenticated()
				// TipoProcedimentoOrigem
				.antMatchers(HttpMethod.GET, "/tiposProcedimentoOrigem/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/tiposProcedimentoOrigem/**").authenticated()
				.antMatchers(HttpMethod.POST, "/tiposProcedimentoOrigem/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/tiposProcedimentoOrigem/**").authenticated()
				// OrgaoProcedimentoOriginario
				.antMatchers(HttpMethod.GET, "/orgaosProcedimentoOriginario/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/orgaosProcedimentoOriginario/**").authenticated()
				.antMatchers(HttpMethod.POST, "/orgaosProcedimentoOriginario/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/orgaosProcedimentoOriginario/**").authenticated()
				// ProcessosProcedimentoOrigem
				.antMatchers("/processosProcedimentosOrigem/**").authenticated()
				// Processos
				.antMatchers("/processos/**").authenticated()
				// NaturezaPrisao
				.antMatchers(HttpMethod.GET, "/naturezasPrisao/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/naturezasPrisao/**").authenticated()
				.antMatchers(HttpMethod.POST, "/naturezasPrisao/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/naturezasPrisao/**").authenticated()
				// UnidadePrisional
				.antMatchers(HttpMethod.GET, "/unidadesPrisionais/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/unidadesPrisionais/**").authenticated()
				.antMatchers(HttpMethod.POST, "/unidadesPrisionais/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/unidadesPrisionais/**").authenticated()
				// TipoDispositivo
				.antMatchers(HttpMethod.GET, "/tiposDispositivos/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/tiposDispositivos/**").authenticated()
				.antMatchers(HttpMethod.POST, "/tiposDispositivos/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/tiposDispositivos/**").authenticated()
				// Dispositivo
				.antMatchers(HttpMethod.GET, "/dispositivos/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/dispositivos/**").authenticated()
				.antMatchers(HttpMethod.POST, "/dispositivos/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/dispositivos/**").authenticated()
				// Norma
				.antMatchers(HttpMethod.GET, "/normas/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/normas/**").authenticated()
				.antMatchers(HttpMethod.POST, "/normas/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/normas/**").authenticated()
				// EventoCriminal
				.antMatchers("/eventosCriminais/**").authenticated()
				// Fuga
				.antMatchers("/fugas/**").authenticated()
				// Prisao
				.antMatchers("/prisoes/**").authenticated()
				// Soltura
				.antMatchers("/solturas/**").authenticated()
				// TipoEventoCriminal
				.antMatchers(HttpMethod.GET, "/tiposEventosCriminais/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/tiposEventosCriminais/**").authenticated()
				.antMatchers(HttpMethod.POST, "/tiposEventosCriminais/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/tiposEventosCriminais/**").authenticated()
				// MotivoSoltura
				.antMatchers(HttpMethod.GET, "/motivosSoltura/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/motivosSoltura/**").authenticated()
				.antMatchers(HttpMethod.POST, "/motivosSoltura/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/motivosSoltura/**").authenticated()				
				// TipoPrisao
				.antMatchers(HttpMethod.GET, "/tiposPrisao/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/tiposPrisao/**").authenticated()
				.antMatchers(HttpMethod.POST, "/tiposPrisao/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/tiposPrisao/**").authenticated()
				// SentencaCondenatoria
				.antMatchers("/sentencasCondenatorias/**").authenticated()
				// Internacao
				.antMatchers("/internacoes/**").authenticated()
				// SentencaInfracional
				.antMatchers("/sentencasInfracionais/**").authenticated()
				// SentencaExtincaoMedidaSocioeducativa
				.antMatchers("/sentencasExtincaoMedidaSocioeducativa/**").authenticated()
				// Evasao
				.antMatchers("/evasoes/**").authenticated()
				// Semiliberdade
				.antMatchers("/semiliberdades/**").authenticated()
				// SuspensaoMedidaSocioeducativa
				.antMatchers("/suspensoesMedidasSocioeducativas/**").authenticated()
				// Decisao
				.antMatchers("/decisoes/**").authenticated()
			.and()
			// Regras de acesso aos recursos da API V2 			
			.authorizeRequests()
				// Dispositivo
				.antMatchers(HttpMethod.GET, "/api/v2/dispositivos/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/dispositivos/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/dispositivos/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/dispositivos/**").authenticated()
				// Estado
				.antMatchers(HttpMethod.GET, "/api/v2/estados/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/estados/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/estados/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/estados/**").authenticated()
				// MotivoPrisao
				.antMatchers(HttpMethod.GET, "/api/v2/motivos-prisoes/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/motivos-prisoes/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/motivos-prisoes/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/motivos-prisoes/**").authenticated()
				// MotivoSoltura
				.antMatchers(HttpMethod.GET, "/api/v2/motivos-solturas/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/motivos-solturas/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/motivos-solturas/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/motivos-solturas/**").authenticated()
				// Normas
				.antMatchers(HttpMethod.GET, "/api/v2/normas/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/normas/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/normas/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/normas/**").authenticated()
				// OrgaoProcedimentoOrigem
				.antMatchers(HttpMethod.GET, "/api/v2/orgaos-procedimentos-origens/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/orgaos-procedimentos-origens/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/orgaos-procedimentos-origens/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/orgaos-procedimentos-origens/**").authenticated()
				// TipoEventoCriminal
				.antMatchers(HttpMethod.GET, "/api/v2/tipos-eventos-criminais/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/tipos-eventos-criminais/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/tipos-eventos-criminais/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/tipos-eventos-criminais/**").authenticated()
				// TipoOrigem
				.antMatchers(HttpMethod.GET, "/api/v2/tipos-origens/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/tipos-origens/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/tipos-origens/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/tipos-origens/**").authenticated()				
				// TipoPrisão
				.antMatchers(HttpMethod.GET, "/api/v2/tipos-prisoes/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/tipos-prisoes/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/tipos-prisoes/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/tipos-prisoes/**").authenticated()				
				// TipoProcedimentoOrigem
				.antMatchers(HttpMethod.GET, "/api/v2/tipos-procedimentos-origens/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/tipos-procedimentos-origens/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/tipos-procedimentos-origens/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/tipos-procedimentos-origens/**").authenticated()
				// TipoRecurso
				.antMatchers(HttpMethod.GET, "/api/v2/tipos-recursos/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/tipos-recursos/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/tipos-recursos/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/tipos-recursos/**").authenticated()
				// UnidadePrisional
				.antMatchers(HttpMethod.GET, "/api/v2/unidades-prisionais/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/unidades-prisionais/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/unidades-prisionais/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/unidades-prisionais/**").authenticated()
				// UnidadePrisional
				.antMatchers(HttpMethod.GET, "/api/v2/usuario-autenticado/**").authenticated()
				// MedidaSocioeducativa
				.antMatchers(HttpMethod.GET, "/api/v2/medidas-socioeducativas/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/medidas-socioeducativas/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/medidas-socioeducativas/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/medidas-socioeducativas/**").authenticated()
				// MotivoExtincaoMedidaSocioeducativa
				.antMatchers(HttpMethod.GET, "/api/v2/motivos-extincao-medida-socioeducativa/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/motivos-extincao-medida-socioeducativa/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/motivos-extincao-medida-socioeducativa/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/motivos-extincao-medida-socioeducativa/**").authenticated()
				// MotivoInternacaoProvisoria
				.antMatchers(HttpMethod.GET, "/api/v2/motivos-internacao-provisoria/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/motivos-internacao-provisoria/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/motivos-internacao-provisoria/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/motivos-internacao-provisoria/**").authenticated()
				// MotivoRetornoEvasao
				.antMatchers(HttpMethod.GET, "/api/v2/motivos-retorno-evasao/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/motivos-retorno-evasao/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/motivos-retorno-evasao/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/motivos-retorno-evasao/**").authenticated()
				// TipoInternacao
				.antMatchers(HttpMethod.GET, "/api/v2/tipos-internacao/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v2/tipos-internacao/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v2/tipos-internacao/**").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v2/tipos-internacao/**").authenticated()
				// processos 
				.antMatchers("/api/v2/processos/**").authenticated();

	}
}
