package br.jus.cnj.pje.criminal.enums;


public enum FrequenciaComparecimentoEmJuizo {
	QUINZENAL("Quinzenal"), MENSAL("Mensal"), BIMESTRAL("Bimestral"), TRIMESTRAL("Trimestral"), SEMESTRAL("Semestral"), ANUAL(
			"Anual");
	private String descricao;

	FrequenciaComparecimentoEmJuizo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
