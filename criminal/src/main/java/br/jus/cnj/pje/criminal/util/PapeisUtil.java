package br.jus.cnj.pje.criminal.util;


public final class PapeisUtil {

	public final static String ROLE_PREFIXO = "ROLE_";
	
	// Padrão antigo de nomenclatura das roles no PJe Legacy. Não utilizar esse padrão para papeis novos
	// o caracter ':' não pode ser utilizado no SSO com keycloak, por isso densencoraja-se seu uso
	public final static String LEGACY_ROLE_ADMIN = ROLE_PREFIXO +  "pje:criminal:admin";
	public final static String LEGACY_ROLE_MANIPULA_INFORMACAO_CRIMINAL = ROLE_PREFIXO +  "pje:criminal:manipulaInformacaoCriminal";
	public final static String LEGACY_ROLE_VISUALIZA_INFORMACAO_CRIMINAL = ROLE_PREFIXO +  "pje:criminal:visualizaInformacaoCriminal";
	
	public final static String ROLE_ADMIN = ROLE_PREFIXO + "admin";
	public final static String ROLE_MANIPULA_INFORMACAO_CRIMINAL = ROLE_PREFIXO + "manipulaInformacaoCriminal";
	public final static String ROLE_VISUALIZA_INFORMACAO_CRIMINAL = ROLE_PREFIXO + "visualizaInformacaoCriminal";
	public final static String ROLE_SISTEMA_PROCESSUAL = ROLE_PREFIXO + "sistemaProcessual";

	public final static String ROLE_APPNAME_PREFIXO =  ROLE_PREFIXO + "APPNAME_";
	
	public final static String HAS_AUTHORITY_ROLE_ADMIN = "customHasAnyAuthority('" + ROLE_ADMIN + "', '"+ LEGACY_ROLE_ADMIN + "')";	
	public final static String HAS_AUTHORITY_ROLE_MANIPULA_INFORMACAO_CRIMINAL = "customHasAnyAuthority('" + ROLE_MANIPULA_INFORMACAO_CRIMINAL + "', '" + LEGACY_ROLE_MANIPULA_INFORMACAO_CRIMINAL +"')";	
	public final static String HAS_AUTHORITY_ROLE_VISUALIZA_INFORMACAO_CRIMINAL = "customHasAnyAuthority('" + ROLE_VISUALIZA_INFORMACAO_CRIMINAL + "', '" + LEGACY_ROLE_VISUALIZA_INFORMACAO_CRIMINAL +"')";	


}
