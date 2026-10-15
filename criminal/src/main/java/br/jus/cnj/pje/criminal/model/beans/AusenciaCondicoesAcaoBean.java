package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class AusenciaCondicoesAcaoBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataAusenciaCondicoesAcao;
	private String conteudoDecisao;
	private Date dataTransitoJulgado;

	public Date getDataAusenciaCondicoesAcao() {
		return dataAusenciaCondicoesAcao;
	}

	public void setDataAusenciaCondicoesAcao(Date dataAusenciaCondicoesAcao) {
		this.dataAusenciaCondicoesAcao = dataAusenciaCondicoesAcao;
	}

	public String getConteudoDecisao() {
		return conteudoDecisao;
	}

	public void setConteudoDecisao(String conteudoDecisao) {
		this.conteudoDecisao = conteudoDecisao;
	}

	public Date getDataTransitoJulgado() {
		return dataTransitoJulgado;
	}

	public void setDataTransitoJulgado(Date dataTransitoJulgado) {
		this.dataTransitoJulgado = dataTransitoJulgado;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		retorno.append(super.obterTextoFinal());
		
		if (dataAusenciaCondicoesAcao != null) {
			retorno.append("Data da ausẽncia de condições da ação: " + DateUtil.dateToString(dataAusenciaCondicoesAcao) + "; ");
		}
		
		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			retorno.append("Conteúdo da decisão: " + conteudoDecisao + "; ");
		}
		
		if (dataTransitoJulgado != null) {
			retorno.append("Data do trânsito julgado: " + DateUtil.dateToString(dataTransitoJulgado) + "; ");
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
		if (dataAusenciaCondicoesAcao != null) {
			camposVisualizacao.put("Data da ausẽncia de condições da ação", DateUtil.dateToString(dataAusenciaCondicoesAcao));
		}
		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			camposVisualizacao.put("Conteúdo da decisão", conteudoDecisao);
		}
		if (dataTransitoJulgado != null) {
			camposVisualizacao.put("Data do trânsito julgado", DateUtil.dateToString(dataTransitoJulgado));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((conteudoDecisao == null) ? 0 : conteudoDecisao.hashCode());
		result = prime * result + ((dataAusenciaCondicoesAcao == null) ? 0 : dataAusenciaCondicoesAcao.hashCode());
		result = prime * result + ((dataTransitoJulgado == null) ? 0 : dataTransitoJulgado.hashCode());
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
		AusenciaCondicoesAcaoBean other = (AusenciaCondicoesAcaoBean) obj;
		if (conteudoDecisao == null) {
			if (other.conteudoDecisao != null)
				return false;
		} else if (!conteudoDecisao.equals(other.conteudoDecisao))
			return false;
		if (dataAusenciaCondicoesAcao == null) {
			if (other.dataAusenciaCondicoesAcao != null)
				return false;
		} else if (!dataAusenciaCondicoesAcao.equals(other.dataAusenciaCondicoesAcao))
			return false;
		if (dataTransitoJulgado == null) {
			if (other.dataTransitoJulgado != null)
				return false;
		} else if (!dataTransitoJulgado.equals(other.dataTransitoJulgado))
			return false;
		return true;
	}

	public Date obterDataVisualizacao() {
		return getDataAusenciaCondicoesAcao();
	}

}
