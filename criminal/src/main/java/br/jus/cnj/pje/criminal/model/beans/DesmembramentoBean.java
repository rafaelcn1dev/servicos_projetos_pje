package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class DesmembramentoBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataDesmembramento;
	private String conteudoDecisao;
		
	public Date getDataDesmembramento() {
		return dataDesmembramento;
	}

	public void setDataDesmembramento(Date dataDesmembramento) {
		this.dataDesmembramento = dataDesmembramento;
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
		
		if (dataDesmembramento != null) {
			retorno.append("Data do desmembramento: " + DateUtil.dateToString(dataDesmembramento) + "; ");
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
		if (dataDesmembramento != null) {
			camposVisualizacao.put("Data do desmembramento", DateUtil.dateToString(dataDesmembramento));
		}

		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			camposVisualizacao.put("Conteúdo da decisão", conteudoDecisao);
		}
		
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((conteudoDecisao == null) ? 0 : conteudoDecisao.hashCode());
		result = prime * result + ((dataDesmembramento == null) ? 0 : dataDesmembramento.hashCode());
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
		DesmembramentoBean other = (DesmembramentoBean) obj;
		if (conteudoDecisao == null) {
			if (other.conteudoDecisao != null)
				return false;
		} else if (!conteudoDecisao.equals(other.conteudoDecisao))
			return false;
		if (dataDesmembramento == null) {
			if (other.dataDesmembramento != null)
				return false;
		} else if (!dataDesmembramento.equals(other.dataDesmembramento))
			return false;
		return true;
	}

	public Date obterDataVisualizacao() {
		return getDataDesmembramento();
	}
}
