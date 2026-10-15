package br.jus.cnj.pje.criminal.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.jus.cnj.pje.criminal.util.OrgaoJulgadorEnumDeserializer;
import br.jus.cnj.pje.criminal.util.OrgaoJulgadorEnumSerializer;

@JsonSerialize(using = OrgaoJulgadorEnumSerializer.class)
@JsonDeserialize(using = OrgaoJulgadorEnumDeserializer.class)
public enum OrgaoJulgadorEnum {

	SUPREMO_TRIBUNAL_FEDERAL(0, "Supremo Tribunal Federal"),
	SUPERIOR_TRIBUNAL_DE_JUSTICA(1, "Superior Tribunal de Justiça"),
	SUPERIOR_TRIBUNAL_MILITAR(2, "Superior Tribunal Militar"),
	TRIBUNAL_DE_ALCADA(3, "Tribunal de Alçada"),
	TRIBUNAL_REGIONAL_FEDERAL_1A_REGIAO(4, "Tribunal Regional Federal - 1a. Região"),
	TRIBUNAL_REGIONAL_ELEITORAL(5, "Tribunal Regional Eleitoral"),
	TRIBUNAL_F_RECURSOS(6, "Tribunal F. Recursos"),
	TRIBUNAL_REGIONAL_FEDERAL_2A_REGIAO(7, "Tribunal Regional Federal - 2a. Região"),
	TRIBUNAL_REGIONAL_FEDERAL_3A_REGIAO(8, "Tribunal Regional Federal - 3a. Região"),
	TRIBUNAL_DE_JUSTICA(9, "Tribunal de Justiça"),
	TRIBUNAL_REGIONAL_FEDERAL_4A_REGIAO(10, "Tribunal Regional Federal - 4a. Região"),
	TURMA_RECURSAL_DO_JUIZADO_ESPECIAL(11, "Turma Recursal do Juizado Especial"),
	TRIBUNAL_REGIONAL_FEDERAL_5A_REGIAO(12, "Tribunal Regional Federal - 5a. Região");
	
	private Integer cod;
	private String label;

	private OrgaoJulgadorEnum(Integer cod, String label) {
		this.cod = cod;
		this.label = label;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static OrgaoJulgadorEnum getPorCod(int cod) {
		for (OrgaoJulgadorEnum t : values()) {
			if (t.getCod() == cod) {
				return t;
			}
		}
		return null;
	}
}