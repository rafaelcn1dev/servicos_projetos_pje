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
@Table(name = "tb_natureza_prisao")
@SequenceGenerator(allocationSize = 1, name = "gen_natureza_prisao", sequenceName = "sq_tb_natureza_prisao")
public class NaturezaPrisao extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String  dsNaturezaPrisao;
	private Boolean ativo;

	public NaturezaPrisao() {
	}

	@Id
	@GeneratedValue(generator = "gen_natureza_prisao")
	@Column(name = "id_natureza_prisao", unique = true, nullable = false)
	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ds_natureza_prisao", nullable = false)
	public String getDsNaturezaPrisao() {
		return dsNaturezaPrisao;
	}

	public void setDsNaturezaPrisao(String dsNaturezaPrisao) {
		this.dsNaturezaPrisao = dsNaturezaPrisao;
	}

	@Column(name = "in_ativo", nullable = false)
	public Boolean getAtivo() {
		return this.ativo;
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
