package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.MotivoPrisao;

public interface MotivoPrisaoService extends BaseService<MotivoPrisao, Long> {
	public List<MotivoPrisao> recuperarMotivosPrisaoAtivos();
}
