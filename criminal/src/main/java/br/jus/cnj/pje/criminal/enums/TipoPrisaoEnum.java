package br.jus.cnj.pje.criminal.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPrisaoEnum {
	 
    PRV("Preventiva"), 
    PRVDM("Preventiva determinada ou mantida em decisão condenatória recorrível"), 
    TMP("Temporária"), 
    FLG("Flagrante"), 
    PRO("Provisória"), 
    DEP("Para fins de deportação"), 
    EXP("Para fins de expulsão"), 
    EXT("Para fins de extradição"), 
    DEF("Definitiva");
 
    private String label;
 
    private TipoPrisaoEnum(String label) {
        this.label = label;
    }

    @JsonValue
    public String getName() {
    		return this.name();
    }
 
    @JsonValue
    public String getLabel() {
        return this.label;
    }
    
}