package br.jus.cnj.pje.criminal.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.ANY)
public enum RegimePenaEnum {
	A("Aberto"), S("Semi-Aberto"), F("Fechado");

	private String label;

	public String getLabel() {
		return label;
	}

	private RegimePenaEnum(String label) {
		this.label = label;
	}
}