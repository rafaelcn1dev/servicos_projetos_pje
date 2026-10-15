package br.jus.cnj.pje.criminal.services;

import java.util.HashMap;
import java.util.Map;

import br.jus.cnj.intercomunicacao.beans.ModalidadeDocumentoIdentificador;

public class CorrelacaoDocumentoIdentificacaoMNIService {
	private static Map<String, ModalidadeDocumentoIdentificador> mapaCorrelacao = carregarMapaCorrelacao();

	public ModalidadeDocumentoIdentificador converter(String codigo) {
		ModalidadeDocumentoIdentificador resultado = null;
		if (codigo != null) {
			resultado = mapaCorrelacao.get(codigo.trim().toUpperCase());
		}
		return resultado;
	}

	private static Map<String, ModalidadeDocumentoIdentificador> carregarMapaCorrelacao() {
		Map<String, ModalidadeDocumentoIdentificador> mapa = new HashMap<String, ModalidadeDocumentoIdentificador>();
		mapa.put("CPF", ModalidadeDocumentoIdentificador.CMF);
		mapa.put("CPJ", ModalidadeDocumentoIdentificador.CMF);
		mapa.put("RG", ModalidadeDocumentoIdentificador.CI);
		mapa.put("PAS", ModalidadeDocumentoIdentificador.PAS);
		mapa.put("TIT", ModalidadeDocumentoIdentificador.TE);
		mapa.put("CNH", ModalidadeDocumentoIdentificador.CNH);
		mapa.put("CNA", ModalidadeDocumentoIdentificador.CN);
		mapa.put("CCA", ModalidadeDocumentoIdentificador.CC);
		mapa.put("CTR", ModalidadeDocumentoIdentificador.CT);
		mapa.put("RIC", ModalidadeDocumentoIdentificador.RIC);
		mapa.put("PIS", ModalidadeDocumentoIdentificador.PIS_PASEP);
		mapa.put("CEI", ModalidadeDocumentoIdentificador.CEI);
		mapa.put("NIT", ModalidadeDocumentoIdentificador.NIT);
		mapa.put("CCP", ModalidadeDocumentoIdentificador.CP);
		mapa.put("IDF", ModalidadeDocumentoIdentificador.IF);
		mapa.put("OAB", ModalidadeDocumentoIdentificador.OAB);
		mapa.put("RJC", ModalidadeDocumentoIdentificador.RJC);
		mapa.put("RGE", ModalidadeDocumentoIdentificador.RGE);
		return mapa;
	}
}
