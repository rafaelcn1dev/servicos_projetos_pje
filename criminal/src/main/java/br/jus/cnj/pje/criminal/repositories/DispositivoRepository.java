package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.jus.cnj.pje.criminal.model.entities.Dispositivo;
import br.jus.cnj.pje.criminal.model.entities.Norma;

public interface DispositivoRepository extends CustomRepository<Dispositivo, Long>{
	public List<Dispositivo> findByNormaNumeroNormaAndTipoDispositivoIdAndAtivoTrue(String numero, Long idD);
	public List<Dispositivo> findByNormaIdAndTipoDispositivoIdAndAtivoTrue(Long id, Long idD);
	public List<Dispositivo> findByNormaIdAndTipoDispositivoIdAndIdentificadorAndAtivoTrue(Long idNorma, Long idTipo, String texto);
	public List<Dispositivo> findByNormaNumeroNormaAndAtivoTrue(String numero);
	public List<Dispositivo> findByNormaIdAndAtivoTrue(Long id);
	public List<Dispositivo> findByNormaNumeroNormaAndAtivoTrueOrderById(Long id);
	public List<Dispositivo> findByNormaNumeroNormaAndAtivoTrueOrderByDispositivoPaiAscIdentificadorAsc(Long id);
	@Query(value = "SELECT d.* FROM core.tb_dispositivo d WHERE d.id_norma = ?1 and d.in_ativo = true order by d.id_dispositivo_pai asc, substring(d.ds_identificador, '^[0-9]+')\\:\\:int asc, substring(d.ds_identificador, '[^0-9_].*$') asc", nativeQuery=true)
	public List<Dispositivo> findByNormaIdAndAtivoTrueOrderByDispositivoPaiAscIdentificadorAsc(Long id);
	public List<Dispositivo> findByNormaOrderByTexto(Norma norma);
	public List<Dispositivo> findByDispositivoPaiIdAndAtivoTrue(Long idDispositivoPai);
}
