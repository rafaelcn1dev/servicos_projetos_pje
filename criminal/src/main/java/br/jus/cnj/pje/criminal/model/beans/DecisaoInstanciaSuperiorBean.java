package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;
import java.util.Map;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.jus.cnj.pje.criminal.enums.OrgaoJulgadorEnum;
import br.jus.cnj.pje.criminal.enums.RecorrenteEnum;
import br.jus.cnj.pje.criminal.model.entities.TipoRecurso;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class DecisaoInstanciaSuperiorBean extends EventoCriminalBean {

	private static final long serialVersionUID = 1L;

	private TipoRecurso tipoRecurso;
	private Date dataDecisao;
	private Date dataPublicacao;
	private String nrProcessoInstanciaRevisao;
	private Date dataEncaminhadoTJRecurso;
	private Date dataDevolucaoTJRecurso;
    private Integer recorrente;
    private Date dataTransitoJulgado;
    private Integer orgaoJulgador;
    private String numeroAcordao;
    private Boolean concedido;
    private RecorrenteEnum recorrenteEnum;

	public DecisaoInstanciaSuperiorBean() {
		super();
		this.recorrente = -1;
	}
	
	public DecisaoInstanciaSuperiorBean(String id, TipoRecurso tipoRecurso, Date dataPublicacao,
			String nrProcessoInstanciaRevisao) {
		super(id);
		this.tipoRecurso = tipoRecurso;
		this.dataPublicacao = dataPublicacao;
		this.nrProcessoInstanciaRevisao = nrProcessoInstanciaRevisao;
	}

	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public Date getDataDecisao() {
		return dataDecisao;
	}

	public void setDataDecisao(Date dataDecisao) {
		this.dataDecisao = dataDecisao;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getNrProcessoInstanciaRevisao() {
		return nrProcessoInstanciaRevisao;
	}

	public void setNrProcessoInstanciaRevisao(String nrProcessoInstanciaRevisao) {
		this.nrProcessoInstanciaRevisao = nrProcessoInstanciaRevisao;
	}

	public Date getDataEncaminhadoTJRecurso() {
		return dataEncaminhadoTJRecurso;
	}

	public void setDataEncaminhadoTJRecurso(Date dataEncaminhadoTJRecurso) {
		this.dataEncaminhadoTJRecurso = dataEncaminhadoTJRecurso;
	}

	public Date getDataDevolucaoTJRecurso() {
		return dataDevolucaoTJRecurso;
	}

	public void setDataDevolucaoTJRecurso(Date dataDevolucaoTJRecurso) {
		this.dataDevolucaoTJRecurso = dataDevolucaoTJRecurso;
	}

	public Integer getRecorrente() {
		return recorrenteEnum.getPorCod(this.recorrente).getCod();
	}

	public void setRecorrente(Integer recorrente) {
		this.recorrente = recorrente;
	}

	public Date getDataTransitoJulgado() {
		return dataTransitoJulgado;
	}

	public void setDataTransitoJulgado(Date dataTransitoJulgado) {
		this.dataTransitoJulgado = dataTransitoJulgado;
	}

	public Integer getOrgaoJulgador() {
		return orgaoJulgador;
	}

	public void setOrgaoJulgador(Integer orgaoJulgador) {
		this.orgaoJulgador = orgaoJulgador;
	}

	public String getNumeroAcordao() {
		return numeroAcordao;
	}

	public void setNumeroAcordao(String numeroAcordao) {
		this.numeroAcordao = numeroAcordao;
	}

	public Boolean getConcedido() {
		return concedido;
	}

	public void setConcedido(Boolean concedido) {
		this.concedido = concedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((concedido == null) ? 0 : concedido.hashCode());
		result = prime * result + ((dataDecisao == null) ? 0 : dataDecisao.hashCode());
		result = prime * result + ((dataDevolucaoTJRecurso == null) ? 0 : dataDevolucaoTJRecurso.hashCode());
		result = prime * result + ((dataEncaminhadoTJRecurso == null) ? 0 : dataEncaminhadoTJRecurso.hashCode());
		result = prime * result + ((dataPublicacao == null) ? 0 : dataPublicacao.hashCode());
		result = prime * result + ((dataTransitoJulgado == null) ? 0 : dataTransitoJulgado.hashCode());
		result = prime * result + ((nrProcessoInstanciaRevisao == null) ? 0 : nrProcessoInstanciaRevisao.hashCode());
		result = prime * result + ((numeroAcordao == null) ? 0 : numeroAcordao.hashCode());
		result = prime * result + ((orgaoJulgador == null) ? 0 : orgaoJulgador.hashCode());
		result = prime * result + ((recorrente == null) ? 0 : recorrente.hashCode());
		result = prime * result + ((tipoRecurso == null) ? 0 : tipoRecurso.hashCode());
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
		DecisaoInstanciaSuperiorBean other = (DecisaoInstanciaSuperiorBean) obj;
		if (concedido == null) {
			if (other.concedido != null)
				return false;
		} else if (!concedido.equals(other.concedido))
			return false;
		if (dataDecisao == null) {
			if (other.dataDecisao != null)
				return false;
		} else if (!dataDecisao.equals(other.dataDecisao))
			return false;
		if (dataDevolucaoTJRecurso == null) {
			if (other.dataDevolucaoTJRecurso != null)
				return false;
		} else if (!dataDevolucaoTJRecurso.equals(other.dataDevolucaoTJRecurso))
			return false;
		if (dataEncaminhadoTJRecurso == null) {
			if (other.dataEncaminhadoTJRecurso != null)
				return false;
		} else if (!dataEncaminhadoTJRecurso.equals(other.dataEncaminhadoTJRecurso))
			return false;
		if (dataPublicacao == null) {
			if (other.dataPublicacao != null)
				return false;
		} else if (!dataPublicacao.equals(other.dataPublicacao))
			return false;
		if (dataTransitoJulgado == null) {
			if (other.dataTransitoJulgado != null)
				return false;
		} else if (!dataTransitoJulgado.equals(other.dataTransitoJulgado))
			return false;
		if (nrProcessoInstanciaRevisao == null) {
			if (other.nrProcessoInstanciaRevisao != null)
				return false;
		} else if (!nrProcessoInstanciaRevisao.equals(other.nrProcessoInstanciaRevisao))
			return false;
		if (numeroAcordao == null) {
			if (other.numeroAcordao != null)
				return false;
		} else if (!numeroAcordao.equals(other.numeroAcordao))
			return false;
		if (orgaoJulgador == null) {
			if (other.orgaoJulgador != null)
				return false;
		} else if (!orgaoJulgador.equals(other.orgaoJulgador))
			return false;
		if (recorrente == null) {
			if (other.recorrente != null)
				return false;
		} else if (!recorrente.equals(other.recorrente))
			return false;
		if (tipoRecurso == null) {
			if (other.tipoRecurso != null)
				return false;
		} else if (!tipoRecurso.equals(other.tipoRecurso))
			return false;
		return true;
	}

	@Override
	public String obterTextoFinal() {
		StringBuilder retorno = new StringBuilder();
		if (tipoRecurso != null) {
			retorno.append("Tipo de recurso: " + tipoRecurso.getTipoRecurso() + "; ");
		}
		if (dataDecisao != null) {
			retorno.append("Data da Decisão: " + DateUtil.dateToString(dataDecisao) + "; ");
		}
		if (dataPublicacao != null) {
			retorno.append("Data da publicação: " + DateUtil.dateToString(dataPublicacao) + "; ");
		}
		if (nrProcessoInstanciaRevisao != null && !nrProcessoInstanciaRevisao.isEmpty()) {
			retorno.append("Número do processo de revisão: " + nrProcessoInstanciaRevisao + "; ");
		}
		retorno.append(super.obterTextoFinal());
		return retorno.toString();
	}
	
	@Override
	public String toString() {
		return obterTextoFinal();
	}
	
	@Override
	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = super.obterCamposVisualizacao();
		if (tipoRecurso != null) {
			camposVisualizacao.put("Tipo de recurso", tipoRecurso.getTipoRecurso());
		}
		if (dataDecisao != null) {
			camposVisualizacao.put("Data da Decisão", DateUtil.dateToString(dataDecisao));
		}
		if (dataPublicacao != null) {
			camposVisualizacao.put("Data da publicação", DateUtil.dateToString(dataPublicacao));
		}
		if (nrProcessoInstanciaRevisao != null && !nrProcessoInstanciaRevisao.isEmpty()) {
			camposVisualizacao.put("Número do processo de revisão", nrProcessoInstanciaRevisao);
		}
		return camposVisualizacao;
	}
	
	@Transient
	@JsonIgnore
	public OrgaoJulgadorEnum getOrgaoJulgadorEnum() {
		return OrgaoJulgadorEnum.getPorCod(orgaoJulgador);
	}
	
	@Transient
	@JsonIgnore
	public RecorrenteEnum getRecorrenteEnum() {
		return RecorrenteEnum.getPorCod(recorrente);
	}
	@Override
	public Date obterDataVisualizacao() {
		return getDataDecisao();
	}

}
