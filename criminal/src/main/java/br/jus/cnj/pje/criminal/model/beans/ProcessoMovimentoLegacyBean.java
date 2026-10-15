package br.jus.cnj.pje.criminal.model.beans;

import java.util.Date;

public class ProcessoMovimentoLegacyBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private Integer idProcessoEvento;
	private String codEvento;
	private String dsEvento;
	private Date dataAtualizacao;
	private String textoFinalExterno;

	public ProcessoMovimentoLegacyBean() {
		super();
	}

	public Integer getIdProcessoEvento() {
		return idProcessoEvento;
	}

	public void setIdProcessoEvento(Integer idProcessoEvento) {
		this.idProcessoEvento = idProcessoEvento;
	}

	public String getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	public String getDsEvento() {
		return dsEvento;
	}

	public void setDsEvento(String dsEvento) {
		this.dsEvento = dsEvento;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getTextoFinalExterno() {
		return textoFinalExterno;
	}

	public void setTextoFinalExterno(String textoFinalExterno) {
		this.textoFinalExterno = textoFinalExterno;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
}
