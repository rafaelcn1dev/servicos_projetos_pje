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
 * <p>Classe Java de advogadoParteSeeu complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="advogadoParteSeeu"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nomeAdvogado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oabAdvogado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoAdvogado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ufOabAdvogado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "advogadoParteSeeu", propOrder = {
    "nomeAdvogado",
    "oabAdvogado",
    "tipoAdvogado",
    "ufOabAdvogado"
})
public class AdvogadoParteSeeu {

    protected String nomeAdvogado;
    protected String oabAdvogado;
    protected String tipoAdvogado;
    protected String ufOabAdvogado;

    /**
     * Obtém o valor da propriedade nomeAdvogado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeAdvogado() {
        return nomeAdvogado;
    }

    /**
     * Define o valor da propriedade nomeAdvogado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeAdvogado(String value) {
        this.nomeAdvogado = value;
    }

    /**
     * Obtém o valor da propriedade oabAdvogado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOabAdvogado() {
        return oabAdvogado;
    }

    /**
     * Define o valor da propriedade oabAdvogado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOabAdvogado(String value) {
        this.oabAdvogado = value;
    }

    /**
     * Obtém o valor da propriedade tipoAdvogado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAdvogado() {
        return tipoAdvogado;
    }

    /**
     * Define o valor da propriedade tipoAdvogado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAdvogado(String value) {
        this.tipoAdvogado = value;
    }

    /**
     * Obtém o valor da propriedade ufOabAdvogado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfOabAdvogado() {
        return ufOabAdvogado;
    }

    /**
     * Define o valor da propriedade ufOabAdvogado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfOabAdvogado(String value) {
        this.ufOabAdvogado = value;
    }

}
