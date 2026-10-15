package br.jus.cnj.pje.criminal.repositories;


import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.Parte;

public interface ParteRepository extends CustomRepository<Parte, Long> {

	public Parte findByIdProcessoParteLegacy(Long idProcessoParteLegacy);
	public List<Parte> findByProcessoNrProcesso(String numeroProcesso);
	public List<Parte> findByProcessoNrProcessoAndIdProcessoParteLegacyAndIdPessoaLegacy(String numeroProcesso, Long idProcessoParteLegacy, Long idPessoaLegacy);
}
