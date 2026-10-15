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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java de penaCriminalSeeu complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="penaCriminalSeeu"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="anoRecurso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="camaraJulgadoraRecurso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codAcaoPenalVinculada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codPenaExecucao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="concedido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataAutuacaoRecurso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataDecisaoRecurso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataTransitoJulgadoRecurso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="diasMulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listaDesmembramentoPenaCriminalSeeu" type="{http://impl.seeu.webservice.projudi.gov/}desmembramentoPenaCriminalSeeu" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="multaPaga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroAcordaoRecurso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroRecurso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgaoJulgadorRecurso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="penaImposta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="recorrenteRecurso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="regimePena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoPena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoPenaDescricao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="valorMulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "penaCriminalSeeu", propOrder = {
    "anoRecurso",
    "camaraJulgadoraRecurso",
    "codAcaoPenalVinculada",
    "codPenaExecucao",
    "concedido",
    "dataAutuacaoRecurso",
    "dataDecisaoRecurso",
    "dataInicio",
    "dataTransitoJulgadoRecurso",
    "diasMulta",
    "listaDesmembramentoPenaCriminalSeeu",
    "multaPaga",
    "numeroAcordaoRecurso",
    "numeroRecurso",
    "orgaoJulgadorRecurso",
    "penaImposta",
    "recorrenteRecurso",
    "regimePena",
    "tipoPena",
    "tipoPenaDescricao",
    "valorMulta"
})
public class PenaCriminalSeeu {

    protected Integer anoRecurso;
    protected String camaraJulgadoraRecurso;
    protected String codAcaoPenalVinculada;
    protected String codPenaExecucao;
    protected String concedido;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAutuacaoRecurso;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDecisaoRecurso;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInicio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataTransitoJulgadoRecurso;
    protected String diasMulta;
    @XmlElement(nillable = true)
    protected List<DesmembramentoPenaCriminalSeeu> listaDesmembramentoPenaCriminalSeeu;
    protected String multaPaga;
    protected String numeroAcordaoRecurso;
    protected String numeroRecurso;
    protected String orgaoJulgadorRecurso;
    protected String penaImposta;
    protected String recorrenteRecurso;
    protected String regimePena;
    protected String tipoPena;
    protected String tipoPenaDescricao;
    protected String valorMulta;

    /**
     * Obtém o valor da propriedade anoRecurso.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnoRecurso() {
        return anoRecurso;
    }

    /**
     * Define o valor da propriedade anoRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnoRecurso(Integer value) {
        this.anoRecurso = value;
    }

    /**
     * Obtém o valor da propriedade camaraJulgadoraRecurso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCamaraJulgadoraRecurso() {
        return camaraJulgadoraRecurso;
    }

    /**
     * Define o valor da propriedade camaraJulgadoraRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCamaraJulgadoraRecurso(String value) {
        this.camaraJulgadoraRecurso = value;
    }

    /**
     * Obtém o valor da propriedade codAcaoPenalVinculada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAcaoPenalVinculada() {
        return codAcaoPenalVinculada;
    }

    /**
     * Define o valor da propriedade codAcaoPenalVinculada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAcaoPenalVinculada(String value) {
        this.codAcaoPenalVinculada = value;
    }

    /**
     * Obtém o valor da propriedade codPenaExecucao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPenaExecucao() {
        return codPenaExecucao;
    }

    /**
     * Define o valor da propriedade codPenaExecucao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPenaExecucao(String value) {
        this.codPenaExecucao = value;
    }

    /**
     * Obtém o valor da propriedade concedido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcedido() {
        return concedido;
    }

    /**
     * Define o valor da propriedade concedido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcedido(String value) {
        this.concedido = value;
    }

    /**
     * Obtém o valor da propriedade dataAutuacaoRecurso.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAutuacaoRecurso() {
        return dataAutuacaoRecurso;
    }

    /**
     * Define o valor da propriedade dataAutuacaoRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAutuacaoRecurso(XMLGregorianCalendar value) {
        this.dataAutuacaoRecurso = value;
    }

    /**
     * Obtém o valor da propriedade dataDecisaoRecurso.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDecisaoRecurso() {
        return dataDecisaoRecurso;
    }

    /**
     * Define o valor da propriedade dataDecisaoRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDecisaoRecurso(XMLGregorianCalendar value) {
        this.dataDecisaoRecurso = value;
    }

    /**
     * Obtém o valor da propriedade dataInicio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInicio() {
        return dataInicio;
    }

    /**
     * Define o valor da propriedade dataInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInicio(XMLGregorianCalendar value) {
        this.dataInicio = value;
    }

    /**
     * Obtém o valor da propriedade dataTransitoJulgadoRecurso.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTransitoJulgadoRecurso() {
        return dataTransitoJulgadoRecurso;
    }

    /**
     * Define o valor da propriedade dataTransitoJulgadoRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTransitoJulgadoRecurso(XMLGregorianCalendar value) {
        this.dataTransitoJulgadoRecurso = value;
    }

    /**
     * Obtém o valor da propriedade diasMulta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiasMulta() {
        return diasMulta;
    }

    /**
     * Define o valor da propriedade diasMulta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiasMulta(String value) {
        this.diasMulta = value;
    }

    /**
     * Gets the value of the listaDesmembramentoPenaCriminalSeeu property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaDesmembramentoPenaCriminalSeeu property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaDesmembramentoPenaCriminalSeeu().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DesmembramentoPenaCriminalSeeu }
     * 
     * 
     */
    public List<DesmembramentoPenaCriminalSeeu> getListaDesmembramentoPenaCriminalSeeu() {
        if (listaDesmembramentoPenaCriminalSeeu == null) {
            listaDesmembramentoPenaCriminalSeeu = new ArrayList<DesmembramentoPenaCriminalSeeu>();
        }
        return this.listaDesmembramentoPenaCriminalSeeu;
    }

    /**
     * Obtém o valor da propriedade multaPaga.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMultaPaga() {
        return multaPaga;
    }

    /**
     * Define o valor da propriedade multaPaga.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMultaPaga(String value) {
        this.multaPaga = value;
    }

    /**
     * Obtém o valor da propriedade numeroAcordaoRecurso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroAcordaoRecurso() {
        return numeroAcordaoRecurso;
    }

    /**
     * Define o valor da propriedade numeroAcordaoRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroAcordaoRecurso(String value) {
        this.numeroAcordaoRecurso = value;
    }

    /**
     * Obtém o valor da propriedade numeroRecurso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroRecurso() {
        return numeroRecurso;
    }

    /**
     * Define o valor da propriedade numeroRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroRecurso(String value) {
        this.numeroRecurso = value;
    }

    /**
     * Obtém o valor da propriedade orgaoJulgadorRecurso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgaoJulgadorRecurso() {
        return orgaoJulgadorRecurso;
    }

    /**
     * Define o valor da propriedade orgaoJulgadorRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgaoJulgadorRecurso(String value) {
        this.orgaoJulgadorRecurso = value;
    }

    /**
     * Obtém o valor da propriedade penaImposta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPenaImposta() {
        return penaImposta;
    }

    /**
     * Define o valor da propriedade penaImposta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPenaImposta(String value) {
        this.penaImposta = value;
    }

    /**
     * Obtém o valor da propriedade recorrenteRecurso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecorrenteRecurso() {
        return recorrenteRecurso;
    }

    /**
     * Define o valor da propriedade recorrenteRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecorrenteRecurso(String value) {
        this.recorrenteRecurso = value;
    }

    /**
     * Obtém o valor da propriedade regimePena.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegimePena() {
        return regimePena;
    }

    /**
     * Define o valor da propriedade regimePena.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegimePena(String value) {
        this.regimePena = value;
    }

    /**
     * Obtém o valor da propriedade tipoPena.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPena() {
        return tipoPena;
    }

    /**
     * Define o valor da propriedade tipoPena.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPena(String value) {
        this.tipoPena = value;
    }

    /**
     * Obtém o valor da propriedade tipoPenaDescricao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPenaDescricao() {
        return tipoPenaDescricao;
    }

    /**
     * Define o valor da propriedade tipoPenaDescricao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPenaDescricao(String value) {
        this.tipoPenaDescricao = value;
    }

    /**
     * Obtém o valor da propriedade valorMulta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValorMulta() {
        return valorMulta;
    }

    /**
     * Define o valor da propriedade valorMulta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValorMulta(String value) {
        this.valorMulta = value;
    }

}
