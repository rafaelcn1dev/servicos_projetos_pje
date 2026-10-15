package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class SentencaAbsolutoriaBean extends EventoCriminalBean {
	private static final long serialVersionUID = 1L;

	private Date dataSentenca;
	private Date dataTransitoAcusacao;
	private Date dataTransitoDefinitivo;

	public SentencaAbsolutoriaBean() {
		super();
	}

	public SentencaAbsolutoriaBean(String id, Date dataSentenca, Date dataTransitoAcusacao, Date dataTransitoDefinitivo) {
		super(id);
		this.dataSentenca = dataSentenca;
		this.dataTransitoAcusacao = dataTransitoAcusacao;
		this.dataTransitoDefinitivo = dataTransitoDefinitivo;
	}
	
	public Date getDataTransitoAcusacao() {
		return dataTransitoAcusacao;
	}

	public void setDataTransitoAcusacao(Date dataTransitoAcusacao) {
		this.dataTransitoAcusacao = dataTransitoAcusacao;
	}

	public Date getDataTransitoDefinitivo() {
		return dataTransitoDefinitivo;
	}

	public void setDataTransitoDefinitivo(Date dataTransitoDefinitivo) {
		this.dataTransitoDefinitivo = dataTransitoDefinitivo;
	}

	public Date getDataSentenca() {
		return dataSentenca;
	}

	public void setDataSentenca(Date dataSentenca) {
		this.dataSentenca = dataSentenca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataSentenca == null) ? 0 : dataSentenca.hashCode());
		result = prime * result + ((dataTransitoAcusacao == null) ? 0 : dataTransitoAcusacao.hashCode());
		result = prime * result + ((dataTransitoDefinitivo == null) ? 0 : dataTransitoDefinitivo.hashCode());
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
		SentencaAbsolutoriaBean other = (SentencaAbsolutoriaBean) obj;
		if (dataSentenca == null) {
			if (other.dataSentenca != null)
				return false;
		} else if (!dataSentenca.equals(other.dataSentenca))
			return false;
		if (dataTransitoAcusacao == null) {
			if (other.dataTransitoAcusacao != null)
				return false;
		} else if (!dataTransitoAcusacao.equals(other.dataTransitoAcusacao))
			return false;
		if (dataTransitoDefinitivo == null) {
			if (other.dataTransitoDefinitivo != null)
				return false;
		} else if (!dataTransitoDefinitivo.equals(other.dataTransitoDefinitivo))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (dataSentenca != null) {
			retorno.append("Data da sentença: " + DateUtil.dateToString(dataSentenca) + "; ");
		}
		if (dataTransitoAcusacao != null) {
			retorno.append("Data do trânsito da acusação: " + DateUtil.dateToString(dataTransitoAcusacao) + "; ");
		}
		if (dataTransitoDefinitivo != null) {
			retorno.append("Data do trânsito em definitivo: " + DateUtil.dateToString(dataTransitoDefinitivo) + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public String toString() {
		return obterTextoFinal();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dataSentenca != null) {
			camposVisualizacao.put("Data da sentença", DateUtil.dateToString(dataSentenca));
		}
		if (dataTransitoAcusacao != null) {
			camposVisualizacao.put("Data do trânsito da acusação", DateUtil.dateToString(dataTransitoAcusacao));
		}
		if (dataTransitoDefinitivo != null) {
			camposVisualizacao.put("Data do trânsito em definitivo", DateUtil.dateToString(dataTransitoDefinitivo));
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDataSentenca();
	}

}
