package br.jus.cnj.pje.criminal.model.dto;

import java.util.LinkedHashMap;
import java.util.Date;
import java.util.Map;

import br.jus.cnj.pje.criminal.model.beans.EventoCriminalBean;

public class EventoCriminalVisualizacaoDTO {

	private EventoCriminalBean eventoCriminal;
	private String textoFinal;
	private Map<String, String> camposVisualizacao;
	private Date dataVisualizacao;

	public EventoCriminalVisualizacaoDTO(EventoCriminalBean eventoCriminal) {
		this.eventoCriminal = eventoCriminal;
		this.textoFinal = eventoCriminal.obterTextoFinal();
		this.camposVisualizacao = definirOrdemFixa(eventoCriminal.obterCamposVisualizacao());
		this.camposVisualizacao = eventoCriminal.obterCamposVisualizacao();
		this.dataVisualizacao = eventoCriminal.obterDataVisualizacao();
	}

	private Map<String, String> definirOrdemFixa(Map<String, String> camposVisualizacao) {
		Map<String, String> map = new LinkedHashMap<>();
		
		String keys [] = {"Evento Criminal", "Data do registro", "Tipificações", "Resumo", "Observação"};
		
		if(camposVisualizacao.containsKey(keys[0])) {
			map.put(keys[0], camposVisualizacao.remove(keys[0]));
		}
		
		if(camposVisualizacao.containsKey(keys[1])) {
			map.put(keys[1], camposVisualizacao.remove(keys[1]));
		}
		
		if(camposVisualizacao.containsKey(keys[2])) {
			map.put(keys[2], camposVisualizacao.remove(keys[2]));
		}
		
		String resumo = "";
		if(camposVisualizacao.containsKey(keys[3])) {
			resumo = camposVisualizacao.remove(keys[3]);
		}
		
		String observacao = "";
		if(camposVisualizacao.containsKey(keys[4])) {
			observacao = camposVisualizacao.get(keys[4]);
		}
		
		map.putAll(camposVisualizacao);
		
		if(!resumo.equals("")) {
			map.put(keys[3], resumo);
		}
		
		if(!observacao.equals("")) {
			map.put(keys[4], observacao);
		}
		
		return map;
	}

	public EventoCriminalBean getEventoCriminal() {
		return eventoCriminal;
	}

	public void setEventoCriminal(EventoCriminalBean eventoCriminal) {
		this.eventoCriminal = eventoCriminal;
	}

	public String getTextoFinal() {
		return textoFinal;
	}

	public void setTextoFinal(String textoFinal) {
		this.textoFinal = textoFinal;
	}

	public Map<String, String> getCamposVisualizacao() {
		return camposVisualizacao;
	}

	public void setCamposVisualizacao(Map<String, String> camposVisualizacao) {
		this.camposVisualizacao = camposVisualizacao;
	}

	public Date getDataVisualizacao() {
		return dataVisualizacao;
	}

	public void setDataVisualizacao(Date dataVisualizacao) {
		this.dataVisualizacao = dataVisualizacao;
	}
}
