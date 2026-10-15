package br.jus.cnj.pje.criminal.api.dto;

import java.io.Serializable;

import br.jus.cnj.pje.criminal.model.entities.Norma;

public class NormaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public NormaDTO(Norma norma) {
		setId(norma.getId());
		setNome(norma.getNorma());
		setSigla(norma.getSigla());
		setNumero(norma.getNumeroNorma());
	}
	
	private Long id;
	private String nome;
	private String sigla;
	private String numero;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

}
