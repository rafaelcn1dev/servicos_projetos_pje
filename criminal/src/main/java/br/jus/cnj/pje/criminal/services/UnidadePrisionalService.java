package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.model.entities.UnidadePrisional;

public interface UnidadePrisionalService extends BaseService<UnidadePrisional, Long> {

	UnidadePrisional persistUnidadePrisional(UnidadePrisional unidadePrisional);
	UnidadePrisional recuperarUnidadePrisional(Long idUnidadePrisional);
	List<UnidadePrisional> recuperarUnidadesPrisionais();
	Page<UnidadePrisional> recuperarUnidadesPrisionaisPorPagina(UnidadePrisional unidadePrisional, Pageable pagina);
	Page<UnidadePrisional> recuperarUnidadesPrisionaisPorPagina(Pageable pagina);
	void removerUnidadePrisional(UnidadePrisional unidadePrisional);
	Long countUnidadePrisional(UnidadePrisional unidadePrisional);
	Long countUnidadePrisional();
	List<UnidadePrisional> recuperarUnidadePrisionalPorSiglaUf(String uf);
	UnidadePrisional inativarUnidadePrisional(UnidadePrisional unidadePrisional);
}
