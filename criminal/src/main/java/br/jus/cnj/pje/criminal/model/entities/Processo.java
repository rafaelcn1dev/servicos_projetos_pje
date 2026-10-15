package br.jus.cnj.pje.criminal.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import br.jus.cnj.pje.criminal.enums.TipoProcessoEnum;
import br.jus.cnj.pje.criminal.listeners.CriminalMultiTenancyListener;


@Entity
@Table(name = "tb_processo")
@SequenceGenerator(allocationSize = 1, name = "gen_processo", sequenceName = "sq_tb_processo")
@FilterDef(name = "tribunalFilter", parameters = {@ParamDef(name = "JTR", type = "string")})
@Filter(name = "tribunalFilter", condition = "cd_tribunal = :JTR")
@Audited
@EntityListeners(CriminalMultiTenancyListener.class)
public class Processo extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nrProcesso;
	private Date dtLocalFato;
	private String dsLocalFato;
	private String dsLongitude;
	private String dsLatitude;
	private String cep;
	private Municipio municipio;
	private String nmBairro;
	private String nmLogradouro;
	private String nmNumero;
	private String complemento;
	private String codigoTribunal;
	private TipoProcessoEnum tipoProcesso;

	private List<ProcessoProcedimentoOrigem> processoProcedimentoOrigemList;

	@Id
	@GeneratedValue(generator = "gen_processo")
	@Column(name = "id_processo", unique = true, nullable = false)
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nr_processo", length = 50, unique = true)
	@Length(max = 50)
	public String getNrProcesso() {
		return this.nrProcesso;
	}

	public void setNrProcesso(String nrProcesso) {
		this.nrProcesso = nrProcesso;
	}

	@Column(name = "dt_local_fato", nullable = true)
	public Date getDtLocalFato() {
		return dtLocalFato;
	}

	public void setDtLocalFato(Date dtLocalFato) {
		this.dtLocalFato = dtLocalFato;
	}

	@Column(name = "ds_local_fato", nullable = false)
	@Length(max = 255)
	@NotNull
	public String getDsLocalFato() {
		return dsLocalFato;
	}

	public void setDsLocalFato(String dsLocalFato) {
		this.dsLocalFato = dsLocalFato;
	}

	@Column(name = "ds_latitude", nullable = true)
	@Length(max = 19)
	public String getDsLatitude() {
		return dsLatitude;
	}

	public void setDsLatitude(String dsLatitude) {
		this.dsLatitude = dsLatitude;
	}

	@Column(name = "ds_longitude", nullable = true)
	@Length(max = 19)
	public String getDsLongitude() {
		return dsLongitude;
	}

	public void setDsLongitude(String dsLongitude) {
		this.dsLongitude = dsLongitude;
	}

	@JsonIgnoreProperties("processo")
	@OneToMany(mappedBy = "processo", fetch = FetchType.LAZY)
	public List<ProcessoProcedimentoOrigem> getProcessoProcedimentoOrigemList() {
		return processoProcedimentoOrigemList;
	}

	public void setProcessoProcedimentoOrigemList(List<ProcessoProcedimentoOrigem> processoProcedimentoOrigemList) {
		this.processoProcedimentoOrigemList = processoProcedimentoOrigemList;
	}

	@Column(name = "nr_cep", nullable = false, length = 9)
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
	public String getNmBairro() {
		return nmBairro;
	}

	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}

	@Column(name = "nm_logradouro", nullable = false)
	@Length(max = 200)
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

	@Column(name = "nm_complemento", nullable = true)
	@Length(max = 70)
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	@Override
	@Transient
	@JsonIgnore
	public Boolean getAtivo() {
		return null;
	}

	@Override
	public void setAtivo(Boolean ativo) {

	}

	@Override
	public String toString() {
		return "Processo [id=" + id + ", nrProcesso=" + nrProcesso + ", dtLocalFato=" + dtLocalFato + ", dsLocalFato="
				+ dsLocalFato + ", dsLongitude=" + dsLongitude + ", dsLatitude=" + dsLatitude + ", cep=" + cep
				+ ", municipio=" + municipio + ", nmBairro=" + nmBairro + ", nmLogradouro=" + nmLogradouro
				+ ", nmNumero=" + nmNumero + ", complemento=" + complemento + ", codigoTribunal=" + codigoTribunal
				+ ", processoProcedimentoOrigemList=" + processoProcedimentoOrigemList + "]";
	}

	@Column(name = "cd_tipo_processo")
	@Enumerated(EnumType.STRING)
	public TipoProcessoEnum getTipoProcesso() {
		return tipoProcesso;
	}

	public void setTipoProcesso(TipoProcessoEnum tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}

}