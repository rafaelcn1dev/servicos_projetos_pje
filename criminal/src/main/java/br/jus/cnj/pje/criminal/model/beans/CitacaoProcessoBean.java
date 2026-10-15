package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class CitacaoProcessoBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataCitacaoIntimicaoReu;
	private Date dataCitacaoEdital;

	public Date getDataCitacaoIntimicaoReu() {
		return dataCitacaoIntimicaoReu;
	}

	public void setDataCitacaoIntimicaoReu(Date dataCitacaoIntimicaoReu) {
		this.dataCitacaoIntimicaoReu = dataCitacaoIntimicaoReu;
	}

	public Date getDataCitacaoEdital() {
		return dataCitacaoEdital;
	}

	public void setDataCitacaoEdital(Date dataCitacaoEdital) {
		this.dataCitacaoEdital = dataCitacaoEdital;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataCitacaoEdital == null) ? 0 : dataCitacaoEdital.hashCode());
		result = prime * result + ((dataCitacaoIntimicaoReu == null) ? 0 : dataCitacaoIntimicaoReu.hashCode());
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
		CitacaoProcessoBean other = (CitacaoProcessoBean) obj;
		if (dataCitacaoEdital == null) {
			if (other.dataCitacaoEdital != null)
				return false;
		} else if (!dataCitacaoEdital.equals(other.dataCitacaoEdital))
			return false;
		if (dataCitacaoIntimicaoReu == null) {
			if (other.dataCitacaoIntimicaoReu != null)
				return false;
		} else if (!dataCitacaoIntimicaoReu.equals(other.dataCitacaoIntimicaoReu))
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
		if (dataCitacaoIntimicaoReu != null) {
			retorno.append("Data intimação do réu: " + DateUtil.dateToString(dataCitacaoIntimicaoReu) + "; ");
		}
		if (dataCitacaoEdital != null) {
			retorno.append("Data da citação por edital: " + DateUtil.dateToString(dataCitacaoEdital) + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dataCitacaoIntimicaoReu != null) {
			camposVisualizacao.put("Data intimação do réu", DateUtil.dateToString(dataCitacaoIntimicaoReu));
		}
		if (dataCitacaoEdital != null) {
			camposVisualizacao.put("Data da citação por edital", DateUtil.dateToString(dataCitacaoEdital));
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDataCitacaoIntimicaoReu();
	}

}