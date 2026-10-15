package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoSoltura;

public interface TipoSolturaService extends BaseService<TipoSoltura, Long> {
	public List<TipoSoltura> recuperarTiposSolturaAtivos();
}
