package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.model.entities.UnidadePrisional;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class FugaBean extends EventoCriminalBean {
	
	private static final long serialVersionUID = 1L;

	private String dsFuga;
	private Date dtFuga;
	private UnidadePrisional unidadePrisional;
	
	public FugaBean() {
		super();
	}

	public FugaBean(String id, String dsFuga, Date dtFuga, UnidadePrisional unidadePrisional) {
		super(id);
		this.dsFuga = dsFuga;
		this.dtFuga = dtFuga;
		this.unidadePrisional = unidadePrisional;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getDsFuga() {
		return dsFuga;
	}

	public void setDsFuga(String dsFuga) {
		this.dsFuga = dsFuga;
	}

	public Date getDtFuga() {
		return dtFuga;
	}

	public void setDtFuga(Date dtFuga) {
		this.dtFuga = dtFuga;
	}

	public UnidadePrisional getUnidadePrisional() {
		return unidadePrisional;
	}

	public void setUnidadePrisional(UnidadePrisional unidadePrisional) {
		this.unidadePrisional = unidadePrisional;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dsFuga == null) ? 0 : dsFuga.hashCode());
		result = prime * result + ((dtFuga == null) ? 0 : dtFuga.hashCode());
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
		FugaBean other = (FugaBean) obj;
		if (dsFuga == null) {
			if (other.dsFuga != null)
				return false;
		} else if (!dsFuga.equals(other.dsFuga))
			return false;
		if (dtFuga == null) {
			if (other.dtFuga != null)
				return false;
		} else if (!dtFuga.equals(other.dtFuga))
			return false;
		if (unidadePrisional == null) {
			if (other.unidadePrisional != null)
				return false;
		} else if (!unidadePrisional.equals(other.unidadePrisional))
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
		if (dtFuga != null) {
			retorno.append("Data: " + DateUtil.dateToString(dtFuga) + "; ");
		}
		if (dsFuga != null && !dsFuga.isEmpty()) {
			retorno.append("Descrição: " + dsFuga + "; ");
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
		if (dtFuga != null) {
			camposVisualizacao.put("Data", DateUtil.dateToString(dtFuga));
		}
		if (dsFuga != null && !dsFuga.isEmpty()) {
			camposVisualizacao.put("Descrição", dsFuga);
		}
		if (unidadePrisional != null) {
			camposVisualizacao.put("Unidade prisional", unidadePrisional.getDsUnidadePrisional());
		}
		return camposVisualizacao;
	}
	
	@Override
	public Date obterDataVisualizacao() {
		return getDtFuga();
	}
	
}
