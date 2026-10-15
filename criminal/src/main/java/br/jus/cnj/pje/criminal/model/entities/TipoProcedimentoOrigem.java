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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import br.jus.cnj.pje.criminal.listeners.CriminalMultiTenancyListener;

@Entity
@Table(name = "tb_tp_procedimento_origem")
@SequenceGenerator(allocationSize = 1, name = "gen_tp_procedimento_origem", sequenceName = "sq_tb_tp_procedimento_origem")
@Audited
@EntityListeners(CriminalMultiTenancyListener.class)
public class TipoProcedimentoOrigem extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String dsTipoProcedimento;
	private Boolean ativo;
	private String codigoTribunal;
	private List<TipoOrigem> tipoOrigemList = new ArrayList<TipoOrigem>(0);

	@Id
	@GeneratedValue(generator = "gen_tp_procedimento_origem")
	@Column(name = "id_tipo_procedimento_origem", unique = true, nullable = false)
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ds_tipo_procedimento", length = 100)
	@Length(max = 100)
	@NotNull
	public String getDsTipoProcedimento() {
		return dsTipoProcedimento;
	}

	public void setDsTipoProcedimento(String dsTipoProcedimento) {
		this.dsTipoProcedimento = dsTipoProcedimento;
	}

	@Column(name = "in_ativo", nullable = false)
	@NotNull
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return dsTipoProcedimento;
	}

	@ManyToMany
	@JsonBackReference
	@JoinTable(name = "tb_tp_org_tp_proced_origem", joinColumns = {
			@JoinColumn(name = "id_tipo_procedimento_origem") }, inverseJoinColumns = {
					@JoinColumn(name = "id_tipo_origem") })
	public List<TipoOrigem> getTipoOrigemList() {
		return tipoOrigemList;
	}

	public void setTipoOrigemList(List<TipoOrigem> tipoOrigemList) {
		this.tipoOrigemList = tipoOrigemList;
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
