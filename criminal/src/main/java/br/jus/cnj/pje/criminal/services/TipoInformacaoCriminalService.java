package br.jus.cnj.pje.criminal.services;

import java.util.List;

import br.jus.cnj.pje.criminal.enums.TipoEventoCriminalEnum;
import br.jus.cnj.pje.criminal.model.entities.TipoEventoCriminal;

public interface TipoInformacaoCriminalService {
	
	List<TipoEventoCriminal> recuperaTiposInformacaoCriminal();		
	List<TipoEventoCriminal> recuperaTipoInformacaoCriminalPorMovimento(String codigoEvento);
	List<String> recuperaTiposInformacaoCriminalMovimento();
	TipoEventoCriminal recuperaTipoInformacaoCriminalInicioProcesso();
	TipoEventoCriminal recuperaTipoInformacaoCriminal(TipoEventoCriminalEnum eventoCriminalEnum);

}

