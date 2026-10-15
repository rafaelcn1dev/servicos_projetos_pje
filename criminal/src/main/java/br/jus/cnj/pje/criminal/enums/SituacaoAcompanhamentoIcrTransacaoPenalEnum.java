package br.jus.cnj.pje.criminal.enums;


public enum SituacaoAcompanhamentoIcrTransacaoPenalEnum {

	ECUMP("Em cumprimento"), NINIC("Não iniciado"), NCUMP("Não cumprido"), CUMPR("Cumprido");

	private String label;

	SituacaoAcompanhamentoIcrTransacaoPenalEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

}
