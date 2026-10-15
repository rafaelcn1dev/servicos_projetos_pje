package br.jus.cnj.pje.criminal.enums;

public enum TipoMedidaCautelarDiversaEnum {

	CPP319I(
			"Art. 319, I - CPP comparecimento periódico em juízo, no prazo e nas condições fixadas pelo juiz, para informar e justificar atividades"
	),
	CPP319II(
			"Art. 319, II - CPP proibição de acesso ou freqüência a determinados lugares quando, por circunstâncias relacionadas ao fato, deva o indiciado ou acusado permanecer distante desses locais para evitar o risco de novas infrações"
	),
	CPP319III(
			"Art. 319, III - CPP proibição de manter contato com pessoa determinada quando, por circunstâncias relacionadas ao fato, deva o indiciado ou acusado dela permanecer distante"
	),
	CPP319IV(
			"Art. 319, IV - CPP proibição de ausentar-se da Comarca quando a permanência seja conveniente ou necessária para a investigação ou instrução"
	),
	CPP319V(
			"Art. 319, V - CPP recolhimento domiciliar no período noturno e nos dias de folga quando o investigado ou acusado tenha residência e trabalho fixos"
	),
	CPP319VI(
			"Art. 319, VI - CPP suspensão do exercício de função pública ou de atividade de natureza econômica ou financeira quando houver justo receio de sua utilização para a prática de infrações penais"
	),
	CPP319VII(
			"Art. 319, VII - CPP internação provisória do acusado nas hipóteses de crimes praticados com violência ou grave ameaça, quando os peritos concluírem ser inimputável ou semi-imputável (art. 26 do Código Penal) e houver risco de reiteração"
	),
	CPP319VIII(
			"Art. 319, VIII - CPP fiança, nas infrações que a admitem, para assegurar o comparecimento a atos do processo, evitar a obstrução do seu andamento ou em caso de resistência injustificada à ordem judicial"
	),
	CPP319IX("Art. 319, IX - CPP monitoração eletrônica"),
	CPP320A(
			"Art. 320 A - CPP proibição de ausentar-se do País será comunicada pelo juiz às autoridades encarregadas de fiscalizar as saídas do território nacional, intimando-se o indiciado ou acusado para entregar o passaporte, no prazo de 24 horas"
	);

	private String descricao;

	TipoMedidaCautelarDiversaEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}

	public String getCodigo(){
		return name();
	}

}
