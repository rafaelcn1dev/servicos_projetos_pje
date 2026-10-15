package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class SentencaPronunciaProcessoBean extends EventoCriminalBean {
	private static final long serialVersionUID = 1L;

	private Date dataPronuncia;
	private Date dataConfirmacao;
	private Date dataTransitoAcusacao;
	private Date dataTransitoDefinitivo;
	
	public Date getDataPronuncia() {
		return dataPronuncia;
	}

	public void setDataPronuncia(Date dataPronuncia) {
		this.dataPronuncia = dataPronuncia;
	}

	public Date getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(Date dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataConfirmacao == null) ? 0 : dataConfirmacao.hashCode());
		result = prime * result + ((dataPronuncia == null) ? 0 : dataPronuncia.hashCode());
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
		SentencaPronunciaProcessoBean other = (SentencaPronunciaProcessoBean) obj;
		if (dataConfirmacao == null) {
			if (other.dataConfirmacao != null)
				return false;
		} else if (!dataConfirmacao.equals(other.dataConfirmacao))
			return false;
		if (dataPronuncia == null) {
			if (other.dataPronuncia != null)
				return false;
		} else if (!dataPronuncia.equals(other.dataPronuncia))
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
	public String toString() {
		return obterTextoFinal();
	}
	
	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (dataConfirmacao != null) {
			retorno.append("Data de confirmação: " + DateUtil.dateToString(dataConfirmacao) + "; ");
		}
		if (dataPronuncia != null) {
			retorno.append("Data da pronúncia: " + DateUtil.dateToString(dataPronuncia) + "; ");
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
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dataConfirmacao != null) {
			camposVisualizacao.put("Data de confirmação", DateUtil.dateToString(dataConfirmacao));
		}
		if (dataPronuncia != null) {
			camposVisualizacao.put("Data da pronúncia", DateUtil.dateToString(dataPronuncia));
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
		return getDataConfirmacao();
	}

}