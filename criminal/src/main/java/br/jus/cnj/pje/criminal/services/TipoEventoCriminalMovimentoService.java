package br.jus.cnj.pje.criminal.services;

import java.util.List;

import javax.validation.Valid;

import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminalMovimento;

public interface TipoEventoCriminalMovimentoService {

	List<TipoEventoCriminalMovimento> recuperarPorCodigoMovimento(String codMovimento);
	TipoEventoCriminalMovimento criarTipoEventoCriminalMovimento(@Valid TipoEventoCriminalMovimento tipoEventoCriminalMovimento);
	void removerTipoEventoCriminalMovimento(TipoEventoCriminalMovimento tipoEventoCriminalMovimento);
	TipoEventoCriminalMovimento recuperarTipoEventoCriminalMovimentoPorId(Integer idTipoEventoCriminalMovimento);
	
}
