package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.jus.cnj.pje.criminal.enums.TipoEventoCriminalEnum;
import br.jus.cnj.pje.criminal.enums.TipoProcessoEnum;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;

public interface TipoEventoCriminalRepository extends CustomRepository<TipoEventoCriminal, Long> {
	
	@Query("select tic from TipoEventoCriminal tic join tic.listTipoEventoCriminalMovimento te where te.codMovimento = :codigoMovimento")
	public List<TipoEventoCriminal> findByTipoEventoCriminalCodigoMovimento(@Param("codigoMovimento") String codigoMovimento);

	@Query("select distinct te.codMovimento from TipoEventoCriminal tic join tic.listTipoEventoCriminalMovimento te")
	public List<String> findAllTiposEventoCriminalMovimento();

	public TipoEventoCriminal findByCodTipoIc(TipoEventoCriminalEnum ind);
	
	public List<TipoEventoCriminal> findByAtivoTrue();

	public List<TipoEventoCriminal> findByTipoProcessoIn(List<TipoProcessoEnum> tiposProcesso);
	
	public List<TipoEventoCriminal> findByAtivoTrueAndTipoProcessoIn(List<TipoProcessoEnum> tiposProcesso);
	
	public List<TipoEventoCriminal> findByAtivoFalseAndTipoProcessoIn(List<TipoProcessoEnum> tiposProcesso);

}
