package br.jus.cnj.pje.criminal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.jus.cnj.pje.criminal.model.beans.CitacaoProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.DecisaoInstanciaSuperiorBean;
import br.jus.cnj.pje.criminal.model.beans.DesclassificacaoProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.EventoCriminalBean;
import br.jus.cnj.pje.criminal.model.beans.FugaBean;
import br.jus.cnj.pje.criminal.model.beans.IncidenciaPenalBean;
import br.jus.cnj.pje.criminal.model.beans.PrisaoBean;
import br.jus.cnj.pje.criminal.model.beans.RecebimentoAditamentoDenunciaBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaAbsolutoriaBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaCondenatoriaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaImpronunciaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaPronunciaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SolturaBean;
import br.jus.cnj.pje.criminal.model.beans.SuspensaoProcessoBean;
import br.jus.cnj.pje.criminal.model.entities.Dispositivo;
import br.jus.cnj.pje.criminal.model.entities.InformacaoCriminal;
import br.jus.cnj.pje.criminal.model.entities.MotivoPrisao;
import br.jus.cnj.pje.criminal.model.entities.MotivoSoltura;
import br.jus.cnj.pje.criminal.model.entities.Parte;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;
import br.jus.cnj.pje.criminal.model.entities.UnidadePrisional;
import br.jus.cnj.pje.criminal.repositories.DispositivoRepository;
import br.jus.cnj.pje.criminal.repositories.InformacaoCriminalRepository;
import br.jus.cnj.pje.criminal.util.PapeisUtil;

@Service
public class InformacaoCriminalServiceImpl implements InformacaoCriminalService{

	@Autowired
	private InformacaoCriminalRepository repository;
	
	@Autowired
	private DispositivoRepository dispostivoRepository;
	
	@Autowired
	private UnidadePrisionalService unidadePrisionalService;

	@Autowired
	private MotivoSolturaService motivoSolturaService;

	@Autowired
	private MotivoPrisaoService motivoPrisaoService;
	

	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public InformacaoCriminal criarNovaInformacaoCriminal(InformacaoCriminal informacaoCriminal) {
		return repository.save(informacaoCriminal);
	}

	@Override
	public List<InformacaoCriminal> criarNovasInformacoesCriminais(List<InformacaoCriminal> informacoesCriminais) {
		return repository.saveAll(informacoesCriminais);
	}

	@Override
	public List<PrisaoBean> recuperarPrisoesPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso,
			Long idProcessoParteLegacy) {
		List<PrisaoBean> listaPrisoes = new ArrayList<PrisaoBean>();
		
		List<InformacaoCriminal> listaIcs = this.repository.findByProcessoIdAndParteIdParteLegacy(idProcesso, idProcessoParteLegacy);
		
		if(!CollectionUtils.isEmpty(listaIcs)) {
			listaPrisoes = listaIcs.get(0).getConteudo().getPrisoes();
		}
		
		return listaPrisoes;
	}
	
	@Override
	public List<SuspensaoProcessoBean> recuperarSuspensoesProcessoPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso,
			Long idProcessoParteLegacy) {
		List<SuspensaoProcessoBean> listaSuspensoesProcesso = new ArrayList<SuspensaoProcessoBean>();
		List<InformacaoCriminal> listaIcs = this.repository.findByProcessoIdAndParteIdParteLegacy(idProcesso, idProcessoParteLegacy);
		if(!CollectionUtils.isEmpty(listaIcs)) {
			listaSuspensoesProcesso = listaIcs.get(0).getConteudo().getSuspensoesProcesso();
		}
		return listaSuspensoesProcesso;
	}
	
	@Override
	public List<DecisaoInstanciaSuperiorBean> recuperarDecisoesInstanciaSuperiorPorIdProcessoEhIdProcessoParteLegacy(
			Long idProcesso, Long idProcessoParteLegacy) {
		List<DecisaoInstanciaSuperiorBean> listaDecisoesInstanciaSuperior = new ArrayList<DecisaoInstanciaSuperiorBean>();
		List<InformacaoCriminal> listaIcs = this.repository.findByProcessoIdAndParteIdParteLegacy(idProcesso,
				Long.valueOf(idProcessoParteLegacy));
		if (!CollectionUtils.isEmpty(listaIcs)) {
			listaDecisoesInstanciaSuperior = listaIcs.get(0).getConteudo().getDecisoesInstanciaSuperior();
		}
		return listaDecisoesInstanciaSuperior;
	}

	@Override
	public List<SentencaCondenatoriaProcessoBean> recuperarSentencasCondenatoriasPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso,
			Long idProcessoParteLegacy) {
		List<SentencaCondenatoriaProcessoBean> listaSentencasCondenatorias = new ArrayList<SentencaCondenatoriaProcessoBean>();
		List<InformacaoCriminal> listaIcs = this.repository.findByProcessoIdAndParteIdParteLegacy(idProcesso, idProcessoParteLegacy);
		if(!CollectionUtils.isEmpty(listaIcs)) {
			listaSentencasCondenatorias = listaIcs.get(0).getConteudo().getSentencasCondenatorias();
		}
		return listaSentencasCondenatorias;
	}

	@Override
	public List<FugaBean> recuperarFugasPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso,
			Long idProcessoParteLegacy) {
		List<FugaBean> listaFugas = new ArrayList<FugaBean>();
		
		List<InformacaoCriminal> listaIcs = this.repository.findByProcessoIdAndParteIdParteLegacy(idProcesso, idProcessoParteLegacy);
		
		if(!CollectionUtils.isEmpty(listaIcs)) {
			listaFugas = listaIcs.get(0).getConteudo().getFugas();
		}
		
		return listaFugas;
	}

	@Override
	public List<SolturaBean> recuperarSolturasPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso,
			Long idProcessoParteLegacy) {
		List<SolturaBean> listaSolturas = new ArrayList<SolturaBean>();
		
		List<InformacaoCriminal> listaIcs = this.repository.findByProcessoIdAndParteIdParteLegacy(idProcesso, idProcessoParteLegacy);
		
		if(!CollectionUtils.isEmpty(listaIcs)) {
			listaSolturas = listaIcs.get(0).getConteudo().getSolturas();
		}
		
		return listaSolturas;
	}

	@Override
	public InformacaoCriminal recuperarInformacaoCriminalPorId(Long idInformacaoCriminal) {
		return this.repository.getById(idInformacaoCriminal).get();
	}

	@Override
	public Optional<InformacaoCriminal> recuperarInformacaoCriminalPorProcessoEhParteLegacy(Processo processo, Parte parte) {
		Optional<InformacaoCriminal> inf = Optional.empty();;
		
		if(processo != null && parte != null) {
			inf = this.recuperarInformacaoCriminalPorProcessoEhParteLegacy(processo.getId(), parte.getId());
		}
		
		return  inf;
	}

	@Override
	public Optional<InformacaoCriminal> recuperarInformacaoCriminalPorProcessoEhParteLegacy(Long idProcesso, Long idParte) {
		InformacaoCriminal inf = null;
		
		if(idProcesso != null && idParte != null) {
			List<InformacaoCriminal> lista = this.repository.findByProcessoIdAndParteIdParteLegacy(idProcesso, idParte);
			if(!CollectionUtils.isEmpty(lista)) {
				inf = lista.get(0);
			}
		}
		
		//TODO. Qual a melhor forma? Se a informação não estiver no campo json no banco ela não é retornada...
		if (inf != null && inf.getConteudo()!=null && inf.getConteudo().getSentencasCondenatorias()!=null && 
				inf.getConteudo().getSentencasCondenatorias().size()>0) {
			for (SentencaCondenatoriaProcessoBean sentenca: inf.getConteudo().getSentencasCondenatorias()) {
				for (IncidenciaPenalBean tipificacao: sentenca.getTipificacoes()) {
					Optional<Dispositivo>resultado = this.dispostivoRepository.getById(tipificacao.getDispositivo().getId());
					if (resultado.isPresent()) {
						Dispositivo dispositivo = resultado.get();
						tipificacao.getDispositivo().setArtigo(dispositivo.getArtigo());
						tipificacao.getDispositivo().setParagrafo(dispositivo.getParagrafo());
						tipificacao.getDispositivo().setInciso(dispositivo.getInciso());
						tipificacao.getDispositivo().setIdentificadorPena(dispositivo.getIdentificadorPena());
					}
				}
			}
		}
		
		return Optional.ofNullable(inf);
	}

	@PreAuthorize(PapeisUtil.HAS_AUTHORITY_ROLE_VISUALIZA_INFORMACAO_CRIMINAL+ " or "+PapeisUtil.HAS_AUTHORITY_ROLE_MANIPULA_INFORMACAO_CRIMINAL)
	@Override
	public List<InformacaoCriminal> recuperarInformacaoCriminalListPorProcessoEhParteLegacy(Long idProcesso, Long idParte) {
		List<InformacaoCriminal> lista = new ArrayList<>();
		
		if(idProcesso != null && idParte != null) {
			lista = this.repository.findByProcessoIdAndParteIdParteLegacy(idProcesso, idParte);
		}
		
		return lista;
	}
	
	//@PreAuthorize(PapeisUtil.HAS_AUTHORITY_ROLE_MANIPULA_INFORMACAO_CRIMINAL)
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public InformacaoCriminal atualizarInformacaoCriminal(InformacaoCriminal informacaoCriminal) {
		return this.repository.save(informacaoCriminal);
	}

	@PreAuthorize(PapeisUtil.HAS_AUTHORITY_ROLE_VISUALIZA_INFORMACAO_CRIMINAL+ " or "+PapeisUtil.HAS_AUTHORITY_ROLE_MANIPULA_INFORMACAO_CRIMINAL)
	@Override
	public List<InformacaoCriminal> recuperarInformacaoCriminalPorProcesso(Long idProcesso) {
	 return	this.repository.findByIdProcesso(idProcesso);
	}


	@Override
	public boolean possuiEventoCriminalVinculadoAoMovimento(Long idProcesso, Integer idProcessoEvento, TipoEventoCriminal tipoEventoCriminal) {
		List<InformacaoCriminal> icsProcesso = recuperarInformacaoCriminalPorProcesso(idProcesso);

		for (InformacaoCriminal ic : icsProcesso) {
			for (EventoCriminalBean ec : ic.getEventosCriminais()) {
				if ((ec.getProcessoMovimentoLegacy() != null && ec.getProcessoMovimentoLegacy().getIdProcessoEvento().equals(idProcessoEvento))
					 && ec.getTipoInformacaoCriminal().equals(tipoEventoCriminal)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public List<SentencaAbsolutoriaBean> recuperarSentencasAbsolutoriasPorIdProcessoEhIdProcessoParteLegacy(
			Long idProcesso, Long idProcessoParteLegacy) {
		List<SentencaAbsolutoriaBean> listaSentencasAbsolutorias = new ArrayList<>();
		List<InformacaoCriminal> listaIcs = this.repository.findByProcessoIdAndParteIdParteLegacy(idProcesso,
				idProcessoParteLegacy);
		if (!CollectionUtils.isEmpty(listaIcs)) {
			listaSentencasAbsolutorias = listaIcs.get(0).getConteudo().getSentencasAbsolutorias();
		}
		return listaSentencasAbsolutorias;
	}
	
	@Override
	public void validarPrisaoBean(EventoCriminalBean eventoCriminalBean) {
		if (eventoCriminalBean instanceof PrisaoBean) {
			PrisaoBean prisaoBean = ((PrisaoBean) eventoCriminalBean);

			UnidadePrisional unidadePrisional = prisaoBean.getUnidadePrisional();
			if (unidadePrisional != null && unidadePrisional.getId() != null) {
				UnidadePrisional unidadePrisionalEntity = unidadePrisionalService.recuperarRecurso(unidadePrisional.getId());
				if(unidadePrisionalEntity != null) {
					prisaoBean.setUnidadePrisional(unidadePrisionalEntity);
				} else {
					throw new RuntimeException("Não foi possível obter a unidade prisional: " + unidadePrisional.getId() + ".");
				}
			} else {
				prisaoBean.setUnidadePrisional(null);
			}

			if (prisaoBean.getMotivoPrisao() != null && prisaoBean.getMotivoPrisao().getId() != null) {
				
				MotivoPrisao motivoPrisaoEntity = motivoPrisaoService.recuperarRecurso(prisaoBean.getMotivoPrisao().getId());
				if(motivoPrisaoEntity != null) {
					prisaoBean.setMotivoPrisao(motivoPrisaoEntity);	
				} else {
					new RuntimeException("Não foi possível obter o motivo da prisão: " + prisaoBean.getMotivoPrisao().getId() + ".");
				}
			} else {
				prisaoBean.setMotivoPrisao(null);
			}

		}

	}

	@Override
	public void validarFugaBean(EventoCriminalBean eventoCriminalBean) {
		if (eventoCriminalBean instanceof FugaBean) {
			FugaBean fugaBean = ((FugaBean) eventoCriminalBean);

			UnidadePrisional unidadePrisional = fugaBean.getUnidadePrisional();
			if (unidadePrisional != null && unidadePrisional.getId() != null) {
				UnidadePrisional unidadePrisionalEntity = unidadePrisionalService.recuperarRecurso(unidadePrisional.getId());
				if(unidadePrisionalEntity != null) {
					fugaBean.setUnidadePrisional(unidadePrisionalEntity);
				} else {
					new RuntimeException("Não foi possível obter a unidade prisional: " + unidadePrisional.getId() + ".");
				}
			} else {
				fugaBean.setUnidadePrisional(null);
			}
		}
	}
	
	@Override
	public void validarSolturaBean(EventoCriminalBean eventoCriminalBean) {
		if (eventoCriminalBean instanceof SolturaBean) {
			SolturaBean solturaBean = ((SolturaBean) eventoCriminalBean);

			MotivoSoltura motivo = solturaBean.getMotivo();
			if (motivo != null && motivo.getId() != null) {
				MotivoSoltura motivoEntity = motivoSolturaService.recuperarRecurso(motivo.getId());
				if(motivoEntity != null) {
					solturaBean.setMotivo(motivoEntity);	
				} else {
					new RuntimeException("Não foi possível obter o motivo da soltura: " + motivo.getId() + ".");
				}
			} else {
				solturaBean.setMotivo(null);
			}

			UnidadePrisional unidadePrisional = solturaBean.getUnidadePrisional();
			if (unidadePrisional != null && unidadePrisional.getId() != null) {
				UnidadePrisional unidadePrisionalEntity = unidadePrisionalService.recuperarRecurso(unidadePrisional.getId());
				if(unidadePrisionalEntity != null) {
					solturaBean.setUnidadePrisional(unidadePrisionalEntity);	
				} else {
					new RuntimeException("Não foi possível obter a unidade prisional: " + unidadePrisional.getId() + ".");
				}
			} else {
				solturaBean.setUnidadePrisional(null);
			}
		}
	}
	
	public List<DesclassificacaoProcessoBean> recuperarDesclassificacoesProcessoPorIdProcessoEhIdProcessoParteLegacy(
			Long idProcesso, Long idProcessoParteLegacy) {
		List<InformacaoCriminal> listaIcs = repository.findByProcessoIdAndParteIdParteLegacy(idProcesso, idProcessoParteLegacy);
		if (!CollectionUtils.isEmpty(listaIcs)) {
			return listaIcs.get(0).getConteudo().getDesclassificacoesProcesso();
		}
		return new ArrayList<>();
	}

	@Override
	public List<SentencaImpronunciaProcessoBean> recuperarSentencasImpronunciaPorIdProcessoEhIdProcessoParteLegacy(
			Long idProcesso, Long idProcessoParteLegacy) {
		List<InformacaoCriminal> listaIcs = repository.findByProcessoIdAndParteIdParteLegacy(idProcesso, idProcessoParteLegacy);
		if (!CollectionUtils.isEmpty(listaIcs)) {
			return listaIcs.get(0).getConteudo().getSentencasImpronuncia();
		}
		return new ArrayList<>();
	}

	@Override
	public List<SentencaPronunciaProcessoBean> recuperarSentencasPronunciaPorIdProcessoEhIdProcessoParteLegacy(
			Long idProcesso, Long idProcessoParteLegacy) {
		List<InformacaoCriminal> listaIcs = repository.findByProcessoIdAndParteIdParteLegacy(idProcesso, idProcessoParteLegacy);
		if (!CollectionUtils.isEmpty(listaIcs)) {
			return listaIcs.get(0).getConteudo().getSentencasPronuncia();
		}
		return new ArrayList<>();
	}
	
	@Override
	public List<CitacaoProcessoBean> recuperarCitacoesPorIdProcessoEhIdProcessoParteLegacy(
			Long idProcesso, Long idProcessoParteLegacy) {
		List<InformacaoCriminal> listaIcs = repository.findByProcessoIdAndParteIdParteLegacy(idProcesso, idProcessoParteLegacy);
		if (!CollectionUtils.isEmpty(listaIcs)) {
			return listaIcs.get(0).getConteudo().getCitacoes();
		}
		return new ArrayList<>();
	}
	
	@Override
	public List<RecebimentoAditamentoDenunciaBean> recuperarRecebimentosAditamentoDenunciaPorIdProcessoEhIdProcessoParteLegacy(
			Long idProcesso, Long idProcessoParteLegacy) {
		List<InformacaoCriminal> listaIcs = repository.findByProcessoIdAndParteIdParteLegacy(idProcesso,
				idProcessoParteLegacy);
		if (!CollectionUtils.isEmpty(listaIcs)) {
			return listaIcs.get(0).getConteudo().getRecebimentosAditamentoDenuncia();
		}
		return new ArrayList<>();
	}

}
