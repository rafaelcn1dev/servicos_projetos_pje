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
 * <p>Classe Java de acaoCriminalPenalSeeu complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="acaoCriminalPenalSeeu"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="aguardandoPrisao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="anoProcesso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codAcaoPenalVinculada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataAutuacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataDevolucaoTJ" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataEncaminhamentoTJ" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataInfracao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataPronuncia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataRecebimentoDenuncia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataRecebimentoVep" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataSentenca" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataTransitoJulgado" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dataTransitoJulgadoAcusacao" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="ehDefesaOuAcusacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ehDefinitivaProvisoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ehReincidente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="listaPenaCriminal" type="{http://impl.seeu.webservice.projudi.gov/}penaCriminalSeeu" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="nomeVaraOrigem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroUnico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="processoEmGrauRecurso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acaoCriminalPenalSeeu", propOrder = {
    "aguardandoPrisao",
    "anoProcesso",
    "codAcaoPenalVinculada",
    "dataAutuacao",
    "dataDevolucaoTJ",
    "dataEncaminhamentoTJ",
    "dataInfracao",
    "dataPronuncia",
    "dataRecebimentoDenuncia",
    "dataRecebimentoVep",
    "dataSentenca",
    "dataTransitoJulgado",
    "dataTransitoJulgadoAcusacao",
    "ehDefesaOuAcusacao",
    "ehDefinitivaProvisoria",
    "ehReincidente",
    "listaPenaCriminal",
    "nomeVaraOrigem",
    "numeroUnico",
    "processoEmGrauRecurso"
})
public class AcaoCriminalPenalSeeu {

    protected String aguardandoPrisao;
    protected String anoProcesso;
    protected String codAcaoPenalVinculada;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataAutuacao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDevolucaoTJ;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataEncaminhamentoTJ;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataInfracao;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataPronuncia;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRecebimentoDenuncia;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataRecebimentoVep;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataSentenca;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataTransitoJulgado;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataTransitoJulgadoAcusacao;
    protected String ehDefesaOuAcusacao;
    protected String ehDefinitivaProvisoria;
    protected String ehReincidente;
    @XmlElement(nillable = true)
    protected List<PenaCriminalSeeu> listaPenaCriminal;
    protected String nomeVaraOrigem;
    protected String numeroUnico;
    protected String processoEmGrauRecurso;

    /**
     * Obtém o valor da propriedade aguardandoPrisao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAguardandoPrisao() {
        return aguardandoPrisao;
    }

    /**
     * Define o valor da propriedade aguardandoPrisao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAguardandoPrisao(String value) {
        this.aguardandoPrisao = value;
    }

    /**
     * Obtém o valor da propriedade anoProcesso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnoProcesso() {
        return anoProcesso;
    }

    /**
     * Define o valor da propriedade anoProcesso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnoProcesso(String value) {
        this.anoProcesso = value;
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
     * Obtém o valor da propriedade dataAutuacao.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAutuacao() {
        return dataAutuacao;
    }

    /**
     * Define o valor da propriedade dataAutuacao.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAutuacao(XMLGregorianCalendar value) {
        this.dataAutuacao = value;
    }

    /**
     * Obtém o valor da propriedade dataDevolucaoTJ.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDevolucaoTJ() {
        return dataDevolucaoTJ;
    }

    /**
     * Define o valor da propriedade dataDevolucaoTJ.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDevolucaoTJ(XMLGregorianCalendar value) {
        this.dataDevolucaoTJ = value;
    }

    /**
     * Obtém o valor da propriedade dataEncaminhamentoTJ.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEncaminhamentoTJ() {
        return dataEncaminhamentoTJ;
    }

    /**
     * Define o valor da propriedade dataEncaminhamentoTJ.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEncaminhamentoTJ(XMLGregorianCalendar value) {
        this.dataEncaminhamentoTJ = value;
    }

    /**
     * Obtém o valor da propriedade dataInfracao.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInfracao() {
        return dataInfracao;
    }

    /**
     * Define o valor da propriedade dataInfracao.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInfracao(XMLGregorianCalendar value) {
        this.dataInfracao = value;
    }

    /**
     * Obtém o valor da propriedade dataPronuncia.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataPronuncia() {
        return dataPronuncia;
    }

    /**
     * Define o valor da propriedade dataPronuncia.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataPronuncia(XMLGregorianCalendar value) {
        this.dataPronuncia = value;
    }

    /**
     * Obtém o valor da propriedade dataRecebimentoDenuncia.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRecebimentoDenuncia() {
        return dataRecebimentoDenuncia;
    }

    /**
     * Define o valor da propriedade dataRecebimentoDenuncia.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRecebimentoDenuncia(XMLGregorianCalendar value) {
        this.dataRecebimentoDenuncia = value;
    }

    /**
     * Obtém o valor da propriedade dataRecebimentoVep.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRecebimentoVep() {
        return dataRecebimentoVep;
    }

    /**
     * Define o valor da propriedade dataRecebimentoVep.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRecebimentoVep(XMLGregorianCalendar value) {
        this.dataRecebimentoVep = value;
    }

    /**
     * Obtém o valor da propriedade dataSentenca.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSentenca() {
        return dataSentenca;
    }

    /**
     * Define o valor da propriedade dataSentenca.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSentenca(XMLGregorianCalendar value) {
        this.dataSentenca = value;
    }

    /**
     * Obtém o valor da propriedade dataTransitoJulgado.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTransitoJulgado() {
        return dataTransitoJulgado;
    }

    /**
     * Define o valor da propriedade dataTransitoJulgado.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTransitoJulgado(XMLGregorianCalendar value) {
        this.dataTransitoJulgado = value;
    }

    /**
     * Obtém o valor da propriedade dataTransitoJulgadoAcusacao.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataTransitoJulgadoAcusacao() {
        return dataTransitoJulgadoAcusacao;
    }

    /**
     * Define o valor da propriedade dataTransitoJulgadoAcusacao.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataTransitoJulgadoAcusacao(XMLGregorianCalendar value) {
        this.dataTransitoJulgadoAcusacao = value;
    }

    /**
     * Obtém o valor da propriedade ehDefesaOuAcusacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEhDefesaOuAcusacao() {
        return ehDefesaOuAcusacao;
    }

    /**
     * Define o valor da propriedade ehDefesaOuAcusacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEhDefesaOuAcusacao(String value) {
        this.ehDefesaOuAcusacao = value;
    }

    /**
     * Obtém o valor da propriedade ehDefinitivaProvisoria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEhDefinitivaProvisoria() {
        return ehDefinitivaProvisoria;
    }

    /**
     * Define o valor da propriedade ehDefinitivaProvisoria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEhDefinitivaProvisoria(String value) {
        this.ehDefinitivaProvisoria = value;
    }

    /**
     * Obtém o valor da propriedade ehReincidente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEhReincidente() {
        return ehReincidente;
    }

    /**
     * Define o valor da propriedade ehReincidente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEhReincidente(String value) {
        this.ehReincidente = value;
    }

    /**
     * Gets the value of the listaPenaCriminal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaPenaCriminal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaPenaCriminal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PenaCriminalSeeu }
     * 
     * 
     */
    public List<PenaCriminalSeeu> getListaPenaCriminal() {
        if (listaPenaCriminal == null) {
            listaPenaCriminal = new ArrayList<PenaCriminalSeeu>();
        }
        return this.listaPenaCriminal;
    }

    /**
     * Obtém o valor da propriedade nomeVaraOrigem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeVaraOrigem() {
        return nomeVaraOrigem;
    }

    /**
     * Define o valor da propriedade nomeVaraOrigem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeVaraOrigem(String value) {
        this.nomeVaraOrigem = value;
    }

    /**
     * Obtém o valor da propriedade numeroUnico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroUnico() {
        return numeroUnico;
    }

    /**
     * Define o valor da propriedade numeroUnico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroUnico(String value) {
        this.numeroUnico = value;
    }

    /**
     * Obtém o valor da propriedade processoEmGrauRecurso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessoEmGrauRecurso() {
        return processoEmGrauRecurso;
    }

    /**
     * Define o valor da propriedade processoEmGrauRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessoEmGrauRecurso(String value) {
        this.processoEmGrauRecurso = value;
    }

}
