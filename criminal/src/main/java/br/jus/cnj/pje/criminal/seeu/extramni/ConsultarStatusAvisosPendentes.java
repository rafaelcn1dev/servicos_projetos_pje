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
 * <p>Classe Java de consultarStatusAvisosPendentes complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="consultarStatusAvisosPendentes"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numeroProcesso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idAviso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="situacaoAvisoPendente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataAbertura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataRetorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarStatusAvisosPendentes", propOrder = {
    "numeroProcesso",
    "idAviso",
    "situacaoAvisoPendente",
    "dataAbertura",
    "dataRetorno"
})
public class ConsultarStatusAvisosPendentes {

    protected String numeroProcesso;
    protected String idAviso;
    protected String situacaoAvisoPendente;
    protected String dataAbertura;
    protected String dataRetorno;

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
     * Obtém o valor da propriedade idAviso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAviso() {
        return idAviso;
    }

    /**
     * Define o valor da propriedade idAviso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAviso(String value) {
        this.idAviso = value;
    }

    /**
     * Obtém o valor da propriedade situacaoAvisoPendente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSituacaoAvisoPendente() {
        return situacaoAvisoPendente;
    }

    /**
     * Define o valor da propriedade situacaoAvisoPendente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSituacaoAvisoPendente(String value) {
        this.situacaoAvisoPendente = value;
    }

    /**
     * Obtém o valor da propriedade dataAbertura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataAbertura() {
        return dataAbertura;
    }

    /**
     * Define o valor da propriedade dataAbertura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataAbertura(String value) {
        this.dataAbertura = value;
    }

    /**
     * Obtém o valor da propriedade dataRetorno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataRetorno() {
        return dataRetorno;
    }

    /**
     * Define o valor da propriedade dataRetorno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataRetorno(String value) {
        this.dataRetorno = value;
    }

}
