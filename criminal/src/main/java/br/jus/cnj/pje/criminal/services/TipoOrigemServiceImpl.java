package br.jus.cnj.pje.criminal.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import br.jus.cnj.pje.criminal.base.AbstractBaseService;
import br.jus.cnj.pje.criminal.error.CriminalBusinessException;
import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.TipoOrigemRepository;
import br.jus.cnj.pje.criminal.util.PapeisUtil;
import br.jus.cnj.pje.criminal.util.PjeLegacyClientUtil;
import br.jus.cnj.pje.pjecommons.model.enums.TribunalEnum;

@Service
public class TipoOrigemServiceImpl extends AbstractBaseService<TipoOrigem, Long> implements TipoOrigemService {

	@Autowired
	private TipoOrigemRepository tipoOrigemRepository;  
	
	@Autowired
	private PjeLegacyClientUtil pjeLegacyClientUtil;
	
	
	@PreAuthorize(PapeisUtil.HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public TipoOrigem criarTipoOrigem(TipoOrigem tipoOrigem) throws CriminalBusinessException{
		TipoOrigem ret = null;
		
		if(pjeLegacyClientUtil.recuperarJTR().equals(TribunalEnum.CNJ.getJtr())) {
			ret = tipoOrigemRepository.save(tipoOrigem);
		} else {
			throw new CriminalBusinessException(CriminalBusinessException.RESTRICAO_ACESSO_CNJ);
		}
		return ret;
	}

	@Override
	public List<TipoOrigem> recuperarTiposOrigem() {
		TipoOrigem tipoOrigem = new TipoOrigem();
		tipoOrigem.setAtivo(true);
		
		return tipoOrigemRepository.findAll(this.getExemploTipoOrigem(tipoOrigem));
	}

	@Override
	public TipoOrigem recuperarTipoOrigem(Long idTipoOrigem){
		Optional<TipoOrigem> optional = tipoOrigemRepository.getById(idTipoOrigem);
		return optional.isPresent() ? optional.get() : null;
	}

	@PreAuthorize(PapeisUtil.HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public TipoOrigem alterarTipoOrigem(TipoOrigem tipoOrigem) throws CriminalBusinessException {
		TipoOrigem newValue = null;

		if(pjeLegacyClientUtil.recuperarJTR().equals(TribunalEnum.CNJ.getJtr())) {
			if(tipoOrigem != null && tipoOrigem.getId() != null){
				try{
					this.tipoOrigemRepository.getOne(tipoOrigem.getId());
					
					newValue = this.tipoOrigemRepository.save(tipoOrigem);
				} catch(EntityNotFoundException e){
					newValue = null;
				}
			}
		} else {
			throw new CriminalBusinessException(CriminalBusinessException.RESTRICAO_ACESSO_CNJ);
		}

		return newValue;
	}

	@PreAuthorize(PapeisUtil.HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public TipoOrigem inativarTipoOrigem(TipoOrigem tipoOrigem) throws CriminalBusinessException {
		tipoOrigem.setAtivo(false);
		return this.alterarTipoOrigem(tipoOrigem);
	}

	@Override
	public Page<TipoOrigem> recuperarTiposOrigemPorPagina(TipoOrigem tipoOrigem, Pageable pagina) {
		return tipoOrigemRepository.findAll(this.getExemploTipoOrigem(tipoOrigem), pagina);
	}
	
	@Override
	public Page<TipoOrigem> recuperarTiposOrigemPorPagina(Pageable pagina) {
		return tipoOrigemRepository.findAll(pagina);
	}
	
	@Override
	public Long countTipoOrigem(TipoOrigem tipoOrigem) {
		return tipoOrigemRepository.count(this.getExemploTipoOrigem(tipoOrigem));
	}
	
	@Override
	public Long countTipoOrigem() {
		return this.tipoOrigemRepository.count();
	}
	
	private Example<TipoOrigem> getExemploTipoOrigem(TipoOrigem tipoOrigem) throws NoSuchElementException{
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(StringMatcher.CONTAINING);
		Example<TipoOrigem> exemploTipoOrigem = Example.of(tipoOrigem, matcher);
		
		return exemploTipoOrigem;
	}

	@Override
	protected CustomRepository<TipoOrigem, Long> getRepository() {
		return this.tipoOrigemRepository;
	}
}
