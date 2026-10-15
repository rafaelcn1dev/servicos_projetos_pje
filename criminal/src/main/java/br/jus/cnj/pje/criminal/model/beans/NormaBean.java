package br.jus.cnj.pje.criminal.model.beans;

import java.io.Serializable;
import java.util.Date;

public class NormaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String numeroNorma;

	private String norma;

	private String sigla;

	private Date inicioVigencia;

	private Date fimVigencia;

	private Boolean ativo;

	public NormaBean(Long id, 
			String numeroNorma, 
			String norma, 
			String sigla, 
			Date inicioVigencia, 
			Date fimVigencia,
			Boolean ativo) {
		super();
		this.id = id;
		this.numeroNorma = numeroNorma;
		this.norma = norma;
		this.sigla = sigla;
		this.inicioVigencia = inicioVigencia;
		this.fimVigencia = fimVigencia;
		this.ativo = ativo;
	}

	public NormaBean() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroNorma() {
		return numeroNorma;
	}

	public void setNumeroNorma(String numeroNorma) {
		this.numeroNorma = numeroNorma;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((fimVigencia == null) ? 0 : fimVigencia.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inicioVigencia == null) ? 0 : inicioVigencia.hashCode());
		result = prime * result + ((norma == null) ? 0 : norma.hashCode());
		result = prime * result + ((numeroNorma == null) ? 0 : numeroNorma.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		NormaBean other = (NormaBean) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (fimVigencia == null) {
			if (other.fimVigencia != null)
				return false;
		} else if (!fimVigencia.equals(other.fimVigencia))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inicioVigencia == null) {
			if (other.inicioVigencia != null)
				return false;
		} else if (!inicioVigencia.equals(other.inicioVigencia))
			return false;
		if (norma == null) {
			if (other.norma != null)
				return false;
		} else if (!norma.equals(other.norma))
			return false;
		if (numeroNorma == null) {
			if (other.numeroNorma != null)
				return false;
		} else if (!numeroNorma.equals(other.numeroNorma))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}	
	
}
