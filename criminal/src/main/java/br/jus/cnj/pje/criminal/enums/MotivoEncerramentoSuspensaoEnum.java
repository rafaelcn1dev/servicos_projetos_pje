package br.jus.cnj.pje.criminal.enums;

public enum MotivoEncerramentoSuspensaoEnum {

	OBG("Revogação por causas obrigatórias"), FAC("Revogação por causas facultativas"), OUT("Outros");

	private String label;

	/**
	 * Atributo referente a Descrição da Motivo Encerramento
	 * 
	 * @param label
	 */
	MotivoEncerramentoSuspensaoEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
