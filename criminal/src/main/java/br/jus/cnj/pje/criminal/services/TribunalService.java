package br.jus.cnj.pje.criminal.services;

import br.jus.cnj.pje.criminal.model.entities.CodigoTribunal;

public interface TribunalService {
	public CodigoTribunal recuperarCodigoTribunal(String appName);
}