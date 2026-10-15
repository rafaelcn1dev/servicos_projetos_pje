package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.MotivoRetornoEvasao;

public interface MotivoRetornoEvasaoService extends BaseService<MotivoRetornoEvasao, Long> {
	
	List<MotivoRetornoEvasao> recuperarMotivosRetornoEvasaoAtivos();
	Page<MotivoRetornoEvasao> recuperarPorPagina(MotivoRetornoEvasao motivoRetornoEvasao, Pageable pagina);
	Long count();
	Long count(MotivoRetornoEvasao motivoRetornoEvasao);

}
