package br.jus.cnj.pje.criminal.enums;

public enum CodigoTipoInternacao {

	PRO("Provisória"),
	EST("Estrita"),
	SAN("Sanção");
	
	private String descricao;

	private CodigoTipoInternacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
