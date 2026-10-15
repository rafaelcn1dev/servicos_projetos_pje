package br.jus.cnj.pje.criminal.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.jus.cnj.pje.criminal.error.CriminalBusinessException;
import br.jus.cnj.pje.criminal.error.NumeroProcessoInvalidoException;
import br.jus.cnj.pje.criminal.error.PjeEntityNotFoundException;
import br.jus.cnj.pje.criminal.model.entities.Municipio;
import br.jus.cnj.pje.criminal.model.entities.OrgaoProcedimentoOriginario;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.model.entities.ProcessoProcedimentoOrigem;
import br.jus.cnj.pje.criminal.model.entities.TipoOrigem;
import br.jus.cnj.pje.criminal.model.entities.TipoProcedimentoOrigem;
import br.jus.cnj.pje.criminal.repositories.MunicipioRepository;
import br.jus.cnj.pje.criminal.repositories.ProcessoProcedimentoOrigemRepository;
import br.jus.cnj.pje.criminal.repositories.ProcessoRepository;
import br.jus.cnj.pje.criminal.util.PjeLegacyClientUtil;
import br.jus.cnj.pje.criminal.util.StringUtil;
import br.jus.cnj.pje.pjecommons.utils.NumeroProcessoUtil;

@Service
public class ProcessoServiceImpl implements ProcessoService {
	
	private static final int TAMANHO_MINIMO_NUMERO_PROCESSO_PARA_PESQUISAS_POR_PARTE = 5;

	private static final Logger logger = LoggerFactory.getLogger(ProcessoServiceImpl.class);

	@Autowired
	private ProcessoRepository processoRepository;

	@Autowired
	private ProcessoProcedimentoOrigemRepository processoProcedimentoOrigemRepository;
	
	@Autowired
	private ProcessoProcedimentoOrigemService processoProcedimentoOrigemService;

	@Autowired
	private MunicipioRepository municipioRepository;
	
	@Autowired
	private OrgaoProcedimentoOriginarioService orgaoProcedimentoOriginarioService;

	@Autowired
	private TipoOrigemService tipoOrigemService;

	@Autowired
	private TipoProcedimentoOrigemService tipoProcedimentoOrigemService;
	
	@Autowired
	private UsuarioAutenticadoService usuarioAutenticadoService;
	
	
	@Autowired(required = false)
	private HttpServletRequest request;	
	
	@Autowired
	private PjeLegacyClientUtil pjeLegacyClientUtil;

	@Value("${criminal.security.bypass-enabled:false}")
	private boolean bypassSegurancaHabilitado;

	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Processo criarNovoProcesso(Processo processo) throws CriminalBusinessException {
		preencherMunicipio(processo);
		if(StringUtils.isEmpty(processo.getCodigoTribunal()) && request != null) {
			processo.setCodigoTribunal(this.recuperarOrigemProcesso(processo.getNrProcesso()));
		}
		Processo novoProcesso = processoRepository.save(processo);
		logger.debug("Novo processo criado. ID {}", novoProcesso.getId());

		if (novoProcesso.getProcessoProcedimentoOrigemList() != null && !novoProcesso.getProcessoProcedimentoOrigemList().isEmpty()) {
			for (ProcessoProcedimentoOrigem ppo : novoProcesso.getProcessoProcedimentoOrigemList()) {
				if (ppo.getProcessoProcedimentoOrigemRetombado() != null) {
					ppo.getProcessoProcedimentoOrigemRetombado().setProcesso(novoProcesso);
				}
				ppo.setProcesso(novoProcesso);
				processoProcedimentoOrigemRepository.save(ppo);
			}
		}
		
		return novoProcesso;
	}

	@Override
	public List<Processo> recuperarProcessos() {
		List<Processo> listaProcessos = processoRepository.findAll();
		if (listaProcessos == null || listaProcessos.isEmpty()) {
			listaProcessos = new ArrayList<>();
		}
		return listaProcessos;
	}

	@Override
	public Processo recuperarProcessoPorId(Long idProcesso) {
		return processoRepository.getById(idProcesso).get();
	}

	@Override
	public Page<Processo> recuperarProcessosPorPagina(Pageable pageable) {
		return processoRepository.findAll(pageable);
	}

	@Override
	public List<Processo> recuperarProcessosPorNumero(String numeroProcesso) throws UnsupportedEncodingException {
		return this.recuperarProcessosPorNumero(numeroProcesso, this.usuarioAutenticadoService.usuarioSistema());		
	}

	public List<Processo> recuperarProcessosPorNumero(String numeroProcesso, boolean sistema) throws UnsupportedEncodingException {
		validarNumeroProcessoParaPesquisasPorParteDoNumero(numeroProcesso);
		List<Processo> listaProcessos = new ArrayList<Processo>();
		listaProcessos = processoRepository.findByNrProcessoLikeIgnoreCase("%" + numeroProcesso + "%");
		return usuarioPossuiPermissao(numeroProcesso, sistema) ? listaProcessos : null;
	}
	

	@Override
	public Processo recuperarProcessoPorNumero(String numeroProcesso) throws UnsupportedEncodingException {
		return this.recuperarProcessoPorNumero(numeroProcesso, this.usuarioAutenticadoService.usuarioSistema());
	}

	@Override
	@Transactional
	public Processo recuperarProcessoPorNumero(String numeroProcesso, boolean sistema) throws UnsupportedEncodingException {
		Processo processo = processoRepository.findByNrProcesso(numeroProcesso);

		if (processo != null && processo.getProcessoProcedimentoOrigemList() != null) {
			processo.getProcessoProcedimentoOrigemList().removeIf(p -> !p.getAtivo());			
		}

		if (processo != null) {			
			boolean possuiPermissao = usuarioPossuiPermissao(numeroProcesso, sistema);
			logger.debug("Processo {} encontrado na base da dados. Usuário {} permissão para acessar.", numeroProcesso, possuiPermissao ? "possui" : "não possui");

			return possuiPermissao ? processo : null;
		}

		logger.debug("Processo {} não encontrado na base de dados.", numeroProcesso);
		return processo;
	}

	private boolean usuarioPossuiPermissao(String numeroProcesso, boolean sistema) throws UnsupportedEncodingException {
		if (isModoDesenvolvimentoAtivo()) {
			return true;
		}

		return sistema 
			|| (this.pjeLegacyClientUtil
					.createPjeLegacyClient(request)
					.validarAcessoAoProcesso(URLEncoder.encode(numeroProcesso, "UTF-8")) > 0);
	}

	private boolean isModoDesenvolvimentoAtivo() {
		return this.bypassSegurancaHabilitado;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Processo updateProcesso(Processo processo) {
		preencherMunicipio(processo);
		return processoRepository.save(processo);
	}

	private void preencherMunicipio(Processo processo) {
		Municipio municipio = null;
		if (processo.getMunicipio().getCodigoIbge() != null) {
			municipio = municipioRepository.findByCodigoIbge(processo.getMunicipio().getCodigoIbge());
		}
		if (municipio == null) {
			municipio = municipioRepository.findByMunicipioAndUf(processo.getMunicipio().getMunicipio(), processo.getMunicipio().getUf());
			if (municipio == null) {
				municipio = municipioRepository.save(processo.getMunicipio());
			}
		}
		processo.setMunicipio(municipio);
	}
	
	public List<String> recuperarNumeroProcessoPorProcedimentoOrigem(ProcessoProcedimentoOrigem processoProcedimentoOrigem) {
		List<ProcessoProcedimentoOrigem>  procProcedimentoOrigemList = processoProcedimentoOrigemService.recuperarProcessoProcedimentoOrigem(processoProcedimentoOrigem);
		List<String> numeroProcessoList = new ArrayList<>();
		procProcedimentoOrigemList.forEach(p -> numeroProcessoList.add(p.getProcesso().getNrProcesso()));

		return numeroProcessoList;
	}
	
	private String recuperarOrigemProcesso(String numeroProcesso) throws CriminalBusinessException {
		if(!StringUtils.isEmpty(numeroProcesso) && NumeroProcessoUtil.numeroProcessoValido(numeroProcesso, false)) {
			return NumeroProcessoUtil.getJtr(numeroProcesso);			
		}
		throw new CriminalBusinessException("O número do processo " + numeroProcesso + " é inválido");
	}
	
	public void validarNovoProcesso(Processo processo) {
		if (!processo.getProcessoProcedimentoOrigemList().isEmpty()) {
			for (ProcessoProcedimentoOrigem pco : processo.getProcessoProcedimentoOrigemList()) {
				List<String> camposObrigatoriosNaoInformados = new ArrayList<>();

				OrgaoProcedimentoOriginario orgaoProcedimentOrigem = pco.getOrgaoProcedimentoOriginario();
				if (orgaoProcedimentOrigem != null && orgaoProcedimentOrigem.getId() != null) {
					OrgaoProcedimentoOriginario orgaoProcedimentOrigemEncontrado = orgaoProcedimentoOriginarioService.recuperarOrgaoProcedimentoOriginario(orgaoProcedimentOrigem.getId());
					if(orgaoProcedimentOrigemEncontrado == null) {
						throw new PjeEntityNotFoundException(OrgaoProcedimentoOriginario.class, pco.getOrgaoProcedimentoOriginario().getId());
					} else {
						pco.setOrgaoProcedimentoOriginario(orgaoProcedimentOrigemEncontrado);
					}
				} else {
					pco.setOrgaoProcedimentoOriginario(null);
				}

				if (pco.getTipoOrigem() != null && pco.getTipoOrigem().getId() != null) {
					TipoOrigem tipoOrigem = tipoOrigemService.recuperarTipoOrigem(pco.getTipoOrigem().getId());

					if(tipoOrigem == null) {
						throw new PjeEntityNotFoundException(TipoOrigem.class, pco.getTipoOrigem().getId());
					} else {
						if(BooleanUtils.isFalse(tipoOrigem.getAtivo())) {
							throw new RuntimeException("Tipo origem inativo: " + pco.getTipoOrigem().getId() + ".");
						}else {
							pco.setTipoOrigem(tipoOrigem);					
						}
					}

					if (BooleanUtils.isTrue(pco.getTipoOrigem().getInObrigatorioNumeroOrigem())) {
						if (pco.getAno() == null) {
							camposObrigatoriosNaoInformados.add("Ano");
						}

						if (pco.getNumero() == null) {
							camposObrigatoriosNaoInformados.add("Número");
						}

						if (pco.getDataInstauracao() == null) {
							camposObrigatoriosNaoInformados.add("Data instauração");
						}

						if (pco.getDataLavratura() == null) {
							camposObrigatoriosNaoInformados.add("Data lavratura");
						}

						if (pco.getUf() == null) {
							camposObrigatoriosNaoInformados.add("Uf");
						}

						if (orgaoProcedimentOrigem == null) {
							camposObrigatoriosNaoInformados.add("Órgão procedimento de origem");
						}
					}
				} else {
					camposObrigatoriosNaoInformados.add("Tipo Origem");
				}

				validarLocalDoFato(processo, camposObrigatoriosNaoInformados);

				if (!camposObrigatoriosNaoInformados.isEmpty()) {
					throw new RuntimeException("Campos obrigatórios não informados: " + StringUtil.listToString(camposObrigatoriosNaoInformados));
				}

				if (pco.getTipoProcedimentoOrigem() != null && pco.getTipoProcedimentoOrigem().getId() != null) {
					TipoProcedimentoOrigem tipoProcedimentoOrigem = tipoProcedimentoOrigemService.recuperarTipoProcedimentoOrigemPorId(pco.getTipoProcedimentoOrigem().getId());
					if(tipoProcedimentoOrigem == null) {
						throw new PjeEntityNotFoundException(TipoProcedimentoOrigem.class, pco.getTipoProcedimentoOrigem().getId());
					} else {
						pco.setTipoProcedimentoOrigem(tipoProcedimentoOrigem);
					}
				}
			}
		} else {
			logger.debug("Processo {} não possui procedimento de origem.", processo.getNrProcesso());
		}
	}

	private void validarLocalDoFato(Processo processo, List<String> camposObrigatoriosNaoInformados) {
		if (processo.getDtLocalFato() == null) {
			camposObrigatoriosNaoInformados.add("Data do fato");
		}

		if (processo.getMunicipio() == null || processo.getMunicipio().getUf() == null) {
			camposObrigatoriosNaoInformados.add("UF/Município");
		}

		if (StringUtil.isEmpty(processo.getDsLocalFato())) {
			camposObrigatoriosNaoInformados.add("Local do Fato");
		}
	}
	
	private void validarNumeroProcessoParaPesquisasPorParteDoNumero(String numeroProcesso)
		 {
		if (StringUtil.isEmpty(numeroProcesso)
				|| numeroProcesso.length() < TAMANHO_MINIMO_NUMERO_PROCESSO_PARA_PESQUISAS_POR_PARTE) {
			throw new NumeroProcessoInvalidoException("Número do processo inválido. Número de caracteres mínimos: "
					+ TAMANHO_MINIMO_NUMERO_PROCESSO_PARA_PESQUISAS_POR_PARTE);
		}
	}
	
}
