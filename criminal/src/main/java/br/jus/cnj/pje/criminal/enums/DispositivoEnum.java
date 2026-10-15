package br.jus.cnj.pje.criminal.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.jus.cnj.pje.criminal.util.DispositivoEnumDeserializer;
import br.jus.cnj.pje.criminal.util.DispositivoEnumSerializer;

@JsonSerialize(using = DispositivoEnumSerializer.class)
@JsonDeserialize(using = DispositivoEnumDeserializer.class)
public enum DispositivoEnum {
	
	TIPO_ARTIGO(1L, "Artigo"),
	TIPO_INCISO(2L, "inciso"),
	TIPO_PARAGRAFO(3L, "Parágrafo");
	
	private Long cod;
	private String label;
	
	private DispositivoEnum(Long cod, String label) {
		this.setCod(cod);
		this.setLabel(label);
	}
	
	public static DispositivoEnum getPorCod(Long cod) {
		for (DispositivoEnum t : values()) {
			if (t.getCod().equals(cod)) {
				return t;
			}
		}
		return null;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
