package br.jus.cnj.pje.criminal.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.base.AbstractBaseService;
import br.jus.cnj.pje.criminal.model.entities.Dispositivo;
import br.jus.cnj.pje.criminal.model.entities.Norma;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.DispositivoRepository;

@Service
public class DispositivoServiceImpl extends AbstractBaseService<Dispositivo, Long> implements DispositivoService{
	
	@Autowired
	private DispositivoRepository repository;

	@Override
	public List<Dispositivo> recuperarDispositivosPorNorma(Norma norma) {
		return repository.findByNormaOrderByTexto(norma);
	}

	@Override
	public List<Dispositivo> recuperarDispositivosPorIdNorma(Long idNorma) {
		return repository.findByNormaIdAndAtivoTrueOrderByDispositivoPaiAscIdentificadorAsc(idNorma);
	}

	@Override
	public List<Dispositivo> recuperarDispositivosPorIdNormaEhTipo(Long idNorma, Long idTipo) {
		return repository.findByNormaIdAndTipoDispositivoIdAndAtivoTrue(idNorma, idTipo);
	}

	@Override
	public List<Dispositivo> recuperarDispositivosPorIdNormaEhTipoEhTexto(Long idNorma, Long idTipo, String texto) {
		return repository.findByNormaIdAndTipoDispositivoIdAndIdentificadorAndAtivoTrue(idNorma, idTipo, texto);
	}
	
	@Override
	public Dispositivo recuperarDispositivoPorId(Long idDispositivo) {
		return repository.getById(idDispositivo).get();
	}
	
	@Override
	public String getTextoFinalDispositivo(Dispositivo dispositivo) {
		String texto = new String("");
		
		if(dispositivo.getDispositivoPai() != null) {
			texto = this.getTextoFinalDispositivo(dispositivo.getDispositivoPai()) + ", ";
		}
		
		texto = texto + dispositivo.getSimbolo() + " " + dispositivo.getIdentificador();
		
		return texto;
	}

	@Override
	public List<Dispositivo> recuperarDispositivosPorIdDispositivoPai(Long idDispositivoPai) {
		return repository.findByDispositivoPaiIdAndAtivoTrue(idDispositivoPai);
	}

	@Override
	protected CustomRepository<Dispositivo, Long> getRepository() {
		return this.repository;
	}
	
	public Dispositivo criarDispositivo(@Valid Dispositivo dispositivo) {
		return repository.save(dispositivo);
	}

	@Override
	public Long countDispositivos() {
		return repository.count();
	}

	@Override
	public Long countDispositivos(Dispositivo dispositivo) {
		return repository.count(getExemploDispositivo(dispositivo));
	}

	@Override
	public Page<Dispositivo> recuperarDispositivosPorPagina(Dispositivo dispositivo, Pageable pagina) {
		return repository.findAll(getExemploDispositivo(dispositivo), pagina);
	}

	@Override
	public Page<Dispositivo> recuperarDispositivosPorPagina(Pageable pagina) {
		return repository.findAll(pagina);
	}

	@Override
	public Dispositivo alterarDispositivo(@Valid Dispositivo dispositivo) {
		return repository.save(dispositivo);
	}

	@Override
	public Dispositivo inativarDispositivo(Dispositivo dispositivoAlterar) {
		dispositivoAlterar.setAtivo(false);
		return alterarDispositivo(dispositivoAlterar);
	}
	
	private Example<Dispositivo> getExemploDispositivo(Dispositivo dispositivo) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
				.withStringMatcher(StringMatcher.CONTAINING);
		return Example.of(dispositivo, matcher);
	}
}
