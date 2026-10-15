package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.model.entities.MotivoExtincaoMedidaSocioeducativa;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class SentencaExtincaoMedidaSocioeducativaBean extends EventoCriminalBean {
	
	private static final long serialVersionUID = 1L;

	private Date dtExtincao;
	private MotivoExtincaoMedidaSocioeducativa motivoExtincaoMedidaSocioeducativa;

	public SentencaExtincaoMedidaSocioeducativaBean() {
		super();
	}

	public SentencaExtincaoMedidaSocioeducativaBean(String id, Date dtExtincao, MotivoExtincaoMedidaSocioeducativa motivoExtincaoMedidaSocioeducativa) {
		super(id);
		this.dtExtincao = dtExtincao;
		this.motivoExtincaoMedidaSocioeducativa = motivoExtincaoMedidaSocioeducativa;
	}
	
	public Date getDtExtincao() {
		return dtExtincao;
	}

	public void setDtExtincao(Date dataExtincao) {
		this.dtExtincao = dataExtincao;
	}

	public MotivoExtincaoMedidaSocioeducativa getMotivoExtincaoMedidaSocioeducativa() {
		return motivoExtincaoMedidaSocioeducativa;
	}

	public void setMotivoExtincaoMedidaSocioeducativa(MotivoExtincaoMedidaSocioeducativa motivoExtincaoMedidaSocioeducativa) {
		this.motivoExtincaoMedidaSocioeducativa = motivoExtincaoMedidaSocioeducativa;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dtExtincao == null) ? 0 : dtExtincao.hashCode());
		result = prime * result + ((motivoExtincaoMedidaSocioeducativa == null) ? 0 : motivoExtincaoMedidaSocioeducativa.hashCode());
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
		SentencaExtincaoMedidaSocioeducativaBean other = (SentencaExtincaoMedidaSocioeducativaBean) obj;
		if (dtExtincao == null) {
			if (other.dtExtincao != null)
				return false;
		} else if (!dtExtincao.equals(other.dtExtincao))
			return false;
		if (motivoExtincaoMedidaSocioeducativa == null) {
			if (other.motivoExtincaoMedidaSocioeducativa != null)
				return false;
		} else if (!motivoExtincaoMedidaSocioeducativa.equals(other.motivoExtincaoMedidaSocioeducativa))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (dtExtincao != null) {
			retorno.append("Data da extinção: " + DateUtil.dateToString(dtExtincao) + "; ");
		}
		if (motivoExtincaoMedidaSocioeducativa != null) {
			retorno.append("Motivo da extinção da medida socioeducativa: " + motivoExtincaoMedidaSocioeducativa.getDsMotivoExtincao() + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dtExtincao != null) {
			camposVisualizacao.put("Data da extinção", DateUtil.dateToString(dtExtincao));
		}
		if (motivoExtincaoMedidaSocioeducativa != null) {
			camposVisualizacao.put("Motivo da extinção da medida socioeducativa", motivoExtincaoMedidaSocioeducativa.getDsMotivoExtincao());
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDtExtincao();
	}

}
