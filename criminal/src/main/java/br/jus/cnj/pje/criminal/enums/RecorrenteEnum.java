package br.jus.cnj.pje.criminal.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.jus.cnj.pje.criminal.util.RecorrenteEnumDeserializer;
import br.jus.cnj.pje.criminal.util.RecorrenteEnumSerializer;

@JsonSerialize(using = RecorrenteEnumSerializer.class)
@JsonDeserialize(using = RecorrenteEnumDeserializer.class)
public enum RecorrenteEnum {
	
	AUSENTE(-1, "Ausente"),
	MINISTERIO_PUBLICO(1, "Ministério público"), 
	O_REU(2, "O réu"), 
	EX_OFICIO(3, "Ex-ofício"),
	MINISTERIO_PUBLICO_E_REU(4, "Ministério público e réu");

	private Integer cod;
	private String label;

	private RecorrenteEnum(Integer cod, String label) {
		this.cod = cod;
		this.label = label;
	}

	public Integer getCod() {
		return cod;
	}

	public String getLabel() {
		return label;
	}
	
	public static RecorrenteEnum getPorCod(int cod) {
		for (RecorrenteEnum t : values()) {
			if (t.getCod() == cod) {
				return t;
			}
		}
		return AUSENTE;
	}
}