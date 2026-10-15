//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.3.0 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2022.09.26 às 04:01:17 PM BRT 
//


package br.jus.cnj.pje.criminal.seeu.extramni2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de todosTipoDocumento complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="todosTipoDocumento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dominio.seeu.pje.jus.br/}sistemaTribunal" minOccurs="0"/&gt;
 *         &lt;element ref="{http://dominio.seeu.pje.jus.br/}systemPass" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "todosTipoDocumento", propOrder = {
    "sistemaTribunal",
    "systemPass"
})
public class TodosTipoDocumento {

    @XmlElement(namespace = "http://dominio.seeu.pje.jus.br/")
    protected String sistemaTribunal;
    @XmlElement(namespace = "http://dominio.seeu.pje.jus.br/")
    protected String systemPass;

    /**
     * Obtém o valor da propriedade sistemaTribunal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSistemaTribunal() {
        return sistemaTribunal;
    }

    /**
     * Define o valor da propriedade sistemaTribunal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSistemaTribunal(String value) {
        this.sistemaTribunal = value;
    }

    /**
     * Obtém o valor da propriedade systemPass.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemPass() {
        return systemPass;
    }

    /**
     * Define o valor da propriedade systemPass.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemPass(String value) {
        this.systemPass = value;
    }

}
