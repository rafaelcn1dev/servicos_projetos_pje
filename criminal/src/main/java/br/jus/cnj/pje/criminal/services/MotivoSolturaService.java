package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.MotivoSoltura;

public interface MotivoSolturaService extends BaseService<MotivoSoltura, Long>{
	public List<MotivoSoltura> recuperarMotivosSolturaAtivos();
}
