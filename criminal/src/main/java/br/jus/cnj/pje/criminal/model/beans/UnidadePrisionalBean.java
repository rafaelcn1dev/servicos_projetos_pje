package br.jus.cnj.pje.criminal.model.beans;

import java.io.Serializable;

import br.jus.cnj.pje.criminal.model.entities.Municipio;
import br.jus.cnj.pje.criminal.model.entities.UnidadePrisional;

public class UnidadePrisionalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String dsUnidadePrisional;
	private String uf;

	public UnidadePrisionalBean() {

	}

	public UnidadePrisionalBean(UnidadePrisional unidadePrisional) {
		this.id = unidadePrisional.getId();
		this.dsUnidadePrisional = unidadePrisional.getDsUnidadePrisional();
		Municipio municipio = unidadePrisional.getMunicipio();
		if (municipio != null) {
			this.uf = municipio.getUf();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsUnidadePrisional() {
		return dsUnidadePrisional;
	}

	public void setDsUnidadePrisional(String dsUnidadePrisional) {
		this.dsUnidadePrisional = dsUnidadePrisional;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsUnidadePrisional == null) ? 0 : dsUnidadePrisional.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnidadePrisionalBean other = (UnidadePrisionalBean) obj;
		if (dsUnidadePrisional == null) {
			if (other.dsUnidadePrisional != null)
				return false;
		} else if (!dsUnidadePrisional.equals(other.dsUnidadePrisional))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}

}
