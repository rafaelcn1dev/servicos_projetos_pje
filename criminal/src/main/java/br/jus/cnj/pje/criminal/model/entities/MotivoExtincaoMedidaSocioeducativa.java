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
@Table(name = "tb_motivo_extincao_med_socioeducativa")
@SequenceGenerator(allocationSize = 1, name = "gen_motivo_extincao_med_socioeducativa", sequenceName = "sq_tb_motivo_extincao_med_socioeducativa")
public class MotivoExtincaoMedidaSocioeducativa extends AbstractEntity<Long>{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String dsMotivoExtincao;
	private Boolean ativo;
	
	public MotivoExtincaoMedidaSocioeducativa() {
		super();
	}

	public MotivoExtincaoMedidaSocioeducativa(Long id, String dsMotivoExtincao, Boolean ativo) {
		super();
		this.id = id;
		this.dsMotivoExtincao = dsMotivoExtincao;
		this.ativo = ativo;
	}

	@Override
	@Id
	@GeneratedValue(generator = "gen_motivo_extincao_med_socioeducativa")
	@Column(name = "id_motivo_extincao_med_socioeducativa", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ds_motivo_extincao_med_socioeducativa", nullable = false)
	public String getDsMotivoExtincao() {
		return dsMotivoExtincao;
	}

	public void setDsMotivoExtincao(String dsMotivoExtincao) {
		this.dsMotivoExtincao = dsMotivoExtincao;
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
