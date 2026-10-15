package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoInternacao;

public interface TipoInternacaoService extends BaseService<TipoInternacao, Long> {
	
	public List<TipoInternacao> recuperarTiposInternacaoAtivos();

}
