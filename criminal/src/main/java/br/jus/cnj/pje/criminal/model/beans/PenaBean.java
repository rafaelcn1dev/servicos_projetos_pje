package br.jus.cnj.pje.criminal.model.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

public class PenaBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private Integer prazoAnos;
	private Integer prazoMeses;
	private Integer prazoDias;
	private Integer diasMulta;
	private Double valorMulta;
	private Boolean pagouMulta;

	public PenaBean() {
		super();
	}
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPrazoAnos() {
		return prazoAnos;
	}

	public void setPrazoAnos(Integer prazoAnos) {
		this.prazoAnos = prazoAnos;
	}

	public Integer getPrazoMeses() {
		return prazoMeses;
	}

	public void setPrazoMeses(Integer prazoMeses) {
		this.prazoMeses = prazoMeses;
	}

	public Integer getPrazoDias() {
		return prazoDias;
	}

	public void setPrazoDias(Integer prazoDias) {
		this.prazoDias = prazoDias;
	}

	public Integer getDiasMulta() {
		return diasMulta;
	}

	public void setDiasMulta(Integer diasMulta) {
		this.diasMulta = diasMulta;
	}

	public Double getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(Double valorMulta) {
		this.valorMulta = valorMulta;
	}

	public Boolean getPagouMulta() {
		return pagouMulta;
	}

	public void setPagouMulta(Boolean pagouMulta) {
		this.pagouMulta = pagouMulta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((diasMulta == null) ? 0 : diasMulta.hashCode());
		result = prime * result + ((pagouMulta == null) ? 0 : pagouMulta.hashCode());
		result = prime * result + ((prazoAnos == null) ? 0 : prazoAnos.hashCode());
		result = prime * result + ((prazoDias == null) ? 0 : prazoDias.hashCode());
		result = prime * result + ((prazoMeses == null) ? 0 : prazoMeses.hashCode());
		result = prime * result + ((valorMulta == null) ? 0 : valorMulta.hashCode());
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
		PenaBean other = (PenaBean) obj;
		if (diasMulta == null) {
			if (other.diasMulta != null)
				return false;
		} else if (!diasMulta.equals(other.diasMulta))
			return false;
		if (pagouMulta == null) {
			if (other.pagouMulta != null)
				return false;
		} else if (!pagouMulta.equals(other.pagouMulta))
			return false;
		if (prazoAnos == null) {
			if (other.prazoAnos != null)
				return false;
		} else if (!prazoAnos.equals(other.prazoAnos))
			return false;
		if (prazoDias == null) {
			if (other.prazoDias != null)
				return false;
		} else if (!prazoDias.equals(other.prazoDias))
			return false;
		if (prazoMeses == null) {
			if (other.prazoMeses != null)
				return false;
		} else if (!prazoMeses.equals(other.prazoMeses))
			return false;
		if (valorMulta == null) {
			if (other.valorMulta != null)
				return false;
		} else if (!valorMulta.equals(other.valorMulta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PenaBean [prazoAnos=" + prazoAnos + ", prazoMeses=" + prazoMeses + ", prazoDias=" + prazoDias
				+ ", diasMulta=" + diasMulta + ", valorMulta=" + valorMulta + ", pagouMulta=" + pagouMulta + "]";
	}

	public String getPrazoDescritivoPena() {
		StringBuilder resultado = new StringBuilder();
		boolean possuiAno = prazoAnos != null && prazoAnos > 0;
		boolean possuiMes = prazoMeses != null && prazoMeses > 0;
		boolean possuiDia = prazoDias != null && prazoDias > 0;
		boolean possuiPrazo = possuiAno || possuiMes || possuiDia;
		if (possuiPrazo) {
			resultado.append("Prazo da pena: ");
			List<String> prazo = new ArrayList<>();
			if (possuiAno) {
				if (prazoAnos == 1) {
					prazo.add(prazoAnos + " ano");
				} else {
					prazo.add(prazoAnos + " anos");
				}
			}
			if (possuiMes) {
				if (prazoMeses == 1) {
					prazo.add(prazoMeses + " mês");
				} else {
					prazo.add(prazoMeses + " meses");
				}
			}
			if (possuiDia) {
				if (prazoDias == 1) {
					prazo.add(prazoDias + " dia");
				} else {
					prazo.add(prazoDias + " dias");
				}
			}
			int listSize = prazo.size();
			int lastIndex = listSize - 1;
			if (listSize == 1) {
				resultado.append(prazo.get(0));
			}
			if (listSize == 2) {
				resultado.append(prazo.stream().collect(Collectors.joining(" e ")));
			}
			resultado.append(prazo.subList(0, lastIndex).stream().collect(Collectors.joining(", ")).concat(" e ")
					.concat(prazo.get(lastIndex)));
			resultado.append("; ");
		}
		if (!StringUtils.isEmpty(diasMulta)) {
			resultado.append("Dias de multa: " + diasMulta + "; ");
		}
		if (valorMulta != null && valorMulta > 0) {
			resultado.append("Valor da multa: " + valorMulta + "; ");
		}
		if (pagouMulta != null) {
			resultado.append("Pagou a multa? " + (pagouMulta ? "Sim" : "Não")+ "; ");
		}
		return resultado.toString();
	}
	
	

}
