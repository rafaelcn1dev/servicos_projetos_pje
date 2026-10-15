package br.jus.cnj.pje.criminal.model.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentoIdentificacaoDTO {
	private String tipo;
	private String numero;

	public DocumentoIdentificacaoDTO() {
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public boolean isCPF() {
		return "CPF".equalsIgnoreCase(tipo);
	}

}
