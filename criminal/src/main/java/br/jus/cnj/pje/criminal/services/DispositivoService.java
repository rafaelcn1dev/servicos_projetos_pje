package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.base.BaseService;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.model.entities.Dispositivo;
import br.jus.cnj.pje.criminal.model.entities.Norma;

public interface DispositivoService extends BaseService<Dispositivo, Long>{
	public List<Dispositivo> recuperarDispositivosPorNorma(Norma norma);
	public List<Dispositivo> recuperarDispositivosPorIdNorma(Long idNorma);
	public List<Dispositivo> recuperarDispositivosPorIdNormaEhTipo(Long idNorma, Long idTipo);
	public List<Dispositivo> recuperarDispositivosPorIdNormaEhTipoEhTexto(Long idNorma, Long idTipo, String texto);
	public List<Dispositivo> recuperarDispositivosPorIdDispositivoPai(Long idDispositivoPai);
	public Dispositivo recuperarDispositivoPorId(Long idDispositivo);
	public String getTextoFinalDispositivo(Dispositivo dispositivo);
	public Dispositivo criarDispositivo(@Valid Dispositivo dispositivo);
	public Long countDispositivos();
	public Long countDispositivos(Dispositivo dispositivo);
	public Page<Dispositivo> recuperarDispositivosPorPagina(Dispositivo dispositivo, Pageable pagina);
	public Page<Dispositivo> recuperarDispositivosPorPagina(Pageable pagina);
	public Dispositivo alterarDispositivo(@Valid Dispositivo dispositivo);
	public Dispositivo inativarDispositivo(Dispositivo dispositivoAlterar);
	
}
