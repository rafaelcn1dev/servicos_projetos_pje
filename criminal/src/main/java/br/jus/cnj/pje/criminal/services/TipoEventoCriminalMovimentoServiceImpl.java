package br.jus.cnj.pje.criminal.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminalMovimento;
import br.jus.cnj.pje.criminal.repositories.TipoEventoCriminalMovimentoRepository;

@Service
public class TipoEventoCriminalMovimentoServiceImpl implements TipoEventoCriminalMovimentoService {

	@Autowired
	private TipoEventoCriminalMovimentoRepository repository;
	
	@Override
	public List<TipoEventoCriminalMovimento> recuperarPorCodigoMovimento(String codMovimento) {
		return repository.findByCodMovimento(codMovimento);
	}

	@Override
	public TipoEventoCriminalMovimento criarTipoEventoCriminalMovimento(@Valid TipoEventoCriminalMovimento tipoEventoCriminalMovimento) {
		return repository.save(tipoEventoCriminalMovimento);
	}

	@Override
	public void removerTipoEventoCriminalMovimento(TipoEventoCriminalMovimento tipoEventoCriminalMovimento) {
		repository.delete(tipoEventoCriminalMovimento);
	}

	@Override
	public TipoEventoCriminalMovimento recuperarTipoEventoCriminalMovimentoPorId(Integer idTipoEventoCriminalMovimento) {
		return repository.getById(idTipoEventoCriminalMovimento).get();
	}

}
