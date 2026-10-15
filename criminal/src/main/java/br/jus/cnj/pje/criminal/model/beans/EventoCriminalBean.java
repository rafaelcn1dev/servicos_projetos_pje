package br.jus.cnj.pje.criminal.model.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class EventoCriminalBean extends BaseBean {

	private static final long serialVersionUID = -1;

	protected Date data;
	protected Date dataCadastro;
	protected TipoEventoCriminal tipoInformacaoCriminal;
	protected List<IncidenciaPenalBean> tipificacoes = new ArrayList<IncidenciaPenalBean>(0);
	protected ProcessoMovimentoLegacyBean processoMovimentoLegacy;
	protected String observacao;
	protected Boolean ativo;
	protected String resumo;
	
	public EventoCriminalBean(String id) {
		super(id);
	}
	

	public EventoCriminalBean() {
		super();
	}
	
	public EventoCriminalBean(EventoCriminalBean beanAntigo) {
		data = beanAntigo.getData();
		dataCadastro = beanAntigo.getDataCadastro();
		tipoInformacaoCriminal = beanAntigo.getTipoInformacaoCriminal();
		tipificacoes = new ArrayList<>();
		processoMovimentoLegacy = beanAntigo.getProcessoMovimentoLegacy();
		observacao = beanAntigo.getObservacao();
		ativo = beanAntigo.getAtivo();
		resumo = beanAntigo.getResumo();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	
	public String obterTextoFinal() {
		StringBuilder txt = new StringBuilder("");
		if (data != null) {
			txt.append("Data do registro: " + DateUtil.dateToString(data) + "; ");
		}
		txt.append(getTipificacoesFormatadas());
		if (observacao != null && !observacao.isEmpty()) {
			txt.append("Observação: " + observacao + "; ");
		}
		return txt.toString();
	}

	public Map<String, String> obterCamposVisualizacao() {
		Map<String, String> camposVisualizacao = new LinkedHashMap<>();
		String tipoInformacaoCriminalDescricao = null;
		if(tipoInformacaoCriminal != null && StringUtils.isNotBlank(tipoInformacaoCriminal.getDescricao())) {
			tipoInformacaoCriminalDescricao = tipoInformacaoCriminal.getDescricao();
		}
		camposVisualizacao.put("Evento criminal", tipoInformacaoCriminalDescricao);
		
		if (data != null) {
			camposVisualizacao.put("Data do registro", DateUtil.dateToString(data));
		}
		
		if (tipificacoes != null && !tipificacoes.isEmpty()) {
			camposVisualizacao.put("Tipificações", getTipificacoesFormatadas());	
		}
		
		if (resumo != null && !resumo.isEmpty()) {
			camposVisualizacao.put("Resumo", resumo);
		}
		
		if (observacao != null && !observacao.isEmpty()) {
			camposVisualizacao.put("Observação", observacao);	
		}
		
		return camposVisualizacao;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return obterTextoFinal();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((processoMovimentoLegacy == null) ? 0 : processoMovimentoLegacy.hashCode());
		result = prime * result + ((resumo == null) ? 0 : resumo.hashCode());
		result = prime * result + ((tipificacoes == null) ? 0 : tipificacoes.hashCode());
		result = prime * result + ((tipoInformacaoCriminal == null) ? 0 : tipoInformacaoCriminal.hashCode());
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
		EventoCriminalBean other = (EventoCriminalBean) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (processoMovimentoLegacy == null) {
			if (other.processoMovimentoLegacy != null)
				return false;
		} else if (!processoMovimentoLegacy.equals(other.processoMovimentoLegacy))
			return false;
		if (resumo == null) {
			if (other.resumo != null)
				return false;
		} else if (!resumo.equals(other.resumo))
			return false;
		if (tipificacoes == null) {
			if (other.tipificacoes != null)
				return false;
		} else if (!tipificacoes.equals(other.tipificacoes))
			return false;
		if (tipoInformacaoCriminal == null) {
			if (other.tipoInformacaoCriminal != null)
				return false;
		} else if (!tipoInformacaoCriminal.equals(other.tipoInformacaoCriminal))
			return false;
		return true;
	}

	@Override
	public EventoCriminalBean clone() {
		try {
			return (EventoCriminalBean) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected String getTipificacoesFormatadas() {
		return getTipificacoesFormatadas(tipificacoes);
	}
	
	public static String getTipificacoesFormatadas(List<IncidenciaPenalBean> tipificacoes) {
		StringBuilder txt = new StringBuilder();
		for (Iterator<IncidenciaPenalBean> iterator = tipificacoes.iterator(); iterator.hasNext();) {
			IncidenciaPenalBean incidencia = iterator.next();
			txt.append(incidencia.getDispositivo().getTextoFinal());			
			txt.append("; ");
		}
		return txt.toString();
	}
	
	public Date obterDataVisualizacao() {
		return getData();
	}
}
