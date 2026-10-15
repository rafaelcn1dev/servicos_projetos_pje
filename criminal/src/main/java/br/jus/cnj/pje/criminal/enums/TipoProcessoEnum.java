package br.jus.cnj.pje.criminal.enums;

public enum TipoProcessoEnum {

	CRI("Criminal"),
	INF("Infracional"),
	T("Todos");
	
	private String descricao;
	
	TipoProcessoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
