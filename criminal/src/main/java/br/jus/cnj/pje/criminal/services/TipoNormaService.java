package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.TipoNorma;

public interface TipoNormaService {

	List<TipoNorma> recuperarTiposNorma();
	Long countTipoNorma(TipoNorma tipoNorma);
	Long countTipoNorma();

}
