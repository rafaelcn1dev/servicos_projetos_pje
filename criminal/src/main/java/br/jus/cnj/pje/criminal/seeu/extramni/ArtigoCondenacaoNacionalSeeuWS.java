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
 * <p>Classe Java de artigoCondenacaoNacionalSeeuWS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="artigoCondenacaoNacionalSeeuWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="anoLei" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="artigoLei" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ehHediondo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroLei" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroUnicoAcaoCriminal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="observacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="penaTotalArtigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="penaTotalArtigoEmDias" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "artigoCondenacaoNacionalSeeuWS", propOrder = {
    "anoLei",
    "artigoLei",
    "ehHediondo",
    "numeroLei",
    "numeroUnicoAcaoCriminal",
    "observacao",
    "penaTotalArtigo",
    "penaTotalArtigoEmDias"
})
public class ArtigoCondenacaoNacionalSeeuWS {

    protected Integer anoLei;
    protected String artigoLei;
    protected String ehHediondo;
    protected String numeroLei;
    protected String numeroUnicoAcaoCriminal;
    protected String observacao;
    protected String penaTotalArtigo;
    protected Integer penaTotalArtigoEmDias;

    /**
     * Obtém o valor da propriedade anoLei.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnoLei() {
        return anoLei;
    }

    /**
     * Define o valor da propriedade anoLei.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnoLei(Integer value) {
        this.anoLei = value;
    }

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
     * Obtém o valor da propriedade ehHediondo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEhHediondo() {
        return ehHediondo;
    }

    /**
     * Define o valor da propriedade ehHediondo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEhHediondo(String value) {
        this.ehHediondo = value;
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
     * Obtém o valor da propriedade numeroUnicoAcaoCriminal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroUnicoAcaoCriminal() {
        return numeroUnicoAcaoCriminal;
    }

    /**
     * Define o valor da propriedade numeroUnicoAcaoCriminal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroUnicoAcaoCriminal(String value) {
        this.numeroUnicoAcaoCriminal = value;
    }

    /**
     * Obtém o valor da propriedade observacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * Define o valor da propriedade observacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacao(String value) {
        this.observacao = value;
    }

    /**
     * Obtém o valor da propriedade penaTotalArtigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenaTotalArtigo() {
        return penaTotalArtigo;
    }

    /**
     * Define o valor da propriedade penaTotalArtigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenaTotalArtigo(String value) {
        this.penaTotalArtigo = value;
    }

    /**
     * Obtém o valor da propriedade penaTotalArtigoEmDias.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPenaTotalArtigoEmDias() {
        return penaTotalArtigoEmDias;
    }

    /**
     * Define o valor da propriedade penaTotalArtigoEmDias.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPenaTotalArtigoEmDias(Integer value) {
        this.penaTotalArtigoEmDias = value;
    }

}
