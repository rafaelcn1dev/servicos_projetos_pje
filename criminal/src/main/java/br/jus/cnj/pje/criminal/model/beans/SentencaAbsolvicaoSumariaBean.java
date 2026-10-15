package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class SentencaAbsolvicaoSumariaBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataSentencaAbsolvicaoSumaria;
	private Date dataTransitoJulgado;
	private String conteudoDecisao;

	public Date getDataSentencaAbsolvicaoSumaria() {
		return dataSentencaAbsolvicaoSumaria;
	}

	public void setDataSetencaAbsolvicaoSumaria(Date dataSetencaAbsolvicaoSumaria) {
		this.dataSentencaAbsolvicaoSumaria = dataSetencaAbsolvicaoSumaria;
	}
	
	public Date getDataTransitoJulgado() {
		return dataTransitoJulgado;
	}

	public void setDataTransitoJulgado(Date dataTransitoJulgado) {
		this.dataTransitoJulgado = dataTransitoJulgado;
	}
	
	public String getConteudoDecisao() {
		return conteudoDecisao;
	}

	public void setConteudoDecisao(String conteudoDecisao) {
		this.conteudoDecisao = conteudoDecisao;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		retorno.append(super.obterTextoFinal());
		
		if (dataSentencaAbsolvicaoSumaria != null) {
			retorno.append("Data da sentença da absolvição sumária: " + DateUtil.dateToString(dataSentencaAbsolvicaoSumaria) + "; ");
		}
		
		if (dataTransitoJulgado != null) {
			retorno.append("Data do trânsito julgado: " + DateUtil.dateToString(dataTransitoJulgado) + "; ");
		}		
		
		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			retorno.append("Conteúdo da decisão: " + conteudoDecisao + "; ");
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
		if (dataSentencaAbsolvicaoSumaria != null) {
			camposVisualizacao.put("Data da sentença absolvição sumária", DateUtil.dateToString(dataSentencaAbsolvicaoSumaria));
		}

		if (dataTransitoJulgado != null) {
			camposVisualizacao.put("Data do trânsito julgado", DateUtil.dateToString(dataTransitoJulgado));
		}

		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			camposVisualizacao.put("Conteúdo da decisão", conteudoDecisao);
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataSentencaAbsolvicaoSumaria == null) ? 0 : dataSentencaAbsolvicaoSumaria.hashCode());
		result = prime * result + ((dataTransitoJulgado == null) ? 0 : dataTransitoJulgado.hashCode());
		result = prime * result + ((conteudoDecisao == null) ? 0 : conteudoDecisao.hashCode());
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
		SentencaAbsolvicaoSumariaBean other = (SentencaAbsolvicaoSumariaBean) obj;
		if (conteudoDecisao == null) {
			if (other.conteudoDecisao != null)
				return false;
		} else if (!conteudoDecisao.equals(other.conteudoDecisao))
			return false;
		if (dataSentencaAbsolvicaoSumaria == null) {
			if (other.dataSentencaAbsolvicaoSumaria != null)
				return false;
		} else if (!dataSentencaAbsolvicaoSumaria.equals(other.dataSentencaAbsolvicaoSumaria))
			return false;
		if (dataTransitoJulgado == null) {
			if (other.dataTransitoJulgado != null)
				return false;
		} else if (!dataTransitoJulgado.equals(other.dataTransitoJulgado))
			return false;
		return true;
	}

	public Date obterDataVisualizacao() {
		return getDataSentencaAbsolvicaoSumaria();
	}
}
