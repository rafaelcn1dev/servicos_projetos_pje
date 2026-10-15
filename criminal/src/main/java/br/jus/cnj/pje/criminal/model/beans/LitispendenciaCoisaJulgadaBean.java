package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class LitispendenciaCoisaJulgadaBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataLitispendencia;
	private Date dataTransitoJulgado;
	private String conteudoDecisao;

	public Date getDataLitispendencia() {
		return dataLitispendencia;
	}

	public void setDataLitispendencia(Date dataLitispendencia) {
		this.dataLitispendencia = dataLitispendencia;
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((conteudoDecisao == null) ? 0 : conteudoDecisao.hashCode());
		result = prime * result + ((dataLitispendencia == null) ? 0 : dataLitispendencia.hashCode());
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
		LitispendenciaCoisaJulgadaBean other = (LitispendenciaCoisaJulgadaBean) obj;
		if (conteudoDecisao == null) {
			if (other.conteudoDecisao != null)
				return false;
		} else if (!conteudoDecisao.equals(other.conteudoDecisao))
			return false;
		if (dataLitispendencia == null) {
			if (other.dataLitispendencia != null)
				return false;
		} else if (!dataLitispendencia.equals(other.dataLitispendencia))
			return false;
		if (dataTransitoJulgado == null) {
			if (other.dataTransitoJulgado != null)
				return false;
		} else if (!dataTransitoJulgado.equals(other.dataTransitoJulgado))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		retorno.append(super.obterTextoFinal());
		if (dataLitispendencia != null) {
			retorno.append("Data da litispendência: " + DateUtil.dateToString(dataLitispendencia) + "; ");
		}
		if (dataTransitoJulgado != null) {
			retorno.append("Data do trânsito em julgado: " + DateUtil.dateToString(dataTransitoJulgado) + "; ");
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
		if (dataLitispendencia != null) {
			camposVisualizacao.put("Data da litispendência", DateUtil.dateToString(dataLitispendencia));
		}
		if (dataTransitoJulgado != null) {
			camposVisualizacao.put("Data do trânsito em julgado", DateUtil.dateToString(dataTransitoJulgado));
		}
		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			camposVisualizacao.put("Conteúdo da decisão", conteudoDecisao);
		}
	}

	public Date obterDataVisualizacao() {
		return getDataLitispendencia();
	}

}
