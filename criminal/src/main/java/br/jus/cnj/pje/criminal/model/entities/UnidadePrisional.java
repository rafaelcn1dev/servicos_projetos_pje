package br.jus.cnj.pje.criminal.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import br.jus.cnj.pje.criminal.listeners.CriminalMultiTenancyListener;


@Entity
@Table(name = "tb_unidade_prisional")
@SequenceGenerator(allocationSize = 1, name = "gen_unidade_prisional", sequenceName = "sq_tb_unidade_prisional")
@FilterDef(name = "tribunalFilter", parameters = {@ParamDef(name = "JTR", type = "string")})
@Filter(name = "tribunalFilter", condition = "cd_tribunal = :JTR")	
@EntityListeners(CriminalMultiTenancyListener.class)
public class UnidadePrisional extends AbstractEntity<Long> {
		

	private static final long serialVersionUID = 1L;

	private Long id;
	private String dsUnidadePrisional;
	private String dsApelidoUnidade;
	private String dsJurisdicao;
	private String nrCep;
	private Municipio municipio;
	private String nmBairro;
	private String nmLogradouro;
	private String nrTelefone;
	private String nrFax;
	private String dsEmail;
	private Boolean ativo;
	private String codigoTribunal;


	@Id
	@GeneratedValue(generator = "gen_unidade_prisional")
	@Column(name = "id_unidade_prisional", unique = true, nullable = false)
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "ds_unidade_prisional", nullable = false)
	public String getDsUnidadePrisional() {
		return dsUnidadePrisional;
	}

	public void setDsUnidadePrisional(String dsUnidadePrisional) {
		this.dsUnidadePrisional = dsUnidadePrisional;
	}

	@Column(name = "ds_apelido_unidade", nullable = true)
	public String getDsApelidoUnidade() {
		return dsApelidoUnidade;
	}

	public void setDsApelidoUnidade(String dsApelidoUnidade) {
		this.dsApelidoUnidade = dsApelidoUnidade;
	}

	@Column(name = "ds_jurisdicao", nullable = false)
	public String getDsJurisdicao() {
		return dsJurisdicao;
	}

	public void setDsJurisdicao(String dsJurisdicao) {
		this.dsJurisdicao = dsJurisdicao;
	}

	@Column(name = "nr_cep", nullable = false)
	public String getNrCep() {
		return nrCep;
	}

	public void setNrCep(String nrCep) {
		this.nrCep = nrCep;
	}

	@Column(name = "nr_telefone", nullable = true)
	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	@Column(name = "nr_fax", nullable = true)
	public String getNrFax() {
		return nrFax;
	}

	public void setNrFax(String nrFax) {
		this.nrFax = nrFax;
	}

	@Column(name = "ds_email", nullable = true)
	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "id_municipio", nullable = true)
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Column(name = "nm_bairro", nullable = false)
	@Length(max = 100)
	@NotNull
	public String getNmBairro() {
		return nmBairro;
	}

	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}
	
	@Column(name = "nm_logradouro", nullable = false)
	@Length(max = 200)
	@NotNull
	public String getNmLogradouro() {
		return nmLogradouro;
	}

	public void setNmLogradouro(String nmLogradouro) {
		this.nmLogradouro = nmLogradouro;
	}

	@Column(name = "in_ativo", nullable = false)
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	@Column(name = "cd_tribunal")
	@JsonIgnore
	public String getCodigoTribunal() {
		return this.codigoTribunal;
	}
	
	@Override
	@JsonProperty
	public void setCodigoTribunal(String codigoTribunal) {
		this.codigoTribunal = codigoTribunal;
	}	

}
