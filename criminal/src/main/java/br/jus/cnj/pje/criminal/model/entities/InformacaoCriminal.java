package br.jus.cnj.pje.criminal.model.entities;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.springframework.util.CollectionUtils;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import br.jus.cnj.pje.criminal.model.beans.ConteudoInformacaoCriminalBean;
import br.jus.cnj.pje.criminal.model.beans.EventoCriminalBean;
import br.jus.cnj.pje.criminal.model.converters.ConteudoInformacaoCriminalConverter;

@Entity
@Table(name = "tb_informacao_criminal")
@SequenceGenerator(allocationSize = 1, name = "gen_informacao_criminal", sequenceName = "sq_tb_informacao_criminal")
@Audited
public class InformacaoCriminal extends AbstractEntity<Long> {
	
	private static final long serialVersionUID = 1L;

 	@Id
	@GeneratedValue(generator = "gen_informacao_criminal")
	@Column(name = "id_informacao_criminal", unique = true, nullable = false)
	private Long id;
 	
	@NotNull
//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_processo")
 	private Processo processo;
	
	@Column(name = "ds_conteudo", nullable = false)
	@Convert(converter = ConteudoInformacaoCriminalConverter.class)
	private ConteudoInformacaoCriminalBean conteudo = new ConteudoInformacaoCriminalBean();
	
	@NotNull
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "id_parte")
	private Parte parte;
	
	public InformacaoCriminal() {
	}
	
	public InformacaoCriminal(Processo proc, Parte p) {
		this.parte = p;
		this.processo = proc;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Processo getProcesso() {
		return processo;
	}
	
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	
	public ConteudoInformacaoCriminalBean getConteudo() {
		return conteudo;
	}
	
	public void setConteudo(ConteudoInformacaoCriminalBean conteudo) {
		this.conteudo = conteudo;
	}
	
	public Parte getParte() {
		return parte;
	}
	
	public void setParte(Parte parte) {
		this.parte = parte;
	}
	
	@Override
	@Transient
	public String getCodigoTribunal() {
		return null;
	}
	
	@Override
	public void setCodigoTribunal(String codigoTribunal) {
	}
	
	@Transient
	public List<EventoCriminalBean> getEventosCriminais(){
		List<EventoCriminalBean> eventoCriminalBeanList = new ArrayList<EventoCriminalBean>();

		if(!CollectionUtils.isEmpty(getConteudo().getFugas())) {
			eventoCriminalBeanList.addAll(getConteudo().getFugas());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getSolturas())) {
			eventoCriminalBeanList.addAll(getConteudo().getSolturas());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getPrisoes())) {
			eventoCriminalBeanList.addAll(getConteudo().getPrisoes());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getIndiciamento())) {
			eventoCriminalBeanList.addAll(getConteudo().getIndiciamento());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getOferecimentoDenuncia())) {
			eventoCriminalBeanList.addAll(getConteudo().getOferecimentoDenuncia());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getRecebimentoDenuncia())) {
			eventoCriminalBeanList.addAll(getConteudo().getRecebimentoDenuncia());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getAditamentoDenuncia())) {
			eventoCriminalBeanList.addAll(getConteudo().getAditamentoDenuncia());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getInicioInstrucaoProcessual())) {
			eventoCriminalBeanList.addAll(getConteudo().getInicioInstrucaoProcessual());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getFimInstrucaoProcessual())) {
			eventoCriminalBeanList.addAll(getConteudo().getFimInstrucaoProcessual());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getSuspensoesProcesso())) {
			eventoCriminalBeanList.addAll(getConteudo().getSuspensoesProcesso());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getDecisoesInstanciaSuperior())) {
			eventoCriminalBeanList.addAll(getConteudo().getDecisoesInstanciaSuperior());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getSentencasCondenatorias())) {
			eventoCriminalBeanList.addAll(getConteudo().getSentencasCondenatorias());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getSentencasAbsolutorias())) {
			eventoCriminalBeanList.addAll(getConteudo().getSentencasAbsolutorias());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getSentencasExtincaoPunibilidade())) {
			eventoCriminalBeanList.addAll(getConteudo().getSentencasExtincaoPunibilidade());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getSentencasImpronuncia())) {
			eventoCriminalBeanList.addAll(getConteudo().getSentencasImpronuncia());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getSentencasPronuncia())) {
			eventoCriminalBeanList.addAll(getConteudo().getSentencasPronuncia());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getDesclassificacoesProcesso())) {
			eventoCriminalBeanList.addAll(getConteudo().getDesclassificacoesProcesso());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getNaoRecebimentoDenuncia())) {
			eventoCriminalBeanList.addAll(getConteudo().getNaoRecebimentoDenuncia());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getCitacoes())) {
			eventoCriminalBeanList.addAll(getConteudo().getCitacoes());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getRecebimentosAditamentoDenuncia())) {
			eventoCriminalBeanList.addAll(getConteudo().getRecebimentosAditamentoDenuncia());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getSentencasAbsolvicaoSumaria())) {
			eventoCriminalBeanList.addAll(getConteudo().getSentencasAbsolvicaoSumaria());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getDesmembramentos())) {
			eventoCriminalBeanList.addAll(getConteudo().getDesmembramentos());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getSentencasAbsolutoriasImproprias())) {
			eventoCriminalBeanList.addAll(getConteudo().getSentencasAbsolutoriasImproprias());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getPresosOutroProcesso())) {
			eventoCriminalBeanList.addAll(getConteudo().getPresosOutroProcesso());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getTransacoesPenais())) {
			eventoCriminalBeanList.addAll(getConteudo().getTransacoesPenais());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getRevogacoesSuspensaoCondicionalProcesso())) {
			eventoCriminalBeanList.addAll(getConteudo().getRevogacoesSuspensaoCondicionalProcesso());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getArquivamentosIncidencias())) {
			eventoCriminalBeanList.addAll(getConteudo().getArquivamentosIncidencias());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getTrancamentos())) {
			eventoCriminalBeanList.addAll(getConteudo().getTrancamentos());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getNulidadesProcessos())) {
			eventoCriminalBeanList.addAll(getConteudo().getNulidadesProcessos());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getLitispendenciasCoisaJulgada())) {
			eventoCriminalBeanList.addAll(getConteudo().getLitispendenciasCoisaJulgada());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getAusenciasCondicoesAcao())) {
			eventoCriminalBeanList.addAll(getConteudo().getAusenciasCondicoesAcao());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getDecliniosCompetencia())) {
			eventoCriminalBeanList.addAll(getConteudo().getDecliniosCompetencia());
		}
		if(!CollectionUtils.isEmpty(getConteudo().getHomologacoesComposicoesDanosCivis())) {
			eventoCriminalBeanList.addAll(getConteudo().getHomologacoesComposicoesDanosCivis());
		}

		if(!CollectionUtils.isEmpty(getConteudo().getInternacoes())) eventoCriminalBeanList.addAll(getConteudo().getInternacoes());
		if(!CollectionUtils.isEmpty(getConteudo().getSentencasInfracionais())) eventoCriminalBeanList.addAll(getConteudo().getSentencasInfracionais());
		if(!CollectionUtils.isEmpty(getConteudo().getSentencasExtincaoMedidaSocioeducativa())) eventoCriminalBeanList.addAll(getConteudo().getSentencasExtincaoMedidaSocioeducativa());
		if(!CollectionUtils.isEmpty(getConteudo().getDecisoes())) eventoCriminalBeanList.addAll(getConteudo().getDecisoes());
		if(!CollectionUtils.isEmpty(getConteudo().getEvasoes())) eventoCriminalBeanList.addAll(getConteudo().getEvasoes());
		if(!CollectionUtils.isEmpty(getConteudo().getSemiliberdades())) eventoCriminalBeanList.addAll(getConteudo().getSemiliberdades());
		if(!CollectionUtils.isEmpty(getConteudo().getSuspensoesMedidasSocioeducativas())) eventoCriminalBeanList.addAll(getConteudo().getSuspensoesMedidasSocioeducativas());
		if(!CollectionUtils.isEmpty(getConteudo().getRemissoes())) eventoCriminalBeanList.addAll(getConteudo().getRemissoes());
		
		return eventoCriminalBeanList;
	}

	@Override
	@javax.persistence.Transient
	public Boolean getAtivo() {
		return null;
	}

	@Override
	public void setAtivo(Boolean ativo) {

	}
}
