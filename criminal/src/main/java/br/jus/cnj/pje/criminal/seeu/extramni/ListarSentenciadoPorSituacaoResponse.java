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
 * <p>Classe Java de listarSentenciadoPorSituacaoResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="listarSentenciadoPorSituacaoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://impl.seeu.webservice.projudi.gov/}sentenciadoSeeu" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="pagina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tamanhoPagina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalRegistros" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="totalPaginas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codigoRetorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="msg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarSentenciadoPorSituacaoResponse", propOrder = {
    "_return",
    "pagina",
    "tamanhoPagina",
    "totalRegistros",
    "totalPaginas",
    "codigoRetorno",
    "msg"
})
public class ListarSentenciadoPorSituacaoResponse {

    @XmlElement(name = "return")
    protected List<SentenciadoSeeu> _return;
    protected String pagina;
    protected String tamanhoPagina;
    protected String totalRegistros;
    protected String totalPaginas;
    protected String codigoRetorno;
    protected String msg;

    /**
     * Gets the value of the return property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the return property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SentenciadoSeeu }
     * 
     * 
     */
    public List<SentenciadoSeeu> getReturn() {
        if (_return == null) {
            _return = new ArrayList<SentenciadoSeeu>();
        }
        return this._return;
    }

    /**
     * Obtém o valor da propriedade pagina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagina() {
        return pagina;
    }

    /**
     * Define o valor da propriedade pagina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagina(String value) {
        this.pagina = value;
    }

    /**
     * Obtém o valor da propriedade tamanhoPagina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTamanhoPagina() {
        return tamanhoPagina;
    }

    /**
     * Define o valor da propriedade tamanhoPagina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTamanhoPagina(String value) {
        this.tamanhoPagina = value;
    }

    /**
     * Obtém o valor da propriedade totalRegistros.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalRegistros() {
        return totalRegistros;
    }

    /**
     * Define o valor da propriedade totalRegistros.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalRegistros(String value) {
        this.totalRegistros = value;
    }

    /**
     * Obtém o valor da propriedade totalPaginas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalPaginas() {
        return totalPaginas;
    }

    /**
     * Define o valor da propriedade totalPaginas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalPaginas(String value) {
        this.totalPaginas = value;
    }

    /**
     * Obtém o valor da propriedade codigoRetorno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRetorno() {
        return codigoRetorno;
    }

    /**
     * Define o valor da propriedade codigoRetorno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRetorno(String value) {
        this.codigoRetorno = value;
    }

    /**
     * Obtém o valor da propriedade msg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Define o valor da propriedade msg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsg(String value) {
        this.msg = value;
    }

}
