package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.util.DateUtil;

public class DesclassificacaoProcessoBean extends EventoCriminalBean {
	private static final long serialVersionUID = 1L;
	
	private Date dataDesclassificacao;

	public Date getDataDesclassificacao() {
		return dataDesclassificacao;
	}

	public void setDataDesclassificacao(Date dataDesclassificacao) {
		this.dataDesclassificacao = dataDesclassificacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataDesclassificacao == null) ? 0 : dataDesclassificacao.hashCode());
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
		DesclassificacaoProcessoBean other = (DesclassificacaoProcessoBean) obj;
		if (dataDesclassificacao == null) {
			if (other.dataDesclassificacao != null)
				return false;
		} else if (!dataDesclassificacao.equals(other.dataDesclassificacao))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return obterTextoFinal();
	}
	
	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (dataDesclassificacao != null) {
			retorno.append("Data da Desclassificação: " + DateUtil.dateToString(dataDesclassificacao) + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dataDesclassificacao != null) {
			camposVisualizacao.put("Data da Desclassificação", DateUtil.dateToString(dataDesclassificacao));
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDataDesclassificacao();
	}
}
