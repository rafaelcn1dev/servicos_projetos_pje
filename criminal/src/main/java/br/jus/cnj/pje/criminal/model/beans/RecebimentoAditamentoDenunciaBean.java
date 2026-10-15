package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class RecebimentoAditamentoDenunciaBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataRecebimentoAditamentoDenuncia;
	private EventoCriminalBean aditamentoDenuncia;
	
	public Date getDataRecebimentoAditamentoDenuncia() {
		return dataRecebimentoAditamentoDenuncia;
	}

	public void setDataRecebimentoAditamentoDenuncia(Date dataRecebimentoAditamentoDenuncia) {
		this.dataRecebimentoAditamentoDenuncia = dataRecebimentoAditamentoDenuncia;
	}

	public EventoCriminalBean getAditamentoDenuncia() {
		return aditamentoDenuncia;
	}

	public void setAditamentoDenuncia(EventoCriminalBean aditamentoDenuncia) {
		this.aditamentoDenuncia = aditamentoDenuncia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aditamentoDenuncia == null) ? 0 : aditamentoDenuncia.hashCode());
		result = prime * result
				+ ((dataRecebimentoAditamentoDenuncia == null) ? 0 : dataRecebimentoAditamentoDenuncia.hashCode());
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
		RecebimentoAditamentoDenunciaBean other = (RecebimentoAditamentoDenunciaBean) obj;
		if (aditamentoDenuncia == null) {
			if (other.aditamentoDenuncia != null)
				return false;
		} else if (!aditamentoDenuncia.equals(other.aditamentoDenuncia))
			return false;
		if (dataRecebimentoAditamentoDenuncia == null) {
			if (other.dataRecebimentoAditamentoDenuncia != null)
				return false;
		} else if (!dataRecebimentoAditamentoDenuncia.equals(other.dataRecebimentoAditamentoDenuncia))
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
		if (dataRecebimentoAditamentoDenuncia != null) {
			retorno.append("Data: " + DateUtil.dateToString(dataRecebimentoAditamentoDenuncia) + "; ");
		}
		if (aditamentoDenuncia != null) {
			retorno.append("Aditamento: " + aditamentoDenuncia.toString());
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dataRecebimentoAditamentoDenuncia != null) {
			camposVisualizacao.put("Data", DateUtil.dateToString(dataRecebimentoAditamentoDenuncia));
		}
		if (aditamentoDenuncia != null) {
			camposVisualizacao.put("Aditamento", aditamentoDenuncia.toString());
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDataRecebimentoAditamentoDenuncia();
	}

}