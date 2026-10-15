package br.jus.cnj.pje.criminal.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.jus.cnj.pje.criminal.util.TipoPenaExecucaoEnumDeserializer;
import br.jus.cnj.pje.criminal.util.TipoPenaExecucaoEnumSerializer;

@JsonSerialize(using = TipoPenaExecucaoEnumSerializer.class)
@JsonDeserialize(using = TipoPenaExecucaoEnumDeserializer.class)
public enum TipoPenaExecucaoEnum {
	AUSENTE(-1, "Ausente"),
	MEDIDA_DE_SEGURANCA(0, "Medida de segurança"),
	PENA_ORIGINARIA(1, "Pena Originária"),
	CONVERTIDA(3, "Convertida"),
	APELACAO_CRIMINAL(4, "Apelação Criminal"),
	NOVA_PENA_APOS_NULIDADE(5, "Nova pena após nulidade"),
	REVISAO_CRIMINAL(6, "Revisão criminal"),
	RECURSO_ESPECIAL(7, "Recurso Especial"),
	RECURSO_EXTRAORDINARIO(8, "Recurso extraordinário"),
	HABEAS_CORPUS(9, "Habeas corpus"),
	PRINCIPAL(10, "Principal");
	
	private Integer cod;
	private String label;
	
	private TipoPenaExecucaoEnum(Integer cod, String label) {
		this.cod = cod;
		this.label = label;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static TipoPenaExecucaoEnum getPorCod(int cod) {
		for (TipoPenaExecucaoEnum t : values()) {
			if (t.getCod() == cod) {
				return t;
			}
		}
		return AUSENTE;
	}
	
}