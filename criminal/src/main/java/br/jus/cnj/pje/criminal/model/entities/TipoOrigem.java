package br.jus.cnj.pje.criminal.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import br.jus.cnj.pje.criminal.listeners.CriminalMultiTenancyListener;

@Entity
@Table(name = "tb_tipo_origem")
@SequenceGenerator(allocationSize = 1, name = "gen_tipo_origem", sequenceName = "sq_tb_tipo_origem")
@Audited
@EntityListeners(CriminalMultiTenancyListener.class)
public class TipoOrigem extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String dsTipoOrigem;
	private Boolean inObrigatorioNumeroOrigem;
	private Boolean ativo;
	private String codigoTribunal;
	private List<TipoProcedimentoOrigem> tipoProcedimentoOrigemList = new ArrayList<TipoProcedimentoOrigem>(0);


	public TipoOrigem() {
	}

	@Id
	@GeneratedValue(generator = "gen_tipo_origem")
	@Column(name = "id_tipo_origem", unique = true, nullable = false)
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ds_tipo_origem", nullable = false)
	@NotNull
	@Length(max=100)
	public String getDsTipoOrigem() {
		return dsTipoOrigem;
	}

	public void setDsTipoOrigem(String dsTipoOrigem) {
		this.dsTipoOrigem = dsTipoOrigem;
	}

	@Column(name = "in_obrigatorio_numero_origem", nullable = false)
	@NotNull
	public Boolean getInObrigatorioNumeroOrigem() {
		return inObrigatorioNumeroOrigem;
	}

	public void setInObrigatorioNumeroOrigem(Boolean inObrigatorioNumeroOrigem) {
		this.inObrigatorioNumeroOrigem = inObrigatorioNumeroOrigem;
	}

	@Column(name = "in_ativo", nullable = false)
	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@ManyToMany
	@JoinTable(name = "tb_tp_org_tp_proced_origem", joinColumns = { @JoinColumn(name = "id_tipo_origem") }, inverseJoinColumns = { @JoinColumn(name = "id_tipo_procedimento_origem") })
	public List<TipoProcedimentoOrigem> getTipoProcedimentoOrigemList() {
		return tipoProcedimentoOrigemList;
	}

	public void setTipoProcedimentoOrigemList(List<TipoProcedimentoOrigem> tipoProcedimentoOrigemList) {
		this.tipoProcedimentoOrigemList = tipoProcedimentoOrigemList;
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

	@Override
	public String toString() {
		return this.getDsTipoOrigem();
	}

}
