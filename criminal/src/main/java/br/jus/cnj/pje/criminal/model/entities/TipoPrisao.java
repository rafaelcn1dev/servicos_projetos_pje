package br.jus.cnj.pje.criminal.model.entities;

import br.jus.cnj.pje.criminal.base.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_tipo_prisao")
@SequenceGenerator(allocationSize = 1, name = "gen_tipo_prisao", sequenceName = "sq_tb_tipo_prisao")
public class TipoPrisao extends AbstractEntity<Long> {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String  tipoPrisao;
	private Boolean ativo;
	
	public TipoPrisao() {
		super();
	}

	public TipoPrisao(Long id, String tipoPrisao, Boolean ativo) {
		super();
		this.id = id;
		this.tipoPrisao = tipoPrisao;
		this.ativo = ativo;
	}

	@Override
	@Id
	@GeneratedValue(generator = "gen_tipo_prisao")
	@Column(name = "id_tipo_prisao", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ds_tipo_prisao", nullable = false)
	public String getTipoPrisao() {
		return tipoPrisao;
	}

	public void setTipoPrisao(String tipoPrisao) {
		this.tipoPrisao = tipoPrisao;
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
	public String getCodigoTribunal() {
		return null;
	}
	
	@Override
	public void setCodigoTribunal(String codigoTribunal) {
	}	

}
