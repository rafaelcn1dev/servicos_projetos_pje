package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.model.entities.NaturezaPrisao;

public interface NaturezaPrisaoService {

	NaturezaPrisao criarNaturezaPrisao(NaturezaPrisao naturezaPrisao);
	NaturezaPrisao recuperarNaturezaPrisao(Long idNaturezaPrisao);
	List<NaturezaPrisao> recuperarNaturezasPrisao();
	Page<NaturezaPrisao> recuperarNaturezasPrisaoPorPagina(NaturezaPrisao naturezaPrisao, Pageable pagina);
	Page<NaturezaPrisao> recuperarNaturezasPrisaoPorPagina(Pageable pagina);
	NaturezaPrisao alterarNaturezaPrisao(NaturezaPrisao naturezaPrisao);
	NaturezaPrisao inativarNaturezaPrisao(NaturezaPrisao naturezaPrisao);
	Long countNaturezaPrisao(NaturezaPrisao naturezaPrisao);
	Long countNaturezaPrisao();

}
