package br.jus.cnj.pje.criminal.model.beans;

import java.util.ArrayList;
import java.util.List;

public class Resposta {
	
	private String numeroProcessoSeeu;
	
	private String numeroProcessoPje;
	
	private List<String> message;

	public String getNumeroProcessoSeeu() {
		return numeroProcessoSeeu;
	}

	public void setNumeroProcessoSeeu(String numeroProcessoSeeu) {
		this.numeroProcessoSeeu = numeroProcessoSeeu;
	}

	public String getNumeroProcessoPje() {
		return numeroProcessoPje;
	}

	public void setNumeroProcessoPje(String numeroProcessoPje) {
		this.numeroProcessoPje = numeroProcessoPje;
	}

	public List<String> getMessage() {
		if(message == null) {
			message = new ArrayList<>();
		}
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Resposta [numeroProcessoSeeu=" + numeroProcessoSeeu + ", numeroProcessoPje=" + numeroProcessoPje
				+ ", message=" + message + "]";
	}

}
