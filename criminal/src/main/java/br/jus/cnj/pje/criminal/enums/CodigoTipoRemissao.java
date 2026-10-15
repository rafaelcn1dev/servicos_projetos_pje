package br.jus.cnj.pje.criminal.enums;

public enum CodigoTipoRemissao {
	
	EXC("Exclusão"),
	SUS("Suspensão"),
	EXP("Extinção do Processo");
	
	private String descricao;

	private CodigoTipoRemissao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
