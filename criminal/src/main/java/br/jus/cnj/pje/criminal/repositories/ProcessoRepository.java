package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.Processo;

public interface ProcessoRepository extends CustomRepository<Processo, Long> {

	public List<Processo> findByNrProcessoLikeIgnoreCase(String processo);
	
	public Processo findByNrProcesso(String processo);
	
}
