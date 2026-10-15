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
import br.jus.cnj.pje.criminal.enums.CodigoTipoRemissao;

@Entity
@Table(name = "tb_tipo_remissao")
@SequenceGenerator(allocationSize = 1, name = "gen_tipo_remissao", sequenceName = "sq_tb_tipo_remissao")
public class TipoRemissao extends AbstractEntity<Long>{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private CodigoTipoRemissao cdTipoRemissao;
	private String dsTipoRemissao;
	private Boolean ativo;
	
	public TipoRemissao() {
		super();
	}

	public TipoRemissao(Long id, CodigoTipoRemissao cdTipoRemissao, String dsTipoRemissao, Boolean ativo) {
		super();
		this.id = id;
		this.cdTipoRemissao = cdTipoRemissao;
		this.dsTipoRemissao = dsTipoRemissao;
		this.ativo = ativo;
	}

	@Override
	@Id
	@GeneratedValue(generator = "gen_tipo_remissao")
	@Column(name = "id_tipo_remissao", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Column(name = "cd_tipo_remissao", nullable = false)
	@Enumerated(EnumType.STRING)
	public CodigoTipoRemissao getCdTipoRemissao() {
		return cdTipoRemissao;
	}

	public void setCdTipoRemissao(CodigoTipoRemissao cdTipoRemissao) {
		this.cdTipoRemissao = cdTipoRemissao;
	}

	@Column(name = "ds_tipo_remissao", nullable = false)
	public String getDsTipoRemissao() {
		return dsTipoRemissao;
	}

	public void setDsTipoRemissao(String dsTipoRemissao) {
		this.dsTipoRemissao = dsTipoRemissao;
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