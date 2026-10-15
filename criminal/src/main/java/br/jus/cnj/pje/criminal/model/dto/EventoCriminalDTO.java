package br.jus.cnj.pje.criminal.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import br.jus.cnj.pje.criminal.enums.TipoEventoCriminalEnum;
import br.jus.cnj.pje.criminal.model.beans.IncidenciaPenalBean;
import br.jus.cnj.pje.criminal.model.beans.ProcessoMovimentoLegacyBean;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;

public class EventoCriminalDTO {

	protected String id;
	protected Date data;
	protected TipoEventoCriminal tipoInformacaoCriminal;
	protected List<IncidenciaPenalBean> tipificacoes = new ArrayList<IncidenciaPenalBean>(0);
	protected ProcessoMovimentoLegacyBean processoMovimentoLegacy;
	protected String observacao;
	protected Boolean ativo;
	
	public EventoCriminalDTO() {
		super();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoEventoCriminal getTipoInformacaoCriminal() {
		return tipoInformacaoCriminal;
	}

	public void setTipoInformacaoCriminal(TipoEventoCriminal tipoInformacaoCriminal) {
		this.tipoInformacaoCriminal = tipoInformacaoCriminal;
	}

	public List<IncidenciaPenalBean> getTipificacoes() {
		return tipificacoes;
	}

	public void setTipificacoes(List<IncidenciaPenalBean> tipificacoes) {
		this.tipificacoes = tipificacoes;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ProcessoMovimentoLegacyBean getProcessoMovimentoLegacy() {
		return processoMovimentoLegacy;
	}

	public void setProcessoMovimentoLegacy(ProcessoMovimentoLegacyBean processoMovimentoLegacy) {
		this.processoMovimentoLegacy = processoMovimentoLegacy;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		StringBuilder txt = new StringBuilder();
		if (tipificacoes != null) {
			for (IncidenciaPenalBean incidencia : tipificacoes) {
				txt.append(incidencia.getDispositivo().getTextoFinal());			
			}
		}
		return txt.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((processoMovimentoLegacy == null) ? 0 : processoMovimentoLegacy.hashCode());
		result = prime * result + ((tipificacoes == null) ? 0 : tipificacoes.hashCode());
		result = prime * result + ((tipoInformacaoCriminal == null) ? 0 : tipoInformacaoCriminal.hashCode());
		return result;
	}
	
	@Transient
	public TipoEventoCriminalEnum getTipoEventoCriminalEnum() {
		if (getTipoInformacaoCriminal() == null) {
			return null;
		}
		return getTipoInformacaoCriminal().getCodTipoIc();
	}

	@Transient
	public String getDescricaoTipoEventoCriminalEnum() {
		if (getTipoInformacaoCriminal() == null) {
			return "";
		}
		if (getTipoInformacaoCriminal().getCodTipoIc() == null) {
			return "";
		}
		return getTipoInformacaoCriminal().getCodTipoIc().getLabel();
	}

}
