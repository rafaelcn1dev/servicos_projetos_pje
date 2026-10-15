package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoRemissao;

public interface TipoRemissaoService extends BaseService<TipoRemissao, Long> {
	
	public List<TipoRemissao> recuperarTiposRemissaoAtivos();

}
