package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.enums.RegimePenaEnum;
import br.jus.cnj.pje.criminal.enums.TipoPenaExecucaoEnum;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class SentencaCondenatoriaProcessoBean extends EventoCriminalBean {
	private static final long serialVersionUID = 1L;

	private Date dataSentenca;
	private Date dataTransitoJulgado;
	private Date dataTransitoAcusacao;
	private Integer recorrivelPrazoDias;
	private Integer recorrivelPrazoMeses;
	private Integer recorrivelPrazoAnos;
	private Integer irrecorrivelPrazoDias;
	private Integer irrecorrivelPrazoMeses;
	private Integer irrecorrivelPrazoAnos;
	private RegimePenaEnum regime;
	private Date dataInicio;
	private Integer tipoPenaExecucao;
	private Double valorMulta;
	private Boolean recurso;
	private Boolean reincidente;
	private String juizProlator;
	private Integer diasMulta;
	private Boolean pagouMulta;
	private String nrProcessoExecucaoSEEU;

	public SentencaCondenatoriaProcessoBean() {
		super();
	}
	
	public SentencaCondenatoriaProcessoBean(String id, Date dataSentenca, Date dataTransitoJulgado,
			Date dataTransitoAcusacao, Integer recorrivelPrazoDias,
			Integer recorrivelPrazoMeses, Integer recorrivelPrazoAnos, Integer irrecorrivelPrazoDias,
			Integer irrecorrivelPrazoMeses, Integer irrecorrivelPrazoAnos, RegimePenaEnum regime) {
		super(id);
		this.dataSentenca = dataSentenca;
		this.dataTransitoJulgado = dataTransitoJulgado;
		this.dataTransitoAcusacao = dataTransitoAcusacao;
		this.recorrivelPrazoDias = recorrivelPrazoDias;
		this.recorrivelPrazoMeses = recorrivelPrazoMeses;
		this.recorrivelPrazoAnos = recorrivelPrazoAnos;
		this.irrecorrivelPrazoDias = irrecorrivelPrazoDias;
		this.irrecorrivelPrazoMeses = irrecorrivelPrazoMeses;
		this.irrecorrivelPrazoAnos = irrecorrivelPrazoAnos;
	}

	public String getNrProcessoExecucaoSEEU() {
		return nrProcessoExecucaoSEEU;
	}

	public void setNrProcessoExecucaoSEEU(String nrProcessoExecucaoSEEU) {
		this.nrProcessoExecucaoSEEU = nrProcessoExecucaoSEEU;
	}

	
	public Date getDataSentenca() {
		return dataSentenca;
	}

	public void setDataSentenca(Date dataSentenca) {
		this.dataSentenca = dataSentenca;
	}

	public Date getDataTransitoJulgado() {
		return dataTransitoJulgado;
	}

	public void setDataTransitoJulgado(Date dataTransitoJulgado) {
		this.dataTransitoJulgado = dataTransitoJulgado;
	}

	public Date getDataTransitoAcusacao() {
		return dataTransitoAcusacao;
	}

	public void setDataTransitoAcusacao(Date dataTransitoAcusacao) {
		this.dataTransitoAcusacao = dataTransitoAcusacao;
	}

	public Integer getRecorrivelPrazoDias() {
		return recorrivelPrazoDias;
	}

	public void setRecorrivelPrazoDias(Integer recorrivelPrazoDias) {
		this.recorrivelPrazoDias = recorrivelPrazoDias;
	}

	public Integer getRecorrivelPrazoMeses() {
		return recorrivelPrazoMeses;
	}

	public void setRecorrivelPrazoMeses(Integer recorrivelPrazoMeses) {
		this.recorrivelPrazoMeses = recorrivelPrazoMeses;
	}

	public Integer getRecorrivelPrazoAnos() {
		return recorrivelPrazoAnos;
	}

	public void setRecorrivelPrazoAnos(Integer recorrivelPrazoAnos) {
		this.recorrivelPrazoAnos = recorrivelPrazoAnos;
	}

	public Integer getIrrecorrivelPrazoDias() {
		return irrecorrivelPrazoDias;
	}

	public void setIrrecorrivelPrazoDias(Integer irrecorrivelPrazoDias) {
		this.irrecorrivelPrazoDias = irrecorrivelPrazoDias;
	}

	public Integer getIrrecorrivelPrazoMeses() {
		return irrecorrivelPrazoMeses;
	}

	public void setIrrecorrivelPrazoMeses(Integer irrecorrivelPrazoMeses) {
		this.irrecorrivelPrazoMeses = irrecorrivelPrazoMeses;
	}

	public Integer getIrrecorrivelPrazoAnos() {
		return irrecorrivelPrazoAnos;
	}

	public void setIrrecorrivelPrazoAnos(Integer irrecorrivelPrazoAnos) {
		this.irrecorrivelPrazoAnos = irrecorrivelPrazoAnos;
	}

	public RegimePenaEnum getRegime() {
		return regime;
	}

	public void setRegime(RegimePenaEnum regime) {
		this.regime = regime;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Double getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(Double valorMulta) {
		this.valorMulta = valorMulta;
	}

	public Boolean getRecurso() {
		return recurso;
	}

	public void setRecurso(Boolean recurso) {
		this.recurso = recurso;
	}

	public Boolean getReincidente() {
		return reincidente;
	}

	public void setReincidente(Boolean reincidente) {
		this.reincidente = reincidente;
	}

	public String getJuizProlator() {
		return juizProlator;
	}

	public void setJuizProlator(String juizProlator) {
		this.juizProlator = juizProlator;
	}

	public Integer getDiasMulta() {
		return diasMulta;
	}

	public void setDiasMulta(Integer diasMulta) {
		this.diasMulta = diasMulta;
	}

	public Boolean getPagouMulta() {
		return pagouMulta;
	}

	public void setPagouMulta(Boolean pagouMulta) {
		this.pagouMulta = pagouMulta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((dataSentenca == null) ? 0 : dataSentenca.hashCode());
		result = prime * result + ((dataTransitoAcusacao == null) ? 0 : dataTransitoAcusacao.hashCode());
		result = prime * result + ((dataTransitoJulgado == null) ? 0 : dataTransitoJulgado.hashCode());
		result = prime * result + ((diasMulta == null) ? 0 : diasMulta.hashCode());
		result = prime * result + ((irrecorrivelPrazoAnos == null) ? 0 : irrecorrivelPrazoAnos.hashCode());
		result = prime * result + ((irrecorrivelPrazoDias == null) ? 0 : irrecorrivelPrazoDias.hashCode());
		result = prime * result + ((irrecorrivelPrazoMeses == null) ? 0 : irrecorrivelPrazoMeses.hashCode());
		result = prime * result + ((juizProlator == null) ? 0 : juizProlator.hashCode());
		result = prime * result + ((pagouMulta == null) ? 0 : pagouMulta.hashCode());
		result = prime * result + ((recorrivelPrazoAnos == null) ? 0 : recorrivelPrazoAnos.hashCode());
		result = prime * result + ((recorrivelPrazoDias == null) ? 0 : recorrivelPrazoDias.hashCode());
		result = prime * result + ((recorrivelPrazoMeses == null) ? 0 : recorrivelPrazoMeses.hashCode());
		result = prime * result + ((recurso == null) ? 0 : recurso.hashCode());
		result = prime * result + ((regime == null) ? 0 : regime.hashCode());
		result = prime * result + ((reincidente == null) ? 0 : reincidente.hashCode());
		result = prime * result + ((getTipoPenaExecucao() == null) ? 0 : getTipoPenaExecucao().hashCode());
		result = prime * result + ((valorMulta == null) ? 0 : valorMulta.hashCode());
		result = prime * result + ((nrProcessoExecucaoSEEU == null) ? 0 : nrProcessoExecucaoSEEU.hashCode());
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
		SentencaCondenatoriaProcessoBean other = (SentencaCondenatoriaProcessoBean) obj;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (dataSentenca == null) {
			if (other.dataSentenca != null)
				return false;
		} else if (!dataSentenca.equals(other.dataSentenca))
			return false;
		if (dataTransitoAcusacao == null) {
			if (other.dataTransitoAcusacao != null)
				return false;
		} else if (!dataTransitoAcusacao.equals(other.dataTransitoAcusacao))
			return false;
		if (dataTransitoJulgado == null) {
			if (other.dataTransitoJulgado != null)
				return false;
		} else if (!dataTransitoJulgado.equals(other.dataTransitoJulgado))
			return false;
		if (diasMulta == null) {
			if (other.diasMulta != null)
				return false;
		} else if (!diasMulta.equals(other.diasMulta))
			return false;
		if (irrecorrivelPrazoAnos == null) {
			if (other.irrecorrivelPrazoAnos != null)
				return false;
		} else if (!irrecorrivelPrazoAnos.equals(other.irrecorrivelPrazoAnos))
			return false;
		if (irrecorrivelPrazoDias == null) {
			if (other.irrecorrivelPrazoDias != null)
				return false;
		} else if (!irrecorrivelPrazoDias.equals(other.irrecorrivelPrazoDias))
			return false;
		if (irrecorrivelPrazoMeses == null) {
			if (other.irrecorrivelPrazoMeses != null)
				return false;
		} else if (!irrecorrivelPrazoMeses.equals(other.irrecorrivelPrazoMeses))
			return false;
		if (juizProlator == null) {
			if (other.juizProlator != null)
				return false;
		} else if (!juizProlator.equals(other.juizProlator))
			return false;
		if (nrProcessoExecucaoSEEU == null) {
			if (other.nrProcessoExecucaoSEEU != null)
				return false;
		} else if (!nrProcessoExecucaoSEEU.equals(other.nrProcessoExecucaoSEEU))
			return false;
		if (pagouMulta == null) {
			if (other.pagouMulta != null)
				return false;
		} else if (!pagouMulta.equals(other.pagouMulta))
			return false;
		if (recorrivelPrazoAnos == null) {
			if (other.recorrivelPrazoAnos != null)
				return false;
		} else if (!recorrivelPrazoAnos.equals(other.recorrivelPrazoAnos))
			return false;
		if (recorrivelPrazoDias == null) {
			if (other.recorrivelPrazoDias != null)
				return false;
		} else if (!recorrivelPrazoDias.equals(other.recorrivelPrazoDias))
			return false;
		if (recorrivelPrazoMeses == null) {
			if (other.recorrivelPrazoMeses != null)
				return false;
		} else if (!recorrivelPrazoMeses.equals(other.recorrivelPrazoMeses))
			return false;
		if (recurso == null) {
			if (other.recurso != null)
				return false;
		} else if (!recurso.equals(other.recurso))
			return false;
		if (regime != other.regime)
			return false;
		if (reincidente == null) {
			if (other.reincidente != null)
				return false;
		} else if (!reincidente.equals(other.reincidente))
			return false;
		if (getTipoPenaExecucao() == null) {
			if (other.getTipoPenaExecucao() != null)
				return false;
		} else if (!getTipoPenaExecucao().equals(other.getTipoPenaExecucao()))
			return false;
		if (valorMulta == null) {
			if (other.valorMulta != null)
				return false;
		} else if (!valorMulta.equals(other.valorMulta))
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
		if (dataSentenca != null) {
			retorno.append("Data da sentença:" + DateUtil.dateToString(dataSentenca) + "; ");
		}
		if (dataTransitoAcusacao != null) {
			retorno.append("Data do trânsito da acusação: " + DateUtil.dateToString(dataTransitoAcusacao) + "; ");
		}
		if (dataTransitoJulgado != null) {
			retorno.append("Data do trânsito em julgado: " + DateUtil.dateToString(dataTransitoJulgado) + "; ");
		}
		if (regime != null) {
			retorno.append("Regime: " + regime.getLabel() + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dataSentenca != null) {
			camposVisualizacao.put("Data da sentença", DateUtil.dateToString(dataSentenca));
		}
		if (dataTransitoAcusacao != null) {
			camposVisualizacao.put("Data do trânsito da acusação", DateUtil.dateToString(dataTransitoAcusacao));
		}
		if (dataTransitoJulgado != null) {
			camposVisualizacao.put("Data do trânsito em julgado", DateUtil.dateToString(dataTransitoJulgado));
		}
		if (regime != null) {
			camposVisualizacao.put("Regime", regime.getLabel());
		}
		if (recorrivelPrazoAnos != null || recorrivelPrazoMeses != null || recorrivelPrazoDias != null) {
			String prazoRecorrivel = "";
			if (recorrivelPrazoAnos != null) {
				if (recorrivelPrazoAnos == 1) {
					prazoRecorrivel += (recorrivelPrazoAnos + " ano ");	
				} else {
					prazoRecorrivel += (recorrivelPrazoAnos + " anos ");
				}
			}
			if (recorrivelPrazoMeses != null) {
				if (recorrivelPrazoMeses == 1) {
					prazoRecorrivel += (recorrivelPrazoMeses + " mês ");	
				} else {
					prazoRecorrivel += (recorrivelPrazoMeses + " meses ");
				}
			}
			if (recorrivelPrazoDias != null) {
				if (recorrivelPrazoDias == 1) {
					prazoRecorrivel += (recorrivelPrazoDias + " dia ");	
				} else {
					prazoRecorrivel += (recorrivelPrazoDias + " dias ");
				}
			}
			camposVisualizacao.put("Prazo recorrível", prazoRecorrivel);
		}
		if (irrecorrivelPrazoAnos != null || irrecorrivelPrazoMeses != null || irrecorrivelPrazoDias != null) {
			String prazoIrrecorivel = "";
			if (irrecorrivelPrazoAnos != null) {
				if (irrecorrivelPrazoAnos == 1) {
					prazoIrrecorivel += (irrecorrivelPrazoAnos + " ano ");	
				} else {
					prazoIrrecorivel += (irrecorrivelPrazoAnos + " anos ");
				}
			}
			if (irrecorrivelPrazoMeses != null) {
				if (irrecorrivelPrazoMeses == 1) {
					prazoIrrecorivel += (irrecorrivelPrazoMeses + " mês ");	
				} else {
					prazoIrrecorivel += (irrecorrivelPrazoMeses + " meses ");
				}
			}
			if (irrecorrivelPrazoDias != null) {
				if (irrecorrivelPrazoDias == 1) {
					prazoIrrecorivel += (irrecorrivelPrazoDias + " dia ");	
				} else {
					prazoIrrecorivel += (irrecorrivelPrazoDias + " dias ");
				}
			}
			camposVisualizacao.put("Prazo irrecorrível", prazoIrrecorivel);
		}
		if (resumo != null) {
			camposVisualizacao.put("Resumo da sentença", resumo);
		}
		if (nrProcessoExecucaoSEEU != null) { 
			camposVisualizacao.put("Nº processo execução SEEU", nrProcessoExecucaoSEEU); 
		}
		return camposVisualizacao;
	}

	public Integer getTipoPenaExecucao() {
		Integer valor = this.tipoPenaExecucao == null ? -1 : this.tipoPenaExecucao;
		
		TipoPenaExecucaoEnum codigoTipoPena = TipoPenaExecucaoEnum.getPorCod(valor.intValue());
		
		return codigoTipoPena.getCod();
	}

	public void setTipoPenaExecucao(Integer tipoPenaExecucao) {
		this.tipoPenaExecucao = tipoPenaExecucao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDataSentenca();
	}
	
}
