package br.jus.cnj.pje.criminal.enums;

public enum GeneroPenaEnum {

	MU("Multa"), PL("Privativa de Liberdade"), RD("Restritiva de Direito");

	private String label;

	GeneroPenaEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

}
