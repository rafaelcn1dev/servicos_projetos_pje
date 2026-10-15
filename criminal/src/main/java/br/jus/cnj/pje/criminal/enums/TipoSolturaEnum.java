package br.jus.cnj.pje.criminal.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoSolturaEnum {
	 
	RLX("Relaxamento"), 
	RVP("Revogação da Prisão"), 
	FPT("Final do Prazo da Temporária"), 
	LPR("Liberdade Provisória"), 
	FIA("Fiança");
 
    private String label;
 
    TipoSolturaEnum(String label) {
        this.label = label;
    }
 
    public String getName() {
    	return this.name();
    }
 
    public String getLabel() {
        return this.label;
    }
 
}
