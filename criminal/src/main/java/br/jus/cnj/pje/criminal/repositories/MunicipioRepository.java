package br.jus.cnj.pje.criminal.repositories;

import br.jus.cnj.pje.criminal.model.entities.Municipio;

public interface MunicipioRepository extends CustomRepository<Municipio, Integer> {

	Municipio findByCodigoIbge(String codigoIbge);

	Municipio findByMunicipioAndUf(String municipio, String uf);

}
