package br.jus.cnj.pje.criminal.model.dto.v1;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import br.jus.cnj.pje.criminal.base.AbstractEntity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.jus.cnj.pje.criminal.listeners.CriminalMultiTenancyListener;
import br.jus.cnj.pje.criminal.model.entities.OrgaoProcedimentoOriginario;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.model.entities.ProcessoProcedimentoOrigem;
import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;
import br.jus.cnj.pje.criminal.model.entities.TipoProcedimentoOrigem;

public class ProcessoProcedimentoOrigemDTO {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Processo processo;
	private OrgaoProcedimentoOriginario orgaoProcedimentoOriginario;
	private TipoProcedimentoOrigem tipoProcedimentoOrigem;
	private TipoOrigem tipoOrigem;
	private Date dataInstauracao;
	private String numero;
	private Integer ano;
	private Date dataLavratura;
	private Boolean ativo = true;
	private Boolean retombamentoRedistribuicao = false;
	private ProcessoProcedimentoOrigemDTO processoProcedimentoOrigemRetombado;
	private String nrProtocoloPolicia;
	private String uf;
	private String codigoTribunal;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	@JsonIgnoreProperties("processoProcedimentoOrigemList")
	public Processo getProcesso() {
		return processo;
	}

	public void setOrgaoProcedimentoOriginario(OrgaoProcedimentoOriginario orgaoProcedimentoOriginario) {
		this.orgaoProcedimentoOriginario = orgaoProcedimentoOriginario;
	}

	public OrgaoProcedimentoOriginario getOrgaoProcedimentoOriginario() {
		return orgaoProcedimentoOriginario;
	}

	public void setTipoProcedimentoOrigem(TipoProcedimentoOrigem tipoProcedimentoOrigem) {
		this.tipoProcedimentoOrigem = tipoProcedimentoOrigem;
	}

	public TipoProcedimentoOrigem getTipoProcedimentoOrigem() {
		return tipoProcedimentoOrigem;
	}

	public void setDataInstauracao(Date dataInstauracao) {
		this.dataInstauracao = dataInstauracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataInstauracao() {
		return dataInstauracao;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getAno() {
		return ano;
	}

	@JsonIgnore
	@Transient
	public String getNumeroAno() {
		if (getNumero() != null && getAno() != null) {
			return getNumero() + "/" + getAno();
		}

		return "";
	}

	public void setTipoOrigem(TipoOrigem tipoOrigem) {
		this.tipoOrigem = tipoOrigem;
	}

	public TipoOrigem getTipoOrigem() {
		return tipoOrigem;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataLavratura() {
		return dataLavratura;
	}

	public void setDataLavratura(Date dataLavratura) {
		this.dataLavratura = dataLavratura;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Boolean getRetombamentoRedistribuicao() {	
		return retombamentoRedistribuicao;
	}

	public void setRetombamentoRedistribuicao(Boolean retombamentoRedistribuicao) {
		this.retombamentoRedistribuicao = retombamentoRedistribuicao;
	}

	public ProcessoProcedimentoOrigemDTO getProcessoProcedimentoOrigemRetombado() {
		return processoProcedimentoOrigemRetombado;
	}

	public void setProcessoProcedimentoOrigemRetombado(ProcessoProcedimentoOrigemDTO processoProcedimentoOrigemRetombado) {
		this.processoProcedimentoOrigemRetombado = processoProcedimentoOrigemRetombado;
	}
	
	public String getNrProtocoloPolicia() {
		return nrProtocoloPolicia;
	}

	public void setNrProtocoloPolicia(String nrProtocoloPolicia) {
		this.nrProtocoloPolicia = nrProtocoloPolicia;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@JsonIgnore
	public String getCodigoTribunal() {
		return this.codigoTribunal;
	}
	
	@JsonProperty
	public void setCodigoTribunal(String codigoTribunal) {
		this.codigoTribunal = codigoTribunal;
	}
	
}
