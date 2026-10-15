package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.TipoDispositivo;

public interface TipoDispositivoService {
	public List<TipoDispositivo> recuperarTiposDispositivos();
	public List<TipoDispositivo> recuperarTiposDispositivosPorNome(String nome);
}
