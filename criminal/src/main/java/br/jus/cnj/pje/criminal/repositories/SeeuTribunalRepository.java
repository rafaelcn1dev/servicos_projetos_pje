package br.jus.cnj.pje.criminal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.cnj.pje.criminal.model.entities.SeeuTribunal;

public interface SeeuTribunalRepository extends JpaRepository<SeeuTribunal, Integer>{
	public SeeuTribunal findByCodigoTribunal(String codigoTribunal);
}
