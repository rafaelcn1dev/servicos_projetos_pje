package br.jus.cnj.pje.criminal.model.entities;

public enum TipoDispositivoEnum {
	ARTIGO("Artigo"), INCISO("Inciso"), PARAGRAFO("Parágrafo"), ALINEA("Alínea");

	private String descricao;

	TipoDispositivoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
