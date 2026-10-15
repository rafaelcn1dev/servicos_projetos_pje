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
 * <p>Classe Java de acaoPenalSeeu complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="acaoPenalSeeu"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numeroUnicoAcaoPenal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataSetencaAcaoPenal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataTransitoJulgadoAcaoPenal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="penaAtivaCompletaAcaoPenal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoAcaoPenal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acaoPenalSeeu", propOrder = {
    "numeroUnicoAcaoPenal",
    "dataSetencaAcaoPenal",
    "dataTransitoJulgadoAcaoPenal",
    "penaAtivaCompletaAcaoPenal",
    "tipoAcaoPenal"
})
public class AcaoPenalSeeu {

    protected String numeroUnicoAcaoPenal;
    protected String dataSetencaAcaoPenal;
    protected String dataTransitoJulgadoAcaoPenal;
    protected String penaAtivaCompletaAcaoPenal;
    protected String tipoAcaoPenal;

    /**
     * Obtém o valor da propriedade numeroUnicoAcaoPenal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroUnicoAcaoPenal() {
        return numeroUnicoAcaoPenal;
    }

    /**
     * Define o valor da propriedade numeroUnicoAcaoPenal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroUnicoAcaoPenal(String value) {
        this.numeroUnicoAcaoPenal = value;
    }

    /**
     * Obtém o valor da propriedade dataSetencaAcaoPenal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSetencaAcaoPenal() {
        return dataSetencaAcaoPenal;
    }

    /**
     * Define o valor da propriedade dataSetencaAcaoPenal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSetencaAcaoPenal(String value) {
        this.dataSetencaAcaoPenal = value;
    }

    /**
     * Obtém o valor da propriedade dataTransitoJulgadoAcaoPenal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataTransitoJulgadoAcaoPenal() {
        return dataTransitoJulgadoAcaoPenal;
    }

    /**
     * Define o valor da propriedade dataTransitoJulgadoAcaoPenal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataTransitoJulgadoAcaoPenal(String value) {
        this.dataTransitoJulgadoAcaoPenal = value;
    }

    /**
     * Obtém o valor da propriedade penaAtivaCompletaAcaoPenal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenaAtivaCompletaAcaoPenal() {
        return penaAtivaCompletaAcaoPenal;
    }

    /**
     * Define o valor da propriedade penaAtivaCompletaAcaoPenal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenaAtivaCompletaAcaoPenal(String value) {
        this.penaAtivaCompletaAcaoPenal = value;
    }

    /**
     * Obtém o valor da propriedade tipoAcaoPenal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAcaoPenal() {
        return tipoAcaoPenal;
    }

    /**
     * Define o valor da propriedade tipoAcaoPenal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAcaoPenal(String value) {
        this.tipoAcaoPenal = value;
    }

}
