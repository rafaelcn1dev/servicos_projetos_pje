package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class ArquivamentoIncidenciaBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private Date dataArquivamento;
	private List<IncidenciaPenalBean> incidenciasArquivadas;
	private String conteudoDecisao;

	public Date getDataArquivamento() {
		return dataArquivamento;
	}

	public void setDataArquivamento(Date dataArquivamento) {
		this.dataArquivamento = dataArquivamento;
	}

	public List<IncidenciaPenalBean> getIncidenciasArquivadas() {
		return incidenciasArquivadas;
	}

	public void setIncidenciasArquivadas(List<IncidenciaPenalBean> incidenciasArquivadas) {
		this.incidenciasArquivadas = incidenciasArquivadas;
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
		result = prime * result + ((dataArquivamento == null) ? 0 : dataArquivamento.hashCode());
		result = prime * result + ((incidenciasArquivadas == null) ? 0 : incidenciasArquivadas.hashCode());
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
		ArquivamentoIncidenciaBean other = (ArquivamentoIncidenciaBean) obj;
		if (conteudoDecisao == null) {
			if (other.conteudoDecisao != null)
				return false;
		} else if (!conteudoDecisao.equals(other.conteudoDecisao))
			return false;
		if (dataArquivamento == null) {
			if (other.dataArquivamento != null)
				return false;
		} else if (!dataArquivamento.equals(other.dataArquivamento))
			return false;
		if (incidenciasArquivadas == null) {
			if (other.incidenciasArquivadas != null)
				return false;
		} else if (!incidenciasArquivadas.equals(other.incidenciasArquivadas))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		retorno.append(super.obterTextoFinal());
		if (dataArquivamento != null) {
			retorno.append("Data do arquivamento: " + DateUtil.dateToString(dataArquivamento) + "; ");
		}
		if (incidenciasArquivadas != null && !incidenciasArquivadas.isEmpty()) {
			retorno.append("Incidências arquivadas: " + getTipificacoesFormatadas(incidenciasArquivadas));
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
		if (dataArquivamento != null) {
			camposVisualizacao.put("Data do arquivamento", DateUtil.dateToString(dataArquivamento));
		}
		if (incidenciasArquivadas != null && !incidenciasArquivadas.isEmpty()) {
			camposVisualizacao.put("Incidências arquivadas", getTipificacoesFormatadas(incidenciasArquivadas));
		}
		if (conteudoDecisao != null && !conteudoDecisao.isEmpty()) {
			camposVisualizacao.put("Conteúdo da decisão", conteudoDecisao);
		}
	}

	public Date obterDataVisualizacao() {
		return getDataArquivamento();
	}

}
