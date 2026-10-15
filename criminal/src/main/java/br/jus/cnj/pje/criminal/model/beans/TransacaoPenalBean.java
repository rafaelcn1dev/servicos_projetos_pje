package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class TransacaoPenalBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataInicioTransacao;
	private Date dataFimTransacao;
	private String conteudoDecisao;
		
	public Date getDataInicioTransacao() {
		return dataInicioTransacao;
	}

	public void setDataInicioTransacao(Date dataInicioTransacao) {
		this.dataInicioTransacao = dataInicioTransacao;
	}

	public Date getDataFimTransacao() {
		return dataFimTransacao;
	}

	public void setDataFimTransacao(Date dataFimTransacao) {
		this.dataFimTransacao = dataFimTransacao;
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
		
		if (dataInicioTransacao != null) {
			retorno.append("Data do início da transação: " + DateUtil.dateToString(dataInicioTransacao) + "; ");
		}
		
		if (dataFimTransacao != null) {
			retorno.append("Data de fim da transação: " + DateUtil.dateToString(dataFimTransacao) + "; ");
		}		
		
		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			retorno.append("Conteúdo da decisão: " + conteudoDecisao + "; ");
		}
		
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = new LinkedHashMap<>();
		
		camposVisualizacao.put("Evento criminal", tipoInformacaoCriminal.getDescricao());
		
		if (data != null) {
			camposVisualizacao.put("Data do registro", DateUtil.dateToString(data));
		}
		
		if (tipificacoes != null && !tipificacoes.isEmpty()) {
			camposVisualizacao.put("Tipificações", getTipificacoesFormatadas());	
		}
		
		this.obterCamposVisualizacaoEspecificos(camposVisualizacao);
		
		if (observacao != null && !observacao.isEmpty()) {
			camposVisualizacao.put("Observação", observacao);	
		}
		
		return camposVisualizacao;
	}

	private void obterCamposVisualizacaoEspecificos(Map<String, String> camposVisualizacao) {
		if (dataInicioTransacao != null) {
			camposVisualizacao.put("Data do início da transação", DateUtil.dateToString(dataInicioTransacao));
		}

		if (dataFimTransacao != null) {
			camposVisualizacao.put("Data de fim da transação", DateUtil.dateToString(dataFimTransacao));
		}

		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			camposVisualizacao.put("Conteúdo da decisão", conteudoDecisao);
		}
		
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataInicioTransacao == null) ? 0 : dataInicioTransacao.hashCode());
		result = prime * result + ((dataFimTransacao == null) ? 0 : dataFimTransacao.hashCode());
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
		TransacaoPenalBean other = (TransacaoPenalBean) obj;
		if (conteudoDecisao == null) {
			if (other.conteudoDecisao != null)
				return false;
		} else if (!conteudoDecisao.equals(other.conteudoDecisao))
			return false;
		if (dataFimTransacao == null) {
			if (other.dataFimTransacao != null)
				return false;
		} else if (!dataFimTransacao.equals(other.dataFimTransacao))
			return false;
		if (dataInicioTransacao == null) {
			if (other.dataInicioTransacao != null)
				return false;
		} else if (!dataInicioTransacao.equals(other.dataInicioTransacao))
			return false;
		return true;
	}

	public Date obterDataVisualizacao() {
		return getDataInicioTransacao();
	}
}
