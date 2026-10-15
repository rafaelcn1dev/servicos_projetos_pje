package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class NulidadeProcessoBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataNulidadeProcesso;
	private String conteudoDecisao;
	private Date dataTransitoJulgado;

	public Date getDataNulidadeProcesso() {
		return dataNulidadeProcesso;
	}

	public void setDataNulidadeProcesso(Date dataNulidadeProcesso) {
		this.dataNulidadeProcesso = dataNulidadeProcesso;
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
		
		if (dataNulidadeProcesso != null) {
			retorno.append("Data da nulidade do processo: " + DateUtil.dateToString(dataNulidadeProcesso) + "; ");
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
		if (dataNulidadeProcesso != null) {
			camposVisualizacao.put("Data da homologação", DateUtil.dateToString(dataNulidadeProcesso));
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
		result = prime * result + ((dataNulidadeProcesso == null) ? 0 : dataNulidadeProcesso.hashCode());
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
		NulidadeProcessoBean other = (NulidadeProcessoBean) obj;
		if (conteudoDecisao == null) {
			if (other.conteudoDecisao != null)
				return false;
		} else if (!conteudoDecisao.equals(other.conteudoDecisao))
			return false;
		if (dataNulidadeProcesso == null) {
			if (other.dataNulidadeProcesso != null)
				return false;
		} else if (!dataNulidadeProcesso.equals(other.dataNulidadeProcesso))
			return false;
		if (dataTransitoJulgado == null) {
			if (other.dataTransitoJulgado != null)
				return false;
		} else if (!dataTransitoJulgado.equals(other.dataTransitoJulgado))
			return false;
		return true;
	}

	public Date obterDataVisualizacao() {
		return getDataNulidadeProcesso();
	}

}
