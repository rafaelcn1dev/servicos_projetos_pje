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
@Table(name = "tb_tipo_recurso")
@SequenceGenerator(allocationSize = 1, name = "gen_tipo_recurso", sequenceName = "sq_tb_tipo_recurso")
public class TipoRecurso extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String codigo;
	private String tipoRecurso;
	private Boolean ativo;
	private Boolean admissibilidadeRecurso;

	public TipoRecurso() {
		super();
	}

	public TipoRecurso(Long id, String codigo, String tipoRecurso, Boolean ativo, Boolean admissibilidadeRecurso) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.tipoRecurso = tipoRecurso;
		this.ativo = ativo;
		this.admissibilidadeRecurso = admissibilidadeRecurso;
	}

	@Override
	@Id
	@GeneratedValue(generator = "gen_tipo_recurso")
	@Column(name = "id_tipo_recurso", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cod_tipo_recurso", nullable = false)
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "ds_tipo_recurso", nullable = false)
	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	@Column(name = "in_ativo", nullable = false)
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Column(name = "in_admissibilidade_recurso")
	public Boolean getAdmissibilidadeRecurso() {
		return admissibilidadeRecurso;
	}

	public void setAdmissibilidadeRecurso(Boolean admissibilidadeRecurso) {
		this.admissibilidadeRecurso = admissibilidadeRecurso;
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
