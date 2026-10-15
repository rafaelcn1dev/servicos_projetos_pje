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
 * <p>Classe Java de defensorAdvogadoNacionalSeeuWS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="defensorAdvogadoNacionalSeeuWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nomeCompleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oab" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ufOab" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "defensorAdvogadoNacionalSeeuWS", propOrder = {
    "nomeCompleto",
    "oab",
    "ufOab"
})
public class DefensorAdvogadoNacionalSeeuWS {

    protected String nomeCompleto;
    protected String oab;
    protected String ufOab;

    /**
     * Obtém o valor da propriedade nomeCompleto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * Define o valor da propriedade nomeCompleto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeCompleto(String value) {
        this.nomeCompleto = value;
    }

    /**
     * Obtém o valor da propriedade oab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOab() {
        return oab;
    }

    /**
     * Define o valor da propriedade oab.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOab(String value) {
        this.oab = value;
    }

    /**
     * Obtém o valor da propriedade ufOab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfOab() {
        return ufOab;
    }

    /**
     * Define o valor da propriedade ufOab.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfOab(String value) {
        this.ufOab = value;
    }

}
