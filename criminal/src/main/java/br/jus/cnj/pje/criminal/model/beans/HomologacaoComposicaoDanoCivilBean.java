package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class HomologacaoComposicaoDanoCivilBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataHomologacao;
	private String conteudoDecisao;
	private Date dataTransitoJulgado;

	public Date getDataHomologacao() {
		return dataHomologacao;
	}

	public void setDataHomologacao(Date dataHomologacao) {
		this.dataHomologacao = dataHomologacao;
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
		
		if (dataHomologacao != null) {
			retorno.append("Data da Homologação: " + DateUtil.dateToString(dataHomologacao) + "; ");
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
		if (dataHomologacao != null) {
			camposVisualizacao.put("Data da homologação", DateUtil.dateToString(dataHomologacao));
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
		result = prime * result + ((dataHomologacao == null) ? 0 : dataHomologacao.hashCode());
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
		HomologacaoComposicaoDanoCivilBean other = (HomologacaoComposicaoDanoCivilBean) obj;
		if (conteudoDecisao == null) {
			if (other.conteudoDecisao != null)
				return false;
		} else if (!conteudoDecisao.equals(other.conteudoDecisao))
			return false;
		if (dataHomologacao == null) {
			if (other.dataHomologacao != null)
				return false;
		} else if (!dataHomologacao.equals(other.dataHomologacao))
			return false;
		if (dataTransitoJulgado == null) {
			if (other.dataTransitoJulgado != null)
				return false;
		} else if (!dataTransitoJulgado.equals(other.dataTransitoJulgado))
			return false;
		return true;
	}

	public Date obterDataVisualizacao() {
		return getDataHomologacao();
	}

}
