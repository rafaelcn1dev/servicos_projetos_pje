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

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import br.jus.cnj.pje.criminal.listeners.CriminalMultiTenancyListener;

@Entity
@Table(name = "tb_org_prcdmnto_originario")
@SequenceGenerator(allocationSize = 1, name = "gen_org_prcdmnto_originario", sequenceName = "sq_tb_org_prcdmnto_originario")
@Audited
@EntityListeners(CriminalMultiTenancyListener.class)
public class OrgaoProcedimentoOriginario extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private TipoOrigem tipoOrigem;
	private String dsCodOrigem;
	private String dsNomeOrgao;
	private String dsTelefone;
	private String dsDdd;
	private String cep;
	private Municipio municipio;
	private String nmBairro;
	private String nmLogradouro;
	private String nmComplemento;
	private String nmNumero;
	private Boolean ativo;
	private String codigoTribunal;

	@Id
	@GeneratedValue(generator = "gen_org_prcdmnto_originario")
	@Column(name = "id_org_procedimento_originario", unique = true, nullable = false)
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_tipo_origem", nullable = false)
	public TipoOrigem getTipoOrigem() {
		return tipoOrigem;
	}

	public void setTipoOrigem(TipoOrigem tipoOrigem) {
		this.tipoOrigem = tipoOrigem;
	}

	@Column(name = "ds_cod_origem", nullable = true)
	@Length(max = 10)
	public String getDsCodOrigem() {
		return dsCodOrigem;
	}

	public void setDsCodOrigem(String dsCodOrigem) {
		this.dsCodOrigem = dsCodOrigem;
	}

	@Column(name = "ds_nome_orgao", nullable = false)
	@Length(max = 100)
	@NotNull
	public String getDsNomeOrgao() {
		return dsNomeOrgao;
	}

	public void setDsNomeOrgao(String dsNomeOrgao) {
		this.dsNomeOrgao = dsNomeOrgao;
	}

	@Column(name = "ds_ddd", nullable = true)
	@Length(max = 2)
	public String getDsDdd() {
		return dsDdd;
	}

	public void setDsDdd(String dsDdd) {
		this.dsDdd = dsDdd;
	}

	@Column(name = "ds_telefone", nullable = true)
	@Length(max = 10)
	public String getDsTelefone() {
		return dsTelefone;
	}

	public void setDsTelefone(String dsTelefone) {
		this.dsTelefone = dsTelefone;
	}

	@Column(name = "nr_cep", nullable = false, length = 9)
	@NotNull
	@Length(max = 9)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	@Column(name = "nm_complemento", nullable = true)
	@Length(max = 70)
	public String getNmComplemento() {
		return nmComplemento;
	}

	public void setNmComplemento(String nmComplemento) {
		this.nmComplemento = nmComplemento;
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

	@Column(name = "nm_numero", nullable = true)
	@Length(max = 10)
	public String getNmNumero() {
		return nmNumero;
	}

	public void setNmNumero(String nmNumero) {
		this.nmNumero = nmNumero;
	}

	@Column(name = "in_ativo", nullable = false)
	@NotNull
	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return getDsNomeOrgao();
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
