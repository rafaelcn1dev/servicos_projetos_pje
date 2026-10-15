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
@Table(name = "tb_tipo_norma")
@SequenceGenerator(allocationSize = 1, name = "gen_tipo_norma", sequenceName = "sq_tb_tipo_norma")
public class TipoNorma extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "gen_tipo_norma")
	@Column(name = "id_tipo_norma", unique = true, nullable = false)
	private Long id;

	@Column(name = "ds_tipo_norma", nullable = false)
	private String tipoNorma;

	@Column(name = "in_ativo", nullable = false)
	private Boolean ativo;

	public TipoNorma(Long id, String tipoNorma, Boolean ativo) {
		super();
		this.id = id;
		this.tipoNorma = tipoNorma;
		this.ativo = ativo;
	}

	public TipoNorma() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoNorma() {
		return tipoNorma;
	}

	public void setTipoNorma(String tipoNorma) {
		this.tipoNorma = tipoNorma;
	}

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
