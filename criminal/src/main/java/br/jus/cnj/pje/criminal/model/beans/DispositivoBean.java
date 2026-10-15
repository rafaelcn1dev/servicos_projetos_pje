package br.jus.cnj.pje.criminal.model.beans;

import java.io.Serializable;
import java.util.Date;

public class DispositivoBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private NormaBean norma;
	private String simbolo;
	private String identificador;
	private String texto;
	private Date inicioVigencia;
	private Date fimVigencia;
	private Boolean ativo;
	private String textoFinal;
	private String identificadorPena;
	private String artigo;
	private String paragrafo;
	private String inciso;

	public DispositivoBean(Long id, 
			NormaBean norma, 
			String simbolo,
			String identificador, 
			String texto, 
			Date inicioVigencia, 
			Date fimVigencia, 
			Boolean ativo,
			String textoFinal,
			String identificadorPena,
			String artigo,
			String paragrafo,
			String inciso) {
		super();
		this.id = id;
		this.norma = norma;
		this.simbolo = simbolo;
		this.identificador = identificador;
		this.texto = texto;
		this.inicioVigencia = inicioVigencia;
		this.fimVigencia = fimVigencia;
		this.ativo = ativo;
		this.textoFinal = textoFinal;
		this.identificadorPena = identificadorPena;
		this.artigo = artigo;
		this.paragrafo = paragrafo;
		this.inciso = inciso;
	}

	public DispositivoBean() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NormaBean getNorma() {
		return norma;
	}

	public void setNorma(NormaBean norma) {
		this.norma = norma;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getTextoFinal() {
		return textoFinal;
	}
	
	public void setTextoFinal(String textoFinal) {
		this.textoFinal = textoFinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((fimVigencia == null) ? 0 : fimVigencia.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		result = prime * result + ((inicioVigencia == null) ? 0 : inicioVigencia.hashCode());
		result = prime * result + ((norma == null) ? 0 : norma.hashCode());
		result = prime * result + ((simbolo == null) ? 0 : simbolo.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DispositivoBean other = (DispositivoBean) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (fimVigencia == null) {
			if (other.fimVigencia != null)
				return false;
		} else if (!fimVigencia.equals(other.fimVigencia))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		if (inicioVigencia == null) {
			if (other.inicioVigencia != null)
				return false;
		} else if (!inicioVigencia.equals(other.inicioVigencia))
			return false;
		if (norma == null) {
			if (other.norma != null)
				return false;
		} else if (!norma.equals(other.norma))
			return false;
		if (simbolo == null) {
			if (other.simbolo != null)
				return false;
		} else if (!simbolo.equals(other.simbolo))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}

	public String getIdentificadorPena() {
		return identificadorPena;
	}

	public void setIdentificadorPena(String identificadorPena) {
		this.identificadorPena = identificadorPena;
	}

	public String getArtigo() {
		return artigo;
	}

	public void setArtigo(String artigo) {
		this.artigo = artigo;
	}

	public String getParagrafo() {
		return paragrafo;
	}

	public void setParagrafo(String paragrafo) {
		this.paragrafo = paragrafo;
	}

	public String getInciso() {
		return inciso;
	}

	public void setInciso(String inciso) {
		this.inciso = inciso;
	}
}
