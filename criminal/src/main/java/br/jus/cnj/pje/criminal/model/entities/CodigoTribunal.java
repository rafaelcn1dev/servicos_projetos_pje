package br.jus.cnj.pje.criminal.model.entities;

import org.springframework.util.StringUtils;

import br.jus.cnj.pje.criminal.util.StringUtil;
import br.jus.cnj.pje.pjecommons.utils.NumeroProcessoUtil;

public class CodigoTribunal {
    private String codigoJTR;
    private String siglaTribunal;
    private String appName;

    public CodigoTribunal(String appName, String sigla){
    	setAppName(appName);
        setSiglaTribunal(sigla);
        setCodigoJTR(NumeroProcessoUtil.jtrPorSiglaTribunal(sigla));
    }
    
    private void setAppName(String appName) {
    	if(StringUtils.isEmpty(appName)) {
            throw new IllegalArgumentException(
                    "O appName do tribunal não pode ser nulo, nem vazio.");
    	}
    	this.appName = appName;
    }

    private void setSiglaTribunal(String sigla){
        if(StringUtils.isEmpty(sigla)){
            throw new IllegalArgumentException(
                    "A sigla do tribunal não pode ser nula, nem vazia.");
        }
        this.siglaTribunal = sigla.toUpperCase();
    }

    private void setCodigoJTR(String codigoJTR){
        if(StringUtils.isEmpty(codigoJTR)){
            throw new IllegalArgumentException(
                "O código JTR do tribunal não pode ser nulo, nem vazio.");
        }
        this.codigoJTR = codigoJTR;
    }
        
    public String siglaTribunal() {
        return this.siglaTribunal;
    }

    public String jtr() {
        return this.codigoJTR;
    }
    
    public String appName() {
    	return this.appName;
    }
    
    public boolean valido() {
    	return !StringUtil.isEmpty(this.siglaTribunal()) && !StringUtil.isEmpty(this.jtr());
    }
}
