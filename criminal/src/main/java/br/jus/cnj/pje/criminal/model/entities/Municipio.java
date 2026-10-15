package br.jus.cnj.pje.criminal.model.entities;

import javax.persistence.*;
import javax.persistence.Transient;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_municipio")
@SequenceGenerator(allocationSize = 1, name = "gen_municipio", sequenceName = "sq_tb_municipio")
@Audited
public class Municipio extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String municipio;
	private String codigoIbge;
	private String uf;

	@Id
	@GeneratedValue(generator = "gen_municipio")
	@Column(name = "id_municipio", unique = true, nullable = false)
	@Override
	@JsonIgnore
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ds_municipio", length = 50)
	@Length(max = 50)
	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Column(name = "cd_municipio_ibge", length = 10)
	@Length(max = 10)
	public String getCodigoIbge() {
		return this.codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	@Column(name = "cd_uf", length = 2)
	@Length(max = 2)
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Override
	@Transient
	@JsonIgnore
	public String getCodigoTribunal() {
		return null;
	}
	
	@Override
	@JsonProperty
	public void setCodigoTribunal(String codigoTribunal) {
	}

	@Override
	@Transient
	@JsonIgnore
	public Boolean getAtivo() {
		return null;
	}

	@Override
	public void setAtivo(Boolean ativo) {

	}


}
