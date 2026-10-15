//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.3.0 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2022.09.26 às 04:01:17 PM BRT 
//


package br.jus.cnj.pje.criminal.seeu.extramni;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de sentenciadoSeeu complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="sentenciadoSeeu"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="assuntoPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="classe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codAssuntoPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codClasse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codForum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codTribunal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codVaraExecucaoPenal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cpf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataDistribuicao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataInicioCumprimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataJustificativaLivramento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataJustificativaProgressao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataJustificativaTerminoPena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataLivramentoCondicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataNascimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataProgressaoRegime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataTerminoPena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="databaseLivramentoCondicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="databaseProgressaoRegime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estahEmLivramentoCondicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estahEmPenaSubstitutiva" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estahEmSursis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estahForagido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estrangeiro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extinto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flagRegime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flagSituacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="justificativaLivramento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="justificativaProgressao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="justificativaTerminoPena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listaAdvogadoParteSeeu" type="{http://impl.seeu.webservice.projudi.gov/}advogadoParteSeeu" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="medidaSeguranca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="naturalidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nivelSigiloProcesso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomeForum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomeMae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomePai" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomeTribunal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomeVaraExecucaoPenal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroDocumentoEstrangeiro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroProcesso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroRji" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroUnico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="penaTotalCumprida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="penaTotalImposta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="penaTotalRestante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="prisaoDomiciliar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="regimePena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rgOrgaoExpedidor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rgParte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="siglaUfRg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="statusProcesso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoPenal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalCondenacaoExtintaIndultada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalInterrupcao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalRemicaoDias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sentenciadoSeeu", propOrder = {
    "assuntoPrincipal",
    "classe",
    "codAssuntoPrincipal",
    "codClasse",
    "codForum",
    "codPais",
    "codTribunal",
    "codVaraExecucaoPenal",
    "cpf",
    "dataDistribuicao",
    "dataInicioCumprimento",
    "dataJustificativaLivramento",
    "dataJustificativaProgressao",
    "dataJustificativaTerminoPena",
    "dataLivramentoCondicional",
    "dataNascimento",
    "dataProgressaoRegime",
    "dataTerminoPena",
    "databaseLivramentoCondicional",
    "databaseProgressaoRegime",
    "estahEmLivramentoCondicional",
    "estahEmPenaSubstitutiva",
    "estahEmSursis",
    "estahForagido",
    "estrangeiro",
    "extinto",
    "flagRegime",
    "flagSituacao",
    "justificativaLivramento",
    "justificativaProgressao",
    "justificativaTerminoPena",
    "listaAdvogadoParteSeeu",
    "medidaSeguranca",
    "naturalidade",
    "nivelSigiloProcesso",
    "nome",
    "nomeForum",
    "nomeMae",
    "nomePai",
    "nomeTribunal",
    "nomeVaraExecucaoPenal",
    "numeroDocumentoEstrangeiro",
    "numeroProcesso",
    "numeroRji",
    "numeroUnico",
    "pais",
    "penaTotalCumprida",
    "penaTotalImposta",
    "penaTotalRestante",
    "prisaoDomiciliar",
    "regimePena",
    "rgOrgaoExpedidor",
    "rgParte",
    "sexo",
    "siglaUfRg",
    "statusProcesso",
    "tipoPenal",
    "totalCondenacaoExtintaIndultada",
    "totalInterrupcao",
    "totalRemicaoDias"
})
public class SentenciadoSeeu {

    protected String assuntoPrincipal;
    protected String classe;
    protected String codAssuntoPrincipal;
    protected String codClasse;
    protected String codForum;
    protected String codPais;
    protected String codTribunal;
    protected String codVaraExecucaoPenal;
    protected String cpf;
    protected String dataDistribuicao;
    protected String dataInicioCumprimento;
    protected String dataJustificativaLivramento;
    protected String dataJustificativaProgressao;
    protected String dataJustificativaTerminoPena;
    protected String dataLivramentoCondicional;
    protected String dataNascimento;
    protected String dataProgressaoRegime;
    protected String dataTerminoPena;
    protected String databaseLivramentoCondicional;
    protected String databaseProgressaoRegime;
    protected String estahEmLivramentoCondicional;
    protected String estahEmPenaSubstitutiva;
    protected String estahEmSursis;
    protected String estahForagido;
    protected String estrangeiro;
    protected String extinto;
    protected String flagRegime;
    protected String flagSituacao;
    protected String justificativaLivramento;
    protected String justificativaProgressao;
    protected String justificativaTerminoPena;
    @XmlElement(nillable = true)
    protected List<AdvogadoParteSeeu> listaAdvogadoParteSeeu;
    protected String medidaSeguranca;
    protected String naturalidade;
    protected String nivelSigiloProcesso;
    protected String nome;
    protected String nomeForum;
    protected String nomeMae;
    protected String nomePai;
    protected String nomeTribunal;
    protected String nomeVaraExecucaoPenal;
    protected String numeroDocumentoEstrangeiro;
    protected String numeroProcesso;
    protected String numeroRji;
    protected String numeroUnico;
    protected String pais;
    protected String penaTotalCumprida;
    protected String penaTotalImposta;
    protected String penaTotalRestante;
    protected String prisaoDomiciliar;
    protected String regimePena;
    protected String rgOrgaoExpedidor;
    protected String rgParte;
    protected String sexo;
    protected String siglaUfRg;
    protected String statusProcesso;
    protected String tipoPenal;
    protected String totalCondenacaoExtintaIndultada;
    protected String totalInterrupcao;
    protected String totalRemicaoDias;

    /**
     * Obtém o valor da propriedade assuntoPrincipal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssuntoPrincipal() {
        return assuntoPrincipal;
    }

    /**
     * Define o valor da propriedade assuntoPrincipal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssuntoPrincipal(String value) {
        this.assuntoPrincipal = value;
    }

    /**
     * Obtém o valor da propriedade classe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClasse() {
        return classe;
    }

    /**
     * Define o valor da propriedade classe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClasse(String value) {
        this.classe = value;
    }

    /**
     * Obtém o valor da propriedade codAssuntoPrincipal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAssuntoPrincipal() {
        return codAssuntoPrincipal;
    }

    /**
     * Define o valor da propriedade codAssuntoPrincipal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAssuntoPrincipal(String value) {
        this.codAssuntoPrincipal = value;
    }

    /**
     * Obtém o valor da propriedade codClasse.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodClasse() {
        return codClasse;
    }

    /**
     * Define o valor da propriedade codClasse.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodClasse(String value) {
        this.codClasse = value;
    }

    /**
     * Obtém o valor da propriedade codForum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodForum() {
        return codForum;
    }

    /**
     * Define o valor da propriedade codForum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodForum(String value) {
        this.codForum = value;
    }

    /**
     * Obtém o valor da propriedade codPais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPais() {
        return codPais;
    }

    /**
     * Define o valor da propriedade codPais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPais(String value) {
        this.codPais = value;
    }

    /**
     * Obtém o valor da propriedade codTribunal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodTribunal() {
        return codTribunal;
    }

    /**
     * Define o valor da propriedade codTribunal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodTribunal(String value) {
        this.codTribunal = value;
    }

    /**
     * Obtém o valor da propriedade codVaraExecucaoPenal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodVaraExecucaoPenal() {
        return codVaraExecucaoPenal;
    }

    /**
     * Define o valor da propriedade codVaraExecucaoPenal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodVaraExecucaoPenal(String value) {
        this.codVaraExecucaoPenal = value;
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
     * Obtém o valor da propriedade dataJustificativaTerminoPena.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataJustificativaTerminoPena() {
        return dataJustificativaTerminoPena;
    }

    /**
     * Define o valor da propriedade dataJustificativaTerminoPena.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataJustificativaTerminoPena(String value) {
        this.dataJustificativaTerminoPena = value;
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
     * Obtém o valor da propriedade estrangeiro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstrangeiro() {
        return estrangeiro;
    }

    /**
     * Define o valor da propriedade estrangeiro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstrangeiro(String value) {
        this.estrangeiro = value;
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
     * Obtém o valor da propriedade flagRegime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagRegime() {
        return flagRegime;
    }

    /**
     * Define o valor da propriedade flagRegime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagRegime(String value) {
        this.flagRegime = value;
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
     * Obtém o valor da propriedade justificativaTerminoPena.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustificativaTerminoPena() {
        return justificativaTerminoPena;
    }

    /**
     * Define o valor da propriedade justificativaTerminoPena.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustificativaTerminoPena(String value) {
        this.justificativaTerminoPena = value;
    }

    /**
     * Gets the value of the listaAdvogadoParteSeeu property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaAdvogadoParteSeeu property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaAdvogadoParteSeeu().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdvogadoParteSeeu }
     * 
     * 
     */
    public List<AdvogadoParteSeeu> getListaAdvogadoParteSeeu() {
        if (listaAdvogadoParteSeeu == null) {
            listaAdvogadoParteSeeu = new ArrayList<AdvogadoParteSeeu>();
        }
        return this.listaAdvogadoParteSeeu;
    }

    /**
     * Obtém o valor da propriedade medidaSeguranca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedidaSeguranca() {
        return medidaSeguranca;
    }

    /**
     * Define o valor da propriedade medidaSeguranca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedidaSeguranca(String value) {
        this.medidaSeguranca = value;
    }

    /**
     * Obtém o valor da propriedade naturalidade.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaturalidade() {
        return naturalidade;
    }

    /**
     * Define o valor da propriedade naturalidade.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaturalidade(String value) {
        this.naturalidade = value;
    }

    /**
     * Obtém o valor da propriedade nivelSigiloProcesso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivelSigiloProcesso() {
        return nivelSigiloProcesso;
    }

    /**
     * Define o valor da propriedade nivelSigiloProcesso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivelSigiloProcesso(String value) {
        this.nivelSigiloProcesso = value;
    }

    /**
     * Obtém o valor da propriedade nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o valor da propriedade nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
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
     * Obtém o valor da propriedade nomeTribunal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeTribunal() {
        return nomeTribunal;
    }

    /**
     * Define o valor da propriedade nomeTribunal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeTribunal(String value) {
        this.nomeTribunal = value;
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
     * Obtém o valor da propriedade numeroProcesso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    /**
     * Define o valor da propriedade numeroProcesso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroProcesso(String value) {
        this.numeroProcesso = value;
    }

    /**
     * Obtém o valor da propriedade numeroRji.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRji() {
        return numeroRji;
    }

    /**
     * Define o valor da propriedade numeroRji.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRji(String value) {
        this.numeroRji = value;
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
     * Obtém o valor da propriedade pais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Define o valor da propriedade pais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Obtém o valor da propriedade penaTotalCumprida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenaTotalCumprida() {
        return penaTotalCumprida;
    }

    /**
     * Define o valor da propriedade penaTotalCumprida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenaTotalCumprida(String value) {
        this.penaTotalCumprida = value;
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
     * Obtém o valor da propriedade penaTotalRestante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenaTotalRestante() {
        return penaTotalRestante;
    }

    /**
     * Define o valor da propriedade penaTotalRestante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenaTotalRestante(String value) {
        this.penaTotalRestante = value;
    }

    /**
     * Obtém o valor da propriedade prisaoDomiciliar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrisaoDomiciliar() {
        return prisaoDomiciliar;
    }

    /**
     * Define o valor da propriedade prisaoDomiciliar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrisaoDomiciliar(String value) {
        this.prisaoDomiciliar = value;
    }

    /**
     * Obtém o valor da propriedade regimePena.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegimePena() {
        return regimePena;
    }

    /**
     * Define o valor da propriedade regimePena.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegimePena(String value) {
        this.regimePena = value;
    }

    /**
     * Obtém o valor da propriedade rgOrgaoExpedidor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRgOrgaoExpedidor() {
        return rgOrgaoExpedidor;
    }

    /**
     * Define o valor da propriedade rgOrgaoExpedidor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRgOrgaoExpedidor(String value) {
        this.rgOrgaoExpedidor = value;
    }

    /**
     * Obtém o valor da propriedade rgParte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRgParte() {
        return rgParte;
    }

    /**
     * Define o valor da propriedade rgParte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRgParte(String value) {
        this.rgParte = value;
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
     * Obtém o valor da propriedade siglaUfRg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiglaUfRg() {
        return siglaUfRg;
    }

    /**
     * Define o valor da propriedade siglaUfRg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiglaUfRg(String value) {
        this.siglaUfRg = value;
    }

    /**
     * Obtém o valor da propriedade statusProcesso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusProcesso() {
        return statusProcesso;
    }

    /**
     * Define o valor da propriedade statusProcesso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusProcesso(String value) {
        this.statusProcesso = value;
    }

    /**
     * Obtém o valor da propriedade tipoPenal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPenal() {
        return tipoPenal;
    }

    /**
     * Define o valor da propriedade tipoPenal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPenal(String value) {
        this.tipoPenal = value;
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
     *     {@link String }
     *     
     */
    public String getTotalRemicaoDias() {
        return totalRemicaoDias;
    }

    /**
     * Define o valor da propriedade totalRemicaoDias.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalRemicaoDias(String value) {
        this.totalRemicaoDias = value;
    }

}
