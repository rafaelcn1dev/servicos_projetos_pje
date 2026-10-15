package br.jus.cnj.pje.criminal.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.TipoRecurso;

public interface TipoRecursoService extends BaseService<TipoRecurso, Long>{

	TipoRecurso criarTipoRecurso(TipoRecurso tipoRecurso);
	Long countTiposRecurso();
	Long countTiposRecurso(TipoRecurso tipoRecurso);
	Page<TipoRecurso> recuperarTiposRecursoPorPagina(TipoRecurso tipoRecurso, Pageable pagina);
	Page<TipoRecurso> recuperarTiposRecursoPorPagina(Pageable pagina);
	TipoRecurso recuperarTipoRecurso(Long idTipoRecurso);
	TipoRecurso alterarTipoRecurso(TipoRecurso tipoRecurso);
	TipoRecurso inativarTipoRecurso(TipoRecurso tipoRecurso);
	List<TipoRecurso> recuperarTiposRecursosAtivos();

}
