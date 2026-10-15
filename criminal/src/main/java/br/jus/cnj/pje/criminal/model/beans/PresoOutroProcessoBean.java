package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

public class PresoOutroProcessoBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private UnidadePrisionalBean unidadePrisional;
	private String numeroProcesso;

	public UnidadePrisionalBean getUnidadePrisional() {
		return unidadePrisional;
	}

	public void setUnidadePrisional(UnidadePrisionalBean unidadePrisional) {
		this.unidadePrisional = unidadePrisional;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numeroProcesso == null) ? 0 : numeroProcesso.hashCode());
		result = prime * result + ((unidadePrisional == null) ? 0 : unidadePrisional.hashCode());
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
		PresoOutroProcessoBean other = (PresoOutroProcessoBean) obj;
		if (numeroProcesso == null) {
			if (other.numeroProcesso != null)
				return false;
		} else if (!numeroProcesso.equals(other.numeroProcesso))
			return false;
		if (unidadePrisional == null) {
			if (other.unidadePrisional != null)
				return false;
		} else if (!unidadePrisional.equals(other.unidadePrisional))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		retorno.append(super.obterTextoFinal());
		if (unidadePrisional != null) {
			retorno.append("Unidade prisional: " + unidadePrisional.getDsUnidadePrisional() + "; ");
		}
		if (numeroProcesso != null && !numeroProcesso.isEmpty()) {
			retorno.append("Número do processo: " + numeroProcesso + "; ");
		}
		return retorno.toString();
	}

	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (unidadePrisional != null) {
			camposVisualizacao.put("Unidade prisional", unidadePrisional.getDsUnidadePrisional());
		}
		if (numeroProcesso != null && !numeroProcesso.isEmpty()) {
			camposVisualizacao.put("Número do processo", numeroProcesso);
		}
		return camposVisualizacao;
	}

	@Override
	public Date obterDataVisualizacao() {
		return getDataCadastro();
	}

}
