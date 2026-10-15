package br.jus.cnj.pje.criminal.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.jus.cnj.pje.criminal.util.PjeLegacyClientUtil;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TribunalNaoIdentificadoException extends CriminalBusinessException {

	private static final long serialVersionUID = 1L;

	public TribunalNaoIdentificadoException() {
		super("Não foi possivel identificar o tribunal, "
				+ "verifique a utilização do campo " 
				+ PjeLegacyClientUtil.HEADER_PJE_LEGACY_APP + 
				" no cabeçalho da requisição.");
    }
}