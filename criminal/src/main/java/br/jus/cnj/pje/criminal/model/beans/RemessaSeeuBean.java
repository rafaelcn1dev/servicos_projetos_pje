package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.Processo;

/**
 * Representa o payload encaminhado pelo frontend.
 * @author adriano.silva
 *
 */
public class RemessaSeeuBean extends BaseBean{

	private static final long serialVersionUID = 1L;

	private Long idProcessoLegacy;
	private Processo processoCriminal;
	private String orgaoJulgador;
	private Date dataDistribuicao;
	private GuiaSeeuBean guia;
	private ReuBean reu;
	private EventoCriminalBean recebimentoDenuncia;
	private SentencaCondenatoriaProcessoBean sentencaCondenatoria;
	private SentencaPronunciaProcessoBean sentencaPronuncia;
	private DecisaoInstanciaSuperiorBean decisaoInstanciaSuperior;
	private String observacao;
	private List<FugaBean> fugas;
	private List<PrisaoBean> prisoes;
	private List<SolturaBean> solturas;
	private List<DocumentoSeeuBean> documentos;

	public RemessaSeeuBean() {
		super();
	}
	
	public Long getIdProcessoLegacy() {
		return idProcessoLegacy;
	}

	public void setIdProcessoLegacy(Long idProcessoLegacy) {
		this.idProcessoLegacy = idProcessoLegacy;
	}

	public Processo getProcessoCriminal() {
		return processoCriminal;
	}

	public void setProcessoCriminal(Processo processoCriminal) {
		this.processoCriminal = processoCriminal;
	}

	public String getOrgaoJulgador() {
		return orgaoJulgador;
	}

	public void setOrgaoJulgador(String orgaoJulgador) {
		this.orgaoJulgador = orgaoJulgador;
	}

	public Date getDataDistribuicao() {
		return dataDistribuicao;
	}

	public void setDataDistribuicao(Date dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}

	public ReuBean getReu() {
		return reu;
	}

	public void setReu(ReuBean reu) {
		this.reu = reu;
	}

	public EventoCriminalBean getRecebimentoDenuncia() {
		return recebimentoDenuncia;
	}

	public void setRecebimentoDenuncia(EventoCriminalBean recebimentoDenuncia) {
		this.recebimentoDenuncia = recebimentoDenuncia;
	}

	public SentencaCondenatoriaProcessoBean getSentencaCondenatoria() {
		return sentencaCondenatoria;
	}

	public void setSentencaCondenatoria(SentencaCondenatoriaProcessoBean sentencaCondenatoria) {
		this.sentencaCondenatoria = sentencaCondenatoria;
	}

	public SentencaPronunciaProcessoBean getSentencaPronuncia() {
		return sentencaPronuncia;
	}

	public void setSentencaPronuncia(SentencaPronunciaProcessoBean sentencaPronuncia) {
		this.sentencaPronuncia = sentencaPronuncia;
	}

	public DecisaoInstanciaSuperiorBean getDecisaoInstanciaSuperior() {
		return decisaoInstanciaSuperior;
	}

	public void setDecisaoInstanciaSuperior(DecisaoInstanciaSuperiorBean decisaoInstanciaSuperior) {
		this.decisaoInstanciaSuperior = decisaoInstanciaSuperior;
	}

	public List<FugaBean> getFugas() {
		return fugas;
	}

	public void setFugas(List<FugaBean> fugas) {
		this.fugas = fugas;
	}

	public List<PrisaoBean> getPrisoes() {
		return prisoes;
	}

	public void setPrisoes(List<PrisaoBean> prisoes) {
		this.prisoes = prisoes;
	}

	public List<SolturaBean> getSolturas() {
		return solturas;
	}

	public void setSolturas(List<SolturaBean> solturas) {
		this.solturas = solturas;
	}

	public List<DocumentoSeeuBean> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentoSeeuBean> documentos) {
		this.documentos = documentos;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}	

	public GuiaSeeuBean getGuia() {
		return guia;
	}

	public void setGuia(GuiaSeeuBean guia) {
		this.guia = guia;
	}
}