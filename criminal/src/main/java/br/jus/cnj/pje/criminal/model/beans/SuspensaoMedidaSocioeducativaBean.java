package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class SuspensaoMedidaSocioeducativaBean extends EventoCriminalBean {
	
	private static final long serialVersionUID = 1L;

	private Date dtInicio;
	private Date dtFim;
	private Integer prazo;

	public SuspensaoMedidaSocioeducativaBean() {
		super();
	}

	public SuspensaoMedidaSocioeducativaBean(String id, Date dtInicio, Date dtFim, Integer prazo) {
		super(id);
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.prazo = prazo;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dtFim == null) ? 0 : dtFim.hashCode());
		result = prime * result + ((dtInicio == null) ? 0 : dtInicio.hashCode());
		result = prime * result + ((prazo == null) ? 0 : prazo.hashCode());
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
		SuspensaoMedidaSocioeducativaBean other = (SuspensaoMedidaSocioeducativaBean) obj;
		if (dtFim == null) {
			if (other.dtFim != null)
				return false;
		} else if (!dtFim.equals(other.dtFim))
			return false;
		if (dtInicio == null) {
			if (other.dtInicio != null)
				return false;
		} else if (!dtInicio.equals(other.dtInicio))
			return false;
		if (prazo == null) {
			if (other.prazo != null)
				return false;
		} else if (!prazo.equals(other.prazo))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (dtInicio != null) {
			retorno.append("Data de início: " + DateUtil.dateToString(dtInicio) + "; ");
		}
		if (dtFim != null) {
			retorno.append("Data de fim: " + DateUtil.dateToString(dtFim) + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dtInicio != null) {
			camposVisualizacao.put("Data de início", DateUtil.dateToString(dtInicio));
		}
		if (dtFim != null) {
			camposVisualizacao.put("Data de fim", DateUtil.dateToString(dtFim));
		}
		if (prazo != null) {
			camposVisualizacao.put("Prazo (dias)", prazo.toString());
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDtInicio();
	}

}
