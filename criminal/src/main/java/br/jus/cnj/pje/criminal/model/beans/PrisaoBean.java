package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.model.entities.MotivoPrisao;
import br.jus.cnj.pje.criminal.model.entities.TipoPrisao;
import br.jus.cnj.pje.criminal.model.entities.UnidadePrisional;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class PrisaoBean extends EventoCriminalBean{
	private static final long serialVersionUID = 1L;

	private TipoPrisao tipoPrisao;
	private MotivoPrisao motivoPrisao;
	private Date dtPrisao;
	private Integer prazoDias;
	private Integer prazoMeses;
	private Integer prazoAnos;
	private UnidadePrisional unidadePrisional;

	public PrisaoBean() {
		super();
	}
	
	public PrisaoBean(String id, TipoPrisao tipoPrisao, MotivoPrisao motivoPrisao, Date dtPrisao,
			Integer prazoDias, Integer prazoMeses, Integer prazoAnos, UnidadePrisional unidadePrisional) {
		super(id);
		this.tipoPrisao = tipoPrisao;
		this.motivoPrisao = motivoPrisao;
		this.dtPrisao = dtPrisao;
		this.prazoDias = prazoDias;
		this.prazoMeses = prazoMeses;
		this.prazoAnos = prazoAnos;
		this.unidadePrisional = unidadePrisional;
		
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public TipoPrisao getTipoPrisao() {
		return tipoPrisao;
	}

	public void setTipoPrisao(TipoPrisao tipoPrisao) {
		this.tipoPrisao = tipoPrisao;
	}

	public MotivoPrisao getMotivoPrisao() {
		return motivoPrisao;
	}

	public void setMotivoPrisao(MotivoPrisao motivoPrisao) {
		this.motivoPrisao = motivoPrisao;
	}

	public Date getDtPrisao() {
		return dtPrisao;
	}

	public void setDtPrisao(Date dtPrisao) {
		this.dtPrisao = dtPrisao;
	}

	public UnidadePrisional getUnidadePrisional() {
		return unidadePrisional;
	}

	public void setUnidadePrisional(UnidadePrisional unidadePrisional) {
		this.unidadePrisional = unidadePrisional;
	}

	public Integer getPrazoDias() {
		return prazoDias;
	}

	public void setPrazoDias(Integer prazoDias) {
		this.prazoDias = prazoDias;
	}

	public Integer getPrazoMeses() {
		return prazoMeses;
	}

	public void setPrazoMeses(Integer prazoMeses) {
		this.prazoMeses = prazoMeses;
	}

	public Integer getPrazoAnos() {
		return prazoAnos;
	}

	public void setPrazoAnos(Integer prazoAnos) {
		this.prazoAnos = prazoAnos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dtPrisao == null) ? 0 : dtPrisao.hashCode());
		result = prime * result + ((motivoPrisao == null) ? 0 : motivoPrisao.hashCode());
		result = prime * result + ((prazoAnos == null) ? 0 : prazoAnos.hashCode());
		result = prime * result + ((prazoDias == null) ? 0 : prazoDias.hashCode());
		result = prime * result + ((prazoMeses == null) ? 0 : prazoMeses.hashCode());
		result = prime * result + ((tipoPrisao == null) ? 0 : tipoPrisao.hashCode());
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
		PrisaoBean other = (PrisaoBean) obj;
		if (dtPrisao == null) {
			if (other.dtPrisao != null)
				return false;
		} else if (!dtPrisao.equals(other.dtPrisao))
			return false;
		if (motivoPrisao == null) {
			if (other.motivoPrisao != null)
				return false;
		} else if (!motivoPrisao.equals(other.motivoPrisao))
			return false;
		if (prazoAnos == null) {
			if (other.prazoAnos != null)
				return false;
		} else if (!prazoAnos.equals(other.prazoAnos))
			return false;
		if (prazoDias == null) {
			if (other.prazoDias != null)
				return false;
		} else if (!prazoDias.equals(other.prazoDias))
			return false;
		if (prazoMeses == null) {
			if (other.prazoMeses != null)
				return false;
		} else if (!prazoMeses.equals(other.prazoMeses))
			return false;
		if (tipoPrisao == null) {
			if (other.tipoPrisao != null)
				return false;
		} else if (!tipoPrisao.equals(other.tipoPrisao))
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
		if (dtPrisao != null) {
			retorno.append("Data: " + DateUtil.dateToString(dtPrisao) + "; ");
		}
		if (tipoPrisao != null) {
			retorno.append("Tipo: " + tipoPrisao.getTipoPrisao() + "; ");
		}
		if (motivoPrisao != null) {
			retorno.append("Motivo: " + motivoPrisao.getMotivoPrisao() + "; ");
		}
		if (unidadePrisional != null) {
			retorno.append("Unidade prisional: " + unidadePrisional.getDsUnidadePrisional() + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (dtPrisao != null) {
			camposVisualizacao.put("Data", DateUtil.dateToString(dtPrisao));
		}
		if (prazoAnos != null || prazoMeses != null || prazoDias != null) {
			String prazo = "";
			if (prazoAnos != null) {
				if (prazoAnos == 1) {
					prazo += (prazoAnos + " ano ");	
				} else {
					prazo += (prazoAnos + " anos ");
				}
			}
			if (prazoMeses != null) {
				if (prazoMeses == 1) {
					prazo += (prazoMeses + " mês ");	
				} else {
					prazo += (prazoMeses + " meses ");
				}
			}
			if (prazoDias != null) {
				if (prazoDias == 1) {
					prazo += (prazoDias + " dia ");	
				} else {
					prazo += (prazoDias + " dias ");
				}
			}
			camposVisualizacao.put("Prazo", prazo);
		}
		if (tipoPrisao != null) {
			camposVisualizacao.put("Tipo", tipoPrisao.getTipoPrisao());
		}
		if (motivoPrisao != null) {
			camposVisualizacao.put("Motivo", motivoPrisao.getMotivoPrisao());
		}
		if (unidadePrisional != null) {
			camposVisualizacao.put("Unidade prisional", unidadePrisional.getDsUnidadePrisional());
		}
		return camposVisualizacao;
	}
	
	@Override
	public String toString() {
		return obterTextoFinal();
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDtPrisao();
	}
	
}
