package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.model.entities.MotivoSoltura;
import br.jus.cnj.pje.criminal.model.entities.UnidadePrisional;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class SolturaBean extends EventoCriminalBean{
	
	private static final long serialVersionUID = 1L;

	private Date dtSoltura;
	private MotivoSoltura motivo;
	private UnidadePrisional unidadePrisional;

	public SolturaBean() {
		super();
	}

	public SolturaBean(String id, Date dtSoltura, MotivoSoltura motivo, UnidadePrisional unidadePrisional) {
		super(id);
		this.dtSoltura = dtSoltura;
		this.motivo = motivo;
		this.unidadePrisional = unidadePrisional;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public Date getDtSoltura() {
		return dtSoltura;
	}

	public void setDtSoltura(Date dtSoltura) {
		this.dtSoltura = dtSoltura;
	}

	public MotivoSoltura getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoSoltura motivo) {
		this.motivo = motivo;
	}

	public UnidadePrisional getUnidadePrisional() {
		return unidadePrisional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dtSoltura == null) ? 0 : dtSoltura.hashCode());
		result = prime * result + ((motivo == null) ? 0 : motivo.hashCode());
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
		SolturaBean other = (SolturaBean) obj;
		if (dtSoltura == null) {
			if (other.dtSoltura != null)
				return false;
		} else if (!dtSoltura.equals(other.dtSoltura))
			return false;
		if (motivo == null) {
			if (other.motivo != null)
				return false;
		} else if (!motivo.equals(other.motivo))
			return false;
		if (unidadePrisional == null) {
			if (other.unidadePrisional != null)
				return false;
		} else if (!unidadePrisional.equals(other.unidadePrisional))
			return false;
		return true;
	}

	public void setUnidadePrisional(UnidadePrisional unidadePrisional) {
		this.unidadePrisional = unidadePrisional;
	}
	
	@Override
	public String toString() {
		return obterTextoFinal();
	}
	
	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (dtSoltura != null) {
			retorno.append("Data: " + DateUtil.dateToString(dtSoltura) + "; ");
		}
		if (motivo != null) {
			retorno.append("Motivo: " + motivo.getMotivoSoltura() + "; ");
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
		if (dtSoltura != null) {
			camposVisualizacao.put("Data", DateUtil.dateToString(dtSoltura));
		}
		if (motivo != null) {
			camposVisualizacao.put("Motivo", motivo.getMotivoSoltura());
		}
		if (unidadePrisional != null) {
			camposVisualizacao.put("Unidade prisional", unidadePrisional.getDsUnidadePrisional());
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDtSoltura();
	}
	
}
