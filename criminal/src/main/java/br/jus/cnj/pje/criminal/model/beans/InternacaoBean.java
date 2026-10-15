package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.model.entities.MotivoInternacaoProvisoria;
import br.jus.cnj.pje.criminal.model.entities.TipoInternacao;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class InternacaoBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;
	
	private TipoInternacao tipoInternacao;
	private MotivoInternacaoProvisoria motivoInternacaoProvisoria;
	private Date dtInicio;
	private Date dtFim;
	private Date dtEsgotamento;
	private Date dtEntregaRelatorio;
	private Integer prazo;
	
	public TipoInternacao getTipoInternacao() {
		return tipoInternacao;
	}
	public void setTipoInternacao(TipoInternacao tipoInternacao) {
		this.tipoInternacao = tipoInternacao;
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
	public Date getDtEsgotamento() {
		return dtEsgotamento;
	}
	public void setDtEsgotamento(Date dtEsgotamento) {
		this.dtEsgotamento = dtEsgotamento;
	}
	public Date getDtEntregaRelatorio() {
		return dtEntregaRelatorio;
	}
	public void setDtEntregaRelatorio(Date dtEntregaRelatorio) {
		this.dtEntregaRelatorio = dtEntregaRelatorio;
	}
	public Integer getPrazo() {
		return prazo;
	}
	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}
	public MotivoInternacaoProvisoria getMotivoInternacaoProvisoria() {
		return motivoInternacaoProvisoria;
	}
	public void setMotivoInternacaoProvisoria(MotivoInternacaoProvisoria motivoInternacaoProvisoria) {
		this.motivoInternacaoProvisoria = motivoInternacaoProvisoria;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (tipoInternacao != null) {
			retorno.append("Tipo: " + tipoInternacao.getDsTipoInternacao() + "; ");
		}
		if (motivoInternacaoProvisoria != null) {
			retorno.append("Motivo da internação provisória: " + motivoInternacaoProvisoria.getDsMotivoInternacaoProvisoria() + "; ");
		}
		if (dtInicio != null) {
			retorno.append("Data de apreensão: " + DateUtil.dateToString(dtInicio) + "; ");
		}
		if (dtFim != null) {
			retorno.append("Data de liberação: " + DateUtil.dateToString(dtFim) + "; ");
		}
		if (dtEsgotamento != null) {
			retorno.append("Data de esgotamento do prazo legal: " + DateUtil.dateToString(dtEsgotamento) + "; ");
		}
		if (prazo != null) {
			retorno.append("Prazo (dias): " + prazo.toString() + "; ");
		}
		if (dtEntregaRelatorio != null) {
			retorno.append("Data de entrega do relatório: " + DateUtil.dateToString(dtEntregaRelatorio) + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (tipoInternacao != null) {
			camposVisualizacao.put("Tipo", tipoInternacao.getDsTipoInternacao());
		}
		if (motivoInternacaoProvisoria != null) {
			camposVisualizacao.put("Motivo da internação provisória", motivoInternacaoProvisoria.getDsMotivoInternacaoProvisoria());
		}
		if (dtInicio != null) {
			camposVisualizacao.put("Data de apreensão", DateUtil.dateToString(dtInicio));
		}
		if (dtFim != null) {
			camposVisualizacao.put("Data de liberação", DateUtil.dateToString(dtFim));
		}
		if (dtEsgotamento != null) {
			camposVisualizacao.put("Data de esgotamento do prazo legal", DateUtil.dateToString(dtEsgotamento));
		}
		if (prazo != null) {
			camposVisualizacao.put("Prazo (dias)", prazo.toString());
		}
		if (dtEntregaRelatorio != null) {
			camposVisualizacao.put("Data de entrega do relatório", DateUtil.dateToString(dtEntregaRelatorio));
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDtInicio();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dtEntregaRelatorio == null) ? 0 : dtEntregaRelatorio.hashCode());
		result = prime * result + ((dtEsgotamento == null) ? 0 : dtEsgotamento.hashCode());
		result = prime * result + ((dtFim == null) ? 0 : dtFim.hashCode());
		result = prime * result + ((dtInicio == null) ? 0 : dtInicio.hashCode());
		result = prime * result + ((prazo == null) ? 0 : prazo.hashCode());
		result = prime * result + ((tipoInternacao == null) ? 0 : tipoInternacao.hashCode());
		result = prime * result + ((motivoInternacaoProvisoria == null) ? 0 : motivoInternacaoProvisoria.hashCode());
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
		InternacaoBean other = (InternacaoBean) obj;
		if (dtEntregaRelatorio == null) {
			if (other.dtEntregaRelatorio != null)
				return false;
		} else if (!dtEntregaRelatorio.equals(other.dtEntregaRelatorio))
			return false;
		if (dtEsgotamento == null) {
			if (other.dtEsgotamento != null)
				return false;
		} else if (!dtEsgotamento.equals(other.dtEsgotamento))
			return false;
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
		if (tipoInternacao == null) {
			if (other.tipoInternacao != null)
				return false;
		} else if (!tipoInternacao.equals(other.tipoInternacao))
			return false;
		if (motivoInternacaoProvisoria == null) {
			if (other.motivoInternacaoProvisoria != null)
				return false;
		} else if (!motivoInternacaoProvisoria.equals(other.motivoInternacaoProvisoria))
			return false;
		return true;
	}
	
}
