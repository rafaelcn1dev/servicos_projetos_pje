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
 * <p>Classe Java de filtroSentenciado complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="filtroSentenciado"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codTribunal" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="cpf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dataNascimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomeMae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nomePai" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numRJI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rgParte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="siglaUfRg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="situacao" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filtroSentenciado", propOrder = {
    "codTribunal",
    "cpf",
    "dataNascimento",
    "nome",
    "nomeMae",
    "nomePai",
    "numRJI",
    "rgParte",
    "sexo",
    "siglaUfRg",
    "situacao"
})
public class FiltroSentenciado {

    protected Long codTribunal;
    protected String cpf;
    protected String dataNascimento;
    protected String nome;
    protected String nomeMae;
    protected String nomePai;
    protected String numRJI;
    protected String rgParte;
    protected String sexo;
    protected String siglaUfRg;
    protected Integer situacao;

    /**
     * Obtém o valor da propriedade codTribunal.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCodTribunal() {
        return codTribunal;
    }

    /**
     * Define o valor da propriedade codTribunal.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCodTribunal(Long value) {
        this.codTribunal = value;
    }

    /**
     * Obtém o valor da propriedade cpf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o valor da propriedade cpf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpf(String value) {
        this.cpf = value;
    }

    /**
     * Obtém o valor da propriedade dataNascimento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Define o valor da propriedade dataNascimento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataNascimento(String value) {
        this.dataNascimento = value;
    }

    /**
     * Obtém o valor da propriedade nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o valor da propriedade nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Obtém o valor da propriedade nomeMae.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeMae() {
        return nomeMae;
    }

    /**
     * Define o valor da propriedade nomeMae.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeMae(String value) {
        this.nomeMae = value;
    }

    /**
     * Obtém o valor da propriedade nomePai.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomePai() {
        return nomePai;
    }

    /**
     * Define o valor da propriedade nomePai.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomePai(String value) {
        this.nomePai = value;
    }

    /**
     * Obtém o valor da propriedade numRJI.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumRJI() {
        return numRJI;
    }

    /**
     * Define o valor da propriedade numRJI.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumRJI(String value) {
        this.numRJI = value;
    }

    /**
     * Obtém o valor da propriedade rgParte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRgParte() {
        return rgParte;
    }

    /**
     * Define o valor da propriedade rgParte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRgParte(String value) {
        this.rgParte = value;
    }

    /**
     * Obtém o valor da propriedade sexo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Define o valor da propriedade sexo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Obtém o valor da propriedade siglaUfRg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiglaUfRg() {
        return siglaUfRg;
    }

    /**
     * Define o valor da propriedade siglaUfRg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiglaUfRg(String value) {
        this.siglaUfRg = value;
    }

    /**
     * Obtém o valor da propriedade situacao.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSituacao() {
        return situacao;
    }

    /**
     * Define o valor da propriedade situacao.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSituacao(Integer value) {
        this.situacao = value;
    }

}
