package br.jus.cnj.pje.criminal.clients;

import java.util.Set;

import br.jus.cnj.pje.criminal.model.beans.PjeUser;
import feign.Param;
import feign.RequestLine;

public interface PjeLegacyClient {
	@RequestLine("GET " + "/seam/resource/rest/pje-legacy/processos/numero-processo/{numeroProcesso}/validar")
	public Integer validarAcessoAoProcesso(@Param("numeroProcesso") String numeroProcesso);
	
	@RequestLine("GET " + "/seam/resource/rest/pje-legacy/identity/auth")
	public PjeUser recuperarUsuarioPjeLegacy();
	
	@RequestLine("GET " + "/seam/resource/rest/pje-legacy/identity/roles/{login}")
	public Set<String> recuperarPapeisByUsuarioPjeLegacy(@Param("login") String login);
}
