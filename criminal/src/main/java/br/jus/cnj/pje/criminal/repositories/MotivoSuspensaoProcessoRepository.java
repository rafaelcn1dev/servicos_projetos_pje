package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.MotivoSuspensaoProcesso;

public interface MotivoSuspensaoProcessoRepository extends CustomRepository<MotivoSuspensaoProcesso, Long> {
	public List<MotivoSuspensaoProcesso> findAllByAtivoTrue();
}
