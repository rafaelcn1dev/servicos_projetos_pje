package br.jus.cnj.pje.criminal.model.beans;

import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;
import br.jus.cnj.pje.criminal.util.DateUtil;

public class PendenciaEventoCriminalBean extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private ProcessoMovimentoLegacyBean processoMovimentoLegacy;
	private TipoEventoCriminal tipoEventoCriminal;
	private String resumo;
	
	
	
	
	public PendenciaEventoCriminalBean(ProcessoMovimentoLegacyBean processoMovimentoLegacy,
			TipoEventoCriminal tipoEventoCriminal) {
		super();
		
		String dataFormatada = DateUtil.dateToString(processoMovimentoLegacy.getDataAtualizacao()); 
					
		this.processoMovimentoLegacy = processoMovimentoLegacy;
		this.tipoEventoCriminal = tipoEventoCriminal;
		this.resumo = String.format("%s para o movimento: (%s) - %s realizado em %s.",
				 tipoEventoCriminal.getDescricao(),
				 processoMovimentoLegacy.getCodEvento(),
				 processoMovimentoLegacy.getDsEvento(),
				 dataFormatada);
				
	}
	
	public ProcessoMovimentoLegacyBean getProcessoMovimentoLegacy() {
		return processoMovimentoLegacy;
	}
	
	public void setProcessoMovimentoLegacy(ProcessoMovimentoLegacyBean processoMovimentoLegacy) {
		this.processoMovimentoLegacy = processoMovimentoLegacy;
	}
	
	public TipoEventoCriminal getTipoEventoCriminal() {
		return tipoEventoCriminal;
	}
	
	public void setTipoEventoCriminal(TipoEventoCriminal tipoEventoCriminal) {
		this.tipoEventoCriminal = tipoEventoCriminal;
	}


	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
}
