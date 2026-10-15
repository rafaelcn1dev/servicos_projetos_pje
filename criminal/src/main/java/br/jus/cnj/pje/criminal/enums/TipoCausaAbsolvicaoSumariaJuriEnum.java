package br.jus.cnj.pje.criminal.enums;


public enum TipoCausaAbsolvicaoSumariaJuriEnum {

	PIF("Provada a inexistência do fato"), PNA("Provado não ser ele autor ou partícipe do fato"), DIP(
			"Demonstrada causa de isenção de pena ou de exclusão do crime"), FNP("O fato não constituir infração penal");

	private String label;

	/**
	 * Atributo referente a Descrição da Causa Absolvicao Sumária do Júri
	 * 
	 * @param label
	 */
	TipoCausaAbsolvicaoSumariaJuriEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
