package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.error.CriminalBusinessException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;

public interface TipoOrigemService extends BaseService<TipoOrigem, Long> {

	TipoOrigem criarTipoOrigem(TipoOrigem tipoOrigem) throws CriminalBusinessException;
	TipoOrigem recuperarTipoOrigem(Long idTipoOrigem);
	List<TipoOrigem> recuperarTiposOrigem();
	Page<TipoOrigem> recuperarTiposOrigemPorPagina(TipoOrigem tipoOrigem, Pageable pagina);
	Page<TipoOrigem> recuperarTiposOrigemPorPagina(Pageable pagina);
	TipoOrigem alterarTipoOrigem(TipoOrigem tipoOrigem) throws CriminalBusinessException;
	TipoOrigem inativarTipoOrigem(TipoOrigem tipoOrigem) throws CriminalBusinessException;
	Long countTipoOrigem(TipoOrigem tipoOrigem);
	Long countTipoOrigem();

}
