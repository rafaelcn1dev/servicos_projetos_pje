package br.jus.cnj.pje.criminal.enums;

public enum UnidadeMultaEnum {

	DI("Dias/Multa"), VA("Valor");

	private String label;

	UnidadeMultaEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

}
