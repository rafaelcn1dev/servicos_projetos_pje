package br.jus.cnj.pje.criminal.services;

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
import br.jus.cnj.pje.criminal.model.entities.Municipio;
import br.jus.cnj.pje.criminal.model.entities.OrgaoProcedimentoOriginario;
import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;
import br.jus.cnj.pje.criminal.repositories.CustomRepository;
import br.jus.cnj.pje.criminal.repositories.MunicipioRepository;
import br.jus.cnj.pje.criminal.repositories.OrgaoProcedimentoOriginarioRepository;
import br.jus.cnj.pje.criminal.util.PapeisUtil;

@Service
public class OrgaoProcedimentoOriginarioServiceImpl extends AbstractBaseService<OrgaoProcedimentoOriginario, Long> implements OrgaoProcedimentoOriginarioService {

	@Autowired
	private OrgaoProcedimentoOriginarioRepository orgaoProcedimentoOriginarioRepository;

	@Autowired
	private MunicipioRepository municipioRepository;
	
	@PreAuthorize(PapeisUtil.HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public OrgaoProcedimentoOriginario saveOrgaoProcedimentoOriginario(
			OrgaoProcedimentoOriginario orgaoProcedimentoOriginario) {
		Municipio municipio = null;
		if (orgaoProcedimentoOriginario.getMunicipio().getCodigoIbge() != null) {
			municipio = municipioRepository
					.findByCodigoIbge(orgaoProcedimentoOriginario.getMunicipio().getCodigoIbge());
		}
		if (municipio == null) {
			municipio = municipioRepository.findByMunicipioAndUf(
					orgaoProcedimentoOriginario.getMunicipio().getMunicipio(),
					orgaoProcedimentoOriginario.getMunicipio().getUf());
			if (municipio == null) {
				municipio = municipioRepository.save(orgaoProcedimentoOriginario.getMunicipio());
				orgaoProcedimentoOriginario.setMunicipio(municipio);
			} else {
				orgaoProcedimentoOriginario.setMunicipio(municipio);
			}
		} else {
			orgaoProcedimentoOriginario.setMunicipio(municipio);
		}
		return orgaoProcedimentoOriginarioRepository.save(orgaoProcedimentoOriginario);
	}

	@Override
	public Page<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginario(Pageable pagina) {
		return orgaoProcedimentoOriginarioRepository.findAll(this.getExemploOrgaoProcedimentoOriginario(null), pagina);
	}

	@Override
	public Page<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginario(OrgaoProcedimentoOriginario opo,
			Pageable pagina) {
		return orgaoProcedimentoOriginarioRepository.findAll(this.getExemploOrgaoProcedimentoOriginario(opo), pagina);
	}

	@Override
	public List<OrgaoProcedimentoOriginario> recuperarOrgaoProcedimentoOriginario() {
		return orgaoProcedimentoOriginarioRepository.findAll(this.getExemploOrgaoProcedimentoOriginario(null));
	}

	@Override
	public OrgaoProcedimentoOriginario recuperarOrgaoProcedimentoOriginario(Long idOrgaoProcedimentoOriginario) {
		Optional<OrgaoProcedimentoOriginario> optional = orgaoProcedimentoOriginarioRepository.getById(idOrgaoProcedimentoOriginario);
		return optional.isPresent() ? optional.get() : null;
	}

	@PreAuthorize(PapeisUtil.HAS_AUTHORITY_ROLE_ADMIN)
	@Override
	public OrgaoProcedimentoOriginario inativarOrgaoProcedimentoOriginario(
			OrgaoProcedimentoOriginario orgaoProcedimentoOriginario) {
		orgaoProcedimentoOriginario.setAtivo(false);
		return saveOrgaoProcedimentoOriginario(orgaoProcedimentoOriginario);
	}

	@Override
	public List<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginarioPorNome(
			String nomeOrgaoProcedimento) {
		return orgaoProcedimentoOriginarioRepository.findByDsNomeOrgaoLikeIgnoreCase("%" + nomeOrgaoProcedimento + "%");

	}

	@Override
	public List<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginarioPorTipoOrigem(TipoOrigem tipoOrigem) {
		return orgaoProcedimentoOriginarioRepository.findByTipoOrigem(tipoOrigem);
	}

	@Override
	public Long countOrgaoProcedimentoOriginario(OrgaoProcedimentoOriginario orgaoProcedimentoOriginario) {
		return orgaoProcedimentoOriginarioRepository
				.count(getExemploOrgaoProcedimentoOriginario(orgaoProcedimentoOriginario));
	}

	@Override
	public Long countOrgaoProcedimentoOriginario() {
		return orgaoProcedimentoOriginarioRepository.count();
	}

	private Example<OrgaoProcedimentoOriginario> getExemploOrgaoProcedimentoOriginario(
			OrgaoProcedimentoOriginario opo) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
				.withStringMatcher(StringMatcher.CONTAINING);
		if(opo == null) {
			opo = new OrgaoProcedimentoOriginario();
		}
		return Example.of(opo, matcher);
	}

	@Override
	public List<OrgaoProcedimentoOriginario> recuperarOrgaosPorTipoOrigemAndUf(Long idTipoOrigem, String uf) {
		return orgaoProcedimentoOriginarioRepository.findByTipoOrigemAndUf(idTipoOrigem, uf);
	}

	@Override
	protected CustomRepository<OrgaoProcedimentoOriginario, Long> getRepository() {
		return this.orgaoProcedimentoOriginarioRepository;
	}	

}
