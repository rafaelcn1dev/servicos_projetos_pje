package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.jus.cnj.pje.criminal.model.entities.InformacaoCriminal;

public interface InformacaoCriminalRepository extends CustomRepository<InformacaoCriminal, Long>{
//	@Query(value = "SELECT * FROM core.tb_informacao_criminal WHERE id_processo = ?1 AND ds_conteudo -> 'parte'->>'idProcessoParteLegacy' = ?2", nativeQuery = true)
//	List<InformacaoCriminal> findByIdProcessoAndIdProcessoParteLegacy(Long idProcesso, String idProcessoParteLegacy);
	
	@Query(value = "SELECT DISTINCT inf FROM InformacaoCriminal inf WHERE inf.processo.id = ?1 AND inf.parte.idProcessoParteLegacy = ?2")
	List<InformacaoCriminal> findByProcessoIdAndParteIdParteLegacy(Long idProcesso, Long idProcessoParteLegacy);

	@Query(value = "SELECT DISTINCT inf FROM InformacaoCriminal inf WHERE inf.processo.id = ?1")
	List<InformacaoCriminal> findByIdProcesso(Long idProcesso);

	
	
}
