package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.model.entities.MotivoSuspensaoProcesso;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class SuspensaoProcessoBean extends EventoCriminalBean {
	private static final long serialVersionUID = 1L;

	private MotivoSuspensaoProcesso motivoSuspensaoProcesso;
	private Date dataInicio;
	private Date dataFim;
	private Integer prazoDias;
	private Integer prazoMeses;
	private Integer prazoAnos;

	public SuspensaoProcessoBean() {
		super();
	}

	public SuspensaoProcessoBean(String id, MotivoSuspensaoProcesso motivoSuspensaoProcesso, Date dataInicio,
			Date dataFim, Integer prazoDias, Integer prazoMeses, Integer prazoAnos) {
		super(id);
		this.motivoSuspensaoProcesso = motivoSuspensaoProcesso;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.prazoDias = prazoDias;
		this.prazoMeses = prazoMeses;
		this.prazoAnos = prazoAnos;
	}

	public MotivoSuspensaoProcesso getMotivoSuspensaoProcesso() {
		return motivoSuspensaoProcesso;
	}

	public void setMotivoSuspensaoProcesso(MotivoSuspensaoProcesso motivoSuspensaoProcesso) {
		this.motivoSuspensaoProcesso = motivoSuspensaoProcesso;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getPrazoDias() {
		return prazoDias;
	}

	public void setPrazoDias(Integer prazoDias) {
		this.prazoDias = prazoDias;
	}

	public Integer getPrazoMeses() {
		return prazoMeses;
	}

	public void setPrazoMeses(Integer prazoMeses) {
		this.prazoMeses = prazoMeses;
	}

	public Integer getPrazoAnos() {
		return prazoAnos;
	}

	public void setPrazoAnos(Integer prazoAnos) {
		this.prazoAnos = prazoAnos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((motivoSuspensaoProcesso == null) ? 0 : motivoSuspensaoProcesso.hashCode());
		result = prime * result + ((prazoAnos == null) ? 0 : prazoAnos.hashCode());
		result = prime * result + ((prazoDias == null) ? 0 : prazoDias.hashCode());
		result = prime * result + ((prazoMeses == null) ? 0 : prazoMeses.hashCode());
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
		SuspensaoProcessoBean other = (SuspensaoProcessoBean) obj;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (motivoSuspensaoProcesso == null) {
			if (other.motivoSuspensaoProcesso != null)
				return false;
		} else if (!motivoSuspensaoProcesso.equals(other.motivoSuspensaoProcesso))
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
		return true;
	}

	@Override
	public String toString() {
		return obterTextoFinal();
	}	

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (dataInicio != null) {
			retorno.append("Data de início: " + DateUtil.dateToString(dataInicio) + "; ");
		}
		if (dataFim != null) {
			retorno.append("Data de fim: " + DateUtil.dateToString(dataFim) + "; ");
		}
		if (motivoSuspensaoProcesso != null) {
			retorno.append("Motivo: " + motivoSuspensaoProcesso.getMotivoSuspensao() + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dataInicio != null) {
			camposVisualizacao.put("Data de início", DateUtil.dateToString(dataInicio));
		}
		if (dataFim != null) {
			camposVisualizacao.put("Data de fim", DateUtil.dateToString(dataFim));
		}
		if (motivoSuspensaoProcesso != null) {
			camposVisualizacao.put("Motivo", motivoSuspensaoProcesso.getMotivoSuspensao());
		}
		if (prazoAnos != null || prazoMeses != null || prazoDias != null) {
			String prazo = "";
			if (prazoAnos != null) {
				if (prazoAnos == 1) {
					prazo += (prazoAnos + " ano ");	
				} else {
					prazo += (prazoAnos + " anos ");
				}
			}
			if (prazoMeses != null) {
				if (prazoMeses == 1) {
					prazo += (prazoMeses + " mês ");	
				} else {
					prazo += (prazoMeses + " meses ");
				}
			}
			if (prazoDias != null) {
				if (prazoDias == 1) {
					prazo += (prazoDias + " dia ");	
				} else {
					prazo += (prazoDias + " dias ");
				}
			}
			camposVisualizacao.put("Prazo", prazo);
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDataInicio();
	}

}
