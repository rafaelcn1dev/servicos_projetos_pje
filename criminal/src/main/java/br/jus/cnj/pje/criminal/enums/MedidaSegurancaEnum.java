package br.jus.cnj.pje.criminal.enums;

public enum MedidaSegurancaEnum {

	AUSENTE(-1, "Ausente"),
	DETENTIVA(1, "Detentiva"),
	RESTRITIVA(2, "Restritiva"),
	INTERNACAO(3, "Internação"),
	TRATAMENTO_AMBULATORIAL(4 ,"Tratamento ambulatorial");

	private Integer cod;
	private String label;
	
	private MedidaSegurancaEnum(Integer cod, String label) {
		this.cod = cod;
		this.label = label;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static MedidaSegurancaEnum getPorCod(int cod) {
		for (MedidaSegurancaEnum t : values()) {
			if (t.getCod().equals(Integer.valueOf(cod))) {
				return t;
			}
		}
		return AUSENTE;
	}
}
