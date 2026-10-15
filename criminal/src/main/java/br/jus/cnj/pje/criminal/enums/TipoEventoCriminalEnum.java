package br.jus.cnj.pje.criminal.enums;

public enum TipoEventoCriminalEnum {
	
		NULL("Nulo"), 
		AAU("Atribuição de Autoria dos Fatos"), 
		SEN("Sentença"), 
		IND("Indiciamento"), 
		OFD("Oferecimento da Denúncia"), 
		ADD("Aditamento da Denúncia"), 
		ADQ("Aditamento da Queixa"), 
		RCD("Recebimento da Denúncia"), 
		PRI("Prisão"), 
		FUG("Fuga"), 
		SOL("Soltura"), 
		TRR("Transferência do Réu"), 
		SAP("Sentença Absolutória"), 
		CQA("Cadastro de Queixa"), 
		NRQ("Não Recebimento da Queixa"), 
		SAI("Sentença Absolutória Imprópria"), 
		SAS("Sentença Absolvição Sumária"), 
		SPR("Sentença de Pronúncia"), 
		SEI("Sentença de Impronúncia"), 
		SEP("Sentença de Extinção da Punibilidade"), 
		DIS("Decisão em Instância Superior"),
		CRQ("Cadastrar Recebimento de Queixa"), 
		TRP("Transação Penal"), 
		ETP("Encerrar Transação Penal"), 
		STP("Suspender Transação Penal"), 
		ESP("Encerrar Suspensão do Processo"), 
		SUS("Suspensao do Processo"), 
		SCO("Sentença Condenatória"), 
		SSP("Suspender Suspensao do Processo"), 
		RSP("Retomar Suspensão do Processo"), 
		DES("Desclassificação do Processo"), 
		NRD("Não Recebimento da Denuncia"),
		CIT("Citação"),
		RAD("Recebimento do Aditamento da Denúncia"),
		DEM("Desmembramento"),
		POP("Preso por Outro Processo"),
		RCP("Revogação da Suspensão Condicional do Processo"),
		ACA("Ausência de condições da ação"),
		DLC("Declínio de competência"),
		HCD("Homologação de composição de danos civis"),
		LCJ("Litispendência e coisa julgada"),
		NPR("Nulidade do Processo"),
		TRA("Trancamento"),
		ARI("Arquivamento da incidência"),
		OFR("Oferecimento da Representação"),
		RCR("Recebimento da Representação"),
		INT("Internação"),
		SML("Semiliberdade"),
		NRR("Não Recebimento da Representação"),
		ADR("Aditamento da Representação"),
		RAR("Recebimento do Aditamento da Representação"),
		IIP("Início da Instrução Processual"),
		FIP("Fim da Instrução Processual"),
		SIN("Sentença Infracional"),
		SEM("Sentença de Extinção de Medida Socioeducativa"),
		EVA("Evasão"),
		SMS("Suspensão da Medida Socioeducativa"),
		DEC("Decisão"),
		REM("Remissão");

	
		private String label;

		TipoEventoCriminalEnum(String label) {
			this.label = label;
		}

		public String getLabel() {
			return this.label;
		}

		@Override
		public String toString() {
			return this.name();
		}
	}
