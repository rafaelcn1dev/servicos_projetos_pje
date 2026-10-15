package br.jus.cnj.pje.criminal.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.jus.cnj.pje.criminal.base.AbstractEntity;

@Entity
@Table(name = "tb_motivo_retorno_evasao")
@SequenceGenerator(allocationSize = 1, name = "gen_motivo_retorno_evasao", sequenceName = "sq_tb_motivo_retorno_evasao")
public class MotivoRetornoEvasao extends AbstractEntity<Long>{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String dsMotivoRetornoEvasao;
	private Boolean ativo;
	
	public MotivoRetornoEvasao() {
		super();
	}

	public MotivoRetornoEvasao(Long id, String dsMotivoRetornoEvasao, Boolean ativo) {
		super();
		this.id = id;
		this.dsMotivoRetornoEvasao = dsMotivoRetornoEvasao;
		this.ativo = ativo;
	}

	@Override
	@Id
	@GeneratedValue(generator = "gen_motivo_retorno_evasao")
	@Column(name = "id_motivo_retorno_evasao", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ds_motivo_retorno_evasao", nullable = false)
	public String getDsMotivoRetornoEvasao() {
		return dsMotivoRetornoEvasao;
	}

	public void setDsMotivoRetornoEvasao(String dsMotivoRetornoEvasao) {
		this.dsMotivoRetornoEvasao = dsMotivoRetornoEvasao;
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
