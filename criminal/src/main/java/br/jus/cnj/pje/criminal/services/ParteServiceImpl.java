package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.model.entities.Parte;
import br.jus.cnj.pje.criminal.repositories.ParteRepository;

@Service
public class ParteServiceImpl implements ParteService {

	@Autowired
	private ParteRepository parteRepository;
	
	@Override
	public Parte recuperarProcessosPorIdProcessoParteLegacy(Long idProcessoParteLegacy) {
		return 	parteRepository.findByIdProcessoParteLegacy(idProcessoParteLegacy);
	}

	@Override
	public List<Parte> recuperarPartePorNumeroProcesso(String numeroProcesso) {
		return parteRepository.findByProcessoNrProcesso(numeroProcesso);
	}

	@Override
	public List<Parte> recuperarParte(String numeroProcesso, Long idProcessoParteLegacy, Long idPessoaLegacy) {
		return parteRepository.findByProcessoNrProcessoAndIdProcessoParteLegacyAndIdPessoaLegacy(numeroProcesso, idProcessoParteLegacy, idPessoaLegacy);
	}

	@Override
	public Parte inserir(Parte parte) {
		return parteRepository.save(parte);
	}


}
