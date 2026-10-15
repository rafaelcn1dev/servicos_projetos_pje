package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.model.entities.MedidaSocioeducativa;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class SentencaInfracionalBean extends EventoCriminalBean {
	
	private static final long serialVersionUID = 1L;

	private Date dtSentenca;
	private Date dtTransitoAcusacao;
	private Date dtTransitoDefesa;
	private Date dtCiencia;
	private Date dtCienciaPessoal;
	private MedidaSocioeducativa medidaSocioeducativa;

	public SentencaInfracionalBean() {
		super();
	}

	public SentencaInfracionalBean(String id, Date dtSentenca, MedidaSocioeducativa medidaSocioeducativa) {
		super(id);
		this.dtSentenca = dtSentenca;
		this.medidaSocioeducativa = medidaSocioeducativa;
	}
	
	public Date getDtSentenca() {
		return dtSentenca;
	}
	public void setDtSentenca(Date dtSentenca) {
		this.dtSentenca = dtSentenca;
	}
	public Date getDtTransitoAcusacao() {
		return dtTransitoAcusacao;
	}
	public void setDtTransitoAcusacao(Date dtTransitoAcusacao) {
		this.dtTransitoAcusacao = dtTransitoAcusacao;
	}
	public Date getDtTransitoDefesa() {
		return dtTransitoDefesa;
	}
	public void setDtTransitoDefesa(Date dtTransitoDefesa) {
		this.dtTransitoDefesa = dtTransitoDefesa;
	}
	public Date getDtCiencia() {
		return dtCiencia;
	}
	public void setDtCiencia(Date dtCiencia) {
		this.dtCiencia = dtCiencia;
	}
	public Date getDtCienciaPessoal() {
		return dtCienciaPessoal;
	}
	public void setDtCienciaPessoal(Date dtCienciaPessoal) {
		this.dtCienciaPessoal = dtCienciaPessoal;
	}
	public MedidaSocioeducativa getMedidaSocioeducativa() {
		return medidaSocioeducativa;
	}
	public void setMedidaSocioeducativa(MedidaSocioeducativa medidaSocioeducativa) {
		this.medidaSocioeducativa = medidaSocioeducativa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dtCiencia == null) ? 0 : dtCiencia.hashCode());
		result = prime * result + ((dtCienciaPessoal == null) ? 0 : dtCienciaPessoal.hashCode());
		result = prime * result + ((dtSentenca == null) ? 0 : dtSentenca.hashCode());
		result = prime * result + ((dtTransitoAcusacao == null) ? 0 : dtTransitoAcusacao.hashCode());
		result = prime * result + ((dtTransitoDefesa == null) ? 0 : dtTransitoDefesa.hashCode());
		result = prime * result + ((medidaSocioeducativa == null) ? 0 : medidaSocioeducativa.hashCode());
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
		SentencaInfracionalBean other = (SentencaInfracionalBean) obj;
		if (dtCiencia == null) {
			if (other.dtCiencia != null)
				return false;
		} else if (!dtCiencia.equals(other.dtCiencia))
			return false;
		if (dtCienciaPessoal == null) {
			if (other.dtCienciaPessoal != null)
				return false;
		} else if (!dtCienciaPessoal.equals(other.dtCienciaPessoal))
			return false;
		if (dtSentenca == null) {
			if (other.dtSentenca != null)
				return false;
		} else if (!dtSentenca.equals(other.dtSentenca))
			return false;
		if (dtTransitoAcusacao == null) {
			if (other.dtTransitoAcusacao != null)
				return false;
		} else if (!dtTransitoAcusacao.equals(other.dtTransitoAcusacao))
			return false;
		if (dtTransitoDefesa == null) {
			if (other.dtTransitoDefesa != null)
				return false;
		} else if (!dtTransitoDefesa.equals(other.dtTransitoDefesa))
			return false;
		if (medidaSocioeducativa == null) {
			if (other.medidaSocioeducativa != null)
				return false;
		} else if (!medidaSocioeducativa.equals(other.medidaSocioeducativa))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (dtSentenca != null) {
			retorno.append("Data da sentença: " + DateUtil.dateToString(dtSentenca) + "; ");
		}
		if (medidaSocioeducativa != null) {
			retorno.append("Medida socioeducativa: " + medidaSocioeducativa.getDsMedidaSocioeducativa() + "; ");
		}
		if (dtTransitoAcusacao != null) {
			retorno.append("Data trânsito acusação: " + DateUtil.dateToString(dtTransitoAcusacao) + "; ");
		}
		if (dtTransitoDefesa != null) {
			retorno.append("Data trânsito defesa: " + DateUtil.dateToString(dtTransitoDefesa) + "; ");
		}
		if (dtCiencia != null) {
			retorno.append("Data ciência: " + DateUtil.dateToString(dtCiencia) + "; ");
		}
		if (dtCienciaPessoal != null) {
			retorno.append("Data ciência pessoal: " + DateUtil.dateToString(dtCienciaPessoal) + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dtSentenca != null) {
			camposVisualizacao.put("Data da sentença", DateUtil.dateToString(dtSentenca));
		}
		if (medidaSocioeducativa != null) {
			camposVisualizacao.put("Medida socioeducativa", medidaSocioeducativa.getDsMedidaSocioeducativa());
		}
		if (dtTransitoAcusacao != null) {
			camposVisualizacao.put("Data trânsito acusação", DateUtil.dateToString(dtTransitoAcusacao));
		}
		if (dtTransitoDefesa != null) {
			camposVisualizacao.put("Data trânsito defesa", DateUtil.dateToString(dtTransitoDefesa));
		}
		if (dtCiencia != null) {
			camposVisualizacao.put("Data ciência", DateUtil.dateToString(dtCiencia));
		}
		if (dtCienciaPessoal != null) {
			camposVisualizacao.put("Data ciência pessoal", DateUtil.dateToString(dtCienciaPessoal));
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDtSentenca();
	}

}
