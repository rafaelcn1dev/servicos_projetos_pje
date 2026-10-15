package br.jus.cnj.pje.criminal.model.beans;

import java.util.Map;

public class DecisaoBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;
	
	private Boolean consultarCertidao;

	public Boolean getConsultarCertidao() {
		return consultarCertidao;
	}
	public void setConsultarCertidao(Boolean consultarCertidao) {
		this.consultarCertidao = consultarCertidao;
	}
	
	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		retorno.append(super.obterTextoFinal());
		retorno.append("Consultar certidão de passagem: " + (Boolean.TRUE.equals(consultarCertidao) ? "Sim" : "Não") + "; ");
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		camposVisualizacao.put("Consultar certidão de passagem", (Boolean.TRUE.equals(consultarCertidao) ? "Sim" : "Não"));
		return camposVisualizacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((consultarCertidao == null) ? 0 : consultarCertidao.hashCode());
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
		DecisaoBean other = (DecisaoBean) obj;
		if (consultarCertidao == null) {
			if (other.consultarCertidao != null)
				return false;
		} else if (!consultarCertidao.equals(other.consultarCertidao))
			return false;
		return true;
	}
	
}
