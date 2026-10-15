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
 * <p>Classe Java de wsAreaDeVara complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="wsAreaDeVara"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codigoComarca" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="tipoCompetencia" type="{http://dominio.webservice.projudi.gov/}wsTipoCompetencia" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsAreaDeVara", propOrder = {
    "codigo",
    "descricao",
    "estado",
    "codigoComarca",
    "tipoCompetencia"
})
public class WsAreaDeVara {

    @XmlElement(namespace = "")
    protected Integer codigo;
    @XmlElement(namespace = "")
    protected String descricao;
    @XmlElement(namespace = "")
    protected String estado;
    @XmlElement(namespace = "")
    protected Integer codigoComarca;
    @XmlElement(namespace = "")
    protected WsTipoCompetencia tipoCompetencia;

    /**
     * Obtém o valor da propriedade codigo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Define o valor da propriedade codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigo(Integer value) {
        this.codigo = value;
    }

    /**
     * Obtém o valor da propriedade descricao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define o valor da propriedade descricao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricao(String value) {
        this.descricao = value;
    }

    /**
     * Obtém o valor da propriedade estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define o valor da propriedade estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtém o valor da propriedade codigoComarca.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigoComarca() {
        return codigoComarca;
    }

    /**
     * Define o valor da propriedade codigoComarca.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigoComarca(Integer value) {
        this.codigoComarca = value;
    }

    /**
     * Obtém o valor da propriedade tipoCompetencia.
     * 
     * @return
     *     possible object is
     *     {@link WsTipoCompetencia }
     *     
     */
    public WsTipoCompetencia getTipoCompetencia() {
        return tipoCompetencia;
    }

    /**
     * Define o valor da propriedade tipoCompetencia.
     * 
     * @param value
     *     allowed object is
     *     {@link WsTipoCompetencia }
     *     
     */
    public void setTipoCompetencia(WsTipoCompetencia value) {
        this.tipoCompetencia = value;
    }

}
