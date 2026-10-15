package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.model.entities.MotivoRetornoEvasao;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class EvasaoBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;
	
	private Date dtEvasao;
	private Date dtRetorno;
	private MotivoRetornoEvasao motivoRetornoEvasao;
	private Date dtMedidaSocioeducativa;
	
	public Date getDtEvasao() {
		return dtEvasao;
	}
	public void setDtEvasao(Date dtEvasao) {
		this.dtEvasao = dtEvasao;
	}
	public Date getDtRetorno() {
		return dtRetorno;
	}
	public void setDtRetorno(Date dtRetorno) {
		this.dtRetorno = dtRetorno;
	}
	public MotivoRetornoEvasao getMotivoRetornoEvasao() {
		return motivoRetornoEvasao;
	}
	public void setMotivoRetornoEvasao(MotivoRetornoEvasao motivoRetornoEvasao) {
		this.motivoRetornoEvasao = motivoRetornoEvasao;
	}
	public Date getDtMedidaSocioeducativa() {
		return dtMedidaSocioeducativa;
	}
	public void setDtMedidaSocioeducativa(Date dtMedidaSocioeducativa) {
		this.dtMedidaSocioeducativa = dtMedidaSocioeducativa;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDtEvasao();
	}
	
	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (dtEvasao != null) {
			retorno.append("Data de evasão: " + DateUtil.dateToString(dtEvasao) + "; ");
		}
		if (dtRetorno != null) {
			retorno.append("Data de retorno: " + DateUtil.dateToString(dtRetorno) + "; ");
		}
		if (motivoRetornoEvasao != null) {
			retorno.append("Motivo de retorno da evasão: " + motivoRetornoEvasao.getDsMotivoRetornoEvasao() + "; ");
		}
		if (dtMedidaSocioeducativa != null) {
			retorno.append("Data da medida socioeducativa: " + DateUtil.dateToString(dtMedidaSocioeducativa) + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dtEvasao != null) {
			camposVisualizacao.put("Data de evasão", DateUtil.dateToString(dtEvasao));
		}
		if (dtRetorno != null) {
			camposVisualizacao.put("Data de retorno", DateUtil.dateToString(dtRetorno));
		}
		if (motivoRetornoEvasao != null) {
			camposVisualizacao.put("Motivo de retorno da evasão", motivoRetornoEvasao.getDsMotivoRetornoEvasao());
		}
		if (dtMedidaSocioeducativa != null) {
			camposVisualizacao.put("Data da medida socioeducativa", DateUtil.dateToString(dtMedidaSocioeducativa));
		}
		return camposVisualizacao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dtEvasao == null) ? 0 : dtEvasao.hashCode());
		result = prime * result + ((dtMedidaSocioeducativa == null) ? 0 : dtMedidaSocioeducativa.hashCode());
		result = prime * result + ((dtRetorno == null) ? 0 : dtRetorno.hashCode());
		result = prime * result + ((motivoRetornoEvasao == null) ? 0 : motivoRetornoEvasao.hashCode());
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
		EvasaoBean other = (EvasaoBean) obj;
		if (dtEvasao == null) {
			if (other.dtEvasao != null)
				return false;
		} else if (!dtEvasao.equals(other.dtEvasao))
			return false;
		if (dtMedidaSocioeducativa == null) {
			if (other.dtMedidaSocioeducativa != null)
				return false;
		} else if (!dtMedidaSocioeducativa.equals(other.dtMedidaSocioeducativa))
			return false;
		if (dtRetorno == null) {
			if (other.dtRetorno != null)
				return false;
		} else if (!dtRetorno.equals(other.dtRetorno))
			return false;
		if (motivoRetornoEvasao == null) {
			if (other.motivoRetornoEvasao != null)
				return false;
		} else if (!motivoRetornoEvasao.equals(other.motivoRetornoEvasao))
			return false;
		return true;
	}
	
}
