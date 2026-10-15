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
@Table(name = "tb_motivo_suspensao_processo")
@SequenceGenerator(allocationSize = 1, name = "gen_motivo_suspensao_processo", sequenceName = "sq_tb_motivo_suspensao_processo")
public class MotivoSuspensaoProcesso extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String motivoSuspensao;
	private Boolean ativo;
	
	public MotivoSuspensaoProcesso() {
		super();
	}

	public MotivoSuspensaoProcesso(Long id, String motivoSuspensao, Boolean ativo) {
		super();
		this.id = id;
		this.motivoSuspensao = motivoSuspensao;
		this.ativo = ativo;
	}

	@Override
	@Id
	@GeneratedValue(generator = "gen_motivo_suspensao_processo")
	@Column(name = "id_motivo_suspensao", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ds_motivo_suspensao", nullable = false)
	public String getMotivoSuspensao() {
		return motivoSuspensao;
	}

	public void setMotivoSuspensao(String motivoSuspensao) {
		this.motivoSuspensao = motivoSuspensao;
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
