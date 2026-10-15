package br.jus.cnj.pje.criminal.model.beans;

import br.jus.cnj.intercomunicacao.beans.RespostaManifestacaoProcessual;
import br.jus.cnj.pje.criminal.model.entities.SeeuTribunal;

public class RespostaSeeuBean {

	private String protocoloSeeu;
	private String numeroProcessoExecucao;
	private String guiaHTML;
	private SeeuTribunal dadosTribunal;

	public RespostaSeeuBean(RespostaManifestacaoProcessual resposta, String guiaHTML, SeeuTribunal dadosTribunal) {
		this.protocoloSeeu = resposta.getProtocoloRecebimento() != null ? resposta.getProtocoloRecebimento() : "" ;
		this.numeroProcessoExecucao = obterNumeroUnico(resposta);
		this.guiaHTML = guiaHTML;
		this.setDadosTribunal(dadosTribunal);
	}

	private String obterNumeroUnico(RespostaManifestacaoProcessual resposta) {
		return resposta
				.getParametro()
				.stream().filter(x -> x.getNome().equals("NUMERO_UNICO")).findFirst()
				.orElseThrow(() -> new RuntimeException("O SEEU não retornou o número único do processo de execução."))
				.getValor();
	}

	public String getProtocoloSeeu() {
		return protocoloSeeu;
	}

	public void setProtocoloSeeu(String protocoloSeeu) {
		this.protocoloSeeu = protocoloSeeu;
	}

	public String getNumeroProcessoExecucao() {
		return numeroProcessoExecucao;
	}

	public void setNumeroProcessoExecucao(String numeroProcessoExecucao) {
		this.numeroProcessoExecucao = numeroProcessoExecucao;
	}

	public String getGuiaHTML() {
		return guiaHTML;
	}

	public void setGuiaHTML(String guiaHTML) {
		this.guiaHTML = guiaHTML;
	}

	public SeeuTribunal getDadosTribunal() {
		return dadosTribunal;
	}

	public void setDadosTribunal(SeeuTribunal dadosTribunal) {
		this.dadosTribunal = dadosTribunal;
	}
}
