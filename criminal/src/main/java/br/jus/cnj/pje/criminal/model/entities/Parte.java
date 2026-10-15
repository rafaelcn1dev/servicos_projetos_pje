package br.jus.cnj.pje.criminal.model.entities;

import javax.persistence.*;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.jus.cnj.pje.criminal.enums.SituacaoParteEnum;
import br.jus.cnj.pje.criminal.base.AbstractEntity;

@Entity
@Table(name = "tb_parte")
@SequenceGenerator(allocationSize = 1, name = "gen_parte", sequenceName = "sq_tb_parte")
@Audited
public class Parte extends AbstractEntity<Long>{
	
	private static final long serialVersionUID = 1L;

 	@Id
	@GeneratedValue(generator = "gen_parte")
	@Column(name = "id_parte", unique = true, nullable = false)
	private Long id;

	@NotNull
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_processo", nullable = false)	
	private Processo processo;

	@Column(name="id_processo_parte_legacy", nullable = false)
	private Long idProcessoParteLegacy;
	
	@Column(name="id_pessoa_legacy", nullable = false)
	private Long idPessoaLegacy;
	
	@Column(name="ds_rji")
	private String rji;

	@Column(name = "in_situacao_parte", length = 1)
	@Enumerated(EnumType.STRING)
	private SituacaoParteEnum situacaoParte = SituacaoParteEnum.A;

	public Parte(Long id, Processo processo, Long idProcessoParteLegacy, Long idPessoaLegacy, String rji,
			SituacaoParteEnum situacaoParte) {
		super();
		this.id = id;
		this.processo = processo;
		this.idProcessoParteLegacy = idProcessoParteLegacy;
		this.idPessoaLegacy = idPessoaLegacy;
		this.rji = rji;
		this.situacaoParte = situacaoParte;
	}
	
	public Parte() {
		
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Long getIdProcessoParteLegacy() {
		return idProcessoParteLegacy;
	}

	public void setIdProcessoParteLegacy(Long idProcessoParteLegacy) {
		this.idProcessoParteLegacy = idProcessoParteLegacy;
	}

	public SituacaoParteEnum getSituacaoParte() {
		return situacaoParte;
	}

	public void setSituacaoParte(SituacaoParteEnum situacaoParte) {
		this.situacaoParte = situacaoParte;
	}

	public Long getIdPessoaLegacy() {
		return idPessoaLegacy;
	}

	public void setIdPessoaLegacy(Long idPessoaLegacy) {
		this.idPessoaLegacy = idPessoaLegacy;
	}

	public String getRji() {
		return rji;
	}

	public void setRji(String rji) {
		this.rji = rji;
	}
	@Override
	@Transient
	public String getCodigoTribunal() {
		return null;
	}
	
	@Override
	public void setCodigoTribunal(String codigoTribunal) {
	}

	@Override
	@Transient
	public Boolean getAtivo() {
		return null;
	}

	@Override
	public void setAtivo(Boolean ativo) {

	}


}
