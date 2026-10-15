package br.jus.cnj.pje.criminal.model.entities;

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

@Entity
@Table(name = "tb_processo_proc_origem")
@SequenceGenerator(allocationSize = 1, name = "gen_processo_proc_origem", sequenceName = "sq_processo_proc_origem")
@FilterDef(name = "tribunalFilter", parameters = {@ParamDef(name = "JTR", type = "string")})
@Filter(name = "tribunalFilter", condition = "cd_tribunal = :JTR")
@Audited
@EntityListeners(CriminalMultiTenancyListener.class)
public class ProcessoProcedimentoOrigem extends AbstractEntity<Long> {

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
	private ProcessoProcedimentoOrigem processoProcedimentoOrigemRetombado;
	private String nrProtocoloPolicia;
	private String uf;
	private String codigoTribunal;


	@Id
	@GeneratedValue(generator = "gen_processo_proc_origem")
	@Column(name = "id_processo_proc_origem", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	@NotNull
	@JsonIgnoreProperties("processoProcedimentoOrigemList")
	@ManyToOne
	@JoinColumn(name = "id_processo")
	public Processo getProcesso() {
		return processo;
	}

	public void setOrgaoProcedimentoOriginario(OrgaoProcedimentoOriginario orgaoProcedimentoOriginario) {
		this.orgaoProcedimentoOriginario = orgaoProcedimentoOriginario;
	}

	@ManyToOne
	@JoinColumn(name = "id_org_procedimento_originario")
	public OrgaoProcedimentoOriginario getOrgaoProcedimentoOriginario() {
		return orgaoProcedimentoOriginario;
	}

	public void setTipoProcedimentoOrigem(TipoProcedimentoOrigem tipoProcedimentoOrigem) {
		this.tipoProcedimentoOrigem = tipoProcedimentoOrigem;
	}

	@ManyToOne
	@JoinColumn(name = "id_tipo_procedimento_origem")
	public TipoProcedimentoOrigem getTipoProcedimentoOrigem() {
		return tipoProcedimentoOrigem;
	}

	public void setDataInstauracao(Date dataInstauracao) {
		this.dataInstauracao = dataInstauracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_instauracao", nullable = true)
	public Date getDataInstauracao() {
		return dataInstauracao;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "nr_procedimento", nullable = true)
	public String getNumero() {
		return numero;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@Column(name = "nr_ano", nullable = true)
	@Max(9999)
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

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_tipo_origem", nullable = false)
	public TipoOrigem getTipoOrigem() {
		return tipoOrigem;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_lavratura", nullable = true)
	public Date getDataLavratura() {
		return dataLavratura;
	}

	public void setDataLavratura(Date dataLavratura) {
		this.dataLavratura = dataLavratura;
	}

	@Column(name = "in_ativo", nullable = false)
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Column(name = "in_retombamento_redistribuicao", nullable = false)
	public Boolean getRetombamentoRedistribuicao() {
		return retombamentoRedistribuicao;
	}

	public void setRetombamentoRedistribuicao(Boolean retombamentoRedistribuicao) {
		this.retombamentoRedistribuicao = retombamentoRedistribuicao;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_processo_proc_origem_retombado")
	public ProcessoProcedimentoOrigem getProcessoProcedimentoOrigemRetombado() {
		return processoProcedimentoOrigemRetombado;
	}

	public void setProcessoProcedimentoOrigemRetombado(ProcessoProcedimentoOrigem processoProcedimentoOrigemRetombado) {
		this.processoProcedimentoOrigemRetombado = processoProcedimentoOrigemRetombado;
	}
	
	@Column(name = "nr_protocolo_policia", nullable = true)
	@Length(max = 20)
	public String getNrProtocoloPolicia() {
		return nrProtocoloPolicia;
	}

	public void setNrProtocoloPolicia(String nrProtocoloPolicia) {
		this.nrProtocoloPolicia = nrProtocoloPolicia;
	}

	@Column(name = "uf", nullable = false)
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Override
	@Column(name = "cd_tribunal")
	@JsonIgnore
	public String getCodigoTribunal() {
		return this.codigoTribunal;
	}
	
	@Override
	@JsonProperty
	public void setCodigoTribunal(String codigoTribunal) {
		this.codigoTribunal = codigoTribunal;
	}

}
