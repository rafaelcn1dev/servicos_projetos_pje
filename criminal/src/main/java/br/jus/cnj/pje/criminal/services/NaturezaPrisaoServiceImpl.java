package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.model.entities.NaturezaPrisao;
import br.jus.cnj.pje.criminal.repositories.NaturezaPrisaoRepository;

@Service
public class NaturezaPrisaoServiceImpl implements NaturezaPrisaoService {

	@Autowired
	private NaturezaPrisaoRepository naturezaPrisaoRepository;  
	
	@Override
	public NaturezaPrisao criarNaturezaPrisao(NaturezaPrisao naturezaPrisao) {
		return naturezaPrisaoRepository.save(naturezaPrisao);
	}

	@Override
	public List<NaturezaPrisao> recuperarNaturezasPrisao() {
		return naturezaPrisaoRepository.findAll();
	}

	@Override
	public NaturezaPrisao recuperarNaturezaPrisao(Long idNaturezaPrisao) {
		return naturezaPrisaoRepository.getById(idNaturezaPrisao).get();
	}

	@Override
	public NaturezaPrisao alterarNaturezaPrisao(NaturezaPrisao naturezaPrisao) {
		return naturezaPrisaoRepository.save(naturezaPrisao);
	}

	@Override
	public NaturezaPrisao inativarNaturezaPrisao(NaturezaPrisao naturezaPrisao) {
		naturezaPrisao.setAtivo(false);
		return this.alterarNaturezaPrisao(naturezaPrisao);
	}

	@Override
	public Page<NaturezaPrisao> recuperarNaturezasPrisaoPorPagina(NaturezaPrisao naturezaPrisao, Pageable pagina) {
		return naturezaPrisaoRepository.findAll(this.getExemploNaturezaPrisao(naturezaPrisao), pagina);
	}
	
	@Override
	public Page<NaturezaPrisao> recuperarNaturezasPrisaoPorPagina(Pageable pagina) {
		return naturezaPrisaoRepository.findAll(pagina);
	}
	
	@Override
	public Long countNaturezaPrisao(NaturezaPrisao naturezaPrisao) {
		return naturezaPrisaoRepository.count(this.getExemploNaturezaPrisao(naturezaPrisao));
	}
	
	@Override
	public Long countNaturezaPrisao() {
		return this.naturezaPrisaoRepository.count();
	}
	
	private Example<NaturezaPrisao> getExemploNaturezaPrisao(NaturezaPrisao naturezaPrisao){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(StringMatcher.CONTAINING);
		Example<NaturezaPrisao> exemploNaturezaPrisao = Example.of(naturezaPrisao, matcher);
		
		return exemploNaturezaPrisao;
	}

}
