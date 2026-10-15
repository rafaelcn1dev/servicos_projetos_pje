package br.jus.cnj.pje.criminal.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.jus.cnj.pje.criminal.enums.TipoEventoCriminalEnum;
import br.jus.cnj.pje.criminal.enums.TipoProcessoEnum;

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name = "tb_tipo_evento_criminal")
@SequenceGenerator(allocationSize = 1, name = "gen_tipo_evento_criminal", sequenceName = "sq_tb_tipo_evento_criminal")
@Audited
public class TipoEventoCriminal extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	private Long id;
	private TipoEventoCriminalEnum codTipoIc;
	private String descricao;
	private Boolean ativo;
	private Boolean exigeTipificacaoDelito;
	private List<TipoEventoCriminalMovimento> listTipoEventoCriminalMovimento;
	private TipoProcessoEnum tipoProcesso;

	@Id
	@GeneratedValue(generator = "gen_tipo_evento_criminal")
	@Column(name = "id_tipo_evento_criminal", unique = true, nullable = false)
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Column(name = "cd_tipo_evento_criminal", unique = true)
	@Enumerated(EnumType.STRING)
	public TipoEventoCriminalEnum getCodTipoIc() {
		return codTipoIc;
	}

	public void setCodTipoIc(TipoEventoCriminalEnum codigo) {
		this.codTipoIc = codigo;
	}

	@Column(name = "ds_tipo_evento_criminal")
	@NotNull
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "in_ativo", nullable = false)
	@NotNull
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Column(name = "in_exige_tipificacao_delito", nullable = false)
	@NotNull
	public Boolean getExigeTipificacaoDelito() {
		return exigeTipificacaoDelito;
	}

	public void setExigeTipificacaoDelito(Boolean exigeTipificacaoDelito) {
		this.exigeTipificacaoDelito = exigeTipificacaoDelito;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "tipoEventoCriminal", fetch = FetchType.LAZY)
	@NotAudited
	public List<TipoEventoCriminalMovimento> getListTipoEventoCriminalMovimento() {
		return listTipoEventoCriminalMovimento;
	}

	public void setListTipoEventoCriminalMovimento(List<TipoEventoCriminalMovimento> listTipoEventoCriminalMovimento) {
		this.listTipoEventoCriminalMovimento = listTipoEventoCriminalMovimento;
	}
	
	@Override
	@Transient
	public String getCodigoTribunal() {
		return null;
	}
	
	@Override
	public void setCodigoTribunal(String codigoTribunal) {
	}	

	@Column(name = "cd_tipo_processo")
	@Enumerated(EnumType.STRING)
	public TipoProcessoEnum getTipoProcesso() {
		return tipoProcesso;
	}

	public void setTipoProcesso(TipoProcessoEnum tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}
	
}
