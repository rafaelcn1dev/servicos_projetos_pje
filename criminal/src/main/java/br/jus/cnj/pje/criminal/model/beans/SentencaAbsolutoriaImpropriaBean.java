package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.jus.cnj.pje.criminal.enums.MedidaSegurancaEnum;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class SentencaAbsolutoriaImpropriaBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataSentenca;
	private Date dataTransitoJulgado;
	private Date datatransitoAcusacao;
	private PrazoBean prazoRecorrivel;
	private PrazoBean prazoIrrecorrivel;
	private MedidaSegurancaEnum medidaSeguranca;
	private List<IncidenciaPenalBean> incidenciasMedida;
	private String resumoSentenca;

	public Date getDataSentenca() {
		return dataSentenca;
	}

	public void setDataSentenca(Date dataSentenca) {
		this.dataSentenca = dataSentenca;
	}

	public Date getDataTransitoJulgado() {
		return dataTransitoJulgado;
	}

	public void setDataTransitoJulgado(Date dataTransitoJulgado) {
		this.dataTransitoJulgado = dataTransitoJulgado;
	}

	public Date getDatatransitoAcusacao() {
		return datatransitoAcusacao;
	}

	public void setDatatransitoAcusacao(Date datatransitoAcusacao) {
		this.datatransitoAcusacao = datatransitoAcusacao;
	}

	public PrazoBean getPrazoRecorrivel() {
		return prazoRecorrivel;
	}

	public void setPrazoRecorrivel(PrazoBean prazoRecorrivel) {
		this.prazoRecorrivel = prazoRecorrivel;
	}

	public PrazoBean getPrazoIrrecorrivel() {
		return prazoIrrecorrivel;
	}

	public void setPrazoIrrecorrivel(PrazoBean prazoIrrecorrivel) {
		this.prazoIrrecorrivel = prazoIrrecorrivel;
	}

	public MedidaSegurancaEnum getMedidaSeguranca() {
		return medidaSeguranca;
	}

	public void setMedidaSeguranca(MedidaSegurancaEnum medidaSeguranca) {
		this.medidaSeguranca = medidaSeguranca;
	}

	public List<IncidenciaPenalBean> getIncidenciasMedida() {
		return incidenciasMedida;
	}

	public void setIncidenciasMedida(List<IncidenciaPenalBean> incidenciasMedida) {
		this.incidenciasMedida = incidenciasMedida;
	}

	public String getResumoSentenca() {
		return resumoSentenca;
	}

	public void setResumoSentenca(String resumoSentenca) {
		this.resumoSentenca = resumoSentenca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataSentenca == null) ? 0 : dataSentenca.hashCode());
		result = prime * result + ((dataTransitoJulgado == null) ? 0 : dataTransitoJulgado.hashCode());
		result = prime * result + ((datatransitoAcusacao == null) ? 0 : datatransitoAcusacao.hashCode());
		result = prime * result + ((incidenciasMedida == null) ? 0 : incidenciasMedida.hashCode());
		result = prime * result + ((medidaSeguranca == null) ? 0 : medidaSeguranca.hashCode());
		result = prime * result + ((prazoIrrecorrivel == null) ? 0 : prazoIrrecorrivel.hashCode());
		result = prime * result + ((prazoRecorrivel == null) ? 0 : prazoRecorrivel.hashCode());
		result = prime * result + ((resumoSentenca == null) ? 0 : resumoSentenca.hashCode());
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
		SentencaAbsolutoriaImpropriaBean other = (SentencaAbsolutoriaImpropriaBean) obj;
		if (dataSentenca == null) {
			if (other.dataSentenca != null)
				return false;
		} else if (!dataSentenca.equals(other.dataSentenca))
			return false;
		if (dataTransitoJulgado == null) {
			if (other.dataTransitoJulgado != null)
				return false;
		} else if (!dataTransitoJulgado.equals(other.dataTransitoJulgado))
			return false;
		if (datatransitoAcusacao == null) {
			if (other.datatransitoAcusacao != null)
				return false;
		} else if (!datatransitoAcusacao.equals(other.datatransitoAcusacao))
			return false;
		if (incidenciasMedida == null) {
			if (other.incidenciasMedida != null)
				return false;
		} else if (!incidenciasMedida.equals(other.incidenciasMedida))
			return false;
		if (medidaSeguranca != other.medidaSeguranca)
			return false;
		if (prazoIrrecorrivel == null) {
			if (other.prazoIrrecorrivel != null)
				return false;
		} else if (!prazoIrrecorrivel.equals(other.prazoIrrecorrivel))
			return false;
		if (prazoRecorrivel == null) {
			if (other.prazoRecorrivel != null)
				return false;
		} else if (!prazoRecorrivel.equals(other.prazoRecorrivel))
			return false;
		if (resumoSentenca == null) {
			if (other.resumoSentenca != null)
				return false;
		} else if (!resumoSentenca.equals(other.resumoSentenca))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();

		retorno.append(super.obterTextoFinal());
		
		if (dataSentenca != null) {
			retorno.append("Data do sentença: " + DateUtil.dateToString(dataSentenca) + "; ");
		}
		if (dataTransitoJulgado != null) {
			retorno.append("Data do trânsito em julgado: " + DateUtil.dateToString(dataTransitoJulgado) + "; ");
		}
		if (datatransitoAcusacao != null) {
			retorno.append("Data do trânsito da acusação: " + DateUtil.dateToString(datatransitoAcusacao) + "; ");
		}
		if (prazoRecorrivel != null && !prazoRecorrivel.toString().isEmpty()) {
			retorno.append("Prazo recorrível: " + prazoRecorrivel.toString() + "; ");
		}
		if (prazoIrrecorrivel != null && !prazoIrrecorrivel.toString().isEmpty()) {
			retorno.append("Prazo irrecorrível: " + prazoIrrecorrivel.toString() + "; ");
		}
		if (medidaSeguranca != null) {
			retorno.append("Medida de segurança: " + medidaSeguranca.getLabel() + "; ");
		}
		if (incidenciasMedida != null && !incidenciasMedida.isEmpty()) {
			retorno.append("Incidências da medida: " + getTipificacoesFormatadas(incidenciasMedida));
		}
		if (resumoSentenca != null && !resumoSentenca.isEmpty()) {
			retorno.append("Resumo da sentença: " + resumoSentenca + "; ");
		}
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		
		obterCamposVisualizacaoEspecificos(camposVisualizacao);
		
		return camposVisualizacao;
	}

	protected void obterCamposVisualizacaoEspecificos(Map<String, String> camposVisualizacao) {
		if (dataSentenca != null) {
			camposVisualizacao.put("Data do sentença", DateUtil.dateToString(dataSentenca));
		}
		if (dataTransitoJulgado != null) {
			camposVisualizacao.put("Data do trânsito em julgado", DateUtil.dateToString(dataTransitoJulgado));
		}
		if (datatransitoAcusacao != null) {
			camposVisualizacao.put("Data do trânsito da acusação", DateUtil.dateToString(datatransitoAcusacao));
		}
		if (prazoRecorrivel != null && !prazoRecorrivel.toString().isEmpty()) {
			camposVisualizacao.put("Prazo recorrível", prazoRecorrivel.toString());
		}
		if (prazoIrrecorrivel != null && !prazoIrrecorrivel.toString().isEmpty()) {
			camposVisualizacao.put("Prazo irrecorrível", prazoIrrecorrivel.toString());
		}
		if (medidaSeguranca != null) {
			camposVisualizacao.put("Medida de segurança", medidaSeguranca.getLabel());
		}
		if (incidenciasMedida != null && !incidenciasMedida.isEmpty()) {
			camposVisualizacao.put("Incidências da medida", getTipificacoesFormatadas(incidenciasMedida));
		}
		if (resumoSentenca != null && !resumoSentenca.isEmpty()) {
			camposVisualizacao.put("Resumo da sentença", resumoSentenca);
		}
	}

	public Date obterDataVisualizacao() {
		return getDataSentenca();
	}

}
