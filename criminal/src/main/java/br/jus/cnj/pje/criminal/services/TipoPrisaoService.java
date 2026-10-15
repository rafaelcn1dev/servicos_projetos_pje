package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoPrisao;

public interface TipoPrisaoService extends BaseService<TipoPrisao, Long> {
	
	List<TipoPrisao> recuperarTiposPrisaoAtivos();

}
