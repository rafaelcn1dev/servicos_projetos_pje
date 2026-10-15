package br.jus.cnj.pje.criminal.model.beans;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import br.jus.cnj.pje.criminal.enums.TipoEventoCriminalEnum;

public class ConteudoInformacaoCriminalBean extends BaseBean{

	private static final long serialVersionUID = 1L;

	private String nomePrincipal;
	private List<String> filiacoes;
	private List<DocumentoIdentificacaoBean> documentos;
	private List<CaracteristicaFisicaBean> caracteristicasFisicas;
	private List<String> nomes;
	
	private List<EventoCriminalBean> indiciamento  = new ArrayList<EventoCriminalBean>();
	private List<EventoCriminalBean> oferecimentoDenuncia  = new ArrayList<EventoCriminalBean>();
	private List<EventoCriminalBean> recebimentoDenuncia  = new ArrayList<EventoCriminalBean>();
	private List<EventoCriminalBean> aditamentoDenuncia  = new ArrayList<EventoCriminalBean>();
	private List<EventoCriminalBean> inicioInstrucaoProcessual = new ArrayList<EventoCriminalBean>();
	private List<EventoCriminalBean> fimInstrucaoProcessual = new ArrayList<EventoCriminalBean>();

	private List<PrisaoBean> prisoes = new ArrayList<PrisaoBean>();
	private List<SuspensaoProcessoBean> suspensoesProcesso = new ArrayList<SuspensaoProcessoBean>();
	private List<DecisaoInstanciaSuperiorBean> decisoesInstanciaSuperior = new ArrayList<DecisaoInstanciaSuperiorBean>();
	private List<SentencaCondenatoriaProcessoBean> sentencasCondenatorias = new ArrayList<SentencaCondenatoriaProcessoBean>();
	private List<SolturaBean> solturas = new ArrayList<SolturaBean> ();
	private List<FugaBean> fugas = new ArrayList<FugaBean>();
	private List<SentencaAbsolutoriaBean> sentencasAbsolutorias = new ArrayList<SentencaAbsolutoriaBean>();
	private List<EventoCriminalBean> naoRecebimentoDenuncia  = new ArrayList<EventoCriminalBean>();
	private List<SentencaExtincaoPunibilidadeBean> sentencasExtincaoPunibilidade;
	private List<SentencaImpronunciaProcessoBean> sentencasImpronuncia;
	private List<SentencaPronunciaProcessoBean> sentencasPronuncia;
	private List<DesclassificacaoProcessoBean> desclassificacoesProcesso;
	private List<CitacaoProcessoBean> citacoes;
	private List<RecebimentoAditamentoDenunciaBean> recebimentosAditamentoDenuncia;
	private List<DesmembramentoBean> desmembramentos;
	private List<SentencaAbsolutoriaImpropriaBean> sentencasAbsolutoriasImproprias;
	private List<PresoOutroProcessoBean> presosOutroProcesso;
	private List<TransacaoPenalBean> transacoesPenais;
	private List<RevogacaoSuspensaoCondicionalProcessoBean> revogacoesSuspensaoCondicionalProcesso;
	private List<TrancamentoBean> trancamentos;
	private List<NulidadeProcessoBean> nulidadesProcessos;
	private List<LitispendenciaCoisaJulgadaBean> litispendenciasCoisaJulgada;
	private List<AusenciaCondicoesAcaoBean> ausenciasCondicoesAcao;
	private List<DeclinioCompetenciaBean> decliniosCompetencia;
	private List<ArquivamentoIncidenciaBean> arquivamentosIncidencias;
	
	private List<HomologacaoComposicaoDanoCivilBean> homologacoesComposicoesDanosCivis;
	private List<SentencaAbsolvicaoSumariaBean> sentencasAbsolvicaoSumaria;
	private List<InternacaoBean> internacoes;
	private List<SentencaInfracionalBean> sentencasInfracionais;
	private List<SentencaExtincaoMedidaSocioeducativaBean> sentencasExtincaoMedidaSocioeducativa;
	private List<DecisaoBean> decisoes = new ArrayList<>();
	private List<EvasaoBean> evasoes;
	private List<SemiliberdadeBean> semiliberdades;
	private List<SuspensaoMedidaSocioeducativaBean> suspensoesMedidasSocioeducativas;
	private List<RemissaoBean> remissoes;

	public ConteudoInformacaoCriminalBean() {
		super();
	}

	public ConteudoInformacaoCriminalBean(
			String nomePrincipal,
			List<IncidenciaPenalBean> incidenciasPenais,
			List<PrisaoBean> prisoes, 
			List<SuspensaoProcessoBean> suspensoesProcesso,
			List<DecisaoInstanciaSuperiorBean> decisoesInstanciaSuperior,
			List<SentencaCondenatoriaProcessoBean> sentencasCondenatorias,
			List<SolturaBean> solturas, 
			List<FugaBean> fugas,
			List<String> filiacoes,
			List<DocumentoIdentificacaoBean> documentos,
			List<CaracteristicaFisicaBean> caracteristicasFisicas,
			List<String> nomes,
			List<SentencaAbsolutoriaBean> sentencasAbsolutorias,
			List<SentencaExtincaoPunibilidadeBean> sentencasExtincaoPunibilidade,
			List<SentencaImpronunciaProcessoBean> sentencasImpronuncia,
			List<SentencaPronunciaProcessoBean> sentencasPronuncia,
			List<DesclassificacaoProcessoBean> desclassificacoesProcesso,
			List<EventoCriminalBean> naoRecebimentoDenuncia,
			List<CitacaoProcessoBean> citacoes,
			List<RecebimentoAditamentoDenunciaBean> recebimentosAditamentoDenuncia,
			List<InternacaoBean> internacoes,
			List<SentencaInfracionalBean> sentencasInfracionais,
			List<SentencaExtincaoMedidaSocioeducativaBean> sentencasExtincaoMedidaSocioeducativa,
			List<DecisaoBean> decisoes,
			List<EvasaoBean> evasoes,
			List<SemiliberdadeBean> semiliberdades,
			List<SuspensaoMedidaSocioeducativaBean> suspensoesMedidasSocioeducativas,
			List<RemissaoBean> remissoes
) {
		super();
		this.nomePrincipal = nomePrincipal;
		this.prisoes = prisoes;
		this.suspensoesProcesso = suspensoesProcesso;
		this.decisoesInstanciaSuperior = decisoesInstanciaSuperior;
		this.sentencasCondenatorias = sentencasCondenatorias;
		this.solturas = solturas;
		this.fugas = fugas;
		this.filiacoes = filiacoes;
		this.documentos = documentos;
		this.caracteristicasFisicas = caracteristicasFisicas;
		this.nomes = nomes;
		this.sentencasAbsolutorias = sentencasAbsolutorias;
		this.sentencasExtincaoPunibilidade = sentencasExtincaoPunibilidade;
		this.sentencasImpronuncia = sentencasImpronuncia;
		this.sentencasPronuncia = sentencasPronuncia;
		this.desclassificacoesProcesso = desclassificacoesProcesso;
		this.naoRecebimentoDenuncia = naoRecebimentoDenuncia;
		this.citacoes = citacoes;
		this.recebimentosAditamentoDenuncia = recebimentosAditamentoDenuncia;
		this.internacoes = internacoes;
		this.sentencasInfracionais = sentencasInfracionais;
		this.sentencasExtincaoMedidaSocioeducativa = sentencasExtincaoMedidaSocioeducativa;
		this.decisoes = decisoes;
		this.evasoes = evasoes;
		this.semiliberdades = semiliberdades;
		this.suspensoesMedidasSocioeducativas = suspensoesMedidasSocioeducativas; 
		this.remissoes = remissoes;

	}
	
	public ConteudoInformacaoCriminalBean(String id, 
			String nomePrincipal,
			List<PrisaoBean> prisoes, 
			List<SuspensaoProcessoBean> suspensoesProcesso,
			List<DecisaoInstanciaSuperiorBean> decisoesInstanciaSuperior,
			List<SentencaCondenatoriaProcessoBean> sentencasCondenatorias,
			List<SolturaBean> solturas, 
			List<FugaBean> fugas,
			List<String> filiacoes,
			List<DocumentoIdentificacaoBean> documentos,
			List<CaracteristicaFisicaBean> caracteristicasFisicas,
			List<String> nomes,
			List<SentencaAbsolutoriaBean> sentencasAbsolutorias,
			List<SentencaExtincaoPunibilidadeBean> sentencasExtincaoPunibilidade,
			List<SentencaImpronunciaProcessoBean> sentencasImpronuncia,
			List<SentencaPronunciaProcessoBean> sentencasPronuncia,
			List<DesclassificacaoProcessoBean> desclassificacoesProcesso,
			List<EventoCriminalBean> naoRecebimentoDenuncia,
			List<CitacaoProcessoBean> citacoes,
			List<RecebimentoAditamentoDenunciaBean> recebimentosAditamentoDenuncia,
			List<InternacaoBean> internacoes,
			List<SentencaInfracionalBean> sentencasInfracionais,
			List<SentencaExtincaoMedidaSocioeducativaBean> sentencasExtincaoMedidaSocioeducativa,
			List<DecisaoBean> decisoes,
			List<EvasaoBean> evasoes,
			List<SemiliberdadeBean> semiliberdades,
			List<SuspensaoMedidaSocioeducativaBean> suspensoesMedidasSocioeducativas) {
		super(id);
		this.nomePrincipal = nomePrincipal;
		this.prisoes = prisoes;
		this.suspensoesProcesso = suspensoesProcesso;
		this.decisoesInstanciaSuperior = decisoesInstanciaSuperior;
		this.sentencasCondenatorias = sentencasCondenatorias;
		this.solturas = solturas;
		this.fugas = fugas;
		this.filiacoes = filiacoes;
		this.documentos = documentos;
		this.caracteristicasFisicas = caracteristicasFisicas;
		this.nomes = nomes;
		this.sentencasAbsolutorias = sentencasAbsolutorias;
		this.sentencasExtincaoPunibilidade = sentencasExtincaoPunibilidade;
		this.sentencasImpronuncia = sentencasImpronuncia;
		this.sentencasPronuncia = sentencasPronuncia;
		this.desclassificacoesProcesso = desclassificacoesProcesso;
		this.naoRecebimentoDenuncia = naoRecebimentoDenuncia;
		this.citacoes = citacoes;
		this.recebimentosAditamentoDenuncia = recebimentosAditamentoDenuncia;
		this.internacoes = internacoes;
		this.sentencasInfracionais = sentencasInfracionais;
		this.sentencasExtincaoMedidaSocioeducativa = sentencasExtincaoMedidaSocioeducativa;
		this.decisoes = decisoes;
		this.evasoes = evasoes;
		this.semiliberdades = semiliberdades;
		this.suspensoesMedidasSocioeducativas = suspensoesMedidasSocioeducativas;
	}

	@Transient
	public Boolean removeEventoCriminal(EventoCriminalBean eventoCriminalBean) {
		Boolean isRemovido = Boolean.FALSE;
		switch (eventoCriminalBean.getTipoInformacaoCriminal().getCodTipoIc()){
			case IND:
				isRemovido = indiciamento.remove(eventoCriminalBean);
				break;
			case OFD:
			case OFR:
				isRemovido = oferecimentoDenuncia.remove(eventoCriminalBean);
				break;
			case RCD:
			case RCR:
				isRemovido = recebimentoDenuncia.remove(eventoCriminalBean);
				break;
		case ADD:
		case ADR:
				isRemovido = aditamentoDenuncia.remove(eventoCriminalBean);
				break;
			case IIP:
				isRemovido = inicioInstrucaoProcessual.remove(eventoCriminalBean);
				break;
			case FIP:
				isRemovido = fimInstrucaoProcessual.remove(eventoCriminalBean);
				break;
			case PRI:
				isRemovido = prisoes.remove((PrisaoBean) eventoCriminalBean);
				break;
			case SUS:
				isRemovido = suspensoesProcesso.remove((SuspensaoProcessoBean) eventoCriminalBean);
				break;
			case DIS:
				isRemovido = decisoesInstanciaSuperior.remove((DecisaoInstanciaSuperiorBean) eventoCriminalBean);
				break;
			case SCO:
				isRemovido = sentencasCondenatorias.remove((SentencaCondenatoriaProcessoBean) eventoCriminalBean);
				break;
			case FUG:
				isRemovido = fugas.remove((FugaBean) eventoCriminalBean);
				break;
			case SOL:
				isRemovido = solturas.remove((SolturaBean) eventoCriminalBean);
				break;
			case SAP:
				isRemovido = sentencasAbsolutorias.remove((SentencaAbsolutoriaBean) eventoCriminalBean);
				break;
			case SEP:
				isRemovido = sentencasExtincaoPunibilidade.remove((SentencaExtincaoPunibilidadeBean) eventoCriminalBean);
				break;
			case DES:
				isRemovido = desclassificacoesProcesso.remove((DesclassificacaoProcessoBean) eventoCriminalBean);
				break;
			case SEI:
				isRemovido = sentencasImpronuncia.remove((SentencaImpronunciaProcessoBean) eventoCriminalBean);
				break;
			case SPR:
				isRemovido = sentencasPronuncia.remove((SentencaPronunciaProcessoBean) eventoCriminalBean);
				break;
			case NRD:
			case NRR:
				isRemovido = naoRecebimentoDenuncia.remove(eventoCriminalBean);
				break;
			case CIT:
				isRemovido = citacoes.remove((CitacaoProcessoBean) eventoCriminalBean);
				break;
			case RAD:
			case RAR:
				isRemovido = recebimentosAditamentoDenuncia.remove((RecebimentoAditamentoDenunciaBean) eventoCriminalBean);
				break;
			case SAS:
				isRemovido = sentencasAbsolvicaoSumaria.remove((SentencaAbsolvicaoSumariaBean) eventoCriminalBean);
				break;
			case DEM:
				isRemovido = desmembramentos.remove((DesmembramentoBean) eventoCriminalBean);
				break;
			case SAI:
				isRemovido = sentencasAbsolutoriasImproprias.remove((SentencaAbsolutoriaImpropriaBean) eventoCriminalBean);
				break;
			case POP:
				isRemovido = presosOutroProcesso.remove((PresoOutroProcessoBean) eventoCriminalBean);
				break;
			case TRP:
				isRemovido = transacoesPenais.remove((TransacaoPenalBean) eventoCriminalBean);
				break;
			case RCP:
				isRemovido = revogacoesSuspensaoCondicionalProcesso.remove((RevogacaoSuspensaoCondicionalProcessoBean) eventoCriminalBean);
				break;
			case ARI:
				isRemovido = arquivamentosIncidencias.remove((ArquivamentoIncidenciaBean) eventoCriminalBean);
				break;
			case REM:
				isRemovido = remissoes.remove((RemissaoBean) eventoCriminalBean);
				break;
			case TRA:
				isRemovido = trancamentos.remove((TrancamentoBean) eventoCriminalBean);
				break;			
			case NPR:
				isRemovido = nulidadesProcessos.remove((NulidadeProcessoBean) eventoCriminalBean);
				break;
			case DLC:
				isRemovido = decliniosCompetencia.remove((DeclinioCompetenciaBean) eventoCriminalBean);
				break;
			case ACA:
				isRemovido = ausenciasCondicoesAcao.remove((AusenciaCondicoesAcaoBean) eventoCriminalBean);
				break;
			case HCD:
				isRemovido = homologacoesComposicoesDanosCivis.remove((HomologacaoComposicaoDanoCivilBean) eventoCriminalBean);
				break;
			case LCJ:
				isRemovido = litispendenciasCoisaJulgada.remove((LitispendenciaCoisaJulgadaBean) eventoCriminalBean);
				break;
			case INT:
				isRemovido = internacoes.remove((InternacaoBean) eventoCriminalBean);
				break;
			case SIN:
				isRemovido = sentencasInfracionais.remove((SentencaInfracionalBean) eventoCriminalBean);
				break;
			case SEM:
				isRemovido = sentencasExtincaoMedidaSocioeducativa.remove((SentencaExtincaoMedidaSocioeducativaBean) eventoCriminalBean);
				break;
			case DEC:
				isRemovido = decisoes.remove((DecisaoBean) eventoCriminalBean);
				break;
			case EVA:
				isRemovido = evasoes.remove((EvasaoBean) eventoCriminalBean);
				break;
			case SML:
				isRemovido = semiliberdades.remove((SemiliberdadeBean) eventoCriminalBean);
				break;
			case SMS:
				isRemovido = suspensoesMedidasSocioeducativas.remove((SuspensaoMedidaSocioeducativaBean) eventoCriminalBean);
				break;
			default:
				break;
		}
		return isRemovido;		
	}
	
	public void addEventoCriminal(EventoCriminalBean eventoCriminalBean) {
		switch (eventoCriminalBean.getTipoInformacaoCriminal().getCodTipoIc()){
			case IND:
				indiciamento.add(eventoCriminalBean);
				break;
			case OFD:
			case OFR:
				oferecimentoDenuncia.add(eventoCriminalBean);
				break;
			case RCD:
			case RCR:
				recebimentoDenuncia.add(eventoCriminalBean);
				break;
		case ADD:
		case ADR:
				aditamentoDenuncia.add(eventoCriminalBean);
				break;
			case IIP:
				inicioInstrucaoProcessual.add(eventoCriminalBean);
				break;
			case FIP:
				fimInstrucaoProcessual.add(eventoCriminalBean);
				break;
			case PRI:
				prisoes.add((PrisaoBean) eventoCriminalBean);
				break;
			case SUS:
				suspensoesProcesso.add((SuspensaoProcessoBean) eventoCriminalBean);
				break;
			case DIS:
				decisoesInstanciaSuperior.add((DecisaoInstanciaSuperiorBean) eventoCriminalBean);
				break;
			case SCO:
				sentencasCondenatorias.add((SentencaCondenatoriaProcessoBean) eventoCriminalBean);
				break;
			case FUG:
				fugas.add((FugaBean) eventoCriminalBean);
				break;
			case SOL:
				solturas.add((SolturaBean) eventoCriminalBean);
				break;
			case SAP:
				sentencasAbsolutorias.add((SentencaAbsolutoriaBean) eventoCriminalBean);
				break;
			case SEP:
				sentencasExtincaoPunibilidade.add((SentencaExtincaoPunibilidadeBean) eventoCriminalBean);
				break;
			case SEI:
				sentencasImpronuncia.add((SentencaImpronunciaProcessoBean) eventoCriminalBean);
				break;
			case SPR:
				sentencasPronuncia.add((SentencaPronunciaProcessoBean) eventoCriminalBean);
				break;
			case DES:
				desclassificacoesProcesso.add((DesclassificacaoProcessoBean) eventoCriminalBean);
				break;
			case NRD:
			case NRR:
				naoRecebimentoDenuncia.add(eventoCriminalBean);
				break;
			case CIT:
				citacoes.add((CitacaoProcessoBean) eventoCriminalBean);
				break;
			case RAD:
			case RAR:
				recebimentosAditamentoDenuncia.add((RecebimentoAditamentoDenunciaBean) eventoCriminalBean);
				break;
			case SAS:
				sentencasAbsolvicaoSumaria.add((SentencaAbsolvicaoSumariaBean) eventoCriminalBean);
				break;
			case DEM:
				desmembramentos.add((DesmembramentoBean) eventoCriminalBean);
				break;
			case SAI:
				sentencasAbsolutoriasImproprias.add((SentencaAbsolutoriaImpropriaBean) eventoCriminalBean);
				break;
			case POP:
				presosOutroProcesso.add((PresoOutroProcessoBean) eventoCriminalBean);
				break;
			case TRP:
				transacoesPenais.add((TransacaoPenalBean) eventoCriminalBean);
				break;			
			case RCP:
				revogacoesSuspensaoCondicionalProcesso.add((RevogacaoSuspensaoCondicionalProcessoBean) eventoCriminalBean);
				break;
			case ARI:
				arquivamentosIncidencias.add((ArquivamentoIncidenciaBean) eventoCriminalBean);
				break;
			case REM:
				remissoes.add((RemissaoBean) eventoCriminalBean);
				break;
			case TRA:
				trancamentos.add((TrancamentoBean) eventoCriminalBean);
				break;
			case NPR:
				nulidadesProcessos.add((NulidadeProcessoBean) eventoCriminalBean);
				break;
			case ACA:
				ausenciasCondicoesAcao.add((AusenciaCondicoesAcaoBean) eventoCriminalBean);
				break;
			case DLC:
				decliniosCompetencia.add((DeclinioCompetenciaBean) eventoCriminalBean);
				break;
			case HCD:
				homologacoesComposicoesDanosCivis.add((HomologacaoComposicaoDanoCivilBean) eventoCriminalBean);
				break;
			case LCJ:
				litispendenciasCoisaJulgada.add((LitispendenciaCoisaJulgadaBean) eventoCriminalBean);
				break;
			case INT:
				internacoes.add((InternacaoBean) eventoCriminalBean);
				break;
			case SIN:
				sentencasInfracionais.add((SentencaInfracionalBean) eventoCriminalBean);
				break;
			case SEM:
				sentencasExtincaoMedidaSocioeducativa.add((SentencaExtincaoMedidaSocioeducativaBean) eventoCriminalBean);
				break;
			case DEC:
				decisoes.add((DecisaoBean) eventoCriminalBean);
				break;
			case EVA:
				evasoes.add((EvasaoBean) eventoCriminalBean);
				break;
			case SML:
				semiliberdades.add((SemiliberdadeBean) eventoCriminalBean);
				break;
			case SMS:
				suspensoesMedidasSocioeducativas.add((SuspensaoMedidaSocioeducativaBean) eventoCriminalBean);
				break;
			default:
				break;
		}
	}
	
	public List<EventoCriminalBean> getEventosCriminaisPorTipo(TipoEventoCriminalEnum codTipoIc) {
		switch (codTipoIc) {
		case IND:
			return indiciamento;
		case OFD:
		case OFR:
			return oferecimentoDenuncia;
		case RCD:
		case RCR:
			return recebimentoDenuncia;
		case ADD:
		case ADR:
			return aditamentoDenuncia;
		case IIP:
			return inicioInstrucaoProcessual;
		case FIP:
			return fimInstrucaoProcessual;
		case NRD:
		case NRR:
			return naoRecebimentoDenuncia;
		default:
			return new ArrayList<>();
		}
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}

	public List<PrisaoBean> getPrisoes() {
		if(prisoes == null) {
			prisoes = new ArrayList<>();
		}
		return prisoes;
	}

	public void setPrisoes(List<PrisaoBean> prisoes) {
		this.prisoes = prisoes;
	}

	public List<SolturaBean> getSolturas() {
		if(solturas == null) {
			solturas = new ArrayList<>();
		}
		return solturas;
	}

	public void setSolturas(List<SolturaBean> solturas) {
		this.solturas = solturas;
	}

	public List<FugaBean> getFugas() {
		if(fugas == null) {
			fugas = new ArrayList<>();
		}		
		return fugas;
	}

	public void setFugas(List<FugaBean> fugas) {
		this.fugas = fugas;
	}

	public String getNomePrincipal() {
		return nomePrincipal;
	}

	public void setNomePrincipal(String nomePrincipal) {
		this.nomePrincipal = nomePrincipal;
	}

	public List<String> getFiliacoes() {
		if(filiacoes == null) {
			filiacoes = new ArrayList<>();
		}			
		return filiacoes;
	}

	public void setFiliacoes(List<String> filiacoes) {
		this.filiacoes = filiacoes;
	}

	public List<DocumentoIdentificacaoBean> getDocumentos() {
		if(documentos == null) {
			documentos = new ArrayList<>();
		}			
		return documentos;
	}

	public void setDocumentos(List<DocumentoIdentificacaoBean> documentos) {
		this.documentos = documentos;
	}

	public List<CaracteristicaFisicaBean> getCaracteristicasFisicas() {
		if(caracteristicasFisicas == null) {
			caracteristicasFisicas = new ArrayList<>();
		}		
		return caracteristicasFisicas;
	}

	public void setCaracteristicasFisicas(List<CaracteristicaFisicaBean> caracteristicasFisicas) {
		this.caracteristicasFisicas = caracteristicasFisicas;
	}

	public List<EventoCriminalBean> getIndiciamento() {
		if(indiciamento == null) {
			indiciamento = new ArrayList<>();
		}			
		return indiciamento;
	}

	public void setIndiciamento(List<EventoCriminalBean> indiciamento) {
		this.indiciamento = indiciamento;
	}

	public List<EventoCriminalBean> getOferecimentoDenuncia() {
		if(oferecimentoDenuncia == null) {
			oferecimentoDenuncia = new ArrayList<>();
		}			
		return oferecimentoDenuncia;
	}

	public void setOferecimentoDenuncia(List<EventoCriminalBean> oferecimentoDenuncia) {
		this.oferecimentoDenuncia = oferecimentoDenuncia;
	}

	public List<EventoCriminalBean> getRecebimentoDenuncia() {
		if(recebimentoDenuncia == null) {
			recebimentoDenuncia = new ArrayList<>();
		}			
		return recebimentoDenuncia;
	}

	public void setRecebimentoDenuncia(List<EventoCriminalBean> recebimentoDenuncia) {
		this.recebimentoDenuncia = recebimentoDenuncia;
	}

	public List<EventoCriminalBean> getAditamentoDenuncia() {
		if(aditamentoDenuncia == null) {
			aditamentoDenuncia = new ArrayList<>();
		}			
		return aditamentoDenuncia;
	}

	public void setAditamentoDenuncia(List<EventoCriminalBean> aditamentoDenuncia) {
		this.aditamentoDenuncia = aditamentoDenuncia;
	}

	public List<EventoCriminalBean> getInicioInstrucaoProcessual() {
		if (inicioInstrucaoProcessual == null) {
			inicioInstrucaoProcessual = new ArrayList<>();
		}
		return inicioInstrucaoProcessual;
	}

	public void setInicioInstrucaoProcessual(List<EventoCriminalBean> inicioInstrucaoProcessual) {
		this.inicioInstrucaoProcessual = inicioInstrucaoProcessual;
	}

	public List<EventoCriminalBean> getFimInstrucaoProcessual() {
		if (fimInstrucaoProcessual == null) {
			fimInstrucaoProcessual = new ArrayList<>();
		}
		return fimInstrucaoProcessual;
	}

	public void setFimInstrucaoProcessual(List<EventoCriminalBean> fimInstrucaoProcessual) {
		this.fimInstrucaoProcessual = fimInstrucaoProcessual;
	}

	public List<String> getNomes() {
		if(nomes == null) {
			nomes = new ArrayList<>();
		}			
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}

	public List<SuspensaoProcessoBean> getSuspensoesProcesso() {
		if(suspensoesProcesso == null) {
			suspensoesProcesso = new ArrayList<>();
		}			
		return suspensoesProcesso;
	}

	public void setSuspensoesProcesso(List<SuspensaoProcessoBean> suspensoesProcesso) {
		this.suspensoesProcesso = suspensoesProcesso;
	}
	
	public List<SentencaCondenatoriaProcessoBean> getSentencasCondenatorias() {
		if(sentencasCondenatorias == null) {
			sentencasCondenatorias = new ArrayList<>();
		}			
		return sentencasCondenatorias;
	}

	public void setSentencasCondenatorias(List<SentencaCondenatoriaProcessoBean> sentencasCondenatorias) {
		this.sentencasCondenatorias = sentencasCondenatorias;
	}

	public List<DecisaoInstanciaSuperiorBean> getDecisoesInstanciaSuperior() {
		if(decisoesInstanciaSuperior == null) {
			decisoesInstanciaSuperior = new ArrayList<>();
		}		
		return decisoesInstanciaSuperior;
	}

	public void setDecisoesInstanciaSuperior(List<DecisaoInstanciaSuperiorBean> decisoesInstanciaSuperior) {
		this.decisoesInstanciaSuperior = decisoesInstanciaSuperior;
	}

	public List<SentencaAbsolutoriaBean> getSentencasAbsolutorias() {
		if(sentencasAbsolutorias == null) {
			sentencasAbsolutorias = new ArrayList<>();
		}			
		return sentencasAbsolutorias;
	}

	public void setSentencasAbsolutorias(List<SentencaAbsolutoriaBean> sentencasAbsolutorias) {
		this.sentencasAbsolutorias = sentencasAbsolutorias;
	}
	
	public List<SentencaExtincaoPunibilidadeBean> getSentencasExtincaoPunibilidade() {
		return sentencasExtincaoPunibilidade;
	}

	public void setSentencasExtincaoPunibilidade(List<SentencaExtincaoPunibilidadeBean> sentencasExtincaoPunibilidade) {
		this.sentencasExtincaoPunibilidade = sentencasExtincaoPunibilidade;
	}

	public List<SentencaImpronunciaProcessoBean> getSentencasImpronuncia() {
		return sentencasImpronuncia;
	}

	public void setSentencasImpronuncia(List<SentencaImpronunciaProcessoBean> sentencasImpronuncia) {
		this.sentencasImpronuncia = sentencasImpronuncia;
	}

	public List<SentencaPronunciaProcessoBean> getSentencasPronuncia() {
		return sentencasPronuncia;
	}

	public void setSentencasPronuncia(List<SentencaPronunciaProcessoBean> sentencasPronuncia) {
		this.sentencasPronuncia = sentencasPronuncia;
	}
	
	public List<DesclassificacaoProcessoBean> getDesclassificacoesProcesso() {
		return desclassificacoesProcesso;
	}

	public void setDesclassificacoesProcesso(List<DesclassificacaoProcessoBean> desclassificacoesProcesso) {
		this.desclassificacoesProcesso = desclassificacoesProcesso;
	}
	
	public List<EventoCriminalBean> getNaoRecebimentoDenuncia() {
		return naoRecebimentoDenuncia;
	}

	public void setNaoRecebimentoDenuncia(List<EventoCriminalBean> naoRecebimentoDenuncia) {
		this.naoRecebimentoDenuncia = naoRecebimentoDenuncia;
	}

	public List<CitacaoProcessoBean> getCitacoes() {
		return citacoes;
	}

	public void setCitacoes(List<CitacaoProcessoBean> citacoes) {
		this.citacoes = citacoes;
	}
	
	public List<RecebimentoAditamentoDenunciaBean> getRecebimentosAditamentoDenuncia() {
		return recebimentosAditamentoDenuncia;
	}

	public void setRecebimentosAditamentoDenuncia(List<RecebimentoAditamentoDenunciaBean> recebimentosAditamentoDenuncia) {
		this.recebimentosAditamentoDenuncia = recebimentosAditamentoDenuncia;
	}
	
	public List<SentencaAbsolvicaoSumariaBean> getSentencasAbsolvicaoSumaria() {
		return sentencasAbsolvicaoSumaria;
	}

	public void setSentencasAbsolvicaoSumaria(List<SentencaAbsolvicaoSumariaBean> sentencasAbsolvicaoSumaria) {
		this.sentencasAbsolvicaoSumaria = sentencasAbsolvicaoSumaria;
	}

	public List<ArquivamentoIncidenciaBean> getArquivamentosIncidencias() {
		return arquivamentosIncidencias;
	}

	public void setArquivamentosIncidencias(List<ArquivamentoIncidenciaBean> arquivamentosIncidencias) {
		this.arquivamentosIncidencias = arquivamentosIncidencias;
	}

	public List<TrancamentoBean> getTrancamentos() {
		return trancamentos;
	}

	public void setTrancamentos(List<TrancamentoBean> trancamentos) {
		this.trancamentos = trancamentos;
	}

	public List<NulidadeProcessoBean> getNulidadesProcessos() {
		return nulidadesProcessos;
	}

	public void setNulidadesProcessos(List<NulidadeProcessoBean> nulidadesProcessos) {
		this.nulidadesProcessos = nulidadesProcessos;
	}

	public List<LitispendenciaCoisaJulgadaBean> getLitispendenciasCoisaJulgada() {
		return litispendenciasCoisaJulgada;
	}

	public void setLitispendenciasCoisaJulgada(List<LitispendenciaCoisaJulgadaBean> litispendenciasCoisaJulgada) {
		this.litispendenciasCoisaJulgada = litispendenciasCoisaJulgada;
	}
	
	public List<AusenciaCondicoesAcaoBean> getAusenciasCondicoesAcao() {
		return ausenciasCondicoesAcao;
	}
	
	public void setAusenciasCondicoesAcao(List<AusenciaCondicoesAcaoBean> ausenciaCondicoesAcaoBeans) {
		this.ausenciasCondicoesAcao = ausenciaCondicoesAcaoBeans;
	}
	
	public List<DeclinioCompetenciaBean> getDecliniosCompetencia() {
		return decliniosCompetencia;
	}
	
	public List<HomologacaoComposicaoDanoCivilBean> getHomologacoesComposicoesDanosCivis() {
		return homologacoesComposicoesDanosCivis;
	}

	public void setHomologacoesComposicoesDanosCivis(
			List<HomologacaoComposicaoDanoCivilBean> homologacoesComposicoesDanosCivis) {
		this.homologacoesComposicoesDanosCivis = homologacoesComposicoesDanosCivis;
	}

	public void setDecliniosCompetencia(List<DeclinioCompetenciaBean> decliniosCompetencia) {
		this.decliniosCompetencia = decliniosCompetencia;
	}

	public List<DesmembramentoBean> getDesmembramentos() {
		return desmembramentos;
	}
	
	public List<SentencaAbsolutoriaImpropriaBean> getSentencasAbsolutoriasImproprias() {
		return sentencasAbsolutoriasImproprias;
	}

	public void setDesmembramentos(List<DesmembramentoBean> desmembramentos) {
		this.desmembramentos = desmembramentos;
	}
	
	public void setSentencasAbsolutoriasImproprias(List<SentencaAbsolutoriaImpropriaBean> sentencasAbsolutoriasImproprias) {
		this.sentencasAbsolutoriasImproprias = sentencasAbsolutoriasImproprias;

	}
	
	public List<InternacaoBean> getInternacoes() {
		return internacoes;
	}

	public void setInternacoes(List<InternacaoBean> internacoes) {
		this.internacoes = internacoes;
	}
	
	public List<SentencaInfracionalBean> getSentencasInfracionais() {
		return sentencasInfracionais;
	}

	public void setSentencasInfracionais(List<SentencaInfracionalBean> sentencasInfracionais) {
		this.sentencasInfracionais = sentencasInfracionais;
	}
	
	public List<SentencaExtincaoMedidaSocioeducativaBean> getSentencasExtincaoMedidaSocioeducativa() {
		return sentencasExtincaoMedidaSocioeducativa;
	}

	public void setSentencasExtincaoMedidaSocioeducativa(List<SentencaExtincaoMedidaSocioeducativaBean> sentencasExtincaoMedidaSocioeducativa) {
		this.sentencasExtincaoMedidaSocioeducativa = sentencasExtincaoMedidaSocioeducativa;
	}
	
	public List<DecisaoBean> getDecisoes() {
		return decisoes;
	}

	public void setDecisoes(List<DecisaoBean> decisoes) {
		this.decisoes = decisoes;
	}
	
	public List<EvasaoBean> getEvasoes() {
		return evasoes;
	}

	public void setEvasoes(List<EvasaoBean> evasoes) {
		this.evasoes = evasoes;
	}
	
	public List<SemiliberdadeBean> getSemiliberdades() {
		return semiliberdades;
	}

	public void setSemiliberdades(List<SemiliberdadeBean> semiliberdades) {
		this.semiliberdades = semiliberdades;
	}
	
	public List<SuspensaoMedidaSocioeducativaBean> getSuspensoesMedidasSocioeducativas() {
		return suspensoesMedidasSocioeducativas;
	}

	public void setSuspensoesMedidasSocioeducativas(List<SuspensaoMedidaSocioeducativaBean> suspensoesMedidasSocioeducativas) {
		this.suspensoesMedidasSocioeducativas = suspensoesMedidasSocioeducativas;
	}

	public List<PresoOutroProcessoBean> getPresosOutroProcesso() {
		return presosOutroProcesso;
	}

	public void setPresosOutroProcesso(List<PresoOutroProcessoBean> presosOutroProcesso) {
		this.presosOutroProcesso = presosOutroProcesso;
	}
	
	public List<TransacaoPenalBean> getTransacoesPenais() {
		return transacoesPenais;
	}

	public void setTransacoesPenais(List<TransacaoPenalBean> transacoesPenais) {
		this.transacoesPenais = transacoesPenais;
	}

	public List<RevogacaoSuspensaoCondicionalProcessoBean> getRevogacoesSuspensaoCondicionalProcesso() {
		return revogacoesSuspensaoCondicionalProcesso;
	}

	public void setRevogacoesSuspensaoCondicionalProcesso(
			List<RevogacaoSuspensaoCondicionalProcessoBean> revogacoesSuspensaoCondicionalProcesso) {
		this.revogacoesSuspensaoCondicionalProcesso = revogacoesSuspensaoCondicionalProcesso;
	}
	public List<RemissaoBean> getRemissoes() {
		if (remissoes == null) {
			return new ArrayList<>();
		}
		return remissoes;
	}

	public void setRemissoes(List<RemissaoBean> remissoes) {
		this.remissoes = remissoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aditamentoDenuncia == null) ? 0 : aditamentoDenuncia.hashCode());
		result = prime * result + ((ausenciasCondicoesAcao == null) ? 0 : ausenciasCondicoesAcao.hashCode());
		result = prime * result + ((caracteristicasFisicas == null) ? 0 : caracteristicasFisicas.hashCode());
		result = prime * result + ((citacoes == null) ? 0 : citacoes.hashCode());
		result = prime * result + ((decisoesInstanciaSuperior == null) ? 0 : decisoesInstanciaSuperior.hashCode());
		result = prime * result + ((decliniosCompetencia == null) ? 0 : decliniosCompetencia.hashCode());
		result = prime * result + ((desclassificacoesProcesso == null) ? 0 : desclassificacoesProcesso.hashCode());
		result = prime * result + ((desmembramentos == null) ? 0 : desmembramentos.hashCode());
		result = prime * result + ((documentos == null) ? 0 : documentos.hashCode());
		result = prime * result + ((filiacoes == null) ? 0 : filiacoes.hashCode());
		result = prime * result + ((fugas == null) ? 0 : fugas.hashCode());
		result = prime * result
				+ ((homologacoesComposicoesDanosCivis == null) ? 0 : homologacoesComposicoesDanosCivis.hashCode());
		result = prime * result + ((indiciamento == null) ? 0 : indiciamento.hashCode());
        result = prime * result + ((inicioInstrucaoProcessual == null) ? 0 : inicioInstrucaoProcessual.hashCode());
        result = prime * result + ((fimInstrucaoProcessual == null) ? 0 : fimInstrucaoProcessual.hashCode());
		result = prime * result + ((litispendenciasCoisaJulgada == null) ? 0 : litispendenciasCoisaJulgada.hashCode());
		result = prime * result + ((naoRecebimentoDenuncia == null) ? 0 : naoRecebimentoDenuncia.hashCode());
		result = prime * result + ((nomePrincipal == null) ? 0 : nomePrincipal.hashCode());
		result = prime * result + ((nomes == null) ? 0 : nomes.hashCode());
		result = prime * result + ((nulidadesProcessos == null) ? 0 : nulidadesProcessos.hashCode());
		result = prime * result + ((oferecimentoDenuncia == null) ? 0 : oferecimentoDenuncia.hashCode());
		result = prime * result + ((presosOutroProcesso == null) ? 0 : presosOutroProcesso.hashCode());
		result = prime * result + ((prisoes == null) ? 0 : prisoes.hashCode());
		result = prime * result + ((recebimentoDenuncia == null) ? 0 : recebimentoDenuncia.hashCode());
		result = prime * result
				+ ((recebimentosAditamentoDenuncia == null) ? 0 : recebimentosAditamentoDenuncia.hashCode());
		result = prime * result + ((revogacoesSuspensaoCondicionalProcesso == null) ? 0
				: revogacoesSuspensaoCondicionalProcesso.hashCode());
		result = prime * result + ((sentencasAbsolutorias == null) ? 0 : sentencasAbsolutorias.hashCode());
		result = prime * result
				+ ((sentencasAbsolutoriasImproprias == null) ? 0 : sentencasAbsolutoriasImproprias.hashCode());
		result = prime * result + ((sentencasExtincaoPunibilidade == null) ? 0 : sentencasExtincaoPunibilidade.hashCode());
		result = prime * result + ((sentencasCondenatorias == null) ? 0 : sentencasCondenatorias.hashCode());
		result = prime * result + ((sentencasImpronuncia == null) ? 0 : sentencasImpronuncia.hashCode());
		result = prime * result + ((sentencasPronuncia == null) ? 0 : sentencasPronuncia.hashCode());
		result = prime * result + ((solturas == null) ? 0 : solturas.hashCode());
		result = prime * result + ((suspensoesProcesso == null) ? 0 : suspensoesProcesso.hashCode());
		result = prime * result + ((transacoesPenais == null) ? 0 : transacoesPenais.hashCode());
		result = prime * result + ((trancamentos == null) ? 0 : trancamentos.hashCode());
		result = prime * result + ((arquivamentosIncidencias == null) ? 0 : arquivamentosIncidencias.hashCode());
		result = prime * result + ((sentencasAbsolvicaoSumaria == null) ? 0 : sentencasAbsolvicaoSumaria.hashCode());
		result = prime * result + ((internacoes == null) ? 0 : internacoes.hashCode());
		result = prime * result + ((sentencasInfracionais == null) ? 0 : sentencasInfracionais.hashCode());
		result = prime * result + ((sentencasExtincaoMedidaSocioeducativa == null) ? 0 : sentencasExtincaoMedidaSocioeducativa.hashCode());
		result = prime * result + ((decisoes == null) ? 0 : decisoes.hashCode());
		result = prime * result + ((evasoes == null) ? 0 : evasoes.hashCode());
		result = prime * result + ((semiliberdades == null) ? 0 : semiliberdades.hashCode());
		result = prime * result + ((suspensoesMedidasSocioeducativas == null) ? 0 : suspensoesMedidasSocioeducativas.hashCode());
		result = prime * result + ((remissoes == null) ? 0 : remissoes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConteudoInformacaoCriminalBean other = (ConteudoInformacaoCriminalBean) obj;
		if (aditamentoDenuncia == null) {
			if (other.aditamentoDenuncia != null)
				return false;
		} else if (!aditamentoDenuncia.equals(other.aditamentoDenuncia))
			return false;
		if (arquivamentosIncidencias == null) {
			if (other.arquivamentosIncidencias != null)
				return false;
		} else if (!arquivamentosIncidencias.equals(other.arquivamentosIncidencias))
			return false;
		if (ausenciasCondicoesAcao == null) {
			if (other.ausenciasCondicoesAcao != null)
				return false;
		} else if (!ausenciasCondicoesAcao.equals(other.ausenciasCondicoesAcao))
			return false;
		if (caracteristicasFisicas == null) {
			if (other.caracteristicasFisicas != null)
				return false;
		} else if (!caracteristicasFisicas.equals(other.caracteristicasFisicas))
			return false;
		if (citacoes == null) {
			if (other.citacoes != null)
				return false;
		} else if (!citacoes.equals(other.citacoes))
			return false;
		if (decisoesInstanciaSuperior == null) {
			if (other.decisoesInstanciaSuperior != null)
				return false;
		} else if (!decisoesInstanciaSuperior.equals(other.decisoesInstanciaSuperior))
			return false;
		if (decliniosCompetencia == null) {
			if (other.decliniosCompetencia != null)
				return false;
		} else if (!decliniosCompetencia.equals(other.decliniosCompetencia))
			return false;
		if (desclassificacoesProcesso == null) {
			if (other.desclassificacoesProcesso != null)
				return false;
		} else if (!desclassificacoesProcesso.equals(other.desclassificacoesProcesso))
			return false;
		if (desmembramentos == null) {
			if (other.desmembramentos != null)
				return false;
		} else if (!desmembramentos.equals(other.desmembramentos))
			return false;
		if (documentos == null) {
			if (other.documentos != null)
				return false;
		} else if (!documentos.equals(other.documentos))
			return false;
		if (filiacoes == null) {
			if (other.filiacoes != null)
				return false;
		} else if (!filiacoes.equals(other.filiacoes))
			return false;
		if (fugas == null) {
			if (other.fugas != null)
				return false;
		} else if (!fugas.equals(other.fugas))
			return false;
		if (homologacoesComposicoesDanosCivis == null) {
			if (other.homologacoesComposicoesDanosCivis != null)
				return false;
		} else if (!homologacoesComposicoesDanosCivis.equals(other.homologacoesComposicoesDanosCivis))
			return false;
		if (indiciamento == null) {
			if (other.indiciamento != null)
				return false;
		} else if (!indiciamento.equals(other.indiciamento))
			return false;
        if (inicioInstrucaoProcessual == null) {
            if (other.inicioInstrucaoProcessual != null)
                return false;
        } else if (!inicioInstrucaoProcessual.equals(other.inicioInstrucaoProcessual))
            return false;
        if (fimInstrucaoProcessual == null) {
            if (other.fimInstrucaoProcessual != null)
                return false;
        } else if (!fimInstrucaoProcessual.equals(other.fimInstrucaoProcessual))
            return false;
		if (litispendenciasCoisaJulgada == null) {
			if (other.litispendenciasCoisaJulgada != null)
				return false;
		} else if (!litispendenciasCoisaJulgada.equals(other.litispendenciasCoisaJulgada))
			return false;
		if (naoRecebimentoDenuncia == null) {
			if (other.naoRecebimentoDenuncia != null)
				return false;
		} else if (!naoRecebimentoDenuncia.equals(other.naoRecebimentoDenuncia))
			return false;
		if (nomePrincipal == null) {
			if (other.nomePrincipal != null)
				return false;
		} else if (!nomePrincipal.equals(other.nomePrincipal))
			return false;
		if (nomes == null) {
			if (other.nomes != null)
				return false;
		} else if (!nomes.equals(other.nomes))
			return false;
		if (nulidadesProcessos == null) {
			if (other.nulidadesProcessos != null)
				return false;
		} else if (!nulidadesProcessos.equals(other.nulidadesProcessos))
			return false;
		if (oferecimentoDenuncia == null) {
			if (other.oferecimentoDenuncia != null)
				return false;
		} else if (!oferecimentoDenuncia.equals(other.oferecimentoDenuncia))
			return false;
		if (presosOutroProcesso == null) {
			if (other.presosOutroProcesso != null)
				return false;
		} else if (!presosOutroProcesso.equals(other.presosOutroProcesso))
			return false;
		if (prisoes == null) {
			if (other.prisoes != null)
				return false;
		} else if (!prisoes.equals(other.prisoes))
			return false;
		if (recebimentoDenuncia == null) {
			if (other.recebimentoDenuncia != null)
				return false;
		} else if (!recebimentoDenuncia.equals(other.recebimentoDenuncia))
			return false;
		if (recebimentosAditamentoDenuncia == null) {
			if (other.recebimentosAditamentoDenuncia != null)
				return false;
		} else if (!recebimentosAditamentoDenuncia.equals(other.recebimentosAditamentoDenuncia))
			return false;
		if (remissoes == null) {
			if (other.remissoes != null)
				return false;
		} else if (!remissoes.equals(other.remissoes))
			return false;
		if (revogacoesSuspensaoCondicionalProcesso == null) {
			if (other.revogacoesSuspensaoCondicionalProcesso != null)
				return false;
		} else if (!revogacoesSuspensaoCondicionalProcesso.equals(other.revogacoesSuspensaoCondicionalProcesso))
			return false;
		if (sentencasAbsolutorias == null) {
			if (other.sentencasAbsolutorias != null)
				return false;
		} else if (!sentencasAbsolutorias.equals(other.sentencasAbsolutorias))
			return false;
		if (sentencasAbsolvicaoSumaria == null) {
			if (other.sentencasAbsolvicaoSumaria != null)
				return false;
		} else if (!sentencasAbsolvicaoSumaria.equals(other.sentencasAbsolvicaoSumaria))
			return false;
		if (sentencasAbsolutoriasImproprias == null) {
			if (other.sentencasAbsolutoriasImproprias != null)
				return false;
		} else if (!sentencasAbsolutoriasImproprias.equals(other.sentencasAbsolutoriasImproprias))
			return false;
		if (sentencasExtincaoPunibilidade == null) {
			if (other.sentencasExtincaoPunibilidade != null)
				return false;
		} else if (!sentencasExtincaoPunibilidade.equals(other.sentencasExtincaoPunibilidade))
			return false;
		if (sentencasCondenatorias == null) {
			if (other.sentencasCondenatorias != null)
				return false;
		} else if (!sentencasCondenatorias.equals(other.sentencasCondenatorias))
			return false;
		if (sentencasImpronuncia == null) {
			if (other.sentencasImpronuncia != null)
				return false;
		} else if (!sentencasImpronuncia.equals(other.sentencasImpronuncia))
			return false;
		if (sentencasPronuncia == null) {
			if (other.sentencasPronuncia != null)
				return false;
		} else if (!sentencasPronuncia.equals(other.sentencasPronuncia))
			return false;
		if (solturas == null) {
			if (other.solturas != null)
				return false;
		} else if (!solturas.equals(other.solturas))
			return false;
		if (suspensoesProcesso == null) {
			if (other.suspensoesProcesso != null)
				return false;
		} else if (!suspensoesProcesso.equals(other.suspensoesProcesso))
			return false;
		if (transacoesPenais == null) {
			if (other.transacoesPenais != null)
				return false;
		} else if (!transacoesPenais.equals(other.transacoesPenais))
			return false;		
		if (trancamentos == null) {
			if (other.trancamentos != null)
				return false;
		} else if (!trancamentos.equals(other.trancamentos))
			return false;
		if (internacoes == null) {
			if (other.internacoes != null)
				return false;
		} else if (!internacoes.equals(other.internacoes))
			return false;
		if (sentencasInfracionais == null) {
			if (other.sentencasInfracionais != null)
				return false;
		} else if (!sentencasInfracionais.equals(other.sentencasInfracionais))
			return false;
		if (sentencasExtincaoMedidaSocioeducativa == null) {
			if (other.sentencasExtincaoMedidaSocioeducativa != null)
				return false;
		} else if (!sentencasExtincaoMedidaSocioeducativa.equals(other.sentencasExtincaoMedidaSocioeducativa))
			return false;
		if (decisoes == null) {
			if (other.decisoes != null)
				return false;
		} else if (!decisoes.equals(other.decisoes))
			return false;
		if (evasoes == null) {
			if (other.evasoes != null)
				return false;
		} else if (!evasoes.equals(other.evasoes))
			return false;
		if (semiliberdades == null) {
			if (other.semiliberdades != null)
				return false;
		} else if (!semiliberdades.equals(other.semiliberdades))
			return false;
		if (suspensoesMedidasSocioeducativas == null) {
			if (other.suspensoesMedidasSocioeducativas != null)
				return false;
		} else if (!suspensoesMedidasSocioeducativas.equals(other.suspensoesMedidasSocioeducativas))
			return false;
		return true;
	}

	@Override
	public ConteudoInformacaoCriminalBean clone() {
		try {
			return (ConteudoInformacaoCriminalBean) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
