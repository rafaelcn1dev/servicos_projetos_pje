package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.base.BaseService;
import br.jus.cnj.pje.criminal.model.entities.MotivoInternacaoProvisoria;

public interface MotivoInternacaoProvisoriaService extends BaseService<MotivoInternacaoProvisoria, Long> {
	
	List<MotivoInternacaoProvisoria> recuperarMotivosInternacaoProvisoriaAtivos();
	Page<MotivoInternacaoProvisoria> recuperarPorPagina(MotivoInternacaoProvisoria motivoInternacaoProvisoria, Pageable pagina);
	Long count();
	Long count(MotivoInternacaoProvisoria motivoInternacaoProvisoria);

}
