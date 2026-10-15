package br.jus.cnj.pje.criminal.model.beans;

import java.util.List;

import br.jus.cnj.pje.extensao.auxiliar.ResultadoAssinatura;

public class RespostaAssinatura {

	private String status;
	private Integer code;
	private List<String> messages;
	private ResultadoAssinatura data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	public ResultadoAssinatura getData() {
		return data;
	}
	public void setData(ResultadoAssinatura data) {
		this.data = data;
	}
}
