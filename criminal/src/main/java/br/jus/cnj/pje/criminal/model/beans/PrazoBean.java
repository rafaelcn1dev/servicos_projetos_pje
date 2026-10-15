package br.jus.cnj.pje.criminal.model.beans;

import java.io.Serializable;

public class PrazoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ano;
	private Integer mes;
	private Integer dia;

	@Override
	public String toString() {
		String prazo = "";
		boolean possuiAno = ano != null && ano > 0;
		boolean possuiMes = mes != null && mes > 0;
		boolean possuiDia = dia != null && dia > 0;
		boolean possuiPrazo = possuiAno || possuiMes || possuiDia;
		if (possuiPrazo) {
			if (possuiAno) {
				if (ano == 1) {
					prazo += (ano + " ano ");
				} else {
					prazo += (ano + " anos ");
				}
			}
			if (possuiMes) {
				if (mes == 1) {
					prazo += (mes + " mês ");
				} else {
					prazo += (mes + " meses ");
				}
			}
			if (possuiDia) {
				if (dia == 1) {
					prazo += (dia + " dia ");
				} else {
					prazo += (dia + " dias ");
				}
			}
			prazo = prazo.replace("  ", " ").trim();
		}
		return prazo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
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
		PrazoBean other = (PrazoBean) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		return true;
	}

}
