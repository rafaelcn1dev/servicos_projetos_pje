package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.enums.TipoProcessoEnum;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;

public interface TipoEventoCriminalService extends BaseService<TipoEventoCriminal, Long>{

	List<TipoEventoCriminal> recuperarTiposEventoCriminalAtivos();
	List<TipoEventoCriminal> recuperaTiposEventoCriminalPorTipoProcesso(TipoProcessoEnum tipoProcesso);
	List<TipoEventoCriminal> recuperaTiposEventoCriminalAtivosPorTipoProcesso(TipoProcessoEnum tipoProcesso);
	List<TipoEventoCriminal> recuperaTiposEventoCriminalInativosPorTipoProcesso(TipoProcessoEnum tipoProcesso);
	Page<TipoEventoCriminal> recuperarPorPagina(TipoEventoCriminal tipoEventoCriminal, Pageable pagina);
	Long count();
	Long count(TipoEventoCriminal tipoEventoCriminal);
	
}
