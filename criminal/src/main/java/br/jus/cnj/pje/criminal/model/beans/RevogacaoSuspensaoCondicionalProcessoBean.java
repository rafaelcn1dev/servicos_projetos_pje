package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class RevogacaoSuspensaoCondicionalProcessoBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataRevogacao;
	private String conteudoDecisao;

	public Date getDataRevogacao() {
		return dataRevogacao;
	}

	public void setDataRevogacao(Date dataRevogacao) {
		this.dataRevogacao = dataRevogacao;
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
		result = prime * result + ((dataRevogacao == null) ? 0 : dataRevogacao.hashCode());
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
		RevogacaoSuspensaoCondicionalProcessoBean other = (RevogacaoSuspensaoCondicionalProcessoBean) obj;
		if (conteudoDecisao == null) {
			if (other.conteudoDecisao != null)
				return false;
		} else if (!conteudoDecisao.equals(other.conteudoDecisao))
			return false;
		if (dataRevogacao == null) {
			if (other.dataRevogacao != null)
				return false;
		} else if (!dataRevogacao.equals(other.dataRevogacao))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		retorno.append(super.obterTextoFinal());
		if (dataRevogacao != null) {
			retorno.append("Data da revogação: " + DateUtil.dateToString(dataRevogacao) + "; ");
		}
		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			retorno.append("Conteúdo da decisão: " + conteudoDecisao + "; ");
		}
		return retorno.toString();
	}

	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dataRevogacao != null) {
			camposVisualizacao.put("Data da revogação", DateUtil.dateToString(dataRevogacao));
		}
		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			camposVisualizacao.put("Conteúdo da decisão", conteudoDecisao);
		}
		return camposVisualizacao;
	}

	public Date obterDataVisualizacao() {
		return getDataRevogacao();
	}

}
