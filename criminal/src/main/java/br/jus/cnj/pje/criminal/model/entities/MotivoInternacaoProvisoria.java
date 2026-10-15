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
@Table(name = "tb_motivo_internacao_provisoria")
@SequenceGenerator(allocationSize = 1, name = "gen_motivo_internacao_provisoria", sequenceName = "sq_tb_motivo_internacao_provisoria")
public class MotivoInternacaoProvisoria extends AbstractEntity<Long>{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String dsMotivoInternacaoProvisoria;
	private Boolean ativo;
	
	public MotivoInternacaoProvisoria() {
		super();
	}

	public MotivoInternacaoProvisoria(Long id, String dsMotivoInternacaoProvisoria, Boolean ativo) {
		super();
		this.id = id;
		this.dsMotivoInternacaoProvisoria = dsMotivoInternacaoProvisoria;
		this.ativo = ativo;
	}

	@Override
	@Id
	@GeneratedValue(generator = "gen_motivo_internacao_provisoria")
	@Column(name = "id_motivo_internacao_provisoria", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ds_motivo_internacao_provisoria", nullable = false)
	public String getDsMotivoInternacaoProvisoria() {
		return dsMotivoInternacaoProvisoria;
	}

	public void setDsMotivoInternacaoProvisoria(String dsMotivoInternacaoProvisoria) {
		this.dsMotivoInternacaoProvisoria = dsMotivoInternacaoProvisoria;
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
