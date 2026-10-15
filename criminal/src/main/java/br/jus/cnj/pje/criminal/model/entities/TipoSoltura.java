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
@Table(name = "tb_tipo_soltura")
@SequenceGenerator(allocationSize = 1, name = "gen_tipo_soltura", sequenceName = "sq_tb_tipo_soltura")
public class TipoSoltura extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String  tipoSoltura;
	private Boolean ativo;
	
	public TipoSoltura() {
		super();
	}
	public TipoSoltura(Long id, String tipoSoltura, Boolean ativo) {
		super();
		this.id = id;
		this.tipoSoltura = tipoSoltura;
		this.ativo = ativo;
	}
	
	@Id
	@GeneratedValue(generator = "gen_tipo_soltura")
	@Column(name = "id_tipo_soltura", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "ds_tipo_soltura", nullable = false)
	public String getTipoSoltura() {
		return tipoSoltura;
	}
	
	public void setTipoSoltura(String tipoSoltura) {
		this.tipoSoltura = tipoSoltura;
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
