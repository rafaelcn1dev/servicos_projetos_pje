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
 * <p>Classe Java de medidaAlternativaNacionalSeeuWS complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="medidaAlternativaNacionalSeeuWS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codSituacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codTipoAplicacao" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="codTipoMedida" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="dataInicioCumprimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataPrevistaEncerramento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataRealEncerramento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroUnicoAcaoCriminal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="observacao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "medidaAlternativaNacionalSeeuWS", propOrder = {
    "codSituacao",
    "codTipoAplicacao",
    "codTipoMedida",
    "dataInicioCumprimento",
    "dataPrevistaEncerramento",
    "dataRealEncerramento",
    "numeroUnicoAcaoCriminal",
    "observacao"
})
public class MedidaAlternativaNacionalSeeuWS {

    protected String codSituacao;
    protected Integer codTipoAplicacao;
    protected Integer codTipoMedida;
    protected String dataInicioCumprimento;
    protected String dataPrevistaEncerramento;
    protected String dataRealEncerramento;
    protected String numeroUnicoAcaoCriminal;
    protected String observacao;

    /**
     * Obtém o valor da propriedade codSituacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodSituacao() {
        return codSituacao;
    }

    /**
     * Define o valor da propriedade codSituacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodSituacao(String value) {
        this.codSituacao = value;
    }

    /**
     * Obtém o valor da propriedade codTipoAplicacao.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodTipoAplicacao() {
        return codTipoAplicacao;
    }

    /**
     * Define o valor da propriedade codTipoAplicacao.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodTipoAplicacao(Integer value) {
        this.codTipoAplicacao = value;
    }

    /**
     * Obtém o valor da propriedade codTipoMedida.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodTipoMedida() {
        return codTipoMedida;
    }

    /**
     * Define o valor da propriedade codTipoMedida.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodTipoMedida(Integer value) {
        this.codTipoMedida = value;
    }

    /**
     * Obtém o valor da propriedade dataInicioCumprimento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataInicioCumprimento() {
        return dataInicioCumprimento;
    }

    /**
     * Define o valor da propriedade dataInicioCumprimento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataInicioCumprimento(String value) {
        this.dataInicioCumprimento = value;
    }

    /**
     * Obtém o valor da propriedade dataPrevistaEncerramento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataPrevistaEncerramento() {
        return dataPrevistaEncerramento;
    }

    /**
     * Define o valor da propriedade dataPrevistaEncerramento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataPrevistaEncerramento(String value) {
        this.dataPrevistaEncerramento = value;
    }

    /**
     * Obtém o valor da propriedade dataRealEncerramento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataRealEncerramento() {
        return dataRealEncerramento;
    }

    /**
     * Define o valor da propriedade dataRealEncerramento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataRealEncerramento(String value) {
        this.dataRealEncerramento = value;
    }

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
     * Obtém o valor da propriedade observacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * Define o valor da propriedade observacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacao(String value) {
        this.observacao = value;
    }

}
