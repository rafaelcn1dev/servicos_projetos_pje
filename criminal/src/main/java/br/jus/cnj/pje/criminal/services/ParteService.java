package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.Parte;

public interface ParteService {
	
	Parte recuperarProcessosPorIdProcessoParteLegacy(Long idProcessoParteLegacy);
	List<Parte> recuperarPartePorNumeroProcesso(String numeroProcesso);
	List<Parte> recuperarParte(String numeroProcesso, Long idProcessoParteLegacy, Long idPessoaLegacy);
	Parte inserir(Parte parte);
}
