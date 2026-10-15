package br.jus.cnj.pje.criminal.services;

import java.util.List;
import java.util.Optional;

import br.jus.cnj.pje.criminal.model.beans.CitacaoProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.DecisaoInstanciaSuperiorBean;
import br.jus.cnj.pje.criminal.model.beans.EventoCriminalBean;
import br.jus.cnj.pje.criminal.model.beans.FugaBean;
import br.jus.cnj.pje.criminal.model.beans.PrisaoBean;
import br.jus.cnj.pje.criminal.model.beans.DesclassificacaoProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.RecebimentoAditamentoDenunciaBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaCondenatoriaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaImpronunciaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaPronunciaProcessoBean;
import br.jus.cnj.pje.criminal.model.beans.SentencaAbsolutoriaBean;
import br.jus.cnj.pje.criminal.model.beans.SolturaBean;
import br.jus.cnj.pje.criminal.model.beans.SuspensaoProcessoBean;
import br.jus.cnj.pje.criminal.model.entities.InformacaoCriminal;
import br.jus.cnj.pje.criminal.model.entities.Parte;
import br.jus.cnj.pje.criminal.model.entities.Processo;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;

public interface InformacaoCriminalService {
	
	InformacaoCriminal criarNovaInformacaoCriminal(InformacaoCriminal informacaoCriminal);
	InformacaoCriminal atualizarInformacaoCriminal(InformacaoCriminal informacaoCriminal);
	InformacaoCriminal recuperarInformacaoCriminalPorId(Long idInformacaoCriminal);
	Optional<InformacaoCriminal> recuperarInformacaoCriminalPorProcessoEhParteLegacy(Processo processo, Parte parte);
	Optional<InformacaoCriminal> recuperarInformacaoCriminalPorProcessoEhParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	
	List<InformacaoCriminal> recuperarInformacaoCriminalListPorProcessoEhParteLegacy(Long idProcesso, Long idParte);
	List<InformacaoCriminal> criarNovasInformacoesCriminais(List<InformacaoCriminal> informacoesCriminais);
	List<PrisaoBean> recuperarPrisoesPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	List<SuspensaoProcessoBean> recuperarSuspensoesProcessoPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	List<DecisaoInstanciaSuperiorBean> recuperarDecisoesInstanciaSuperiorPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	List<FugaBean> recuperarFugasPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	List<SolturaBean> recuperarSolturasPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	List<InformacaoCriminal> recuperarInformacaoCriminalPorProcesso(Long idProcesso);
	boolean possuiEventoCriminalVinculadoAoMovimento(Long idProcesso, Integer idProcessoEvento, TipoEventoCriminal tipoEventoCriminal);
	List<SentencaCondenatoriaProcessoBean> recuperarSentencasCondenatoriasPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	List<SentencaAbsolutoriaBean> recuperarSentencasAbsolutoriasPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	void validarFugaBean(EventoCriminalBean fuga);
	void validarPrisaoBean(EventoCriminalBean prisao);
	void validarSolturaBean(EventoCriminalBean soltura);
	List<SentencaImpronunciaProcessoBean> recuperarSentencasImpronunciaPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	List<SentencaPronunciaProcessoBean> recuperarSentencasPronunciaPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	List<DesclassificacaoProcessoBean> recuperarDesclassificacoesProcessoPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	List<CitacaoProcessoBean> recuperarCitacoesPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
	List<RecebimentoAditamentoDenunciaBean> recuperarRecebimentosAditamentoDenunciaPorIdProcessoEhIdProcessoParteLegacy(Long idProcesso, Long idProcessoParteLegacy);
}
