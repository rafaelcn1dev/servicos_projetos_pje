package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class DeclinioCompetenciaBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataDeclinio;
	private String conteudoDecisao;

	public Date getDataDeclinio() {
		return dataDeclinio;
	}

	public void setDataDeclinio(Date dataDeclinio) {
		this.dataDeclinio = dataDeclinio;
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
		result = prime * result + ((dataDeclinio == null) ? 0 : dataDeclinio.hashCode());
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
		DeclinioCompetenciaBean other = (DeclinioCompetenciaBean) obj;
		if (conteudoDecisao == null) {
			if (other.conteudoDecisao != null)
				return false;
		} else if (!conteudoDecisao.equals(other.conteudoDecisao))
			return false;
		if (dataDeclinio == null) {
			if (other.dataDeclinio != null)
				return false;
		} else if (!dataDeclinio.equals(other.dataDeclinio))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		retorno.append(super.obterTextoFinal());
		if (dataDeclinio != null) {
			retorno.append("Data do declínio: " + DateUtil.dateToString(dataDeclinio) + "; ");
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
		if (dataDeclinio != null) {
			camposVisualizacao.put("Data do declínio", DateUtil.dateToString(dataDeclinio));
		}
		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			camposVisualizacao.put("Conteúdo da decisão", conteudoDecisao);
		}
	}

	public Date obterDataVisualizacao() {
		return getDataDeclinio();
	}

}
