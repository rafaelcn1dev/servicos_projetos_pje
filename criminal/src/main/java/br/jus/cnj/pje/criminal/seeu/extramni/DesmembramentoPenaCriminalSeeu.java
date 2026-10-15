//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.3.0 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2022.09.26 às 04:01:17 PM BRT 
//


package br.jus.cnj.pje.criminal.seeu.extramni;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java de desmembramentoPenaCriminalSeeu complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="desmembramentoPenaCriminalSeeu"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="artigoLei" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codItemPenaExecucao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codPenaExecucao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataDelito" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataExtincao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataLei" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataSuspensao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="diasMulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extinto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extintoCumprimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fracaoLivramentoCondicional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fracaoProgressaoRegime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="manterFracao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroLei" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pagouMulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reincidenteComum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="suspenso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tempoPena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="valorMulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "desmembramentoPenaCriminalSeeu", propOrder = {
    "artigoLei",
    "codItemPenaExecucao",
    "codPenaExecucao",
    "complemento",
    "dataDelito",
    "dataExtincao",
    "dataLei",
    "dataSuspensao",
    "diasMulta",
    "extinto",
    "extintoCumprimento",
    "fracaoLivramentoCondicional",
    "fracaoProgressaoRegime",
    "manterFracao",
    "numeroLei",
    "pagouMulta",
    "reincidenteComum",
    "suspenso",
    "tempoPena",
    "valorMulta"
})
public class DesmembramentoPenaCriminalSeeu {

    protected String artigoLei;
    protected String codItemPenaExecucao;
    protected String codPenaExecucao;
    protected String complemento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDelito;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataExtincao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataLei;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataSuspensao;
    protected String diasMulta;
    protected String extinto;
    protected String extintoCumprimento;
    protected String fracaoLivramentoCondicional;
    protected String fracaoProgressaoRegime;
    protected String manterFracao;
    protected String numeroLei;
    protected String pagouMulta;
    protected String reincidenteComum;
    protected String suspenso;
    protected String tempoPena;
    protected String valorMulta;

    /**
     * Obtém o valor da propriedade artigoLei.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtigoLei() {
        return artigoLei;
    }

    /**
     * Define o valor da propriedade artigoLei.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtigoLei(String value) {
        this.artigoLei = value;
    }

    /**
     * Obtém o valor da propriedade codItemPenaExecucao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodItemPenaExecucao() {
        return codItemPenaExecucao;
    }

    /**
     * Define o valor da propriedade codItemPenaExecucao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodItemPenaExecucao(String value) {
        this.codItemPenaExecucao = value;
    }

    /**
     * Obtém o valor da propriedade codPenaExecucao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPenaExecucao() {
        return codPenaExecucao;
    }

    /**
     * Define o valor da propriedade codPenaExecucao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPenaExecucao(String value) {
        this.codPenaExecucao = value;
    }

    /**
     * Obtém o valor da propriedade complemento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Define o valor da propriedade complemento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplemento(String value) {
        this.complemento = value;
    }

    /**
     * Obtém o valor da propriedade dataDelito.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDelito() {
        return dataDelito;
    }

    /**
     * Define o valor da propriedade dataDelito.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDelito(XMLGregorianCalendar value) {
        this.dataDelito = value;
    }

    /**
     * Obtém o valor da propriedade dataExtincao.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataExtincao() {
        return dataExtincao;
    }

    /**
     * Define o valor da propriedade dataExtincao.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataExtincao(XMLGregorianCalendar value) {
        this.dataExtincao = value;
    }

    /**
     * Obtém o valor da propriedade dataLei.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataLei() {
        return dataLei;
    }

    /**
     * Define o valor da propriedade dataLei.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataLei(XMLGregorianCalendar value) {
        this.dataLei = value;
    }

    /**
     * Obtém o valor da propriedade dataSuspensao.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSuspensao() {
        return dataSuspensao;
    }

    /**
     * Define o valor da propriedade dataSuspensao.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSuspensao(XMLGregorianCalendar value) {
        this.dataSuspensao = value;
    }

    /**
     * Obtém o valor da propriedade diasMulta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiasMulta() {
        return diasMulta;
    }

    /**
     * Define o valor da propriedade diasMulta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiasMulta(String value) {
        this.diasMulta = value;
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
     * Obtém o valor da propriedade extintoCumprimento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtintoCumprimento() {
        return extintoCumprimento;
    }

    /**
     * Define o valor da propriedade extintoCumprimento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtintoCumprimento(String value) {
        this.extintoCumprimento = value;
    }

    /**
     * Obtém o valor da propriedade fracaoLivramentoCondicional.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFracaoLivramentoCondicional() {
        return fracaoLivramentoCondicional;
    }

    /**
     * Define o valor da propriedade fracaoLivramentoCondicional.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFracaoLivramentoCondicional(String value) {
        this.fracaoLivramentoCondicional = value;
    }

    /**
     * Obtém o valor da propriedade fracaoProgressaoRegime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFracaoProgressaoRegime() {
        return fracaoProgressaoRegime;
    }

    /**
     * Define o valor da propriedade fracaoProgressaoRegime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFracaoProgressaoRegime(String value) {
        this.fracaoProgressaoRegime = value;
    }

    /**
     * Obtém o valor da propriedade manterFracao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManterFracao() {
        return manterFracao;
    }

    /**
     * Define o valor da propriedade manterFracao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManterFracao(String value) {
        this.manterFracao = value;
    }

    /**
     * Obtém o valor da propriedade numeroLei.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroLei() {
        return numeroLei;
    }

    /**
     * Define o valor da propriedade numeroLei.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroLei(String value) {
        this.numeroLei = value;
    }

    /**
     * Obtém o valor da propriedade pagouMulta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagouMulta() {
        return pagouMulta;
    }

    /**
     * Define o valor da propriedade pagouMulta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagouMulta(String value) {
        this.pagouMulta = value;
    }

    /**
     * Obtém o valor da propriedade reincidenteComum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReincidenteComum() {
        return reincidenteComum;
    }

    /**
     * Define o valor da propriedade reincidenteComum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReincidenteComum(String value) {
        this.reincidenteComum = value;
    }

    /**
     * Obtém o valor da propriedade suspenso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuspenso() {
        return suspenso;
    }

    /**
     * Define o valor da propriedade suspenso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuspenso(String value) {
        this.suspenso = value;
    }

    /**
     * Obtém o valor da propriedade tempoPena.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTempoPena() {
        return tempoPena;
    }

    /**
     * Define o valor da propriedade tempoPena.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTempoPena(String value) {
        this.tempoPena = value;
    }

    /**
     * Obtém o valor da propriedade valorMulta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorMulta() {
        return valorMulta;
    }

    /**
     * Define o valor da propriedade valorMulta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorMulta(String value) {
        this.valorMulta = value;
    }

}
