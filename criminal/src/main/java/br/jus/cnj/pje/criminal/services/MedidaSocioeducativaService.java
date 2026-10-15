package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.MedidaSocioeducativa;

public interface MedidaSocioeducativaService extends BaseService<MedidaSocioeducativa, Long> {
	
	List<MedidaSocioeducativa> recuperarMedidasSocioeducativasAtivas();
	Page<MedidaSocioeducativa> recuperarPorPagina(MedidaSocioeducativa medidaSocioeducativa, Pageable pagina);
	Long count();
	Long count(MedidaSocioeducativa medidaSocioeducativa);

}
