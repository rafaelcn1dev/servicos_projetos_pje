package br.jus.cnj.pje.criminal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import br.jus.cnj.pje.criminal.model.entities.TipoProcedimentoOrigem;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.TipoProcedimentoOrigemRepository;
import br.jus.cnj.pje.criminal.util.PapeisUtil;
import br.jus.cnj.pje.criminal.util.PjeLegacyClientUtil;
import br.jus.cnj.pje.pjecommons.model.enums.TribunalEnum;

@Service
public class TipoProcedimentoOrigemServiceImpl extends AbstractBaseService<TipoProcedimentoOrigem, Long> implements TipoProcedimentoOrigemService {

	@Autowired
	private TipoProcedimentoOrigemRepository tipoProcedimentoOrigemRepository;
	
	@Autowired
	private PjeLegacyClientUtil pjeLegacyClientUtil;

	@PreAuthorize(PapeisUtil.HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public TipoProcedimentoOrigem criarNovoTipoProcedimentoOrigem(TipoProcedimentoOrigem tipoProcedimentoOrigem) throws CriminalBusinessException {
	
		if(pjeLegacyClientUtil.recuperarJTR().equals(TribunalEnum.CNJ.getJtr())) {
			tipoProcedimentoOrigem = tipoProcedimentoOrigemRepository.save(tipoProcedimentoOrigem);
		} else {
			throw new CriminalBusinessException(CriminalBusinessException.RESTRICAO_ACESSO_CNJ);
		}
		return tipoProcedimentoOrigem;
	}

	@Override
	public List<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigem() {
		List<TipoProcedimentoOrigem> listaTiposProcedimentoOrigem = tipoProcedimentoOrigemRepository.findAll();
		if (listaTiposProcedimentoOrigem == null || listaTiposProcedimentoOrigem.isEmpty()) {
			listaTiposProcedimentoOrigem = new ArrayList<>();
		}
		return listaTiposProcedimentoOrigem;
	}

	@Override
	public TipoProcedimentoOrigem recuperarTipoProcedimentoOrigemPorId(Long idTipoProcedimentoOrigem) {
		Optional<TipoProcedimentoOrigem> optional = tipoProcedimentoOrigemRepository.getById(idTipoProcedimentoOrigem);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Page<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorPagina(Pageable pageable) {
		return tipoProcedimentoOrigemRepository.findAll(pageable);
	}
	
	@Override
	public Page<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorPagina(TipoProcedimentoOrigem tipoProcedimentoOrigem, Pageable pagina) {
		return tipoProcedimentoOrigemRepository.findAll(getExemploTipoProcedimentoOrigem(tipoProcedimentoOrigem), pagina);
	}
	
	@Override
	public List<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorNome(String tipoProcedimentoOrigem) {
		return this.tipoProcedimentoOrigemRepository
				.findByDsTipoProcedimentoLikeIgnoreCase("%" + tipoProcedimentoOrigem + "%");
	}

	@PreAuthorize(PapeisUtil.HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public TipoProcedimentoOrigem alterarTipoProcedimentoOrigem(TipoProcedimentoOrigem tipoProcedimentoOrigem) throws CriminalBusinessException{
		if(pjeLegacyClientUtil.recuperarJTR().equals(TribunalEnum.CNJ.getJtr())) {
			tipoProcedimentoOrigem = tipoProcedimentoOrigemRepository.save(tipoProcedimentoOrigem);
		} else {
			throw new CriminalBusinessException(CriminalBusinessException.RESTRICAO_ACESSO_CNJ);
		}
		return tipoProcedimentoOrigem;
	}

	@PreAuthorize(PapeisUtil.HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public TipoProcedimentoOrigem inativarTipoProcedimentoOrigem(TipoProcedimentoOrigem tipoProcedimentoOrigem) throws CriminalBusinessException{
		tipoProcedimentoOrigem.setAtivo(false);
		return alterarTipoProcedimentoOrigem(tipoProcedimentoOrigem);
	}

	@Override
	public List<TipoProcedimentoOrigem> recuperarTiposProcedimentoOrigemPorTipoOrigem(Long idTipoOrigem) {
		return tipoProcedimentoOrigemRepository.findByTipoOrigem(idTipoOrigem);
	}

	@Override
	public Long countTipoProcedimentoOrigem(TipoProcedimentoOrigem tipoProcedimentoOrigem) {
		return tipoProcedimentoOrigemRepository.count(getExemploTipoProcedimentoOrigem(tipoProcedimentoOrigem));
	}

	@Override
	public Long countTipoProcedimentoOrigem() {
		return tipoProcedimentoOrigemRepository.count();
	}
	
	
	private Example<TipoProcedimentoOrigem> getExemploTipoProcedimentoOrigem(TipoProcedimentoOrigem tipoProcedimentoOrigem){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(StringMatcher.CONTAINING);
		Example<TipoProcedimentoOrigem> exemploTipoProcedimentoOrigem = Example.of(tipoProcedimentoOrigem, matcher);
		
		return exemploTipoProcedimentoOrigem;
	}

	@Override
	protected CustomRepository<TipoProcedimentoOrigem, Long> getRepository() {
		return this.tipoProcedimentoOrigemRepository;
	}


}
