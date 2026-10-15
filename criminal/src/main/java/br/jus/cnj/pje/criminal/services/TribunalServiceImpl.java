package br.jus.cnj.pje.criminal.services;

import br.jus.cnj.pje.criminal.model.entities.CodigoTribunal;
import br.jus.cnj.pje.pjecommons.model.enums.TribunalEnum;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

@Service
public class TribunalServiceImpl implements TribunalService{

	public CodigoTribunal recuperarCodigoTribunal(String appName){
		CodigoTribunal codigoTribunal = null;
		if(StringUtils.isNotEmpty(appName)) {
			String[] listaArgs = appName.split("-");
			String siglaTribunal = listaArgs[1];
			if(StringUtils.isNotBlank(siglaTribunal)) {
				siglaTribunal = StringUtils.upperCase(siglaTribunal);
			}
			if(verificaExistenciaSigla(siglaTribunal)){
				codigoTribunal = new CodigoTribunal(appName, siglaTribunal);
			}
		}
		return codigoTribunal;
	}

	private boolean verificaExistenciaSigla(String siglaTribunal){
		return EnumUtils.isValidEnum(TribunalEnum.class, siglaTribunal);
	}
}