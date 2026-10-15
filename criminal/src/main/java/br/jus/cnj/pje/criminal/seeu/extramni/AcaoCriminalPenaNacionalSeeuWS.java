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
 * <p>Classe Java de acaoCriminalPenaNacionalSeeuWS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="acaoCriminalPenaNacionalSeeuWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numeroUnicoAcaoCriminal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="penaTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acaoCriminalPenaNacionalSeeuWS", propOrder = {
    "numeroUnicoAcaoCriminal",
    "penaTotal"
})
public class AcaoCriminalPenaNacionalSeeuWS {

    protected String numeroUnicoAcaoCriminal;
    protected String penaTotal;

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
     * Obtém o valor da propriedade penaTotal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenaTotal() {
        return penaTotal;
    }

    /**
     * Define o valor da propriedade penaTotal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenaTotal(String value) {
        this.penaTotal = value;
    }

}
