package br.jus.cnj.pje.criminal.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import br.jus.cnj.pje.criminal.enums.CodigoTipoInternacao;

@Entity
@Table(name = "tb_tipo_internacao")
@SequenceGenerator(allocationSize = 1, name = "gen_tipo_internacao", sequenceName = "sq_tb_tipo_internacao")
public class TipoInternacao extends AbstractEntity<Long>{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private CodigoTipoInternacao cdTipoInternacao;
	private String dsTipoInternacao;
	private Boolean ativo;
	
	public TipoInternacao() {
		super();
	}

	public TipoInternacao(Long id, CodigoTipoInternacao cdTipoInternacao, String dsTipoInternacao, Boolean ativo) {
		super();
		this.id = id;
		this.cdTipoInternacao = cdTipoInternacao;
		this.dsTipoInternacao = dsTipoInternacao;
		this.ativo = ativo;
	}

	@Override
	@Id
	@GeneratedValue(generator = "gen_tipo_internacao")
	@Column(name = "id_tipo_internacao", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Column(name = "cd_tipo_internacao", nullable = false)
	@Enumerated(EnumType.STRING)
	public CodigoTipoInternacao getCdTipoInternacao() {
		return cdTipoInternacao;
	}

	public void setCdTipoInternacao(CodigoTipoInternacao cdTipoInternacao) {
		this.cdTipoInternacao = cdTipoInternacao;
	}

	@Column(name = "ds_tipo_internacao", nullable = false)
	public String getDsTipoInternacao() {
		return dsTipoInternacao;
	}

	public void setDsTipoInternacao(String dsTipoInternacao) {
		this.dsTipoInternacao = dsTipoInternacao;
	}

	@Column(name = "in_ativo", nullable = false)
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
	public void setCodigoTribunal(String codigoTribunal) {
	}

}
