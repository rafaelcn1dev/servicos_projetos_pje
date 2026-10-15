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
@Table(name = "tb_motivo_soltura")
@SequenceGenerator(allocationSize = 1, name = "gen_motivo_soltura", sequenceName = "sq_tb_motivo_soltura")
public class MotivoSoltura extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String  motivoSoltura;
	private Boolean ativo;
	
	public MotivoSoltura() {
		super();
	}

	public MotivoSoltura(Long id, String motivoSoltura, Boolean ativo) {
		super();
		this.id = id;
		this.motivoSoltura = motivoSoltura;
		this.ativo = ativo;
	}

	@Id
	@GeneratedValue(generator = "gen_motivo_soltura")
	@Column(name = "id_motivo_soltura", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ds_motivo_soltura", nullable = false)
	public String getMotivoSoltura() {
		return motivoSoltura;
	}
	
	public void setMotivoSoltura(String motivoSoltura) {
		this.motivoSoltura = motivoSoltura;
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
