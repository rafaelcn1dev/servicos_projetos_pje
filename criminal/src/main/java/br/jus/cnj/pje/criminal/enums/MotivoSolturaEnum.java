package br.jus.cnj.pje.criminal.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MotivoSolturaEnum {
	
	FIA("Fiança"),
	REL("Relaxamento de prisão"),
	LPF("Liberdade provisória com fiança"),
	LPS("Liberdade provisória sem fiança"),
	REV("Revogação da prisão"),
	HAB("Habeas corpus");
	
	private String label;
	 
    private MotivoSolturaEnum(String label) {
        this.label = label;
    }

    public String getName() {
    	return this.name();
    }
 
    public String getLabel() {
        return this.label;
    }

}
