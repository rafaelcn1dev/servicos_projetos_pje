package br.jus.cnj.pje.criminal.enums;


public enum TipoMedidaSegurancaEnum {

	INT("Internação de hospital de custodia"), TRA("Tratamento ambulatorial");

	private String label;

	TipoMedidaSegurancaEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
