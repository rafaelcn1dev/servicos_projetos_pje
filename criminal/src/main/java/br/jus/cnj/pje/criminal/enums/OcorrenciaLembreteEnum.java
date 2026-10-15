package br.jus.cnj.pje.criminal.enums;

public enum OcorrenciaLembreteEnum {
	DIA("Diário"), SEM("Semanal"), MEN("Mensal"), TRI("Trimestral"), ANU("Anual"), PRE("Data definida");

	private String label;

	OcorrenciaLembreteEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
