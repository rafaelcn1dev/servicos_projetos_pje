package br.jus.cnj.pje.criminal.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.ANY)
public enum SimNaoEnum {
	S("Sim"), N("Não");

	private String label;

	public String getLabel() {
		return label;
	}

	private SimNaoEnum(String label) {
		this.label = label;
	}
}