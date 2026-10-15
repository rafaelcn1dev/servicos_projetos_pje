package br.jus.cnj.pje.criminal.enums;

/**
 * Enum para Tipo Causa da Absolvicao Sumária do Júria serem usados como
 * referência para cadastro de de Informação Criminal Relevante do tipo Sentença
 * Absolvição Sumária
 * 
 * 
 * 
 */
public enum TipoCausaAbsolvicaoSumariaEnum {

	CEI("A existência manifesta de causa excludente da ilicitude do fato"), CEC(
			"A existência manifesta de causa excludente da culpabilidade do agente, salvo inimputabilidade"), NCC(
			"Que o fato narrado evidentemente não constitui crime"), EXP("Extinta a punibilidade do agente");

	private String label;

	/**
	 * Atributo referente a Descrição da Causa Absolvicao Sumária do Júri
	 * 
	 * @param label
	 */
	TipoCausaAbsolvicaoSumariaEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
