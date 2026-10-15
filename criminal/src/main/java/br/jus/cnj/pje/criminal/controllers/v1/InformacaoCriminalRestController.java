package br.jus.cnj.pje.criminal.controllers.v1;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.cnj.intercomunicacao.beans.RespostaConsultaProcesso;
import br.jus.cnj.pje.criminal.aop.InboundRequest;
import br.jus.cnj.pje.criminal.dto.MedidaSegurancaDTO;
import br.jus.cnj.pje.criminal.enums.MedidaSegurancaEnum;
import br.jus.cnj.pje.criminal.enums.OrgaoJulgadorEnum;
import br.jus.cnj.pje.criminal.enums.RecorrenteEnum;
import br.jus.cnj.pje.criminal.enums.TipoEventoCriminalEnum;
import br.jus.cnj.pje.criminal.enums.TipoPenaExecucaoEnum;
import br.jus.cnj.pje.criminal.model.beans.ArquivamentoIncidenciaBean;
import br.jus.cnj.pje.criminal.model.beans.AusenciaCondicoesAcaoBean;
import br.jus.cnj.pje.criminal.model.beans.CitacaoProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.DecisaoBean;
import br.jus.cnj.pje.criminal.model.beans.DecisaoInstanciaSuperiorBean;
import br.jus.cnj.pje.criminal.model.beans.DeclinioCompetenciaBean;
import br.jus.cnj.pje.criminal.model.beans.DesclassificacaoProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.DesmembramentoBean;
import br.jus.cnj.pje.criminal.model.beans.EvasaoBean;
import br.jus.cnj.pje.criminal.model.beans.EventoCriminalBean;
import br.jus.cnj.pje.criminal.model.beans.FugaBean;
import br.jus.cnj.pje.criminal.model.beans.HomologacaoComposicaoDanoCivilBean;
import br.jus.cnj.pje.criminal.model.beans.IncidenciaBean;
import br.jus.cnj.pje.criminal.model.beans.IncidenciaPenalBean;
import br.jus.cnj.pje.criminal.model.beans.LitispendenciaCoisaJulgadaBean;
import br.jus.cnj.pje.criminal.model.beans.NulidadeProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.InternacaoBean;
import br.jus.cnj.pje.criminal.model.beans.PendenciaEventoCriminalBean;
import br.jus.cnj.pje.criminal.model.beans.PresoOutroProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.PrisaoBean;
import br.jus.cnj.pje.criminal.model.beans.ProcessoMovimentoLegacyBean;
import br.jus.cnj.pje.criminal.model.beans.RecebimentoAditamentoDenunciaBean;
import br.jus.cnj.pje.criminal.model.beans.RevogacaoSuspensaoCondicionalProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SemiliberdadeBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaAbsolutoriaBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaAbsolutoriaImpropriaBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaCondenatoriaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaExtincaoMedidaSocioeducativaBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaExtincaoPunibilidadeBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaImpronunciaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaInfracionalBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaPronunciaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SolturaBean;
import br.jus.cnj.pje.criminal.model.beans.SuspensaoMedidaSocioeducativaBean;
import br.jus.cnj.pje.criminal.model.beans.SuspensaoProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.TrancamentoBean;
import br.jus.cnj.pje.criminal.model.beans.TransacaoPenalBean;
import br.jus.cnj.pje.criminal.model.dto.EventoCriminalVisualizacaoDTO;
import br.jus.cnj.pje.criminal.model.entities.InformacaoCriminal;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;
import br.jus.cnj.pje.criminal.services.InformacaoCriminalService;
import br.jus.cnj.pje.criminal.services.ParteService;
import br.jus.cnj.pje.criminal.services.ProcessoService;
import br.jus.cnj.pje.criminal.services.SeeuService;
import br.jus.cnj.pje.criminal.services.TipoInformacaoCriminalService;
import br.jus.cnj.pje.criminal.model.beans.SentencaAbsolvicaoSumariaBean;
import br.jus.cnj.pje.criminal.model.beans.RemissaoBean;


@RestController
public class InformacaoCriminalRestController {
	
	@Autowired
    private SeeuService seeuService;

	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private ParteService parteService;
	
	@Autowired
	private InformacaoCriminalService informacaoCriminalService;
	
	@Autowired
	private TipoInformacaoCriminalService tipoInformacaoCriminalService;
	
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(method = RequestMethod.POST, path = "/processos/{idProcesso}/informacoes-criminais", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<List<Long>> inserirInformacoesCriminaisAoProcesso(@PathVariable("idProcesso") Long idProcesso, @RequestBody List<InformacaoCriminal> informacoesCriminais){
		ResponseEntity<List<Long>> res = ResponseEntity.noContent().build();
		
		List<Long> listaRet = new ArrayList<Long>(0);
		List<InformacaoCriminal> listaIcs = new ArrayList<InformacaoCriminal>();
		Processo processo = this.processoService.recuperarProcessoPorId(idProcesso);
		
		if(processo != null) {
			for (InformacaoCriminal ic : informacoesCriminais) {
				ic.setProcesso(processo);
				ic.getParte().setProcesso(processo);
				listaIcs.add(ic);
			}
			if(!CollectionUtils.isEmpty(listaIcs)) {
				listaIcs = definirTipoDeEventoCriminalInicial(listaIcs);
				listaIcs = this.informacaoCriminalService.criarNovasInformacoesCriminais(listaIcs);
				for (InformacaoCriminal informacaoCriminal : listaIcs) {
					listaRet.add(informacaoCriminal.getId());
				}
				
				res = ResponseEntity.ok(listaRet);
			}
		}
		
		return res;
	}	

	
	@RequestMapping(method = RequestMethod.GET, path = "/prisoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}")
	@InboundRequest
	public ResponseEntity<List<PrisaoBean>> recuperarPrisoesParte(@PathVariable("idProcesso") Long idProcesso, @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy){
		ResponseEntity<List<PrisaoBean>> ret = ResponseEntity.noContent().build();
		
		List<PrisaoBean> listaPrisoes = new ArrayList<PrisaoBean>();
		listaPrisoes = informacaoCriminalService.recuperarPrisoesPorIdProcessoEhIdProcessoParteLegacy(idProcesso, idProcessoParteLegacy);
		
		if(!CollectionUtils.isEmpty(listaPrisoes)) {
			ret = ResponseEntity.ok(listaPrisoes);
		}
		
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/suspensoesProcesso/processos/{idProcesso}/partes/{idProcessoParteLegacy}")
	@InboundRequest
	public ResponseEntity<List<SuspensaoProcessoBean>> recuperarSuspensoesProcessoParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy){
		ResponseEntity<List<SuspensaoProcessoBean>> ret = ResponseEntity.noContent().build();
		List<SuspensaoProcessoBean> suspensoesProcesso = informacaoCriminalService.recuperarSuspensoesProcessoPorIdProcessoEhIdProcessoParteLegacy(idProcesso, idProcessoParteLegacy);
		if (!CollectionUtils.isEmpty(suspensoesProcesso)) {
			ret = ResponseEntity.ok(suspensoesProcesso);
		}
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/sentencasCondenatorias/processos/{idProcesso}/partes/{idProcessoParteLegacy}")
	@InboundRequest
	public ResponseEntity<List<SentencaCondenatoriaProcessoBean>> recuperarSentencasCondenatoriasProcessoParte(@PathVariable("idProcesso") Long idProcesso, 
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy){
		ResponseEntity<List<SentencaCondenatoriaProcessoBean>> ret = ResponseEntity.noContent().build();
		List<SentencaCondenatoriaProcessoBean> listaSentencas = informacaoCriminalService.recuperarSentencasCondenatoriasPorIdProcessoEhIdProcessoParteLegacy(idProcesso, idProcessoParteLegacy);
		if(!CollectionUtils.isEmpty(listaSentencas)) {
			ret = ResponseEntity.ok(listaSentencas);
		}
		return ret;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/prisoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<PrisaoBean> criarPrisaoParte(@PathVariable("idProcesso") Long idProcesso, 
													@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													@RequestBody PrisaoBean prisao){
		ResponseEntity<PrisaoBean> res = ResponseEntity.noContent().build();
		
		if(prisao != null) {
			this.informacaoCriminalService.validarPrisaoBean(prisao);
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			
			if(infoCriminal.getConteudo().getPrisoes() == null) {
				infoCriminal.getConteudo().setPrisoes(new ArrayList<PrisaoBean>());
			}
			prisao.setDataCadastro(new Date());
			infoCriminal.getConteudo().getPrisoes().add(prisao);
			infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getPrisoes().get(0));
		
		}
		
		return res;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/suspensoesProcesso/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SuspensaoProcessoBean> criarSuspensaoProcessoParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SuspensaoProcessoBean suspensaoProcesso) {
		ResponseEntity<SuspensaoProcessoBean> res = ResponseEntity.noContent().build();
		if (suspensaoProcesso != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getSuspensoesProcesso() == null) {
				infoCriminal.getConteudo().setSuspensoesProcesso(new ArrayList<SuspensaoProcessoBean>());
			}
			suspensaoProcesso.setDataCadastro(new Date());
			infoCriminal.getConteudo().getSuspensoesProcesso().add(suspensaoProcesso);
			infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getSuspensoesProcesso().get(0));
		}
		return res;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/decisoesInstanciaSuperior/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<DecisaoInstanciaSuperiorBean> criarDecisaoInstanciaSuperiorParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody DecisaoInstanciaSuperiorBean decisaoInstanciaSuperior) {
		ResponseEntity<DecisaoInstanciaSuperiorBean> res = ResponseEntity.noContent().build();
		if (decisaoInstanciaSuperior != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			
			if (infoCriminal.getConteudo().getDecisoesInstanciaSuperior() == null) {
				infoCriminal.getConteudo()
						.setDecisoesInstanciaSuperior(new ArrayList<DecisaoInstanciaSuperiorBean>());
			}
			decisaoInstanciaSuperior.setDataCadastro(new Date());
			infoCriminal.getConteudo().getDecisoesInstanciaSuperior().add(decisaoInstanciaSuperior);
			infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getDecisoesInstanciaSuperior().get(0));
			
		}
		return res;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/sentencasCondenatorias/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<?> criarSentencaCondenatoriaParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaCondenatoriaProcessoBean sentencaCondenatoria) {
		ResponseEntity<?> res = ResponseEntity.noContent().build();
		if (sentencaCondenatoria != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			
			if (infoCriminal.getConteudo().getSentencasCondenatorias() == null) {
				infoCriminal.getConteudo().setSentencasCondenatorias(new ArrayList<SentencaCondenatoriaProcessoBean>());
			}
            if (!this.validarNumeroProcessoExecucaoSEEU(sentencaCondenatoria.getNrProcessoExecucaoSEEU())) {
				return ResponseEntity.badRequest().body("Número do processo de execução no SEEU não encontrado.");
			}
				sentencaCondenatoria.setDataCadastro(new Date());
				infoCriminal.getConteudo().getSentencasCondenatorias().add(sentencaCondenatoria);
				infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res = ResponseEntity.ok(infoCriminal.getConteudo().getSentencasCondenatorias().get(0));
		}
		return res;
	}

	private boolean validarNumeroProcessoExecucaoSEEU(String nrProcessoExecucaoSEEU) {
		try {
			if (nrProcessoExecucaoSEEU != null && !nrProcessoExecucaoSEEU.isEmpty()) {
					RespostaConsultaProcesso resposta = this.seeuService.consultarProcessoSeeu(nrProcessoExecucaoSEEU, false);
					return resposta.isSucesso();
				}
			return true;
		}
		 catch (Exception e) {
			return false;
		}
	}

	
	@RequestMapping(method = RequestMethod.PUT, path = "/prisoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<PrisaoBean> alterarPrisaoParte(@PathVariable("idProcesso") Long idProcesso, 
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
			@RequestBody PrisaoBean prisao){
		AtomicReference<ResponseEntity<PrisaoBean>> res = new AtomicReference<ResponseEntity<PrisaoBean>>(ResponseEntity.noContent().build());
		if (prisao != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
				if (infoCriminal.getConteudo().getPrisoes() == null) {
					infoCriminal.getConteudo().setPrisoes(new ArrayList<PrisaoBean>());
				}
				ListIterator<PrisaoBean> iterator = infoCriminal.getConteudo().getPrisoes().listIterator();
				while (iterator.hasNext()) {
					PrisaoBean prisaoBean = iterator.next();
					if (prisaoBean.getId().equals(prisao.getId())) {
						iterator.set(prisao);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(prisao));
			});
				
		}
		return res.get();
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/suspensoesProcesso/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SuspensaoProcessoBean> alterarSuspensaoProcessoParte(@PathVariable("idProcesso") Long idProcesso, 
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
			@RequestBody SuspensaoProcessoBean suspensaoProcesso){
		AtomicReference<ResponseEntity<SuspensaoProcessoBean>> res = new AtomicReference<ResponseEntity<SuspensaoProcessoBean>>(ResponseEntity.noContent().build());

		if (suspensaoProcesso != null) {
			 this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
				if (infoCriminal.getConteudo().getSuspensoesProcesso() == null) {
					infoCriminal.getConteudo().setSuspensoesProcesso(new ArrayList<SuspensaoProcessoBean>());
				}
				ListIterator<SuspensaoProcessoBean> iterator = infoCriminal.getConteudo().getSuspensoesProcesso().listIterator();
				while (iterator.hasNext()) {
					SuspensaoProcessoBean suspensaoProcessoBean = iterator.next();
					if (suspensaoProcessoBean.getId().equals(suspensaoProcesso.getId())) {
						iterator.set(suspensaoProcesso);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(suspensaoProcesso));
			});
		}
		return res.get();
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/sentencasCondenatorias/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<?> alterarSentencaCondenatoria(@PathVariable("idProcesso") Long idProcesso, 
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
			@RequestBody SentencaCondenatoriaProcessoBean sentencaCondenatoriaProcesso){
		AtomicReference<ResponseEntity<?>> res = new AtomicReference<ResponseEntity<?>>(ResponseEntity.noContent().build());
		if (sentencaCondenatoriaProcesso != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
				if (infoCriminal.getConteudo().getSentencasCondenatorias() == null) {
					infoCriminal.getConteudo().setSentencasCondenatorias(new ArrayList<SentencaCondenatoriaProcessoBean>());
				}
				ListIterator<SentencaCondenatoriaProcessoBean> iterator = infoCriminal.getConteudo().getSentencasCondenatorias().listIterator();
				while (iterator.hasNext()) {
					SentencaCondenatoriaProcessoBean sentencaCondenatoriaProcessoBean = iterator.next();
					if (sentencaCondenatoriaProcessoBean.getId().equals(sentencaCondenatoriaProcesso.getId())) {
						iterator.set(sentencaCondenatoriaProcesso);
					}
				}
				if (!this.validarNumeroProcessoExecucaoSEEU(sentencaCondenatoriaProcesso.getNrProcessoExecucaoSEEU())) {
					res.set(ResponseEntity.badRequest().body("Número do processo de execução no SEEU não encontrado."));
				}
                else{
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(sentencaCondenatoriaProcesso));
				}
			});
		}
		return res.get();
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/decisoesInstanciaSuperior/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<DecisaoInstanciaSuperiorBean> alterarDecisaoInstanciaSuperiorParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody DecisaoInstanciaSuperiorBean decisaoInstanciaSuperior) {
		AtomicReference<ResponseEntity<DecisaoInstanciaSuperiorBean>> res = new AtomicReference<ResponseEntity<DecisaoInstanciaSuperiorBean>>(ResponseEntity.noContent().build());
		if (decisaoInstanciaSuperior != null) {
			this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
				if (infoCriminal.getConteudo().getDecisoesInstanciaSuperior() == null) {
					infoCriminal.getConteudo()
							.setDecisoesInstanciaSuperior(new ArrayList<DecisaoInstanciaSuperiorBean>());
				}
				ListIterator<DecisaoInstanciaSuperiorBean> iterator = infoCriminal.getConteudo()
						.getDecisoesInstanciaSuperior().listIterator();
				while (iterator.hasNext()) {
					DecisaoInstanciaSuperiorBean decisaoInstanciaSuperiorBean = iterator.next();
					if (decisaoInstanciaSuperiorBean.getId().equals(decisaoInstanciaSuperior.getId())) {
						iterator.set(decisaoInstanciaSuperior);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(decisaoInstanciaSuperior));
			});
		}
		return res.get();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/prisoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Boolean> removerPrisaoParte(@PathVariable("idProcesso") Long idProcesso,
													  @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													  @RequestBody PrisaoBean prisao) {
		ResponseEntity<Boolean> res = ResponseEntity.noContent().build();
		AtomicBoolean deleted = new AtomicBoolean(Boolean.FALSE);
		if(prisao != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
					.filter( i -> !CollectionUtils.isEmpty(i.getConteudo().getPrisoes())).ifPresent(infoCriminal -> {
						List<PrisaoBean> listaPrisoes = infoCriminal.getConteudo().getPrisoes();
						deleted.set(listaPrisoes.remove(prisao)); 
						infoCriminal.getConteudo().setPrisoes(listaPrisoes);
						this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
					});
		}
		
		res = ResponseEntity.ok(deleted.get());
		
		return res;
	}	
	
	@RequestMapping(method = RequestMethod.POST, path = "/suspensoesProcesso/processos/{idProcesso}/partes/{idProcessoParteLegacy}/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Boolean> removerSuspensoesProcessoParte(@PathVariable("idProcesso") Long idProcesso,
													  @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													  @RequestBody SuspensaoProcessoBean suspensaoProcesso) {
		AtomicBoolean deleted = new AtomicBoolean(Boolean.FALSE);
		if(suspensaoProcesso != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.filter( i -> !CollectionUtils.isEmpty(i.getConteudo().getSuspensoesProcesso())).ifPresent(infoCriminal -> {
				List<SuspensaoProcessoBean> listaSuspensoesProcesso = infoCriminal.getConteudo().getSuspensoesProcesso();
				deleted.set(listaSuspensoesProcesso.remove(suspensaoProcesso)); 
				infoCriminal.getConteudo().setSuspensoesProcesso(listaSuspensoesProcesso);
				this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			});
			
		}
		return ResponseEntity.ok(deleted.get());
	}	
	
	@RequestMapping(method = RequestMethod.POST, path = "/sentencasCondenatorias/processos/{idProcesso}/partes/{idProcessoParteLegacy}/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Boolean> removerSuspensoesProcessoParte(@PathVariable("idProcesso") Long idProcesso,
													  @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													  @RequestBody SentencaCondenatoriaProcessoBean sentencaCondenatoriaProcesso) {
		AtomicBoolean deleted = new AtomicBoolean(Boolean.FALSE);
		if(sentencaCondenatoriaProcesso != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.filter( i -> !CollectionUtils.isEmpty(i.getConteudo().getSuspensoesProcesso())).ifPresent(infoCriminal -> {
				List<SentencaCondenatoriaProcessoBean> listaSentencasCondenatorias = infoCriminal.getConteudo().getSentencasCondenatorias();
				deleted.getAndSet( listaSentencasCondenatorias.remove(sentencaCondenatoriaProcesso));
				infoCriminal.getConteudo().setSentencasCondenatorias(listaSentencasCondenatorias);
				this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			});
			
		}
		return ResponseEntity.ok(deleted.get());
	}	

	@RequestMapping(method = RequestMethod.POST, path = "/decisoesInstanciaSuperior/processos/{idProcesso}/partes/{idProcessoParteLegacy}/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Boolean> removerDecisaoInstanciaSuperiorParte(@PathVariable("idProcesso") Long idProcesso,
													  @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													  @RequestBody DecisaoInstanciaSuperiorBean decisaoInstanciaSuperior) {
		AtomicBoolean deleted = new AtomicBoolean(Boolean.FALSE);
		if(decisaoInstanciaSuperior != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.filter( i -> !CollectionUtils.isEmpty(i.getConteudo().getDecisoesInstanciaSuperior())).ifPresent(infoCriminal -> {
				List<DecisaoInstanciaSuperiorBean> listaDecisoesInstanciaSuperior = infoCriminal.getConteudo().getDecisoesInstanciaSuperior();
				deleted.set(listaDecisoesInstanciaSuperior.remove(decisaoInstanciaSuperior)); 
				infoCriminal.getConteudo().setDecisoesInstanciaSuperior(listaDecisoesInstanciaSuperior);
				this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			});
			
		}
		return ResponseEntity.ok(deleted.get());
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/fugas/processos/{idProcesso}/partes/{idProcessoParteLegacy}")
	@InboundRequest
	public ResponseEntity<List<FugaBean>> recuperarFugasParte(@PathVariable("idProcesso") Long idProcesso, @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy){
		ResponseEntity<List<FugaBean>> ret = ResponseEntity.noContent().build();
		
		List<FugaBean> listaFuga = new ArrayList<FugaBean>();
		listaFuga = informacaoCriminalService.recuperarFugasPorIdProcessoEhIdProcessoParteLegacy(idProcesso, idProcessoParteLegacy);
		
		if(!CollectionUtils.isEmpty(listaFuga)) {
			ret = ResponseEntity.ok(listaFuga);
		}
		
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/fugas/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<FugaBean> criarFugaParte(@PathVariable("idProcesso") Long idProcesso, 
													@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													@RequestBody FugaBean fuga){
		ResponseEntity<FugaBean> res = ResponseEntity.noContent().build();
		
		if(fuga != null) {
			this.informacaoCriminalService.validarFugaBean(fuga);
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			if(infoCriminal.getConteudo().getFugas() == null) {
				infoCriminal.getConteudo().setFugas(new ArrayList<FugaBean>());
			}
			fuga.setDataCadastro(new Date());
			infoCriminal.getConteudo().getFugas().add(fuga);
			infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getFugas().get(0));
			
		}
		
		return res;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/fugas/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<FugaBean> alterarFugaParte(@PathVariable("idProcesso") Long idProcesso, 
													@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													@RequestBody FugaBean fuga){
		AtomicReference<ResponseEntity<FugaBean>> res = new AtomicReference<ResponseEntity<FugaBean>>(ResponseEntity.noContent().build());
		if (fuga != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
				if(infoCriminal.getConteudo().getFugas() == null) {
					infoCriminal.getConteudo().setFugas(new ArrayList<FugaBean>());
				}
				ListIterator<FugaBean> iterator = infoCriminal.getConteudo().getFugas().listIterator();
				while (iterator.hasNext()) {
					FugaBean fugaBean = iterator.next();
					if (fugaBean.getId().equals(fuga.getId())) {
						iterator.set(fuga);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(fuga)); 
			});
		}
		return res.get();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/fugas/processos/{idProcesso}/partes/{idProcessoParteLegacy}/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Boolean> removerFugaParte(@PathVariable("idProcesso") Long idProcesso,
													  @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													  @RequestBody FugaBean fuga) {
		ResponseEntity<Boolean> res = ResponseEntity.noContent().build();
		AtomicBoolean deleted = new AtomicBoolean(Boolean.FALSE);
		
		if(fuga != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.filter( i -> !CollectionUtils.isEmpty(i.getConteudo().getFugas())).ifPresent(infoCriminal -> {
				List<FugaBean> listaFugas = infoCriminal.getConteudo().getFugas();
				deleted.set(listaFugas.remove(fuga));
				infoCriminal.getConteudo().setFugas(listaFugas);
				this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			});
		}
		
		res = ResponseEntity.ok(deleted.get());
		
		return res;
	}	

	@RequestMapping(method = RequestMethod.GET, path = "/solturas/processos/{idProcesso}/partes/{idProcessoParteLegacy}")
	@InboundRequest
	public ResponseEntity<List<SolturaBean>> recuperarSolturasParte(@PathVariable("idProcesso") Long idProcesso, @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy){
		ResponseEntity<List<SolturaBean>> ret = ResponseEntity.noContent().build();
		
		List<SolturaBean> listaSolturas = new ArrayList<SolturaBean>();
		listaSolturas = informacaoCriminalService.recuperarSolturasPorIdProcessoEhIdProcessoParteLegacy(idProcesso, idProcessoParteLegacy);
		
		if (!CollectionUtils.isEmpty(listaSolturas)) {
			ret = ResponseEntity.ok(listaSolturas);
		}
		
		return ret;
	}

	
	@RequestMapping(method = RequestMethod.POST, path = "/solturas/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SolturaBean> criarSolturaParte(@PathVariable("idProcesso") Long idProcesso, 
													@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													@RequestBody SolturaBean soltura){
		ResponseEntity<SolturaBean> res = ResponseEntity.noContent().build();
		
		if(soltura != null) {
			this.informacaoCriminalService.validarSolturaBean(soltura);
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			if(infoCriminal.getConteudo().getSolturas() == null) {
				infoCriminal.getConteudo().setSolturas(new ArrayList<SolturaBean>());
			}
			soltura.setDataCadastro(new Date());
			infoCriminal.getConteudo().getSolturas().add(soltura);
			infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getSolturas().get(0));
			
		}
		
		return res;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/solturas/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SolturaBean> alterarSolturaParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, @RequestBody SolturaBean soltura) {
		AtomicReference<ResponseEntity<SolturaBean>> res = new AtomicReference<ResponseEntity<SolturaBean>>(ResponseEntity.noContent().build());

		if (soltura != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
				if (infoCriminal.getConteudo().getSolturas() == null) {
					infoCriminal.getConteudo().setSolturas(new ArrayList<SolturaBean>());
				}
				ListIterator<SolturaBean> iterator = infoCriminal.getConteudo().getSolturas().listIterator();
				while (iterator.hasNext()) {
					SolturaBean solturaBean = iterator.next();
					if (solturaBean.getId().equals(soltura.getId())) {
						iterator.set(soltura);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(soltura));
			});
		}
		return res.get();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/solturas/processos/{idProcesso}/partes/{idProcessoParteLegacy}/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Boolean> removerSolutraParte(@PathVariable("idProcesso") Long idProcesso,
													  @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													  @RequestBody SolturaBean soltura) {
		ResponseEntity<Boolean> res = ResponseEntity.noContent().build();
		AtomicBoolean deleted = new AtomicBoolean(Boolean.FALSE);
		
		if(soltura != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.filter( i -> !CollectionUtils.isEmpty(i.getConteudo().getSolturas())).ifPresent(infoCriminal -> {
				List<SolturaBean> listaSolturas = infoCriminal.getConteudo().getSolturas();
				deleted.set(listaSolturas.remove(soltura)); 
				infoCriminal.getConteudo().setSolturas(listaSolturas);
				this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			});
		}
		
		res = ResponseEntity.ok(deleted.get());
		
		return res;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/tiposEventosCriminais")
	@InboundRequest
	public ResponseEntity<List<TipoEventoCriminal>> recuperarTiposEventosCriminais(){
		ResponseEntity<List<TipoEventoCriminal>> ret = ResponseEntity.noContent().build();
		
		List<TipoEventoCriminal> listaTipoInformacaoCriminal = new ArrayList<TipoEventoCriminal>();
		listaTipoInformacaoCriminal = tipoInformacaoCriminalService.recuperaTiposInformacaoCriminal();
		
		if(!CollectionUtils.isEmpty(listaTipoInformacaoCriminal)) {
			ret = ResponseEntity.ok(listaTipoInformacaoCriminal);
		}
		
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/eventosCriminais/movimento/{codigoMovimento}")
	@InboundRequest
	public ResponseEntity<List<TipoEventoCriminal>> recuperarTiposEventosCriminaisPorMovimento(@PathVariable("codigoMovimento") String codigoMovimento){
		
		ResponseEntity<List<TipoEventoCriminal>> ret = ResponseEntity.noContent().build();
		
		List<TipoEventoCriminal> listaTipoInformacaoCriminal = new ArrayList<TipoEventoCriminal>();
		listaTipoInformacaoCriminal = tipoInformacaoCriminalService.recuperaTipoInformacaoCriminalPorMovimento(codigoMovimento);
		
		if(!CollectionUtils.isEmpty(listaTipoInformacaoCriminal)) {
			ret = ResponseEntity.ok(listaTipoInformacaoCriminal);
		}
		
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/eventosCriminais/processos/{nrProcesso}")
	@InboundRequest
	public ResponseEntity<List<InformacaoCriminal>> recuperarInformacaoCriminal(@PathVariable("nrProcesso") String nrProcesso) throws UnsupportedEncodingException{
		ResponseEntity<List<InformacaoCriminal>> ret = ResponseEntity.noContent().build();
		List<InformacaoCriminal> listaInformacaoCriminal = new ArrayList<InformacaoCriminal>();
		
		Processo processo = processoService.recuperarProcessoPorNumero(nrProcesso);
		
		if (processo != null) {
			listaInformacaoCriminal = informacaoCriminalService.recuperarInformacaoCriminalPorProcesso(processo.getId());
		}
		
		if (!CollectionUtils.isEmpty(listaInformacaoCriminal)) {
			ret = ResponseEntity.ok(listaInformacaoCriminal);
		}
		
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/eventosCriminais/processos/numero/{nrProcesso}/partes/{idProcessoParteLegacy}")
	@InboundRequest
	public ResponseEntity<List<InformacaoCriminal>> recuperarInformacaoCriminalPorParteProcessual(@PathVariable("nrProcesso") String nrProcesso, @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy) throws UnsupportedEncodingException{
		ResponseEntity<List<InformacaoCriminal>> ret = ResponseEntity.noContent().build();
		List<InformacaoCriminal> listaInformacaoCriminal = new ArrayList<InformacaoCriminal>();
		
		Processo processo = processoService.recuperarProcessoPorNumero(nrProcesso);
		
		if (processo != null) {
			listaInformacaoCriminal = informacaoCriminalService.recuperarInformacaoCriminalListPorProcessoEhParteLegacy(processo.getId(), idProcessoParteLegacy);
		}
		
		if (!CollectionUtils.isEmpty(listaInformacaoCriminal)) {
			ret = ResponseEntity.ok(listaInformacaoCriminal);
		}
		
		return ret;
	}	
	
	@RequestMapping(method = RequestMethod.POST, path = "/eventosCriminais/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<EventoCriminalBean> criarInformacaoCriminalParte(@PathVariable("idProcesso") Long idProcesso, 
																		@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
																		@RequestBody EventoCriminalBean eventoCriminalBean){

		ResponseEntity<EventoCriminalBean> ret = ResponseEntity.noContent().build();
		
		if(eventoCriminalBean != null) {
			if(eventoCriminalBean.getData() == null) {
				eventoCriminalBean.setData(new Date());
			}
			eventoCriminalBean.setDataCadastro(new Date());

			InformacaoCriminal informacaoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			
			informacaoCriminal.getConteudo().addEventoCriminal(eventoCriminalBean);
			
			informacaoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(informacaoCriminal);

		}
		
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/eventosCriminais/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<EventoCriminalBean> alterarInformacaoCriminalParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody EventoCriminalBean eventoCriminalBean) {
		this.informacaoCriminalService
				.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(informacaoCriminal -> {
					ListIterator<EventoCriminalBean> iterator = informacaoCriminal.getConteudo()
							.getEventosCriminaisPorTipo(eventoCriminalBean.getTipoInformacaoCriminal().getCodTipoIc())
							.listIterator();
					while (iterator.hasNext()) {
						EventoCriminalBean next = iterator.next();
						if (next.getId().equals(eventoCriminalBean.getId())) {
							iterator.set(eventoCriminalBean);
							
						}
					}
					informacaoCriminalService.atualizarInformacaoCriminal(informacaoCriminal);
				});
		
		return ResponseEntity.ok(eventoCriminalBean);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/eventosCriminais/processos/{idProcesso}/pendencias")
	@InboundRequest
	public ResponseEntity<List<PendenciaEventoCriminalBean>> recuperarPendenciasEventosCriminais(@PathVariable("idProcesso") Long idProcesso, @RequestBody List<ProcessoMovimentoLegacyBean> movimentos ){
		ResponseEntity<List<PendenciaEventoCriminalBean>> ret = ResponseEntity.noContent().build();
		List<PendenciaEventoCriminalBean> pendencias = new ArrayList<>();
		for(ProcessoMovimentoLegacyBean movimento : movimentos) {
			List<TipoEventoCriminal> tipoEventoMovimentoList = tipoInformacaoCriminalService.recuperaTipoInformacaoCriminalPorMovimento(movimento.getCodEvento());
			for(TipoEventoCriminal tipoEventoCriminal : tipoEventoMovimentoList) {			
				if(!informacaoCriminalService.possuiEventoCriminalVinculadoAoMovimento(idProcesso, movimento.getIdProcessoEvento(), tipoEventoCriminal)) {
					pendencias.add(new PendenciaEventoCriminalBean(movimento, tipoEventoCriminal));
					System.out.println(new PendenciaEventoCriminalBean(movimento, tipoEventoCriminal).toString());
				}
			}
		}
		if (!CollectionUtils.isEmpty(pendencias)) {
			ret = ResponseEntity.ok(pendencias);
		}		
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/eventosCriminais/processos/{idProcesso}/partes/{idProcessoParteLegacy}")
	@InboundRequest
	public ResponseEntity<List<EventoCriminalBean>> recuperarEventosCriminais(@PathVariable("idProcesso") Long idProcesso, @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy){
		AtomicReference<ResponseEntity<List<EventoCriminalBean>>> ret = new AtomicReference<ResponseEntity<List<EventoCriminalBean>>>(ResponseEntity.noContent().build());
		informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(ic -> {
			List<EventoCriminalBean> eventoCriminalBeanList = ic.getEventosCriminais();
			
			if (!CollectionUtils.isEmpty(eventoCriminalBeanList)) {
				ret.set(ResponseEntity.ok(eventoCriminalBeanList));
			}
		});
		
		
		return ret.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/eventosCriminaisDTO/processos/{idProcesso}/partes/{idProcessoParteLegacy}")
	@InboundRequest
	public ResponseEntity<List<EventoCriminalVisualizacaoDTO>> recuperarEventosCriminaisDTO(@PathVariable("idProcesso") Long idProcesso, @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy){
		AtomicReference<ResponseEntity<List<EventoCriminalVisualizacaoDTO>>> ret = new AtomicReference<ResponseEntity<List<EventoCriminalVisualizacaoDTO>>>(ResponseEntity.noContent().build());

		informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(ic -> {
	
			List<EventoCriminalBean> eventoCriminalBeanList = ic.getEventosCriminais();
			if (!CollectionUtils.isEmpty(eventoCriminalBeanList)) {
				List<EventoCriminalVisualizacaoDTO> eventoCriminaDTOList = new ArrayList<>();
				eventoCriminalBeanList.forEach(e -> eventoCriminaDTOList.add(new EventoCriminalVisualizacaoDTO(e)));
				ret.set(ResponseEntity.ok(eventoCriminaDTOList)); 
			}
		});
		return ret.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/processos/{idProcesso}/partes/{idProcessoParteLegacy}/incidencias")
	@InboundRequest
	public ResponseEntity<List<IncidenciaBean>> recuperarIncidenciasParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy) {
		AtomicReference<ResponseEntity<List<IncidenciaBean>>> ret = new AtomicReference<ResponseEntity<List<IncidenciaBean>>>(ResponseEntity.noContent().build());

		informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(ic -> {
	
			List<EventoCriminalBean> eventoCriminalBeanList = ic.getEventosCriminais();
			List<IncidenciaBean> incidenciaBeanList = new ArrayList<>();
			if (!CollectionUtils.isEmpty(eventoCriminalBeanList)) {
				for (EventoCriminalBean eventoCriminal : eventoCriminalBeanList) {
					if (!CollectionUtils.isEmpty(eventoCriminal.getTipificacoes())) {
						for (IncidenciaPenalBean incidencia : eventoCriminal.getTipificacoes()) {
							if (incidencia.getDispositivo() != null) {
								IncidenciaBean i = new IncidenciaBean();
								i.setIdIncidencia(incidencia.getId());
								i.setTexto(eventoCriminal.getTipoInformacaoCriminal().getDescricao() + " - " + incidencia.getDispositivo().getTextoFinal());
								i.setIdEventoCriminal(eventoCriminal.getId());
								i.setIdProcessoParteLegacy(idProcessoParteLegacy);
								incidenciaBeanList.add(i);
							}
						}
					}
				}
				ret.set(ResponseEntity.ok(incidenciaBeanList));
			}
		});
		return ret.get();
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/eventosCriminais/processos/{idProcesso}/partes/{idProcessoParteLegacy}/idEventoCriminal/{idEventoCriminal}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Boolean> removerEventoCriminal(@PathVariable("idProcesso") Long idProcesso,
														 @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
														 @PathVariable("idEventoCriminal") String idEventoCriminal){
		AtomicReference<ResponseEntity<Boolean>> ret = new AtomicReference<ResponseEntity<Boolean>>(ResponseEntity.noContent().build());

		
		if(idEventoCriminal != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.filter( i -> !CollectionUtils.isEmpty(i.getEventosCriminais())).ifPresent(infoCriminal -> {
			
				
				EventoCriminalBean eventoCriminalBean = infoCriminal.getEventosCriminais().stream().filter(e -> e.getId().equals(idEventoCriminal)).findAny()                                      // If 'findAny' then return found
		                .orElse(null);   
				
				
				if(infoCriminal.getConteudo().removeEventoCriminal(eventoCriminalBean)) {
					this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
					ret.set(ResponseEntity.ok(Boolean.TRUE));
				}
			});
		}
		
		
		return ret.get();
	}
	
	
	private List<InformacaoCriminal> definirTipoDeEventoCriminalInicial(List<InformacaoCriminal> listaIcs) {
		for(InformacaoCriminal ic : listaIcs) {
			for(EventoCriminalBean ecb : ic.getConteudo().getIndiciamento()) {
				ecb.setDataCadastro(new Date());
				ecb.setData(new Date());
				ecb.setTipoInformacaoCriminal(this.tipoInformacaoCriminalService.recuperaTipoInformacaoCriminalInicioProcesso());
			}
			for(EventoCriminalBean ecb : ic.getConteudo().getOferecimentoDenuncia()) {
				ecb.setData(new Date());
				ecb.setDataCadastro(new Date());
				if (ecb.getTipoInformacaoCriminal() != null) {
					ecb.setTipoInformacaoCriminal(this.tipoInformacaoCriminalService.recuperaTipoInformacaoCriminal(ecb.getTipoInformacaoCriminal().getCodTipoIc()));
				} else {
					ecb.setTipoInformacaoCriminal(this.tipoInformacaoCriminalService.recuperaTipoInformacaoCriminal(TipoEventoCriminalEnum.OFD));
				}
			}
		}
		return listaIcs;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/sentencasAbsolutorias/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaAbsolutoriaBean> criarSentencaAbsolutoriaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaAbsolutoriaBean sentencaAbsolutoria) {
		ResponseEntity<SentencaAbsolutoriaBean> res = ResponseEntity.noContent().build();
		if (sentencaAbsolutoria != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			if (infoCriminal.getConteudo().getSentencasAbsolutorias() == null) {
				infoCriminal.getConteudo().setSentencasAbsolutorias(new ArrayList<SentencaAbsolutoriaBean>());
			}
			sentencaAbsolutoria.setDataCadastro(new Date());
			infoCriminal.getConteudo().getSentencasAbsolutorias().add(sentencaAbsolutoria);
			infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getSentencasAbsolutorias().get(0));
	}
		return res;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/sentencasAbsolutorias/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaAbsolutoriaBean> alterarSentencaAbsolutoriaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaAbsolutoriaBean sentencaAbsolutoria) {
		AtomicReference<ResponseEntity<SentencaAbsolutoriaBean>> res = new AtomicReference<ResponseEntity<SentencaAbsolutoriaBean>>(ResponseEntity.noContent().build());

		if (sentencaAbsolutoria != null) {
			this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
						if (infoCriminal.getConteudo().getSentencasAbsolutorias() == null) {
							infoCriminal.getConteudo().setSentencasAbsolutorias(new ArrayList<SentencaAbsolutoriaBean>());
						}
						ListIterator<SentencaAbsolutoriaBean> iterator = infoCriminal.getConteudo().getSentencasAbsolutorias()
								.listIterator();
						while (iterator.hasNext()) {
							SentencaAbsolutoriaBean sentencaAbsolutoriaBean = iterator.next();
							if (sentencaAbsolutoriaBean.getId().equals(sentencaAbsolutoria.getId())) {
								iterator.set(sentencaAbsolutoria);
							}
						}
						informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
						res.set(ResponseEntity.ok(sentencaAbsolutoria)); 
					});
		}
		return res.get();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/sentencasAbsolutorias/processos/{idProcesso}/partes/{idProcessoParteLegacy}/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Boolean> removerSentencaAbsolutoriaParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaAbsolutoriaBean sentencaAbsolutoria) {
		AtomicBoolean deleted = new AtomicBoolean(Boolean.FALSE);
		if (sentencaAbsolutoria != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.filter( i -> !CollectionUtils.isEmpty(i.getConteudo().getSentencasAbsolutorias())).ifPresent(infoCriminal -> {
				List<SentencaAbsolutoriaBean> listaSentencasAbsolutorias = infoCriminal.getConteudo()
						.getSentencasAbsolutorias();
				deleted.set(listaSentencasAbsolutorias.remove(sentencaAbsolutoria));
				infoCriminal.getConteudo().setSentencasAbsolutorias(listaSentencasAbsolutorias);
				this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			});
		}
		return ResponseEntity.ok(deleted.get());
	}	
	
	@RequestMapping(method = RequestMethod.POST, path = "/sentencasExtincaoPunibilidade/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaExtincaoPunibilidadeBean> criarSentencaExtincaoPunibilidadeParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaExtincaoPunibilidadeBean sentencaExtincaoPunibilidade) {
		ResponseEntity<SentencaExtincaoPunibilidadeBean> res = ResponseEntity.noContent().build();
		if (sentencaExtincaoPunibilidade != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			
			if (infoCriminal.getConteudo().getSentencasExtincaoPunibilidade() == null) {
				infoCriminal.getConteudo().setSentencasExtincaoPunibilidade(new ArrayList<SentencaExtincaoPunibilidadeBean>());
			}
			sentencaExtincaoPunibilidade.setDataCadastro(new Date());
			infoCriminal.getConteudo().getSentencasExtincaoPunibilidade().add(sentencaExtincaoPunibilidade);
			infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getSentencasExtincaoPunibilidade().get(0));
		}
		return res;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/sentencasExtincaoPunibilidade/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaExtincaoPunibilidadeBean> alterarSentencaExtincaoPunibilidadeParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaExtincaoPunibilidadeBean sentencaExtincaoPunibilidade) {
		AtomicReference<ResponseEntity<SentencaExtincaoPunibilidadeBean>> res = new AtomicReference<ResponseEntity<SentencaExtincaoPunibilidadeBean>>(ResponseEntity.noContent().build());

		if (sentencaExtincaoPunibilidade != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
				if (infoCriminal.getConteudo().getSentencasExtincaoPunibilidade() == null) {
					infoCriminal.getConteudo().setSentencasExtincaoPunibilidade(new ArrayList<SentencaExtincaoPunibilidadeBean>());
				}
				ListIterator<SentencaExtincaoPunibilidadeBean> iterator = infoCriminal.getConteudo().getSentencasExtincaoPunibilidade().listIterator();
				while (iterator.hasNext()) {
					SentencaExtincaoPunibilidadeBean sentencaExtincaoPunibilidadeBean = iterator.next();
					if (sentencaExtincaoPunibilidadeBean.getId().equals(sentencaExtincaoPunibilidade.getId())) {
						iterator.set(sentencaExtincaoPunibilidade);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(sentencaExtincaoPunibilidade));
			});
		}
		return res.get();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/desclassificacoesProcesso/processos/{idProcesso}/partes/{idProcessoParteLegacy}")
	@InboundRequest
	public ResponseEntity<List<DesclassificacaoProcessoBean>> recuperarDesclassificacoesProcessoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy) {
		ResponseEntity<List<DesclassificacaoProcessoBean>> ret = ResponseEntity.noContent().build();
		List<DesclassificacaoProcessoBean> listaDesclassificacoes = informacaoCriminalService
				.recuperarDesclassificacoesProcessoPorIdProcessoEhIdProcessoParteLegacy(idProcesso,
						idProcessoParteLegacy);
		if (!CollectionUtils.isEmpty(listaDesclassificacoes)) {
			ret = ResponseEntity.ok(listaDesclassificacoes);
		}
		return ret;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/desclassificacoesProcesso/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<DesclassificacaoProcessoBean> criarDesclassificacaoProcessoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody DesclassificacaoProcessoBean desclassificacaoProcesso) {
		ResponseEntity<DesclassificacaoProcessoBean> res = ResponseEntity.noContent().build();
		if (desclassificacaoProcesso != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			if (infoCriminal.getConteudo().getDesclassificacoesProcesso() == null) {
				infoCriminal.getConteudo()
						.setDesclassificacoesProcesso(new ArrayList<DesclassificacaoProcessoBean>());
			}
			desclassificacaoProcesso.setDataCadastro(new Date());
			infoCriminal.getConteudo().getDesclassificacoesProcesso().add(desclassificacaoProcesso);
			infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getDesclassificacoesProcesso().get(0));
		}
		return res;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/desclassificacoesProcesso/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<DesclassificacaoProcessoBean> alterarDesclassificacaoProcesso(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody DesclassificacaoProcessoBean desclassificacaoProcesso) {
		AtomicReference<ResponseEntity<DesclassificacaoProcessoBean>> res = new AtomicReference<ResponseEntity<DesclassificacaoProcessoBean>>(ResponseEntity.noContent().build());

		if (desclassificacaoProcesso != null) {
			this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
						if (infoCriminal.getConteudo().getDesclassificacoesProcesso() == null) {
							infoCriminal.getConteudo()
									.setDesclassificacoesProcesso(new ArrayList<DesclassificacaoProcessoBean>());
						}
						ListIterator<DesclassificacaoProcessoBean> iterator = infoCriminal.getConteudo()
								.getDesclassificacoesProcesso().listIterator();
						while (iterator.hasNext()) {
							DesclassificacaoProcessoBean desclassificacaoProcessoBean = iterator.next();
							if (desclassificacaoProcessoBean.getId().equals(desclassificacaoProcesso.getId())) {
								iterator.set(desclassificacaoProcesso);
							}
						}
						informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
						res.set(ResponseEntity.ok(desclassificacaoProcesso));
					});
		}
		return res.get();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/desclassificacoesProcesso/processos/{idProcesso}/partes/{idProcessoParteLegacy}/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Boolean> removerDesclassificacoesProcessoParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody DesclassificacaoProcessoBean desclassificacaoProcesso) {
		AtomicBoolean deleted = new AtomicBoolean(Boolean.FALSE);
		if (desclassificacaoProcesso != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.filter( i -> !CollectionUtils.isEmpty(i.getConteudo().getDesclassificacoesProcesso())).ifPresent(infoCriminal -> {
				List<DesclassificacaoProcessoBean> listaDesclassificacoes = infoCriminal.getConteudo()
						.getDesclassificacoesProcesso();
				deleted.set(listaDesclassificacoes.remove(desclassificacaoProcesso)); 
				infoCriminal.getConteudo().setDesclassificacoesProcesso(listaDesclassificacoes);
				this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			});
		}
		return ResponseEntity.ok(deleted.get());
	}
	@RequestMapping(method = RequestMethod.GET, path = "/sentencasImpronuncia/processos/{idProcesso}/partes/{idProcessoParteLegacy}")
	@InboundRequest
	public ResponseEntity<List<SentencaImpronunciaProcessoBean>> recuperarSentencasImpronunciaProcessoParte(@PathVariable("idProcesso") Long idProcesso, @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy){
		ResponseEntity<List<SentencaImpronunciaProcessoBean>> ret = ResponseEntity.noContent().build();
		List<SentencaImpronunciaProcessoBean> listaSentencas = informacaoCriminalService.recuperarSentencasImpronunciaPorIdProcessoEhIdProcessoParteLegacy(idProcesso, idProcessoParteLegacy);
		if(!CollectionUtils.isEmpty(listaSentencas)) {
			ret = ResponseEntity.ok(listaSentencas);
		}
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/sentencasImpronuncia/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaImpronunciaProcessoBean> criarSentencaImpronunciaParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaImpronunciaProcessoBean sentencaImpronuncia) {
		ResponseEntity<SentencaImpronunciaProcessoBean> res = ResponseEntity.noContent().build();
		if (sentencaImpronuncia != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			if (infoCriminal.getConteudo().getSentencasImpronuncia() == null) {
				infoCriminal.getConteudo().setSentencasImpronuncia(new ArrayList<SentencaImpronunciaProcessoBean>());
			}
			sentencaImpronuncia.setDataCadastro(new Date());
			infoCriminal.getConteudo().getSentencasImpronuncia().add(sentencaImpronuncia);
			infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getSentencasImpronuncia().get(0));
		
		}
		return res;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/sentencasImpronuncia/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaImpronunciaProcessoBean> alterarSentencaImpronuncia(@PathVariable("idProcesso") Long idProcesso, 
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
			@RequestBody SentencaImpronunciaProcessoBean sentencaImpronunciaProcesso){
		AtomicReference<ResponseEntity<SentencaImpronunciaProcessoBean>> res = new AtomicReference<ResponseEntity<SentencaImpronunciaProcessoBean>>(ResponseEntity.noContent().build());

		if (sentencaImpronunciaProcesso != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
				if (infoCriminal.getConteudo().getSentencasImpronuncia() == null) {
					infoCriminal.getConteudo().setSentencasImpronuncia(new ArrayList<SentencaImpronunciaProcessoBean>());
				}
				ListIterator<SentencaImpronunciaProcessoBean> iterator = infoCriminal.getConteudo().getSentencasImpronuncia().listIterator();
				while (iterator.hasNext()) {
					SentencaImpronunciaProcessoBean sentencaImpronunciaProcessoBean = iterator.next();
					if (sentencaImpronunciaProcessoBean.getId().equals(sentencaImpronunciaProcesso.getId())) {
						iterator.set(sentencaImpronunciaProcesso);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(sentencaImpronunciaProcesso)); 
			});
		}
		return res.get();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/sentencasImpronuncia/processos/{idProcesso}/partes/{idProcessoParteLegacy}/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Boolean> removerSentencasImpronunciaProcessoParte(@PathVariable("idProcesso") Long idProcesso,
													  @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													  @RequestBody SentencaImpronunciaProcessoBean sentencaImpronunciaProcesso) {
		AtomicBoolean deleted = new AtomicBoolean(Boolean.FALSE);
		if(sentencaImpronunciaProcesso != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.filter( i -> !CollectionUtils.isEmpty(i.getConteudo().getSentencasImpronuncia())).ifPresent(infoCriminal -> {
				List<SentencaImpronunciaProcessoBean> listaSentencasImpronuncia = infoCriminal.getConteudo().getSentencasImpronuncia();
				deleted.set( listaSentencasImpronuncia.remove(sentencaImpronunciaProcesso));
				infoCriminal.getConteudo().setSentencasImpronuncia(listaSentencasImpronuncia);
				this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			});
		}
		return ResponseEntity.ok(deleted.get());
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/sentencasPronuncia/processos/{idProcesso}/partes/{idProcessoParteLegacy}")
	@InboundRequest
	public ResponseEntity<List<SentencaPronunciaProcessoBean>> recuperarSentencasPronunciaProcessoParte(@PathVariable("idProcesso") Long idProcesso, @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy){
		ResponseEntity<List<SentencaPronunciaProcessoBean>> ret = ResponseEntity.noContent().build();
		List<SentencaPronunciaProcessoBean> listaSentencas = informacaoCriminalService.recuperarSentencasPronunciaPorIdProcessoEhIdProcessoParteLegacy(idProcesso, idProcessoParteLegacy);
		if(!CollectionUtils.isEmpty(listaSentencas)) {
			ret = ResponseEntity.ok(listaSentencas);
		}
		return ret;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/sentencasPronuncia/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaPronunciaProcessoBean> criarSentencaPronunciaParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaPronunciaProcessoBean sentencaPronuncia) {
		ResponseEntity<SentencaPronunciaProcessoBean> res = ResponseEntity.noContent().build();
		if (sentencaPronuncia != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			if (infoCriminal.getConteudo().getSentencasPronuncia() == null) {
				infoCriminal.getConteudo().setSentencasPronuncia(new ArrayList<SentencaPronunciaProcessoBean>());
			}
			sentencaPronuncia.setDataCadastro(new Date());
			infoCriminal.getConteudo().getSentencasPronuncia().add(sentencaPronuncia);
			infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getSentencasPronuncia().get(0));
		}
		return res;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/sentencasPronuncia/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaPronunciaProcessoBean> alterarSentencaPronuncia(@PathVariable("idProcesso") Long idProcesso, 
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
			@RequestBody SentencaPronunciaProcessoBean sentencaPronunciaProcesso){
		AtomicReference<ResponseEntity<SentencaPronunciaProcessoBean>> res = new AtomicReference<ResponseEntity<SentencaPronunciaProcessoBean>>(ResponseEntity.noContent().build());

		if (sentencaPronunciaProcesso != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
				if (infoCriminal.getConteudo().getSentencasPronuncia() == null) {
					infoCriminal.getConteudo().setSentencasPronuncia(new ArrayList<SentencaPronunciaProcessoBean>());
				}
				ListIterator<SentencaPronunciaProcessoBean> iterator = infoCriminal.getConteudo().getSentencasPronuncia().listIterator();
				while (iterator.hasNext()) {
					SentencaPronunciaProcessoBean sentencaPronunciaProcessoBean = iterator.next();
					if (sentencaPronunciaProcessoBean.getId().equals(sentencaPronunciaProcesso.getId())) {
						iterator.set(sentencaPronunciaProcesso);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(sentencaPronunciaProcesso));
			});
		}
		return res.get();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/sentencasPronuncia/processos/{idProcesso}/partes/{idProcessoParteLegacy}/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<Boolean> removerSentencasPronunciaProcessoParte(@PathVariable("idProcesso") Long idProcesso,
													  @PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													  @RequestBody SentencaPronunciaProcessoBean sentencaPronunciaProcesso) {
		AtomicBoolean deleted = new AtomicBoolean(Boolean.FALSE);
		if(sentencaPronunciaProcesso != null) {
			this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.filter( i -> !CollectionUtils.isEmpty(i.getConteudo().getSentencasPronuncia())).ifPresent(infoCriminal -> {
				List<SentencaPronunciaProcessoBean> listaSentencasPronuncia = infoCriminal.getConteudo().getSentencasPronuncia();
				deleted.set(listaSentencasPronuncia.remove(sentencaPronunciaProcesso));
				infoCriminal.getConteudo().setSentencasPronuncia(listaSentencasPronuncia);
				this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			});
		}
		return ResponseEntity.ok(deleted.get());
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/citacoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<CitacaoProcessoBean> criarCitacaoParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody CitacaoProcessoBean citacao) {
		ResponseEntity<CitacaoProcessoBean> res = ResponseEntity.noContent().build();
		if (citacao != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			if (infoCriminal.getConteudo().getCitacoes() == null) {
				infoCriminal.getConteudo().setCitacoes(new ArrayList<CitacaoProcessoBean>());
			}
			citacao.setDataCadastro(new Date());
			infoCriminal.getConteudo().getCitacoes().add(citacao);
			infoCriminal = informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getCitacoes().get(0));
		}
		return res;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/citacoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<CitacaoProcessoBean> alterarCitacao(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody CitacaoProcessoBean citacao) {
		AtomicReference<ResponseEntity<CitacaoProcessoBean>> res = new AtomicReference<ResponseEntity<CitacaoProcessoBean>>(ResponseEntity.noContent().build());

		if (citacao != null) {
			informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
						if (infoCriminal.getConteudo().getCitacoes() == null) {
							infoCriminal.getConteudo().setCitacoes(new ArrayList<CitacaoProcessoBean>());
						}
						ListIterator<CitacaoProcessoBean> iterator = infoCriminal.getConteudo().getCitacoes().listIterator();
						while (iterator.hasNext()) {
							CitacaoProcessoBean citacaoProcessoBean = iterator.next();
							if (citacaoProcessoBean.getId().equals(citacao.getId())) {
								iterator.set(citacao);
							}
						}
						informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
						res.set(ResponseEntity.ok(citacao));
					});
		}
		return res.get();
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/recebimentosAditamentoDenuncia/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<RecebimentoAditamentoDenunciaBean> criarRecebimentosAditamentoDenunciaProcessoParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody RecebimentoAditamentoDenunciaBean recebimentosAditamentoDenuncia) {
		ResponseEntity<RecebimentoAditamentoDenunciaBean> res = ResponseEntity.noContent().build();
		if (recebimentosAditamentoDenuncia != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			if (infoCriminal.getConteudo().getRecebimentosAditamentoDenuncia() == null) {
				infoCriminal.getConteudo().setRecebimentosAditamentoDenuncia(new ArrayList<RecebimentoAditamentoDenunciaBean>());
			}
			recebimentosAditamentoDenuncia.setDataCadastro(new Date());
			infoCriminal.getConteudo().getRecebimentosAditamentoDenuncia().add(recebimentosAditamentoDenuncia);
			infoCriminal = informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(infoCriminal.getConteudo().getRecebimentosAditamentoDenuncia().get(0));
		}
		return res;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/recebimentosAditamentoDenuncia/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<RecebimentoAditamentoDenunciaBean> alterarRecebimentosAditamentoDenunciaProcessoParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody RecebimentoAditamentoDenunciaBean recebimentoAditamentoDenuncia) {
		AtomicReference<ResponseEntity<RecebimentoAditamentoDenunciaBean>> res = new AtomicReference<ResponseEntity<RecebimentoAditamentoDenunciaBean>>(ResponseEntity.noContent().build());
		if (recebimentoAditamentoDenuncia != null) {
			informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal -> {
						if (infoCriminal.getConteudo().getRecebimentosAditamentoDenuncia() == null) {
							infoCriminal.getConteudo().setRecebimentosAditamentoDenuncia(new ArrayList<RecebimentoAditamentoDenunciaBean>());
						}
						ListIterator<RecebimentoAditamentoDenunciaBean> iterator = infoCriminal.getConteudo().getRecebimentosAditamentoDenuncia().listIterator();
						while (iterator.hasNext()) {
							RecebimentoAditamentoDenunciaBean recebimentoAditamentoDenunciaBean = iterator.next();
							if (recebimentoAditamentoDenunciaBean.getId().equals(recebimentoAditamentoDenuncia.getId())) {
								iterator.set(recebimentoAditamentoDenuncia);
							}
						}
						informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
						res.set(ResponseEntity.ok(recebimentoAditamentoDenuncia)); 
					});
		}
		return res.get();
	}
	
	@PostMapping(path = "/litispendencias-coisa-julgada/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<LitispendenciaCoisaJulgadaBean> criarLitispendenciaCoisaJulgadaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody LitispendenciaCoisaJulgadaBean litispendenciaCoisaJulgada) {
		ResponseEntity<LitispendenciaCoisaJulgadaBean> res = ResponseEntity.noContent().build();
		if (litispendenciaCoisaJulgada != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getLitispendenciasCoisaJulgada() == null) {
				infoCriminal.getConteudo().setLitispendenciasCoisaJulgada(new ArrayList<>());
			}
			litispendenciaCoisaJulgada.setDataCadastro(new Date());
			infoCriminal.getConteudo().getLitispendenciasCoisaJulgada().add(litispendenciaCoisaJulgada);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(litispendenciaCoisaJulgada);
		}
		return res;
	}
	
	@PutMapping(path = "/litispendencias-coisa-julgada/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<LitispendenciaCoisaJulgadaBean> alterarLitispendenciaCoisaJulgadaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody LitispendenciaCoisaJulgadaBean litispendenciaCoisaJulgada) {
		AtomicReference<ResponseEntity<LitispendenciaCoisaJulgadaBean>> res = new AtomicReference<ResponseEntity<LitispendenciaCoisaJulgadaBean>>(ResponseEntity.noContent().build());
		if (litispendenciaCoisaJulgada != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				if (infoCriminal.getConteudo().getLitispendenciasCoisaJulgada() == null) {
					infoCriminal.getConteudo().setLitispendenciasCoisaJulgada(new ArrayList<>());
				}
				ListIterator<LitispendenciaCoisaJulgadaBean> iterator = infoCriminal.getConteudo()
						.getLitispendenciasCoisaJulgada().listIterator();
				while (iterator.hasNext()) {
					LitispendenciaCoisaJulgadaBean litispendenciaCoisaJulgadaBean = iterator.next();
					if (litispendenciaCoisaJulgadaBean.getId().equals(litispendenciaCoisaJulgada.getId())) {
						iterator.set(litispendenciaCoisaJulgada);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(litispendenciaCoisaJulgada));
			});
		}
		return res.get();
	}

	@PostMapping(path = "/desmembramento/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<DesmembramentoBean> criarDesmembramentoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody DesmembramentoBean desmembramento) {
		ResponseEntity<DesmembramentoBean> res = ResponseEntity.noContent().build();
		if (desmembramento != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getDesmembramentos() == null) {
				infoCriminal.getConteudo().setDesmembramentos(new ArrayList<>());
			}
			desmembramento.setDataCadastro(new Date());
			infoCriminal.getConteudo().getDesmembramentos().add(desmembramento);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(desmembramento);
		}
		return res;
	}
	
	@PutMapping(path = "/desmembramento/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<DesmembramentoBean> alterarDesmembramentoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody DesmembramentoBean desmembramento) {
		AtomicReference<ResponseEntity<DesmembramentoBean>> res = new AtomicReference<ResponseEntity<DesmembramentoBean>>(ResponseEntity.noContent().build());
		if (desmembramento != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				if (infoCriminal.getConteudo().getDesmembramentos() == null) {
					infoCriminal.getConteudo().setDesmembramentos(new ArrayList<>());
				}
				ListIterator<DesmembramentoBean> iterator = infoCriminal.getConteudo()
						.getDesmembramentos().listIterator();
				while (iterator.hasNext()) {
					DesmembramentoBean desmembramentoBean = iterator.next();
					if (desmembramentoBean.getId().equals(desmembramento.getId())) {
						iterator.set(desmembramento);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(desmembramento));
				
			});
		}
		return res.get();
	}

	@PostMapping(path = "/declinios-competencia/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<DeclinioCompetenciaBean> criarDeclinioCompetenciaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody DeclinioCompetenciaBean declinioCompetencia) {
		ResponseEntity<DeclinioCompetenciaBean> res = ResponseEntity.noContent().build();
		if (declinioCompetencia != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getDecliniosCompetencia() == null) {
				infoCriminal.getConteudo().setDecliniosCompetencia(new ArrayList<>());
			}
			declinioCompetencia.setDataCadastro(new Date());
			infoCriminal.getConteudo().getDecliniosCompetencia().add(declinioCompetencia);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(declinioCompetencia);
		}
		return res;
	}

	@PutMapping(path = "/declinios-competencia/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<DeclinioCompetenciaBean> alterarDeclinioCompetenciaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody DeclinioCompetenciaBean declinioCompetencia) {
		AtomicReference<ResponseEntity<DeclinioCompetenciaBean>> res = new AtomicReference<ResponseEntity<DeclinioCompetenciaBean>>(ResponseEntity.noContent().build());
		if (declinioCompetencia != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				if (infoCriminal.getConteudo().getDecliniosCompetencia() == null) {
					infoCriminal.getConteudo().setDecliniosCompetencia(new ArrayList<>());
				}
				ListIterator<DeclinioCompetenciaBean> iterator = infoCriminal.getConteudo().getDecliniosCompetencia()
						.listIterator();
				while (iterator.hasNext()) {
					DeclinioCompetenciaBean declinioCompetenciaBean = iterator.next();
					if (declinioCompetenciaBean.getId().equals(declinioCompetencia.getId())) {
						iterator.set(declinioCompetencia);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(declinioCompetencia));
				
			});
		}
		return res.get();
	}

	@PostMapping(path = "/ausencias-condicoes-acao/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<AusenciaCondicoesAcaoBean> criarAusenciasCondicoesAcaoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody AusenciaCondicoesAcaoBean ausenciaCondicoesAcao) {
		ResponseEntity<AusenciaCondicoesAcaoBean> res = ResponseEntity.noContent().build();
		if (ausenciaCondicoesAcao != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getAusenciasCondicoesAcao() == null) {
				infoCriminal.getConteudo().setAusenciasCondicoesAcao(new ArrayList<>());
			}
			ausenciaCondicoesAcao.setDataCadastro(new Date());
			infoCriminal.getConteudo().getAusenciasCondicoesAcao().add(ausenciaCondicoesAcao);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(ausenciaCondicoesAcao);
		}
		return res;
	}

	@PutMapping(path = "/ausencias-condicoes-acao/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<AusenciaCondicoesAcaoBean> alterarAusenciasCondicoesAcaoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody AusenciaCondicoesAcaoBean ausenciaCondicoesAcao) {
		AtomicReference<ResponseEntity<AusenciaCondicoesAcaoBean>> res = new AtomicReference<ResponseEntity<AusenciaCondicoesAcaoBean>>(ResponseEntity.noContent().build());
		if (ausenciaCondicoesAcao != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				if (infoCriminal.getConteudo().getAusenciasCondicoesAcao() == null) {
					infoCriminal.getConteudo().setAusenciasCondicoesAcao(new ArrayList<>());
				}
				ListIterator<AusenciaCondicoesAcaoBean> iterator = infoCriminal.getConteudo().getAusenciasCondicoesAcao()
						.listIterator();
				while (iterator.hasNext()) {
					AusenciaCondicoesAcaoBean ausenciaCondicoesAcaoBean = iterator.next();
					if (ausenciaCondicoesAcaoBean.getId().equals(ausenciaCondicoesAcao.getId())) {
						iterator.set(ausenciaCondicoesAcao);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(ausenciaCondicoesAcao));
				
			});
		}
		return res.get();
	}

	@PostMapping(path = "/trancamento/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<TrancamentoBean> criarTrancamentoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody TrancamentoBean trancamento) {
		ResponseEntity<TrancamentoBean> res = ResponseEntity.noContent().build();
		if (trancamento != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getTrancamentos() == null) {
				infoCriminal.getConteudo().setTrancamentos(new ArrayList<>());
			}
			trancamento.setDataCadastro(new Date());
			infoCriminal.getConteudo().getTrancamentos().add(trancamento);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(trancamento);
		}
		return res;
	}
	
	@PutMapping(path = "/trancamento/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<TrancamentoBean> alterarTrancamentoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody TrancamentoBean trancamento) {
		AtomicReference<ResponseEntity<TrancamentoBean>> res = new AtomicReference<ResponseEntity<TrancamentoBean>>(ResponseEntity.noContent().build());
		if (trancamento != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				if (infoCriminal.getConteudo().getTrancamentos() == null) {
					infoCriminal.getConteudo().setTrancamentos(new ArrayList<>());
				}
				ListIterator<TrancamentoBean> iterator = infoCriminal.getConteudo()
						.getTrancamentos().listIterator();
				while (iterator.hasNext()) {
					TrancamentoBean trancamentoBean = iterator.next();
					if (trancamentoBean.getId().equals(trancamento.getId())) {
						iterator.set(trancamento);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(trancamento));
			});
		}
		return res.get();
	}

	@PostMapping(path = "/sentencas-absolutorias-improprias/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaAbsolutoriaImpropriaBean> criarSentencaAbsolutoriaImpropriaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaAbsolutoriaImpropriaBean sentencaAbsolutoriaImpropria) {
		ResponseEntity<SentencaAbsolutoriaImpropriaBean> res = ResponseEntity.noContent().build();
		if (sentencaAbsolutoriaImpropria != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getSentencasAbsolutoriasImproprias() == null) {
				infoCriminal.getConteudo().setSentencasAbsolutoriasImproprias(new ArrayList<>());
			}
			sentencaAbsolutoriaImpropria.setDataCadastro(new Date());
			infoCriminal.getConteudo().getSentencasAbsolutoriasImproprias().add(sentencaAbsolutoriaImpropria);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(sentencaAbsolutoriaImpropria);
		}
		return res;
	}

	@PutMapping(path = "/sentencas-absolutorias-improprias/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaAbsolutoriaImpropriaBean> alterarSentencaAbsolutoriaImpropriaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaAbsolutoriaImpropriaBean sentencaAbsolutoriaImpropria) {
		AtomicReference<ResponseEntity<SentencaAbsolutoriaImpropriaBean>> res = new AtomicReference<ResponseEntity<SentencaAbsolutoriaImpropriaBean>>(ResponseEntity.noContent().build());
		if (sentencaAbsolutoriaImpropria != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				if (infoCriminal.getConteudo().getSentencasAbsolutoriasImproprias() == null) {
					infoCriminal.getConteudo().setSentencasAbsolutoriasImproprias(new ArrayList<>());
				}
				ListIterator<SentencaAbsolutoriaImpropriaBean> iterator = infoCriminal.getConteudo()
						.getSentencasAbsolutoriasImproprias().listIterator();
				while (iterator.hasNext()) {
					SentencaAbsolutoriaImpropriaBean sentencaAbsolutoriaImpropriaBean = iterator.next();
					if (sentencaAbsolutoriaImpropriaBean.getId().equals(sentencaAbsolutoriaImpropria.getId())) {
						iterator.set(sentencaAbsolutoriaImpropria);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(sentencaAbsolutoriaImpropria));
				
			});
		}
		return res.get();
	}
	
	@PostMapping(path = "/presos-outro-processo/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<PresoOutroProcessoBean> criarPresoOutroProcessoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody PresoOutroProcessoBean presoOutroProcesso) {
		ResponseEntity<PresoOutroProcessoBean> res = ResponseEntity.noContent().build();
		if (presoOutroProcesso != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getPresosOutroProcesso() == null) {
				infoCriminal.getConteudo().setPresosOutroProcesso(new ArrayList<PresoOutroProcessoBean>());
			}
			presoOutroProcesso.setDataCadastro(new Date());
			infoCriminal.getConteudo().getPresosOutroProcesso().add(presoOutroProcesso);
			infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(presoOutroProcesso);
		}
		return res;
	}

	@PutMapping(path = "/presos-outro-processo/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<PresoOutroProcessoBean> alterarPresoOutroProcessoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody PresoOutroProcessoBean presoOutroProcesso) {
		AtomicReference<ResponseEntity<PresoOutroProcessoBean>> res = new AtomicReference<ResponseEntity<PresoOutroProcessoBean>>(ResponseEntity.noContent().build());
		if (presoOutroProcesso != null) {
			this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
						
						if (infoCriminal.getConteudo().getPresosOutroProcesso() == null) {
							infoCriminal.getConteudo().setPresosOutroProcesso(new ArrayList<PresoOutroProcessoBean>());
						}
						ListIterator<PresoOutroProcessoBean> iterator = infoCriminal.getConteudo().getPresosOutroProcesso()
								.listIterator();
						while (iterator.hasNext()) {
							PresoOutroProcessoBean presoOutroProcessoBean = iterator.next();
							if (presoOutroProcessoBean.getId().equals(presoOutroProcesso.getId())) {
								iterator.set(presoOutroProcesso);
							}
						}
						informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
						res.set(ResponseEntity.ok(presoOutroProcesso));
					});
		}
		return res.get();
	}
	
	@PostMapping(path = "/transacao-penal/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<TransacaoPenalBean> criarTransacaoPenalParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody TransacaoPenalBean transacaoPenal) {
		ResponseEntity<TransacaoPenalBean> res = ResponseEntity.noContent().build();
		if (transacaoPenal != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getTransacoesPenais() == null) {
				infoCriminal.getConteudo().setTransacoesPenais(new ArrayList<>());
			}
			transacaoPenal.setDataCadastro(new Date());
			infoCriminal.getConteudo().getTransacoesPenais().add(transacaoPenal);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(transacaoPenal);
		}
		return res;
	}

	@PutMapping(path = "/transacao-penal/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<TransacaoPenalBean> alterarTransacaoPenalParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody TransacaoPenalBean transacaoPenal) {
		AtomicReference<ResponseEntity<TransacaoPenalBean>> res = new AtomicReference<ResponseEntity<TransacaoPenalBean>>(ResponseEntity.noContent().build());
		if (transacaoPenal != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				
				if (infoCriminal.getConteudo().getTransacoesPenais() == null) {
					infoCriminal.getConteudo().setTransacoesPenais(new ArrayList<>());
				}
				ListIterator<TransacaoPenalBean> iterator = infoCriminal.getConteudo().getTransacoesPenais()
						.listIterator();
				while (iterator.hasNext()) {
					TransacaoPenalBean transacaoPenalBean = iterator.next();
					if (transacaoPenalBean.getId().equals(transacaoPenal.getId())) {
						iterator.set(transacaoPenal);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(transacaoPenal));
			});
		}
		return res.get();
	}

	@GetMapping(path = "/tiposPenaExecucao", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoPenaExecucaoEnum>> recuperarTiposPenaExecucao() {
		return ResponseEntity.ok(Arrays.asList(TipoPenaExecucaoEnum.values()));
	}
	
	@GetMapping(path = "/recorrentes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecorrenteEnum>> recuperarRecorrentes() {
		return ResponseEntity.ok(Arrays.asList(RecorrenteEnum.values()));
	}
	
	@GetMapping(path = "/orgaosJulgadores", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrgaoJulgadorEnum>> recuperarOrgaosJulgadores() {
		return ResponseEntity.ok(Arrays.asList(OrgaoJulgadorEnum.values()));
	}
	
	@GetMapping(path = "/medidasSeguranca", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedidaSegurancaDTO>> recuperarMedidasSeguranca() {
		List<MedidaSegurancaDTO> list = new ArrayList<>();
		
		for(MedidaSegurancaEnum value : MedidaSegurancaEnum.values()) {
			list.add(new MedidaSegurancaDTO(value));
		}
		return ResponseEntity.ok(list);
	}
	
	@PostMapping(path = "/revogacoes-suspensao-condicional-processo/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<RevogacaoSuspensaoCondicionalProcessoBean> criarRevogacaoSuspensaoCondicionalProcessoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody RevogacaoSuspensaoCondicionalProcessoBean revogacaoSuspensaoCondicionalProcesso) {
		ResponseEntity<RevogacaoSuspensaoCondicionalProcessoBean> res = ResponseEntity.noContent().build();
		if (revogacaoSuspensaoCondicionalProcesso != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getRevogacoesSuspensaoCondicionalProcesso() == null) {
				infoCriminal.getConteudo().setRevogacoesSuspensaoCondicionalProcesso(new ArrayList<>());
			}
			revogacaoSuspensaoCondicionalProcesso.setDataCadastro(new Date());
			infoCriminal.getConteudo().getRevogacoesSuspensaoCondicionalProcesso()
			.add(revogacaoSuspensaoCondicionalProcesso);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(revogacaoSuspensaoCondicionalProcesso);
		}
		return res;
	}

	@PutMapping(path = "/revogacoes-suspensao-condicional-processo/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<RevogacaoSuspensaoCondicionalProcessoBean> alterarRevogacaoSuspensaoCondicionalProcessoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody RevogacaoSuspensaoCondicionalProcessoBean revogacaoSuspensaoCondicionalProcesso) {
		AtomicReference<ResponseEntity<RevogacaoSuspensaoCondicionalProcessoBean>> res = new AtomicReference<ResponseEntity<RevogacaoSuspensaoCondicionalProcessoBean>>(ResponseEntity.noContent().build());
		if (revogacaoSuspensaoCondicionalProcesso != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				if (infoCriminal.getConteudo().getRevogacoesSuspensaoCondicionalProcesso() == null) {
					infoCriminal.getConteudo().setRevogacoesSuspensaoCondicionalProcesso(new ArrayList<>());
				}
				ListIterator<RevogacaoSuspensaoCondicionalProcessoBean> iterator = infoCriminal.getConteudo()
						.getRevogacoesSuspensaoCondicionalProcesso().listIterator();
				while (iterator.hasNext()) {
					RevogacaoSuspensaoCondicionalProcessoBean revogacaoSuspensaoCondicionalProcessoBean = iterator
							.next();
					if (revogacaoSuspensaoCondicionalProcessoBean.getId()
							.equals(revogacaoSuspensaoCondicionalProcesso.getId())) {
						iterator.set(revogacaoSuspensaoCondicionalProcesso);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(revogacaoSuspensaoCondicionalProcesso));
			});
		}
		return res.get();
	}
	
	@PostMapping(path = "/nulidades-processos/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<NulidadeProcessoBean> criarNulidadeProcessoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody NulidadeProcessoBean nulidadeProcesso) {
		ResponseEntity<NulidadeProcessoBean> res = ResponseEntity.noContent().build();
		if (nulidadeProcesso != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getNulidadesProcessos() == null) {
				infoCriminal.getConteudo().setNulidadesProcessos(new ArrayList<>());
			}
			nulidadeProcesso.setDataCadastro(new Date());
			infoCriminal.getConteudo().getNulidadesProcessos().add(nulidadeProcesso);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(nulidadeProcesso);
		}
		return res;
	}
	
	@PutMapping(path = "/nulidades-processos/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<NulidadeProcessoBean> alterarNulidadeProcessoParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody NulidadeProcessoBean nulidadeProcesso) {
		AtomicReference<ResponseEntity<NulidadeProcessoBean>> res = new AtomicReference<ResponseEntity<NulidadeProcessoBean>>(ResponseEntity.noContent().build());
		if (nulidadeProcesso != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				if (infoCriminal.getConteudo().getNulidadesProcessos() == null) {
					infoCriminal.getConteudo().setNulidadesProcessos(new ArrayList<>());
				}
				ListIterator<NulidadeProcessoBean> iterator = infoCriminal.getConteudo().getNulidadesProcessos()
						.listIterator();
				while (iterator.hasNext()) {
					NulidadeProcessoBean nulidadeProcessoBean = iterator.next();
					if (nulidadeProcessoBean.getId().equals(nulidadeProcesso.getId())) {
						iterator.set(nulidadeProcesso);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(nulidadeProcesso));
				
			});
		}
		return res.get();
	}

	@PostMapping(path = "/homologacoes-composicoes-danos-civis/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<HomologacaoComposicaoDanoCivilBean> criarHomologacaoComposicaoDanosCivisParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody HomologacaoComposicaoDanoCivilBean homologacaoComposicaoDanosCivis) {
		ResponseEntity<HomologacaoComposicaoDanoCivilBean> res = ResponseEntity.noContent().build();
		if (homologacaoComposicaoDanosCivis != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});

			if (infoCriminal.getConteudo().getHomologacoesComposicoesDanosCivis() == null) {
				infoCriminal.getConteudo().setHomologacoesComposicoesDanosCivis(new ArrayList<>());
			}
			homologacaoComposicaoDanosCivis.setDataCadastro(new Date());
			infoCriminal.getConteudo().getHomologacoesComposicoesDanosCivis().add(homologacaoComposicaoDanosCivis);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(homologacaoComposicaoDanosCivis);
		}
		return res;
	}

	@PutMapping(path = "/homologacoes-composicoes-danos-civis/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<HomologacaoComposicaoDanoCivilBean> alterarHomologacaoComposicaoDanosCivisParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody HomologacaoComposicaoDanoCivilBean homologacaoComposicaoDanosCivis) {
		AtomicReference<ResponseEntity<HomologacaoComposicaoDanoCivilBean>> res = new AtomicReference<ResponseEntity<HomologacaoComposicaoDanoCivilBean>>(ResponseEntity.noContent().build());
		if (homologacaoComposicaoDanosCivis != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				if (infoCriminal.getConteudo().getHomologacoesComposicoesDanosCivis() == null) {
					infoCriminal.getConteudo().setHomologacoesComposicoesDanosCivis(new ArrayList<>());
				}
				ListIterator<HomologacaoComposicaoDanoCivilBean> iterator = infoCriminal.getConteudo().getHomologacoesComposicoesDanosCivis()
						.listIterator();
				while (iterator.hasNext()) {
					HomologacaoComposicaoDanoCivilBean homologacaoComposicaoDanosCivisBean = iterator.next();
					if (homologacaoComposicaoDanosCivisBean.getId().equals(homologacaoComposicaoDanosCivis.getId())) {
						iterator.set(homologacaoComposicaoDanosCivis);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(homologacaoComposicaoDanosCivis));
				
			});
		}
		return res.get();
	}
	@PostMapping(path = "/arquivamentos-incidencias/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<ArquivamentoIncidenciaBean> criarArquivamentoIncidenciaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody ArquivamentoIncidenciaBean arquivamentoIncidencia) {
		ResponseEntity<ArquivamentoIncidenciaBean> res = ResponseEntity.noContent().build();
		if (arquivamentoIncidencia != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getArquivamentosIncidencias() == null) {
				infoCriminal.getConteudo().setArquivamentosIncidencias(new ArrayList<>());
			}
			arquivamentoIncidencia.setDataCadastro(new Date());
			infoCriminal.getConteudo().getArquivamentosIncidencias().add(arquivamentoIncidencia);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(arquivamentoIncidencia);
		}
		return res;
	}

	@PutMapping(path = "/arquivamentos-incidencias/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<ArquivamentoIncidenciaBean> alterarArquivamentoIncidenciaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody ArquivamentoIncidenciaBean arquivamentoIncidencia) {
		AtomicReference<ResponseEntity<ArquivamentoIncidenciaBean>> res = new AtomicReference<ResponseEntity<ArquivamentoIncidenciaBean>>(ResponseEntity.noContent().build());
		if (arquivamentoIncidencia != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				if (infoCriminal.getConteudo().getArquivamentosIncidencias() == null) {
					infoCriminal.getConteudo().setArquivamentosIncidencias(new ArrayList<>());
				}
				ListIterator<ArquivamentoIncidenciaBean> iterator = infoCriminal.getConteudo()
						.getArquivamentosIncidencias().listIterator();
				while (iterator.hasNext()) {
					ArquivamentoIncidenciaBean arquivamentoIncidenciaBean = iterator.next();
					if (arquivamentoIncidenciaBean.getId().equals(arquivamentoIncidencia.getId())) {
						iterator.set(arquivamentoIncidencia);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(arquivamentoIncidencia));
			
			});
		}
		return res.get();
	}
	@PostMapping(path = "/sentencas-absolvicao-sumaria/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaAbsolvicaoSumariaBean> criarSentencaAbsolvicaoSumariaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaAbsolvicaoSumariaBean sentencaAbsolvicaoSumaria) {
		ResponseEntity<SentencaAbsolvicaoSumariaBean> res = ResponseEntity.noContent().build();
		if (sentencaAbsolvicaoSumaria != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			if (infoCriminal.getConteudo().getSentencasAbsolvicaoSumaria() == null) {
				infoCriminal.getConteudo().setSentencasAbsolvicaoSumaria(new ArrayList<>());
			}
			sentencaAbsolvicaoSumaria.setDataCadastro(new Date());
			infoCriminal.getConteudo().getSentencasAbsolvicaoSumaria().add(sentencaAbsolvicaoSumaria);
			this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
			res = ResponseEntity.ok(sentencaAbsolvicaoSumaria);
		}
		return res;
	}

	@PutMapping(path = "/sentencas-absolvicao-sumaria/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaAbsolvicaoSumariaBean> alterarSentencaAbsolvicaoSumariaParte(
			@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaAbsolvicaoSumariaBean sentencaAbsolvicaoSumaria) {
		AtomicReference<ResponseEntity<SentencaAbsolvicaoSumariaBean>> res = new AtomicReference<ResponseEntity<SentencaAbsolvicaoSumariaBean>>(ResponseEntity.noContent().build());
		if (sentencaAbsolvicaoSumaria != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).ifPresent(infoCriminal ->{
				if (infoCriminal.getConteudo().getSentencasAbsolvicaoSumaria() == null) {
					infoCriminal.getConteudo().setSentencasAbsolvicaoSumaria(new ArrayList<>());
				}
				ListIterator<SentencaAbsolvicaoSumariaBean> iterator = infoCriminal.getConteudo().getSentencasAbsolvicaoSumaria()
						.listIterator();
				while (iterator.hasNext()) {
					SentencaAbsolvicaoSumariaBean sentencaAbsolvicaoSumariaBean = iterator.next();
					if (sentencaAbsolvicaoSumariaBean.getId().equals(sentencaAbsolvicaoSumaria.getId())) {
						iterator.set(sentencaAbsolvicaoSumaria);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res.set(ResponseEntity.ok(sentencaAbsolvicaoSumaria));
			});
		}
		return res.get();
	}	

	@PostMapping(path = "/internacoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<InternacaoBean> criarInternacaoParte(@PathVariable("idProcesso") Long idProcesso, 
													@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													@RequestBody InternacaoBean internacao){
		ResponseEntity<InternacaoBean> res = ResponseEntity.noContent().build();
		if(internacao != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			
			if(infoCriminal != null) {
				if(infoCriminal.getConteudo().getInternacoes() == null) {
					infoCriminal.getConteudo().setInternacoes(new ArrayList<InternacaoBean>());
				}
				internacao.setDataCadastro(new Date());
				infoCriminal.getConteudo().getInternacoes().add(internacao);
				infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res = ResponseEntity.ok(internacao);
			}			
		}
		
		return res;
	}
	
	@PutMapping(path = "/internacoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<InternacaoBean> alterarInternacaoParte(@PathVariable("idProcesso") Long idProcesso, 
														@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
														@RequestBody InternacaoBean internacao){
		AtomicReference<ResponseEntity<InternacaoBean>> res = new AtomicReference<ResponseEntity<InternacaoBean>>(ResponseEntity.noContent().build());
		if (internacao != null) {
			this.informacaoCriminalService
				.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
				.ifPresent(infoCriminal -> {
					if(infoCriminal != null) {
						if(infoCriminal.getConteudo().getInternacoes() == null) {
							infoCriminal.getConteudo().setInternacoes(new ArrayList<InternacaoBean>());
						}
						ListIterator<InternacaoBean> iterator = infoCriminal.getConteudo().getInternacoes().listIterator();
						while (iterator.hasNext()) {
							InternacaoBean internacaoBean = iterator.next();
							if (internacaoBean.getId().equals(internacao.getId())) {
								iterator.set(internacao);
							}
						}
						informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
						res.set(ResponseEntity.ok(internacao));
					}
				});
		}
		return res.get();
	}
	
	@PostMapping(path = "/sentencasInfracionais/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaInfracionalBean> criarSentencaInfracionalParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaInfracionalBean sentencaInfracional) {
		ResponseEntity<SentencaInfracionalBean> res = ResponseEntity.noContent().build();
		if (sentencaInfracional != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			
			if (infoCriminal != null) {
				if (infoCriminal.getConteudo().getSentencasInfracionais() == null) {
					infoCriminal.getConteudo().setSentencasInfracionais(new ArrayList<SentencaInfracionalBean>());
				}
				sentencaInfracional.setDataCadastro(new Date());
				infoCriminal.getConteudo().getSentencasInfracionais().add(sentencaInfracional);
				infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res = ResponseEntity.ok(sentencaInfracional);
			}
		}
		return res;
	}
	
	@PutMapping(path = "/sentencasInfracionais/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaInfracionalBean> alterarSentencaInfracionalParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaInfracionalBean sentencaSocioeducativa) {
		AtomicReference<ResponseEntity<SentencaInfracionalBean>> res = new AtomicReference<ResponseEntity<SentencaInfracionalBean>>(ResponseEntity.noContent().build());
		if (sentencaSocioeducativa != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.ifPresent(infoCriminal -> {
				if (infoCriminal != null) {
					if (infoCriminal.getConteudo().getSentencasInfracionais() == null) {
						infoCriminal.getConteudo().setSentencasInfracionais(new ArrayList<SentencaInfracionalBean>());
					}
					ListIterator<SentencaInfracionalBean> iterator = infoCriminal.getConteudo().getSentencasInfracionais().listIterator();
					while (iterator.hasNext()) {
						SentencaInfracionalBean sentencaInfracionalBean = iterator.next();
						if (sentencaInfracionalBean.getId().equals(sentencaSocioeducativa.getId())) {
							iterator.set(sentencaSocioeducativa);
						}
					}
					informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
					res.set(ResponseEntity.ok(sentencaSocioeducativa));
				}
			});
			
		}
		return res.get();
	}
	
	@PostMapping(path = "/sentencasExtincaoMedidaSocioeducativa/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaExtincaoMedidaSocioeducativaBean> criarSentencaExtincaoMedidaSocioeducativaParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaExtincaoMedidaSocioeducativaBean sentencaExtincaoMedidaSocioeducativa) {
		ResponseEntity<SentencaExtincaoMedidaSocioeducativaBean> res = ResponseEntity.noContent().build();
		if (sentencaExtincaoMedidaSocioeducativa != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			
			if (infoCriminal != null) {
				if (infoCriminal.getConteudo().getSentencasExtincaoMedidaSocioeducativa() == null) {
					infoCriminal.getConteudo().setSentencasExtincaoMedidaSocioeducativa(new ArrayList<SentencaExtincaoMedidaSocioeducativaBean>());
				}
				sentencaExtincaoMedidaSocioeducativa.setDataCadastro(new Date());
				infoCriminal.getConteudo().getSentencasExtincaoMedidaSocioeducativa().add(sentencaExtincaoMedidaSocioeducativa);
				infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res = ResponseEntity.ok(sentencaExtincaoMedidaSocioeducativa);
			}
		}
		return res;
	}
	
	@PutMapping(path = "/sentencasExtincaoMedidaSocioeducativa/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SentencaExtincaoMedidaSocioeducativaBean> alterarSentencaExtincaoMedidaSocioeducativaParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SentencaExtincaoMedidaSocioeducativaBean sentencaExtincaoMedidaSocioeducativa) {
		AtomicReference<ResponseEntity<SentencaExtincaoMedidaSocioeducativaBean>> res = new AtomicReference<ResponseEntity<SentencaExtincaoMedidaSocioeducativaBean>>(ResponseEntity.noContent().build());
		if (sentencaExtincaoMedidaSocioeducativa != null) {
			this.informacaoCriminalService
				.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
				.ifPresent(infoCriminal -> {
					if (infoCriminal != null) {
						if (infoCriminal.getConteudo().getSentencasExtincaoMedidaSocioeducativa() == null) {
							infoCriminal.getConteudo().setSentencasExtincaoMedidaSocioeducativa(new ArrayList<SentencaExtincaoMedidaSocioeducativaBean>());
						}
						ListIterator<SentencaExtincaoMedidaSocioeducativaBean> iterator = infoCriminal.getConteudo().getSentencasExtincaoMedidaSocioeducativa().listIterator();
						while (iterator.hasNext()) {
							SentencaExtincaoMedidaSocioeducativaBean sentencaExtincaoMedidaSocioeducativaBean = iterator.next();
							if (sentencaExtincaoMedidaSocioeducativaBean.getId().equals(sentencaExtincaoMedidaSocioeducativa.getId())) {
								iterator.set(sentencaExtincaoMedidaSocioeducativa);
							}
						}
						informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
						res.set(ResponseEntity.ok(sentencaExtincaoMedidaSocioeducativa));
					}
				});
		}
		return res.get();
	}
	
	@PostMapping(path = "/evasoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<EvasaoBean> criarEvasaoParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody EvasaoBean evasao) {
		ResponseEntity<EvasaoBean> res = ResponseEntity.noContent().build();
		if (evasao != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			
			if (infoCriminal != null) {
				if (infoCriminal.getConteudo().getEvasoes() == null) {
					infoCriminal.getConteudo().setEvasoes(new ArrayList<EvasaoBean>());
				}
				evasao.setDataCadastro(new Date());
				infoCriminal.getConteudo().getEvasoes().add(evasao);
				infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res = ResponseEntity.ok(evasao);
			}
		}
		return res;
	}
	
	@PutMapping(path = "/evasoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<EvasaoBean> alterarEvasaoParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody EvasaoBean evasao) {
		AtomicReference<ResponseEntity<EvasaoBean>> res = new AtomicReference<ResponseEntity<EvasaoBean>>(ResponseEntity.noContent().build());
		if (evasao != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.ifPresent(infoCriminal -> {
				if (infoCriminal != null) {
					if (infoCriminal.getConteudo().getEvasoes() == null) {
						infoCriminal.getConteudo().setEvasoes(new ArrayList<EvasaoBean>());
					}
					ListIterator<EvasaoBean> iterator = infoCriminal.getConteudo().getEvasoes().listIterator();
					while (iterator.hasNext()) {
						EvasaoBean evasaoBean = iterator.next();
						if (evasaoBean.getId().equals(evasao.getId())) {
							iterator.set(evasao);
						}
					}
					informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
					res.set(ResponseEntity.ok(evasao));
				}
			});
		}
		return res.get();
	}
	
	@PostMapping(path = "/semiliberdades/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SemiliberdadeBean> criarSemiliberdadeParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SemiliberdadeBean semiliberdade) {
		ResponseEntity<SemiliberdadeBean> res = ResponseEntity.noContent().build();
		if (semiliberdade != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			
			if (infoCriminal != null) {
				if (infoCriminal.getConteudo().getSemiliberdades() == null) {
					infoCriminal.getConteudo().setSemiliberdades(new ArrayList<SemiliberdadeBean>());
				}
				semiliberdade.setDataCadastro(new Date());
				infoCriminal.getConteudo().getSemiliberdades().add(semiliberdade);
				infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res = ResponseEntity.ok(semiliberdade);
			}
		}
		return res;
	}
	
	@PutMapping(path = "/semiliberdades/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SemiliberdadeBean> alterarSemiliberdadeParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SemiliberdadeBean semiliberdade) {
		AtomicReference<ResponseEntity<SemiliberdadeBean>> res = new AtomicReference<ResponseEntity<SemiliberdadeBean>>(ResponseEntity.noContent().build());
		if (semiliberdade != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.ifPresent(infoCriminal -> {
				if (infoCriminal != null) {
					if (infoCriminal.getConteudo().getSemiliberdades() == null) {
						infoCriminal.getConteudo().setSemiliberdades(new ArrayList<SemiliberdadeBean>());
					}
					ListIterator<SemiliberdadeBean> iterator = infoCriminal.getConteudo().getSemiliberdades().listIterator();
					while (iterator.hasNext()) {
						SemiliberdadeBean semiliberdadeBean = iterator.next();
						if (semiliberdadeBean.getId().equals(semiliberdade.getId())) {
							iterator.set(semiliberdade);
						}
					}
					informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
					res.set(ResponseEntity.ok(semiliberdade));
				}
			});
		}
		return res.get();
	}
	
	@PostMapping(path = "/suspensoesMedidasSocioeducativas/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SuspensaoMedidaSocioeducativaBean> criarSuspensaoMedidaSocioeducativaParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SuspensaoMedidaSocioeducativaBean suspensaoMedidaSocioeducativa) {
		ResponseEntity<SuspensaoMedidaSocioeducativaBean> res = ResponseEntity.noContent().build();
		if (suspensaoMedidaSocioeducativa != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			
			if (infoCriminal != null) {
				if (infoCriminal.getConteudo().getSuspensoesMedidasSocioeducativas() == null) {
					infoCriminal.getConteudo().setSuspensoesMedidasSocioeducativas(new ArrayList<SuspensaoMedidaSocioeducativaBean>());
				}
				suspensaoMedidaSocioeducativa.setDataCadastro(new Date());
				infoCriminal.getConteudo().getSuspensoesMedidasSocioeducativas().add(suspensaoMedidaSocioeducativa);
				infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res = ResponseEntity.ok(suspensaoMedidaSocioeducativa);
			}
		}
		return res;
	}
	
	@PutMapping(path = "/suspensoesMedidasSocioeducativas/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<SuspensaoMedidaSocioeducativaBean> alterarSuspensaoMedidaSocioeducativaParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody SuspensaoMedidaSocioeducativaBean suspensaoMedidaSocioeducativa) {
		AtomicReference<ResponseEntity<SuspensaoMedidaSocioeducativaBean>> res = new AtomicReference<ResponseEntity<SuspensaoMedidaSocioeducativaBean>>(ResponseEntity.noContent().build());
		if (suspensaoMedidaSocioeducativa != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.ifPresent(infoCriminal -> {
				if (infoCriminal != null) {
					if (infoCriminal.getConteudo().getSuspensoesMedidasSocioeducativas() == null) {
						infoCriminal.getConteudo().setSuspensoesMedidasSocioeducativas(new ArrayList<SuspensaoMedidaSocioeducativaBean>());
					}
					ListIterator<SuspensaoMedidaSocioeducativaBean> iterator = infoCriminal.getConteudo().getSuspensoesMedidasSocioeducativas().listIterator();
					while (iterator.hasNext()) {
						SuspensaoMedidaSocioeducativaBean suspensaoMedidaSocioeducativaBean = iterator.next();
						if (suspensaoMedidaSocioeducativaBean.getId().equals(suspensaoMedidaSocioeducativa.getId())) {
							iterator.set(suspensaoMedidaSocioeducativa);
						}
					}
					informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
					res.set(ResponseEntity.ok(suspensaoMedidaSocioeducativa));
				}
			});
		}
		return res.get();
	}
	
	@PostMapping(path = "/decisoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<DecisaoBean> criarDecisaoParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody DecisaoBean decisao) {
		ResponseEntity<DecisaoBean> res = ResponseEntity.noContent().build();
		if (decisao != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService
					.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() -> {
						return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
					});
			
			if (infoCriminal != null) {
				if (infoCriminal.getConteudo().getDecisoes() == null) {
					infoCriminal.getConteudo().setDecisoes(new ArrayList<DecisaoBean>());
				}
				decisao.setDataCadastro(new Date());
				infoCriminal.getConteudo().getDecisoes().add(decisao);
				infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res = ResponseEntity.ok(decisao);
			}
		}
		return res;
	}
	
	@PutMapping(path = "/decisoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<DecisaoBean> alterarDecisaoParte(@PathVariable("idProcesso") Long idProcesso,
			@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy,
			@RequestBody DecisaoBean decisao) {
		AtomicReference<ResponseEntity<DecisaoBean>> res = new AtomicReference<ResponseEntity<DecisaoBean>>(ResponseEntity.noContent().build());
		if (decisao != null) {
			this.informacaoCriminalService
			.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy)
			.ifPresent(infoCriminal -> {
				if (infoCriminal != null) {
					if (infoCriminal.getConteudo().getDecisoes() == null) {
						infoCriminal.getConteudo().setDecisoes(new ArrayList<DecisaoBean>());
					}
					ListIterator<DecisaoBean> iterator = infoCriminal.getConteudo().getDecisoes().listIterator();
					while (iterator.hasNext()) {
						DecisaoBean decisaoBean = iterator.next();
						if (decisaoBean.getId().equals(decisao.getId())) {
							iterator.set(decisao);
						}
					}
					informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
					res.set(ResponseEntity.ok(decisao));
				}
			});
		}
		return res.get();
	}
	@PostMapping(path = "/remissoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<RemissaoBean> criarRemissaoParte(@PathVariable("idProcesso") Long idProcesso, 
													@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
													@RequestBody RemissaoBean remissao){
		ResponseEntity<RemissaoBean> res = ResponseEntity.noContent().build();
		if(remissao != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			if (infoCriminal != null) {
				if(infoCriminal.getConteudo().getRemissoes() == null) {
					infoCriminal.getConteudo().setRemissoes(new ArrayList<RemissaoBean>());
				}
				remissao.setDataCadastro(new Date());
				infoCriminal.getConteudo().getRemissoes().add(remissao);
				infoCriminal = this.informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res = ResponseEntity.ok(remissao);
			}			
		}
		
		return res;
	}
	
	@PutMapping(path = "/remissoes/processos/{idProcesso}/partes/{idProcessoParteLegacy}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InboundRequest
	public ResponseEntity<RemissaoBean> alterarRemissaoParte(@PathVariable("idProcesso") Long idProcesso, 
														@PathVariable("idProcessoParteLegacy") Long idProcessoParteLegacy, 
														@RequestBody RemissaoBean remissao){
		ResponseEntity<RemissaoBean> res = ResponseEntity.noContent().build();
		if (remissao != null) {
			InformacaoCriminal infoCriminal = this.informacaoCriminalService.recuperarInformacaoCriminalPorProcessoEhParteLegacy(idProcesso, idProcessoParteLegacy).orElseGet(() ->{
				return new InformacaoCriminal(processoService.recuperarProcessoPorId(idProcesso), parteService.recuperarProcessosPorIdProcessoParteLegacy(idProcessoParteLegacy));
			});
			if (infoCriminal != null) {
				if(infoCriminal.getConteudo().getRemissoes() == null) {
					infoCriminal.getConteudo().setRemissoes(new ArrayList<RemissaoBean>());
				}
				ListIterator<RemissaoBean> iterator = infoCriminal.getConteudo().getRemissoes().listIterator();
				while (iterator.hasNext()) {
					RemissaoBean remissaoBean = iterator.next();
					if (remissaoBean.getId().equals(remissao.getId())) {
						iterator.set(remissao);
					}
				}
				informacaoCriminalService.atualizarInformacaoCriminal(infoCriminal);
				res = ResponseEntity.ok(remissao);
			}
		}
		
		return res;
	}


}
