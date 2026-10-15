package br.jus.cnj.pje.criminal.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MotivoPrisaoEnum {

    FLG("Prisão em flagrante"),
    REC("Recomendação de prisão"),
    MAN("Manutenção de prisão");
	
	private String label;
	 
    private MotivoPrisaoEnum(String label) {
        this.label = label;
    }

    public String getName() {
    	return this.name();
    }
 
    public String getLabel() {
        return this.label;
    }	
	
}
