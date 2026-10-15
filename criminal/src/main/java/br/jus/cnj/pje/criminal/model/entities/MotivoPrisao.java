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
@Table(name = "tb_motivo_prisao")
@SequenceGenerator(allocationSize = 1, name = "gen_motivo_prisao", sequenceName = "sq_tb_motivo_prisao")
public class MotivoPrisao extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String  motivoPrisao;
	private Boolean ativo;
	
	public MotivoPrisao() {
		super();
	}

	public MotivoPrisao(Long id, String motivoPrisao, Boolean ativo) {
		super();
		this.id = id;
		this.motivoPrisao = motivoPrisao;
		this.ativo = ativo;
	}

	@Override
	@Id
	@GeneratedValue(generator = "gen_motivo_prisao")
	@Column(name = "id_motivo_prisao", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ds_motivo_prisao", nullable = false)
	public String getMotivoPrisao() {
		return motivoPrisao;
	}

	public void setMotivoPrisao(String motivoPrisao) {
		this.motivoPrisao = motivoPrisao;
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
