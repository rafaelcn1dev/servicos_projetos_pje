package br.jus.cnj.pje.criminal.model.beans;

public class IncidenciaBean {

	private String idIncidencia;
	private String idEventoCriminal;
	private Long idProcessoParteLegacy;
	private String texto;

	public String getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(String idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getIdEventoCriminal() {
		return idEventoCriminal;
	}

	public void setIdEventoCriminal(String idEventoCriminal) {
		this.idEventoCriminal = idEventoCriminal;
	}

	public Long getIdProcessoParteLegacy() {
		return idProcessoParteLegacy;
	}

	public void setIdProcessoParteLegacy(Long idProcessoParteLegacy) {
		this.idProcessoParteLegacy = idProcessoParteLegacy;
	}

}