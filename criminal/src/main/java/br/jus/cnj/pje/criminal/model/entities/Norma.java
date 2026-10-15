package br.jus.cnj.pje.criminal.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_norma")
@SequenceGenerator(allocationSize = 1, name = "gen_norma", sequenceName = "sq_tb_norma")
public class Norma extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "gen_norma")
	@Column(name = "id_norma", unique = true, nullable = false)
	private Long id;

	@Column(name = "nr_norma", nullable = false)
	private String numeroNorma;

	@Column(name = "nm_norma", nullable = false)
	private String norma;

	@Column(name = "ds_sigla")
	private String sigla;

	@Column(name = "dt_inicio_vigencia", nullable = false)
	private Date inicioVigencia;

	@Column(name = "dt_fim_vigencia")
	private Date fimVigencia;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "id_tipo_norma")
	private TipoNorma tipoNorma;

	@Column(name = "in_ativo", nullable = false)
	private Boolean ativo;

	public Norma(Long id, String numeroNorma, String norma, String sigla, Date inicioVigencia, Date fimVigencia,
			TipoNorma tipoNorma, Boolean ativo) {
		super();
		this.id = id;
		this.numeroNorma = numeroNorma;
		this.norma = norma;
		this.sigla = sigla;
		this.inicioVigencia = inicioVigencia;
		this.fimVigencia = fimVigencia;
		this.tipoNorma = tipoNorma;
		this.ativo = ativo;
	}
	
	public Norma() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroNorma() {
		return numeroNorma;
	}

	public void setNumeroNorma(String numeroNorma) {
		this.numeroNorma = numeroNorma;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public TipoNorma getTipoNorma() {
		return tipoNorma;
	}

	public void setTipoNorma(TipoNorma tipoNorma) {
		this.tipoNorma = tipoNorma;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	@Transient
	@JsonIgnore
	public String getCodigoTribunal() {
		return null;
	}
	
	@Override
	@JsonProperty
	public void setCodigoTribunal(String codigoTribunal) {
	}
}
