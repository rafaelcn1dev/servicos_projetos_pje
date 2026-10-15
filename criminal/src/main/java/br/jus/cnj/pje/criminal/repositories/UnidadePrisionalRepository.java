package br.jus.cnj.pje.criminal.repositories;

import java.util.List;

import br.jus.cnj.pje.criminal.model.entities.UnidadePrisional;

public interface UnidadePrisionalRepository extends CustomRepository<UnidadePrisional, Long>{
	public List<UnidadePrisional> findByMunicipioUf(String uf);
}
