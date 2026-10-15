package br.jus.cnj.pje.criminal.dto;

import br.jus.cnj.pje.criminal.enums.MedidaSegurancaEnum;

public class MedidaSegurancaDTO {

	private String cod;
	
	private String label;
	
	public MedidaSegurancaDTO(MedidaSegurancaEnum value) {
		this.cod = value.name();
		this.label = value.getLabel();
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "MedidaSegurancaDTO [cod=" + cod + ", label=" + label + "]";
	}
	
}
