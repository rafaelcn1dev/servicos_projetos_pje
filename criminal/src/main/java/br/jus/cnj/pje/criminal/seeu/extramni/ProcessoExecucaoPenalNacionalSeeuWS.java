//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.3.0 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2022.09.26 às 04:01:17 PM BRT 
//


package br.jus.cnj.pje.criminal.seeu.extramni;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de processoExecucaoPenalNacionalSeeuWS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="processoExecucaoPenalNacionalSeeuWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cidadeLocalPrisao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cidadeVaraExecucaoPenal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codAssuntoPrincipalCnj" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="codClasseProcessualCnj" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="codNivelEscolaridade" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="codRegimePena" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="codSituacaoProcesso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="codTribunal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="cpf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataDistribuicao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataInicioCumprimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataJustificativaLivramento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataJustificativaProgressao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataJustificativaTermino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataLivramentoCondicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataNascimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataProgressaoRegime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataTerminoPena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="databaseLivramentoCondicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="databaseProgressaoRegime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="digitoVerificadorRg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ehEstrangeiro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estahEmLivramentoCondicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estahEmMedidaSeguranca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estahEmMonitoramentoEletronico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estahEmPenaSubstitutiva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estahEmSursis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estahForagido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extinto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flagSituacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="justificativaLivramento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="justificativaProgressao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="justificativaTermino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="localPrisao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nacionalidadeSentenciado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="naturalidadeSentenciado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomeForum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomeMae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomePai" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomeSentenciado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomeVaraExecucaoPenal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroDocumentoEstrangeiro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroUnico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgaoExpedidorRg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="penaTotalHedionda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="penaTotalImposta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoDocumentoEstrangeiro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalCondenacaoExtintaIndultada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalInterrupcao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalRemicaoDias" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="ufLocalPrisao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ufRg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ufSentenciado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ufVaraExecucaoPenal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "processoExecucaoPenalNacionalSeeuWS", propOrder = {
    "cidadeLocalPrisao",
    "cidadeVaraExecucaoPenal",
    "codAssuntoPrincipalCnj",
    "codClasseProcessualCnj",
    "codNivelEscolaridade",
    "codRegimePena",
    "codSituacaoProcesso",
    "codTribunal",
    "cpf",
    "dataDistribuicao",
    "dataInicioCumprimento",
    "dataJustificativaLivramento",
    "dataJustificativaProgressao",
    "dataJustificativaTermino",
    "dataLivramentoCondicional",
    "dataNascimento",
    "dataProgressaoRegime",
    "dataTerminoPena",
    "databaseLivramentoCondicional",
    "databaseProgressaoRegime",
    "digitoVerificadorRg",
    "ehEstrangeiro",
    "estahEmLivramentoCondicional",
    "estahEmMedidaSeguranca",
    "estahEmMonitoramentoEletronico",
    "estahEmPenaSubstitutiva",
    "estahEmSursis",
    "estahForagido",
    "extinto",
    "flagSituacao",
    "justificativaLivramento",
    "justificativaProgressao",
    "justificativaTermino",
    "localPrisao",
    "nacionalidadeSentenciado",
    "naturalidadeSentenciado",
    "nomeForum",
    "nomeMae",
    "nomePai",
    "nomeSentenciado",
    "nomeVaraExecucaoPenal",
    "numeroDocumentoEstrangeiro",
    "numeroUnico",
    "orgaoExpedidorRg",
    "penaTotalHedionda",
    "penaTotalImposta",
    "rg",
    "sexo",
    "tipoDocumentoEstrangeiro",
    "totalCondenacaoExtintaIndultada",
    "totalInterrupcao",
    "totalRemicaoDias",
    "ufLocalPrisao",
    "ufRg",
    "ufSentenciado",
    "ufVaraExecucaoPenal"
})
public class ProcessoExecucaoPenalNacionalSeeuWS {

    protected String cidadeLocalPrisao;
    protected String cidadeVaraExecucaoPenal;
    protected Long codAssuntoPrincipalCnj;
    protected Long codClasseProcessualCnj;
    protected Integer codNivelEscolaridade;
    protected Integer codRegimePena;
    protected Integer codSituacaoProcesso;
    protected Long codTribunal;
    protected String cpf;
    protected String dataDistribuicao;
    protected String dataInicioCumprimento;
    protected String dataJustificativaLivramento;
    protected String dataJustificativaProgressao;
    protected String dataJustificativaTermino;
    protected String dataLivramentoCondicional;
    protected String dataNascimento;
    protected String dataProgressaoRegime;
    protected String dataTerminoPena;
    protected String databaseLivramentoCondicional;
    protected String databaseProgressaoRegime;
    protected String digitoVerificadorRg;
    protected String ehEstrangeiro;
    protected String estahEmLivramentoCondicional;
    protected String estahEmMedidaSeguranca;
    protected String estahEmMonitoramentoEletronico;
    protected String estahEmPenaSubstitutiva;
    protected String estahEmSursis;
    protected String estahForagido;
    protected String extinto;
    protected String flagSituacao;
    protected String justificativaLivramento;
    protected String justificativaProgressao;
    protected String justificativaTermino;
    protected String localPrisao;
    protected String nacionalidadeSentenciado;
    protected String naturalidadeSentenciado;
    protected String nomeForum;
    protected String nomeMae;
    protected String nomePai;
    protected String nomeSentenciado;
    protected String nomeVaraExecucaoPenal;
    protected String numeroDocumentoEstrangeiro;
    protected String numeroUnico;
    protected String orgaoExpedidorRg;
    protected String penaTotalHedionda;
    protected String penaTotalImposta;
    protected String rg;
    protected String sexo;
    protected String tipoDocumentoEstrangeiro;
    protected String totalCondenacaoExtintaIndultada;
    protected String totalInterrupcao;
    protected Long totalRemicaoDias;
    protected String ufLocalPrisao;
    protected String ufRg;
    protected String ufSentenciado;
    protected String ufVaraExecucaoPenal;

    /**
     * Obtém o valor da propriedade cidadeLocalPrisao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidadeLocalPrisao() {
        return cidadeLocalPrisao;
    }

    /**
     * Define o valor da propriedade cidadeLocalPrisao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidadeLocalPrisao(String value) {
        this.cidadeLocalPrisao = value;
    }

    /**
     * Obtém o valor da propriedade cidadeVaraExecucaoPenal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidadeVaraExecucaoPenal() {
        return cidadeVaraExecucaoPenal;
    }

    /**
     * Define o valor da propriedade cidadeVaraExecucaoPenal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidadeVaraExecucaoPenal(String value) {
        this.cidadeVaraExecucaoPenal = value;
    }

    /**
     * Obtém o valor da propriedade codAssuntoPrincipalCnj.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodAssuntoPrincipalCnj() {
        return codAssuntoPrincipalCnj;
    }

    /**
     * Define o valor da propriedade codAssuntoPrincipalCnj.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodAssuntoPrincipalCnj(Long value) {
        this.codAssuntoPrincipalCnj = value;
    }

    /**
     * Obtém o valor da propriedade codClasseProcessualCnj.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodClasseProcessualCnj() {
        return codClasseProcessualCnj;
    }

    /**
     * Define o valor da propriedade codClasseProcessualCnj.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodClasseProcessualCnj(Long value) {
        this.codClasseProcessualCnj = value;
    }

    /**
     * Obtém o valor da propriedade codNivelEscolaridade.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodNivelEscolaridade() {
        return codNivelEscolaridade;
    }

    /**
     * Define o valor da propriedade codNivelEscolaridade.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodNivelEscolaridade(Integer value) {
        this.codNivelEscolaridade = value;
    }

    /**
     * Obtém o valor da propriedade codRegimePena.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodRegimePena() {
        return codRegimePena;
    }

    /**
     * Define o valor da propriedade codRegimePena.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodRegimePena(Integer value) {
        this.codRegimePena = value;
    }

    /**
     * Obtém o valor da propriedade codSituacaoProcesso.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodSituacaoProcesso() {
        return codSituacaoProcesso;
    }

    /**
     * Define o valor da propriedade codSituacaoProcesso.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodSituacaoProcesso(Integer value) {
        this.codSituacaoProcesso = value;
    }

    /**
     * Obtém o valor da propriedade codTribunal.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodTribunal() {
        return codTribunal;
    }

    /**
     * Define o valor da propriedade codTribunal.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodTribunal(Long value) {
        this.codTribunal = value;
    }

    /**
     * Obtém o valor da propriedade cpf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o valor da propriedade cpf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpf(String value) {
        this.cpf = value;
    }

    /**
     * Obtém o valor da propriedade dataDistribuicao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataDistribuicao() {
        return dataDistribuicao;
    }

    /**
     * Define o valor da propriedade dataDistribuicao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataDistribuicao(String value) {
        this.dataDistribuicao = value;
    }

    /**
     * Obtém o valor da propriedade dataInicioCumprimento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataInicioCumprimento() {
        return dataInicioCumprimento;
    }

    /**
     * Define o valor da propriedade dataInicioCumprimento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataInicioCumprimento(String value) {
        this.dataInicioCumprimento = value;
    }

    /**
     * Obtém o valor da propriedade dataJustificativaLivramento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataJustificativaLivramento() {
        return dataJustificativaLivramento;
    }

    /**
     * Define o valor da propriedade dataJustificativaLivramento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataJustificativaLivramento(String value) {
        this.dataJustificativaLivramento = value;
    }

    /**
     * Obtém o valor da propriedade dataJustificativaProgressao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataJustificativaProgressao() {
        return dataJustificativaProgressao;
    }

    /**
     * Define o valor da propriedade dataJustificativaProgressao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataJustificativaProgressao(String value) {
        this.dataJustificativaProgressao = value;
    }

    /**
     * Obtém o valor da propriedade dataJustificativaTermino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataJustificativaTermino() {
        return dataJustificativaTermino;
    }

    /**
     * Define o valor da propriedade dataJustificativaTermino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataJustificativaTermino(String value) {
        this.dataJustificativaTermino = value;
    }

    /**
     * Obtém o valor da propriedade dataLivramentoCondicional.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataLivramentoCondicional() {
        return dataLivramentoCondicional;
    }

    /**
     * Define o valor da propriedade dataLivramentoCondicional.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataLivramentoCondicional(String value) {
        this.dataLivramentoCondicional = value;
    }

    /**
     * Obtém o valor da propriedade dataNascimento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Define o valor da propriedade dataNascimento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataNascimento(String value) {
        this.dataNascimento = value;
    }

    /**
     * Obtém o valor da propriedade dataProgressaoRegime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataProgressaoRegime() {
        return dataProgressaoRegime;
    }

    /**
     * Define o valor da propriedade dataProgressaoRegime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataProgressaoRegime(String value) {
        this.dataProgressaoRegime = value;
    }

    /**
     * Obtém o valor da propriedade dataTerminoPena.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataTerminoPena() {
        return dataTerminoPena;
    }

    /**
     * Define o valor da propriedade dataTerminoPena.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataTerminoPena(String value) {
        this.dataTerminoPena = value;
    }

    /**
     * Obtém o valor da propriedade databaseLivramentoCondicional.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabaseLivramentoCondicional() {
        return databaseLivramentoCondicional;
    }

    /**
     * Define o valor da propriedade databaseLivramentoCondicional.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabaseLivramentoCondicional(String value) {
        this.databaseLivramentoCondicional = value;
    }

    /**
     * Obtém o valor da propriedade databaseProgressaoRegime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatabaseProgressaoRegime() {
        return databaseProgressaoRegime;
    }

    /**
     * Define o valor da propriedade databaseProgressaoRegime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatabaseProgressaoRegime(String value) {
        this.databaseProgressaoRegime = value;
    }

    /**
     * Obtém o valor da propriedade digitoVerificadorRg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDigitoVerificadorRg() {
        return digitoVerificadorRg;
    }

    /**
     * Define o valor da propriedade digitoVerificadorRg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigitoVerificadorRg(String value) {
        this.digitoVerificadorRg = value;
    }

    /**
     * Obtém o valor da propriedade ehEstrangeiro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEhEstrangeiro() {
        return ehEstrangeiro;
    }

    /**
     * Define o valor da propriedade ehEstrangeiro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEhEstrangeiro(String value) {
        this.ehEstrangeiro = value;
    }

    /**
     * Obtém o valor da propriedade estahEmLivramentoCondicional.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstahEmLivramentoCondicional() {
        return estahEmLivramentoCondicional;
    }

    /**
     * Define o valor da propriedade estahEmLivramentoCondicional.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstahEmLivramentoCondicional(String value) {
        this.estahEmLivramentoCondicional = value;
    }

    /**
     * Obtém o valor da propriedade estahEmMedidaSeguranca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstahEmMedidaSeguranca() {
        return estahEmMedidaSeguranca;
    }

    /**
     * Define o valor da propriedade estahEmMedidaSeguranca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstahEmMedidaSeguranca(String value) {
        this.estahEmMedidaSeguranca = value;
    }

    /**
     * Obtém o valor da propriedade estahEmMonitoramentoEletronico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstahEmMonitoramentoEletronico() {
        return estahEmMonitoramentoEletronico;
    }

    /**
     * Define o valor da propriedade estahEmMonitoramentoEletronico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstahEmMonitoramentoEletronico(String value) {
        this.estahEmMonitoramentoEletronico = value;
    }

    /**
     * Obtém o valor da propriedade estahEmPenaSubstitutiva.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstahEmPenaSubstitutiva() {
        return estahEmPenaSubstitutiva;
    }

    /**
     * Define o valor da propriedade estahEmPenaSubstitutiva.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstahEmPenaSubstitutiva(String value) {
        this.estahEmPenaSubstitutiva = value;
    }

    /**
     * Obtém o valor da propriedade estahEmSursis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstahEmSursis() {
        return estahEmSursis;
    }

    /**
     * Define o valor da propriedade estahEmSursis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstahEmSursis(String value) {
        this.estahEmSursis = value;
    }

    /**
     * Obtém o valor da propriedade estahForagido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstahForagido() {
        return estahForagido;
    }

    /**
     * Define o valor da propriedade estahForagido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstahForagido(String value) {
        this.estahForagido = value;
    }

    /**
     * Obtém o valor da propriedade extinto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtinto() {
        return extinto;
    }

    /**
     * Define o valor da propriedade extinto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtinto(String value) {
        this.extinto = value;
    }

    /**
     * Obtém o valor da propriedade flagSituacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagSituacao() {
        return flagSituacao;
    }

    /**
     * Define o valor da propriedade flagSituacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagSituacao(String value) {
        this.flagSituacao = value;
    }

    /**
     * Obtém o valor da propriedade justificativaLivramento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustificativaLivramento() {
        return justificativaLivramento;
    }

    /**
     * Define o valor da propriedade justificativaLivramento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustificativaLivramento(String value) {
        this.justificativaLivramento = value;
    }

    /**
     * Obtém o valor da propriedade justificativaProgressao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustificativaProgressao() {
        return justificativaProgressao;
    }

    /**
     * Define o valor da propriedade justificativaProgressao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustificativaProgressao(String value) {
        this.justificativaProgressao = value;
    }

    /**
     * Obtém o valor da propriedade justificativaTermino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustificativaTermino() {
        return justificativaTermino;
    }

    /**
     * Define o valor da propriedade justificativaTermino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustificativaTermino(String value) {
        this.justificativaTermino = value;
    }

    /**
     * Obtém o valor da propriedade localPrisao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalPrisao() {
        return localPrisao;
    }

    /**
     * Define o valor da propriedade localPrisao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalPrisao(String value) {
        this.localPrisao = value;
    }

    /**
     * Obtém o valor da propriedade nacionalidadeSentenciado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidadeSentenciado() {
        return nacionalidadeSentenciado;
    }

    /**
     * Define o valor da propriedade nacionalidadeSentenciado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidadeSentenciado(String value) {
        this.nacionalidadeSentenciado = value;
    }

    /**
     * Obtém o valor da propriedade naturalidadeSentenciado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaturalidadeSentenciado() {
        return naturalidadeSentenciado;
    }

    /**
     * Define o valor da propriedade naturalidadeSentenciado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaturalidadeSentenciado(String value) {
        this.naturalidadeSentenciado = value;
    }

    /**
     * Obtém o valor da propriedade nomeForum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeForum() {
        return nomeForum;
    }

    /**
     * Define o valor da propriedade nomeForum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeForum(String value) {
        this.nomeForum = value;
    }

    /**
     * Obtém o valor da propriedade nomeMae.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeMae() {
        return nomeMae;
    }

    /**
     * Define o valor da propriedade nomeMae.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeMae(String value) {
        this.nomeMae = value;
    }

    /**
     * Obtém o valor da propriedade nomePai.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomePai() {
        return nomePai;
    }

    /**
     * Define o valor da propriedade nomePai.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomePai(String value) {
        this.nomePai = value;
    }

    /**
     * Obtém o valor da propriedade nomeSentenciado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeSentenciado() {
        return nomeSentenciado;
    }

    /**
     * Define o valor da propriedade nomeSentenciado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeSentenciado(String value) {
        this.nomeSentenciado = value;
    }

    /**
     * Obtém o valor da propriedade nomeVaraExecucaoPenal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeVaraExecucaoPenal() {
        return nomeVaraExecucaoPenal;
    }

    /**
     * Define o valor da propriedade nomeVaraExecucaoPenal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeVaraExecucaoPenal(String value) {
        this.nomeVaraExecucaoPenal = value;
    }

    /**
     * Obtém o valor da propriedade numeroDocumentoEstrangeiro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDocumentoEstrangeiro() {
        return numeroDocumentoEstrangeiro;
    }

    /**
     * Define o valor da propriedade numeroDocumentoEstrangeiro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDocumentoEstrangeiro(String value) {
        this.numeroDocumentoEstrangeiro = value;
    }

    /**
     * Obtém o valor da propriedade numeroUnico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroUnico() {
        return numeroUnico;
    }

    /**
     * Define o valor da propriedade numeroUnico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroUnico(String value) {
        this.numeroUnico = value;
    }

    /**
     * Obtém o valor da propriedade orgaoExpedidorRg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgaoExpedidorRg() {
        return orgaoExpedidorRg;
    }

    /**
     * Define o valor da propriedade orgaoExpedidorRg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgaoExpedidorRg(String value) {
        this.orgaoExpedidorRg = value;
    }

    /**
     * Obtém o valor da propriedade penaTotalHedionda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenaTotalHedionda() {
        return penaTotalHedionda;
    }

    /**
     * Define o valor da propriedade penaTotalHedionda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenaTotalHedionda(String value) {
        this.penaTotalHedionda = value;
    }

    /**
     * Obtém o valor da propriedade penaTotalImposta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenaTotalImposta() {
        return penaTotalImposta;
    }

    /**
     * Define o valor da propriedade penaTotalImposta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenaTotalImposta(String value) {
        this.penaTotalImposta = value;
    }

    /**
     * Obtém o valor da propriedade rg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRg() {
        return rg;
    }

    /**
     * Define o valor da propriedade rg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRg(String value) {
        this.rg = value;
    }

    /**
     * Obtém o valor da propriedade sexo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Define o valor da propriedade sexo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Obtém o valor da propriedade tipoDocumentoEstrangeiro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocumentoEstrangeiro() {
        return tipoDocumentoEstrangeiro;
    }

    /**
     * Define o valor da propriedade tipoDocumentoEstrangeiro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocumentoEstrangeiro(String value) {
        this.tipoDocumentoEstrangeiro = value;
    }

    /**
     * Obtém o valor da propriedade totalCondenacaoExtintaIndultada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalCondenacaoExtintaIndultada() {
        return totalCondenacaoExtintaIndultada;
    }

    /**
     * Define o valor da propriedade totalCondenacaoExtintaIndultada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalCondenacaoExtintaIndultada(String value) {
        this.totalCondenacaoExtintaIndultada = value;
    }

    /**
     * Obtém o valor da propriedade totalInterrupcao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalInterrupcao() {
        return totalInterrupcao;
    }

    /**
     * Define o valor da propriedade totalInterrupcao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalInterrupcao(String value) {
        this.totalInterrupcao = value;
    }

    /**
     * Obtém o valor da propriedade totalRemicaoDias.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalRemicaoDias() {
        return totalRemicaoDias;
    }

    /**
     * Define o valor da propriedade totalRemicaoDias.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalRemicaoDias(Long value) {
        this.totalRemicaoDias = value;
    }

    /**
     * Obtém o valor da propriedade ufLocalPrisao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfLocalPrisao() {
        return ufLocalPrisao;
    }

    /**
     * Define o valor da propriedade ufLocalPrisao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfLocalPrisao(String value) {
        this.ufLocalPrisao = value;
    }

    /**
     * Obtém o valor da propriedade ufRg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfRg() {
        return ufRg;
    }

    /**
     * Define o valor da propriedade ufRg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfRg(String value) {
        this.ufRg = value;
    }

    /**
     * Obtém o valor da propriedade ufSentenciado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfSentenciado() {
        return ufSentenciado;
    }

    /**
     * Define o valor da propriedade ufSentenciado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfSentenciado(String value) {
        this.ufSentenciado = value;
    }

    /**
     * Obtém o valor da propriedade ufVaraExecucaoPenal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfVaraExecucaoPenal() {
        return ufVaraExecucaoPenal;
    }

    /**
     * Define o valor da propriedade ufVaraExecucaoPenal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfVaraExecucaoPenal(String value) {
        this.ufVaraExecucaoPenal = value;
    }

}
