package br.jus.cnj.pje.criminal.enums;

public enum EfeitoSobreSentencaAnteriorEnum {

	M("Mantida"), R("Reformada");

	private String label;

	EfeitoSobreSentencaAnteriorEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
