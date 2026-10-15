package br.jus.cnj.pje.criminal.amqp.handlers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.cnj.pje.criminal.amqp.model.CloudEvent;
import br.jus.cnj.pje.criminal.amqp.model.ProcessoParteEventDTO;
import br.jus.cnj.pje.criminal.model.entities.Parte;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.services.ParteService;
import br.jus.cnj.pje.criminal.services.ProcessoService;

@Controller
public class ParteEventHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ParteService parteService;

	@Autowired
	private ProcessoService processoService;
	
	public void handle(CloudEvent event) throws Exception {
		ProcessoParteEventDTO processoParte = this.getMapper().convertValue(event.getPayload(), ProcessoParteEventDTO.class);

		if(event.getRoutingKey().toLowerCase().contains("post")) {
			this.criarParteProcessoPorEvento(processoParte);
		} else if(event.getRoutingKey().toLowerCase().contains("patch") || event.getRoutingKey().toLowerCase().contains("put")) {
			this.atualizarParteProcessoPorEvento(processoParte);
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	private void criarParteProcessoPorEvento(ProcessoParteEventDTO processoParte) throws Exception {
		if(!StringUtils.isEmpty(processoParte.getNumeroProcesso())) {
			Processo proc = this.processoService.recuperarProcessoPorNumero(processoParte.getNumeroProcesso(), true);
			if(proc != null) {
				List<Parte> partes = this.parteService.recuperarParte(processoParte.getNumeroProcesso(), processoParte.getIdProcessoPartePje(), processoParte.getIdPessoaPje());
				
				if(CollectionUtils.isEmpty(partes)) {
					Parte parte = new Parte();
					parte.setIdPessoaLegacy(processoParte.getIdPessoaPje());
					parte.setIdProcessoParteLegacy(processoParte.getIdProcessoPartePje());
					parte.setProcesso(proc);
					parte.setSituacaoParte(processoParte.getSituacaoParte());
					parte = this.parteService.inserir(parte);
				}

			}
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	private void atualizarParteProcessoPorEvento(ProcessoParteEventDTO processoParte) throws Exception {
		if(!StringUtils.isEmpty(processoParte.getNumeroProcesso())) {
			Processo proc = this.processoService.recuperarProcessoPorNumero(processoParte.getNumeroProcesso(), true);
			if(proc != null) {
				List<Parte> partes = this.parteService.recuperarParte(processoParte.getNumeroProcesso(), processoParte.getIdProcessoPartePje(), processoParte.getIdPessoaPje());
				if(CollectionUtils.isEmpty(partes)) {
					this.criarParteProcessoPorEvento(processoParte);
				} else {
					for (Parte parte : partes) {
						parte.setIdPessoaLegacy(processoParte.getIdPessoaPje());
						parte.setIdProcessoParteLegacy(processoParte.getIdProcessoPartePje());
						parte.setSituacaoParte(processoParte.getSituacaoParte());
						parte = this.parteService.inserir(parte);						
					}
				}
			}
		}
	}
	
	private ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		// para ignorar propriedades que cheguem via mensasgem, mas que não existem na classe do serviço
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		return mapper;
	}	
}
