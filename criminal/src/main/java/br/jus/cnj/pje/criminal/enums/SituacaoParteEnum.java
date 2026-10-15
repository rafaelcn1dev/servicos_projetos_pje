package br.jus.cnj.pje.criminal.enums;

public enum SituacaoParteEnum {
	
	A("Ativo"), 
	I("Inativo"), 
	S("Suspenso"), 
	B("Baixado");

	private String label;

	SituacaoParteEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

}
