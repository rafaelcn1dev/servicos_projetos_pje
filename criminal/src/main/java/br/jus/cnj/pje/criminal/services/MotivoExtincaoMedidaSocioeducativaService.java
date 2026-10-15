package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.MotivoExtincaoMedidaSocioeducativa;

public interface MotivoExtincaoMedidaSocioeducativaService extends BaseService<MotivoExtincaoMedidaSocioeducativa, Long> {
	
	List<MotivoExtincaoMedidaSocioeducativa> recuperarMotivosExtincaoMedidaSocioeducativaAtivas();
	Page<MotivoExtincaoMedidaSocioeducativa> recuperarPorPagina(MotivoExtincaoMedidaSocioeducativa motivoExtincaoMedidaSocioeducativa, Pageable pagina);
	Long count();
	Long count(MotivoExtincaoMedidaSocioeducativa motivoExtincaoMedidaSocioeducativa);

}
