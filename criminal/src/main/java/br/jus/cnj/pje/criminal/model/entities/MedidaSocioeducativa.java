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
@Table(name = "tb_medida_socioeducativa")
@SequenceGenerator(allocationSize = 1, name = "gen_medida_socioeducativa", sequenceName = "sq_tb_medida_socioeducativa")
public class MedidaSocioeducativa extends AbstractEntity<Long>{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String cdMedidaSocioeducativa;
	private String dsMedidaSocioeducativa;
	private Boolean ativo;
	
	public MedidaSocioeducativa() {
		super();
	}

	public MedidaSocioeducativa(Long id, String cdMedidaSocioeducativa, String dsMedidaSocioeducativa, Boolean ativo) {
		super();
		this.id = id;
		this.cdMedidaSocioeducativa = cdMedidaSocioeducativa;
		this.dsMedidaSocioeducativa = dsMedidaSocioeducativa;
		this.ativo = ativo;
	}

	@Override
	@Id
	@GeneratedValue(generator = "gen_medida_socioeducativa")
	@Column(name = "id_medida_socioeducativa", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "cd_medida_socioeducativa", nullable = false)
	public String getCdMedidaSocioeducativa() {
		return cdMedidaSocioeducativa;
	}

	public void setCdMedidaSocioeducativa(String cdMedidaSocioeducativa) {
		this.cdMedidaSocioeducativa = cdMedidaSocioeducativa;
	}

	@Column(name = "ds_medida_socioeducativa", nullable = false)
	public String getDsMedidaSocioeducativa() {
		return dsMedidaSocioeducativa;
	}

	public void setDsMedidaSocioeducativa(String dsMedidaSocioeducativa) {
		this.dsMedidaSocioeducativa = dsMedidaSocioeducativa;
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
