package br.jus.cnj.pje.criminal.listeners;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;

import br.jus.cnj.pje.criminal.aop.TribunalContext;
import br.jus.cnj.pje.criminal.base.AbstractEntity;
import br.jus.cnj.pje.criminal.model.entities.CodigoTribunal;

public class CriminalMultiTenancyListener {
	
    @SuppressWarnings("rawtypes")
	@PrePersist
    public void prePersist(AbstractEntity abstractEntity) {
    	if(StringUtils.isEmpty(abstractEntity.getCodigoTribunal())) {
			CodigoTribunal codigoTribunal = TribunalContext.codigoTribunal();

    		abstractEntity.setCodigoTribunal(codigoTribunal.jtr());    		
    	}
    } 

	@SuppressWarnings("rawtypes")
	@PreUpdate
    public void preUpdate(AbstractEntity abstractEntity) {
    	if(StringUtils.isEmpty(abstractEntity.getCodigoTribunal())) {
			CodigoTribunal codigoTribunal = TribunalContext.codigoTribunal();

    		abstractEntity.setCodigoTribunal(codigoTribunal.jtr());    		
    	}
    } 
}
