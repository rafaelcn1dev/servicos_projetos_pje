package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;

public class IncidenciaPenalBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private DispositivoBean dispositivo;
	private Date dtFato;
	private Date dtPrescricao;
	private PenaBean pena;

	public IncidenciaPenalBean() {
		super();
	}
	
	public IncidenciaPenalBean(String id, DispositivoBean dispositivo, Date dtFato, Date dtPrescricao) {
		super(id);
		this.dispositivo = dispositivo;
		this.dtFato = dtFato;
		this.dtPrescricao = dtPrescricao;
	}
	
	public IncidenciaPenalBean(String id, DispositivoBean dispositivo, Date dtFato, Date dtPrescricao, PenaBean pena) {
		super(id);
		this.dispositivo = dispositivo;
		this.dtFato = dtFato;
		this.dtPrescricao = dtPrescricao;
		this.pena = pena;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public DispositivoBean getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(DispositivoBean dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Date getDtFato() {
		return dtFato;
	}

	public void setDtFato(Date dtFato) {
		this.dtFato = dtFato;
	}

	public Date getDtPrescricao() {
		return dtPrescricao;
	}

	public void setDtPrescricao(Date dtPrescricao) {
		this.dtPrescricao = dtPrescricao;
	}
	
	public PenaBean getPena() {
		return pena;
	}
	
	public void setPena(PenaBean pena) {
		this.pena = pena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dispositivo == null) ? 0 : dispositivo.hashCode());
		result = prime * result + ((dtFato == null) ? 0 : dtFato.hashCode());
		result = prime * result + ((dtPrescricao == null) ? 0 : dtPrescricao.hashCode());
		result = prime * result + ((pena == null) ? 0 : pena.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncidenciaPenalBean other = (IncidenciaPenalBean) obj;
		if (dispositivo == null) {
			if (other.dispositivo != null)
				return false;
		} else if (!dispositivo.equals(other.dispositivo))
			return false;
		if (dtFato == null) {
			if (other.dtFato != null)
				return false;
		} else if (!dtFato.equals(other.dtFato))
			return false;
		if (dtPrescricao == null) {
			if (other.dtPrescricao != null)
				return false;
		} else if (!dtPrescricao.equals(other.dtPrescricao))
			return false;
		if (pena == null) {
			if (other.pena != null)
				return false;
		} else if (!pena.equals(other.pena))
			return false;
		return true;
	}
}
