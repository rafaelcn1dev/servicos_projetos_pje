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
 * <p>Classe Java de incidenteSeeu complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="incidenteSeeu"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataAtuacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="peticao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataDecisao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="juiz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="situacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listaAcaoPenalSeeu" type="{http://impl.seeu.webservice.projudi.gov/}acaoPenalSeeu" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "incidenteSeeu", propOrder = {
    "numero",
    "categoria",
    "tipo",
    "complemento",
    "dataReferencia",
    "dataAtuacao",
    "peticao",
    "dataDecisao",
    "juiz",
    "situacao",
    "listaAcaoPenalSeeu"
})
public class IncidenteSeeu {

    protected String numero;
    protected String categoria;
    protected String tipo;
    protected String complemento;
    protected String dataReferencia;
    protected String dataAtuacao;
    protected String peticao;
    protected String dataDecisao;
    protected String juiz;
    protected String situacao;
    @XmlElement(nillable = true)
    protected List<AcaoPenalSeeu> listaAcaoPenalSeeu;

    /**
     * Obtém o valor da propriedade numero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Define o valor da propriedade numero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Obtém o valor da propriedade categoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define o valor da propriedade categoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoria(String value) {
        this.categoria = value;
    }

    /**
     * Obtém o valor da propriedade tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o valor da propriedade tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
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
     * Obtém o valor da propriedade dataReferencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataReferencia() {
        return dataReferencia;
    }

    /**
     * Define o valor da propriedade dataReferencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataReferencia(String value) {
        this.dataReferencia = value;
    }

    /**
     * Obtém o valor da propriedade dataAtuacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataAtuacao() {
        return dataAtuacao;
    }

    /**
     * Define o valor da propriedade dataAtuacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataAtuacao(String value) {
        this.dataAtuacao = value;
    }

    /**
     * Obtém o valor da propriedade peticao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeticao() {
        return peticao;
    }

    /**
     * Define o valor da propriedade peticao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeticao(String value) {
        this.peticao = value;
    }

    /**
     * Obtém o valor da propriedade dataDecisao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataDecisao() {
        return dataDecisao;
    }

    /**
     * Define o valor da propriedade dataDecisao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataDecisao(String value) {
        this.dataDecisao = value;
    }

    /**
     * Obtém o valor da propriedade juiz.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJuiz() {
        return juiz;
    }

    /**
     * Define o valor da propriedade juiz.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJuiz(String value) {
        this.juiz = value;
    }

    /**
     * Obtém o valor da propriedade situacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * Define o valor da propriedade situacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSituacao(String value) {
        this.situacao = value;
    }

    /**
     * Gets the value of the listaAcaoPenalSeeu property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaAcaoPenalSeeu property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaAcaoPenalSeeu().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AcaoPenalSeeu }
     * 
     * 
     */
    public List<AcaoPenalSeeu> getListaAcaoPenalSeeu() {
        if (listaAcaoPenalSeeu == null) {
            listaAcaoPenalSeeu = new ArrayList<AcaoPenalSeeu>();
        }
        return this.listaAcaoPenalSeeu;
    }

}
