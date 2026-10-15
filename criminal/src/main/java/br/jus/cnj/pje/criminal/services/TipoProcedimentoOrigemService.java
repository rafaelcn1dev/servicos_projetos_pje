package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.error.CriminalBusinessException;
import br.jus.cnj.pje.criminal.model.entities.TipoProcedimentoOrigem;

public interface TipoProcedimentoOrigemService extends BaseService<TipoProcedimentoOrigem, Long>{

	TipoProcedimentoOrigem criarNovoTipoProcedimentoOrigem(TipoProcedimentoOrigem tipoProcedimentoOrigem) throws CriminalBusinessException ;

	List<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigem();

	TipoProcedimentoOrigem recuperarTipoProcedimentoOrigemPorId(Long idTipoProcedimentoOrigem);

	Page<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorPagina(Pageable pageable);

	List<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorNome(String tipoProcedimentoOrigem);

	TipoProcedimentoOrigem alterarTipoProcedimentoOrigem(TipoProcedimentoOrigem tipoProcedimentoOrigem) throws CriminalBusinessException ;

	TipoProcedimentoOrigem inativarTipoProcedimentoOrigem(TipoProcedimentoOrigem tipoProcedimentoOrigem) throws CriminalBusinessException ;

	List<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorTipoOrigem(Long idTipoOrigem);

	Long countTipoProcedimentoOrigem(TipoProcedimentoOrigem tipoProcedimentoOrigem);

	Long countTipoProcedimentoOrigem();

	Page<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorPagina(TipoProcedimentoOrigem tipoProcedimentoOrigem, Pageable pagina);
}
