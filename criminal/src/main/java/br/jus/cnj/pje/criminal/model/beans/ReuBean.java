package br.jus.cnj.pje.criminal.model.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReuBean extends BaseBean{

	private static final long serialVersionUID = 1L;

	private String id;
	private String nomePessoa;
	private String nomeMae;
	private Date dataNascimento;
	private String sexo;
	private String nomeLogradouro;
	private String nomeBairro;
	private String municipio;
	private String estado;
	private String numeroCep;
	private String cpf;
	private String numeroEndereco;
	private String complemento;
	// Já tem um com bean. Verificar.
	private List<DocumentoIdentificacaoDTO> documentosIdentificacao  = new ArrayList<DocumentoIdentificacaoDTO>();
	private Date dataObito;
	private List<String> outrosNomes = new ArrayList<String>();


	public ReuBean() {
		super();
	}
	
	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNomeLogradouro() {
		return nomeLogradouro;
	}

	public void setNomeLogradouro(String nomeLogradouro) {
		this.nomeLogradouro = nomeLogradouro;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumeroCep() {
		return numeroCep;
	}

	public void setNumeroCep(String numeroCep) {
		this.numeroCep = numeroCep;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return this.nomePessoa;
	}

	public List<DocumentoIdentificacaoDTO> getDocumentosIdentificacao() {
		return documentosIdentificacao;
	}

	public void setDocumentosIdentificacao(List<DocumentoIdentificacaoDTO> documentosIdentificacao) {
		this.documentosIdentificacao = documentosIdentificacao;
	}

	public Date getDataObito() {
		return dataObito;
	}

	public void setDataObito(Date dataObito) {
		this.dataObito = dataObito;
	}

	public List<String> getOutrosNomes() {
		return outrosNomes;
	}

	public void setOutrosNomes(List<String> outrosNomes) {
		this.outrosNomes = outrosNomes;
	}
	
	
	
	
}
