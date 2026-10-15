package br.jus.cnj.pje.criminal.controllers.v1;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.model.entities.OrgaoProcedimentoOriginario;
import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;
import br.jus.cnj.pje.criminal.services.OrgaoProcedimentoOriginarioService;
import br.jus.cnj.pje.criminal.services.TipoOrigemService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrgaoProcedimentoOriginarioRestController {

	@Autowired
	private OrgaoProcedimentoOriginarioService orgaoProcedimentoOriginarioService;

	@Autowired
	private TipoOrigemService tipoOrigemService;

	@Transactional(rollbackFor = Exception.class)
	@PostMapping(path = "/orgaosProcedimentoOriginario", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public OrgaoProcedimentoOriginario criarOrgaoProcedimentoOriginario(
			@Valid @RequestBody OrgaoProcedimentoOriginario orgaoProcedimentoOriginario) {
		return orgaoProcedimentoOriginarioService.saveOrgaoProcedimentoOriginario(orgaoProcedimentoOriginario);
	}

	@Transactional(rollbackFor = Exception.class)
	@PutMapping(path = "/orgaosProcedimentoOriginario", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public OrgaoProcedimentoOriginario alterarOrgaoProcedimentoOriginario(
			@Valid @RequestBody OrgaoProcedimentoOriginario orgaoProcedimentoOriginario) {
		if (orgaoProcedimentoOriginario.getId() != null) {
			OrgaoProcedimentoOriginario orgaoProcedimentoOriginarioAlterar = orgaoProcedimentoOriginarioService
					.recuperarOrgaoProcedimentoOriginario(orgaoProcedimentoOriginario.getId());
			if (orgaoProcedimentoOriginarioAlterar != null) {
				return orgaoProcedimentoOriginarioService.saveOrgaoProcedimentoOriginario(orgaoProcedimentoOriginario);
			}
		}
		return new OrgaoProcedimentoOriginario();
	}

	@PostMapping(path = "/orgaosProcedimentoOriginario/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginario(Pageable pagina) {
		return orgaoProcedimentoOriginarioService.recuperarOrgaosProcedimentoOriginario(pagina);
	}

	@PostMapping(path = "/orgaosProcedimentoOriginario/pesquisar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Page<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginario(Pageable pagina,
			@RequestBody OrgaoProcedimentoOriginario opo) {
		return orgaoProcedimentoOriginarioService.recuperarOrgaosProcedimentoOriginario(opo, pagina);
	}

	@GetMapping(path = "/orgaosProcedimentoOriginario", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginario() {
		return orgaoProcedimentoOriginarioService.recuperarOrgaoProcedimentoOriginario();
	}

	@GetMapping(path = "/orgaosProcedimentoOriginario/nome/{nomeOrgaoProcedimento}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginarioPorNome(
			@PathVariable("nomeOrgaoProcedimento") String nomeOrgaoProcedimento) {
		return orgaoProcedimentoOriginarioService.recuperarOrgaosProcedimentoOriginarioPorNome(nomeOrgaoProcedimento);
	}

	@GetMapping(path = "/orgaosProcedimentoOriginario/{idOrgaoProcedimentoOriginario}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public OrgaoProcedimentoOriginario recuperarOrgaoProcedimentoOriginario(
			@PathVariable("idOrgaoProcedimentoOriginario") Long idOrgaoProcedimentoOriginario) {
		return orgaoProcedimentoOriginarioService.recuperarOrgaoProcedimentoOriginario(idOrgaoProcedimentoOriginario);
	}

	@GetMapping(path = "/tiposOrigem/{idTipoOrigem}/orgaosProcedimentoOriginario", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<OrgaoProcedimentoOriginario> recuperarOrgaosProcedimentoOriginarioPorTipoOrigem(
			@PathVariable("idTipoOrigem") Long idTipoOrigem) {
		TipoOrigem tipoOrigem = tipoOrigemService.recuperarTipoOrigem(idTipoOrigem);

		if (tipoOrigem != null) {
			return orgaoProcedimentoOriginarioService.recuperarOrgaosProcedimentoOriginarioPorTipoOrigem(tipoOrigem);
		}
		return new ArrayList<>();
	}

	@DeleteMapping(path = "/orgaosProcedimentoOriginario/{idOrgaoProcedimentoOriginario}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public OrgaoProcedimentoOriginario inativarOrgaoProcedimentoOriginario(
			@PathVariable("idOrgaoProcedimentoOriginario") Long idOrgaoProcedimentoOriginario) {
		OrgaoProcedimentoOriginario orgaoProcedimentoOriginario = orgaoProcedimentoOriginarioService
				.recuperarOrgaoProcedimentoOriginario(idOrgaoProcedimentoOriginario);
		if (orgaoProcedimentoOriginario != null) {
			return orgaoProcedimentoOriginarioService.inativarOrgaoProcedimentoOriginario(orgaoProcedimentoOriginario);
		}
		return new OrgaoProcedimentoOriginario();
	}

	@GetMapping(path = "/orgaosProcedimentoOriginario/count", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countOrgaoProcedimentoOriginario() {
		return this.orgaoProcedimentoOriginarioService.countOrgaoProcedimentoOriginario();
	}

	@PostMapping(path = "/orgaosProcedimentoOriginario/count", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public Long countOrgaoProcedimentoOriginario(@RequestBody OrgaoProcedimentoOriginario orgaoProcedimentoOriginario) {
		return this.orgaoProcedimentoOriginarioService.countOrgaoProcedimentoOriginario(orgaoProcedimentoOriginario);
	}

	@GetMapping(path = "/orgaosProcedimentoOriginario/{idTipoOrigem}/{uf}", produces = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public List<OrgaoProcedimentoOriginario> recuperarProcessoProcedimentoOrigemPorTipoOrigemAndUf(
			@PathVariable("idTipoOrigem") Long idTipoOrigem, @PathVariable("uf") String uf) {
		return orgaoProcedimentoOriginarioService.recuperarOrgaosPorTipoOrigemAndUf(idTipoOrigem, uf);
	}
}
