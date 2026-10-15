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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de incluirAtualizarProcessoExecucaoPenalNacional complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="incluirAtualizarProcessoExecucaoPenalNacional"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="senha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="processoExecucaoPenal" type="{http://impl.seeu.webservice.projudi.gov/}processoExecucaoPenalNacionalSeeuWS" minOccurs="0"/&gt;
 *         &lt;element name="listaDefensor" type="{http://impl.seeu.webservice.projudi.gov/}defensorAdvogadoNacionalSeeuWS" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaAdvogado" type="{http://impl.seeu.webservice.projudi.gov/}defensorAdvogadoNacionalSeeuWS" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaAcaoCriminalPenaNacional" type="{http://impl.seeu.webservice.projudi.gov/}acaoCriminalPenaNacionalSeeuWS" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaArtigoCondenacao" type="{http://impl.seeu.webservice.projudi.gov/}artigoCondenacaoNacionalSeeuWS" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="listaMedidaAlternativa" type="{http://impl.seeu.webservice.projudi.gov/}medidaAlternativaNacionalSeeuWS" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "incluirAtualizarProcessoExecucaoPenalNacional", propOrder = {
    "usuario",
    "senha",
    "processoExecucaoPenal",
    "listaDefensor",
    "listaAdvogado",
    "listaAcaoCriminalPenaNacional",
    "listaArtigoCondenacao",
    "listaMedidaAlternativa",
    "codigoRetorno",
    "msg"
})
public class IncluirAtualizarProcessoExecucaoPenalNacional {

    protected String usuario;
    protected String senha;
    protected ProcessoExecucaoPenalNacionalSeeuWS processoExecucaoPenal;
    protected List<DefensorAdvogadoNacionalSeeuWS> listaDefensor;
    protected List<DefensorAdvogadoNacionalSeeuWS> listaAdvogado;
    protected List<AcaoCriminalPenaNacionalSeeuWS> listaAcaoCriminalPenaNacional;
    protected List<ArtigoCondenacaoNacionalSeeuWS> listaArtigoCondenacao;
    protected List<MedidaAlternativaNacionalSeeuWS> listaMedidaAlternativa;
    protected String codigoRetorno;
    protected String msg;

    /**
     * Obtém o valor da propriedade usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define o valor da propriedade usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtém o valor da propriedade senha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define o valor da propriedade senha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenha(String value) {
        this.senha = value;
    }

    /**
     * Obtém o valor da propriedade processoExecucaoPenal.
     * 
     * @return
     *     possible object is
     *     {@link ProcessoExecucaoPenalNacionalSeeuWS }
     *     
     */
    public ProcessoExecucaoPenalNacionalSeeuWS getProcessoExecucaoPenal() {
        return processoExecucaoPenal;
    }

    /**
     * Define o valor da propriedade processoExecucaoPenal.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessoExecucaoPenalNacionalSeeuWS }
     *     
     */
    public void setProcessoExecucaoPenal(ProcessoExecucaoPenalNacionalSeeuWS value) {
        this.processoExecucaoPenal = value;
    }

    /**
     * Gets the value of the listaDefensor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaDefensor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaDefensor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DefensorAdvogadoNacionalSeeuWS }
     * 
     * 
     */
    public List<DefensorAdvogadoNacionalSeeuWS> getListaDefensor() {
        if (listaDefensor == null) {
            listaDefensor = new ArrayList<DefensorAdvogadoNacionalSeeuWS>();
        }
        return this.listaDefensor;
    }

    /**
     * Gets the value of the listaAdvogado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaAdvogado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaAdvogado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DefensorAdvogadoNacionalSeeuWS }
     * 
     * 
     */
    public List<DefensorAdvogadoNacionalSeeuWS> getListaAdvogado() {
        if (listaAdvogado == null) {
            listaAdvogado = new ArrayList<DefensorAdvogadoNacionalSeeuWS>();
        }
        return this.listaAdvogado;
    }

    /**
     * Gets the value of the listaAcaoCriminalPenaNacional property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaAcaoCriminalPenaNacional property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaAcaoCriminalPenaNacional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AcaoCriminalPenaNacionalSeeuWS }
     * 
     * 
     */
    public List<AcaoCriminalPenaNacionalSeeuWS> getListaAcaoCriminalPenaNacional() {
        if (listaAcaoCriminalPenaNacional == null) {
            listaAcaoCriminalPenaNacional = new ArrayList<AcaoCriminalPenaNacionalSeeuWS>();
        }
        return this.listaAcaoCriminalPenaNacional;
    }

    /**
     * Gets the value of the listaArtigoCondenacao property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaArtigoCondenacao property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaArtigoCondenacao().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArtigoCondenacaoNacionalSeeuWS }
     * 
     * 
     */
    public List<ArtigoCondenacaoNacionalSeeuWS> getListaArtigoCondenacao() {
        if (listaArtigoCondenacao == null) {
            listaArtigoCondenacao = new ArrayList<ArtigoCondenacaoNacionalSeeuWS>();
        }
        return this.listaArtigoCondenacao;
    }

    /**
     * Gets the value of the listaMedidaAlternativa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaMedidaAlternativa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaMedidaAlternativa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MedidaAlternativaNacionalSeeuWS }
     * 
     * 
     */
    public List<MedidaAlternativaNacionalSeeuWS> getListaMedidaAlternativa() {
        if (listaMedidaAlternativa == null) {
            listaMedidaAlternativa = new ArrayList<MedidaAlternativaNacionalSeeuWS>();
        }
        return this.listaMedidaAlternativa;
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
