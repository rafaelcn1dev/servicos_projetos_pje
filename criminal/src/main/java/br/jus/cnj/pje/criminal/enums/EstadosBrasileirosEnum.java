package br.jus.cnj.pje.criminal.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EstadosBrasileirosEnum {
	
	AC("Acre"), 
	AL("Alagoas"), 
	AP("Amapá"), 
	AM("Amazonas"), 
	BA("Bahia"), 
	CE("Ceará"), 
	DF("Distrito Federal"), 
	ES("Espírito Santo"), 
	GO("Goiás"), 
	MA("Maranhão"), 
	MT("Mato Grosso"), 
	MS("Mato Grosso do Sul"), 
	MG("Minas Gerais"), 
	PA("Pará"), 
	PB("Paraíba"), 
	PR("Paraná"), 
	PE("Pernambuco"), 
	PI("Piauí"), 
	RJ("Rio de Janeiro"), 
	RN("Rio Grande do Norte"), 
	RS("Rio Grande do Sul"), 
	RO("Rondônia"), 
	RR("Roraima"), 
	SC("Santa Catarina"), 
	SP("São Paulo"), 
	SE("Sergipe"), 
	TO("Tocantins");

    private String nome;
    
    private EstadosBrasileirosEnum(String nome) {
        this.nome = nome;
    }
    
    public String getSigla() {
    		return this.name();
    }
    
    public String getNome() {
        return this.nome;
    }

}