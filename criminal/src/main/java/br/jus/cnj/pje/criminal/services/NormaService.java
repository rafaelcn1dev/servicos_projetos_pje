package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.model.entities.Norma;

public interface NormaService extends BaseService<Norma, Long>{

	List<Norma> recuperarNormasAtivas();
	Norma recuperarNormaPorNumero(String numeroNorma);
	Norma criarNorma(Norma norma);
	Long countNormas();
	Long countNormas(Norma norma);
	Page<Norma> recuperarNormasPorPagina(Norma norma, Pageable pagina);
	Page<Norma> recuperarNormasPorPagina(Pageable pagina);
	Norma recuperarNorma(Long idNorma);
	Norma alterarNorma(Norma norma);
	Norma inativarNorma(Norma norma);
	
}
