package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.model.entities.MedidaSocioeducativa;
import br.jus.cnj.pje.criminal.model.entities.TipoRemissao;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class RemissaoBean extends EventoCriminalBean {
	
	private static final long serialVersionUID = 1L;

	private Date dtDecisao;
	private Date dtTransitoAcusacao;
	private Date dtTransitoDefesa;
	private TipoRemissao tipoRemissao;
	private Date dtCiencia;
	private Date dtCienciaPessoal;
	private Date dtInicio;
	private Date dtFim;
	private Integer prazo;
	private MedidaSocioeducativa medidaSocioeducativa;
	
	public RemissaoBean() {
		super();
	}

	public RemissaoBean(String id, Date dtDecisao, Date dtTransitoAcusacao, Date dtTransitoDefesa,
			TipoRemissao tipoRemissao, Date dtCiencia, Date dtCienciaPessoal, Date dtInicio, Date dtFim, Integer prazo,
			MedidaSocioeducativa medidaSocioeducativa) {
		super(id);
		this.dtDecisao = dtDecisao;
		this.dtTransitoAcusacao = dtTransitoAcusacao;
		this.dtTransitoDefesa = dtTransitoDefesa;
		this.tipoRemissao = tipoRemissao;
		this.dtCiencia = dtCiencia;
		this.dtCienciaPessoal = dtCienciaPessoal;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.prazo = prazo;
		this.medidaSocioeducativa = medidaSocioeducativa;
	}

	public Date getDtDecisao() {
		return dtDecisao;
	}

	public void setDtDecisao(Date dtDecisao) {
		this.dtDecisao = dtDecisao;
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

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}
	
	public TipoRemissao getTipoRemissao() {
		return tipoRemissao;
	}

	public void setTipoRemissao(TipoRemissao tipoRemissao) {
		this.tipoRemissao = tipoRemissao;
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
		result = prime * result + ((dtDecisao == null) ? 0 : dtDecisao.hashCode());
		result = prime * result + ((dtFim == null) ? 0 : dtFim.hashCode());
		result = prime * result + ((dtInicio == null) ? 0 : dtInicio.hashCode());
		result = prime * result + ((dtTransitoAcusacao == null) ? 0 : dtTransitoAcusacao.hashCode());
		result = prime * result + ((dtTransitoDefesa == null) ? 0 : dtTransitoDefesa.hashCode());
		result = prime * result + ((medidaSocioeducativa == null) ? 0 : medidaSocioeducativa.hashCode());
		result = prime * result + ((prazo == null) ? 0 : prazo.hashCode());
		result = prime * result + ((tipoRemissao == null) ? 0 : tipoRemissao.hashCode());
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
		RemissaoBean other = (RemissaoBean) obj;
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
		if (dtDecisao == null) {
			if (other.dtDecisao != null)
				return false;
		} else if (!dtDecisao.equals(other.dtDecisao))
			return false;
		if (dtFim == null) {
			if (other.dtFim != null)
				return false;
		} else if (!dtFim.equals(other.dtFim))
			return false;
		if (dtInicio == null) {
			if (other.dtInicio != null)
				return false;
		} else if (!dtInicio.equals(other.dtInicio))
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
		if (prazo == null) {
			if (other.prazo != null)
				return false;
		} else if (!prazo.equals(other.prazo))
			return false;
		if (tipoRemissao == null) {
			if (other.tipoRemissao != null)
				return false;
		} else if (!tipoRemissao.equals(other.tipoRemissao))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (dtTransitoAcusacao != null) {
			retorno.append("Data trânsito acusação: " + DateUtil.dateToString(dtTransitoAcusacao) + "; ");
		}
		if (dtTransitoDefesa != null) {
			retorno.append("Data trânsito defesa: " + DateUtil.dateToString(dtTransitoDefesa) + "; ");
		}
		if (tipoRemissao != null) {
			retorno.append("Forma de Remissão: " + tipoRemissao.getDsTipoRemissao() + "; ");
		}
		if (dtCiencia != null) {
			retorno.append("Data ciência: " + DateUtil.dateToString(dtCiencia) + "; ");
		}
		if (dtCienciaPessoal != null) {
			retorno.append("Data ciência pessoal: " + DateUtil.dateToString(dtCienciaPessoal) + "; ");
		}
		if (dtInicio != null) {
			retorno.append("Data de início: " + DateUtil.dateToString(dtInicio) + "; ");
		}
		if (dtFim != null) {
			retorno.append("Data de fim: " + DateUtil.dateToString(dtFim) + "; ");
		}
		if (medidaSocioeducativa != null) {
			retorno.append("Aplicação da Medida socioeducativa em meio aberto: " + medidaSocioeducativa.getDsMedidaSocioeducativa() + "; ");
		}
		if (prazo != null) {
			retorno.append("Prazo (dias): " + prazo.toString() + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dtTransitoAcusacao != null) {
			camposVisualizacao.put("Data trânsito acusação", DateUtil.dateToString(dtTransitoAcusacao));
		}
		if (dtTransitoDefesa != null) {
			camposVisualizacao.put("Data trânsito defesa", DateUtil.dateToString(dtTransitoDefesa));
		}
		if (tipoRemissao != null) {
			camposVisualizacao.put("Forma de Remissão", tipoRemissao.getDsTipoRemissao());
		}
		if (dtCiencia != null) {
			camposVisualizacao.put("Data ciência", DateUtil.dateToString(dtCiencia));
		}
		if (dtCienciaPessoal != null) {
			camposVisualizacao.put("Data ciência pessoal", DateUtil.dateToString(dtCienciaPessoal));
		}
		if (dtInicio != null) {
			camposVisualizacao.put("Data de início", DateUtil.dateToString(dtInicio));
		}
		if (dtFim != null) {
			camposVisualizacao.put("Data de fim", DateUtil.dateToString(dtFim));
		}
		if (medidaSocioeducativa != null) {
			camposVisualizacao.put("Aplicação da Medida socioeducativa em meio aberto", medidaSocioeducativa.getDsMedidaSocioeducativa());
		}
		if (prazo != null) {
			camposVisualizacao.put("Prazo (dias)", prazo.toString());
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDtDecisao();
	}
	
}
