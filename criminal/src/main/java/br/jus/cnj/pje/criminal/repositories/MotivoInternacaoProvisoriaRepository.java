package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.MotivoInternacaoProvisoria;

public interface MotivoInternacaoProvisoriaRepository extends CustomRepository<MotivoInternacaoProvisoria, Long> {
	
	public List<MotivoInternacaoProvisoria> findAllByAtivoTrue();

}
