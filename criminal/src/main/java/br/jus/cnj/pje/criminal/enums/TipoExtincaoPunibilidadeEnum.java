package br.jus.cnj.pje.criminal.enums;


public enum TipoExtincaoPunibilidadeEnum {

	ANI("Anistia, Graça ou Indulto", true), CDP("Cumprimento da pena", false), CSC(
			"Cumprimento da suspensão condicional do processo", false), MOR("Morte do agente", false), PAG(
			"Pagamento integral do débito", true), PER("Perdão judicial", true), PRE("Prescrição", true), DEC(
			"Decadência", true), PRP("Perempção", true), REN("Renúncia do queixoso ou Perdão aceito", true), REA(
			"Retratação do agente", true), REL("Retroatividade de Lei", true);

	private String label;
	private boolean exigeTipificacaoDelito;


	TipoExtincaoPunibilidadeEnum(String label, boolean exigeTipificacaoDelito) {
		this.label = label;
		this.exigeTipificacaoDelito = exigeTipificacaoDelito;
	}

	public String getLabel() {
		return this.label;
	}

	public boolean exigeTipificacaoDelito() {
		return this.exigeTipificacaoDelito;
	}
}
