package br.jus.cnj.pje.criminal.model.dto.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.jus.cnj.pje.criminal.model.entities.Municipio;
import br.jus.cnj.pje.criminal.model.entities.ProcessoProcedimentoOrigem;

public class ProcessoDTO implements Serializable {
	
	
	private static final long serialVersionUID = -1976125557824254030L;
	private Long id;
	private String nrProcesso;
	private Date dtLocalFato;
	private String dsLocalFato;
	private String dsLongitude;
	private String dsLatitude;
	private String cep;
	private Municipio municipio;
	private String nmBairro;
	private String nmLogradouro;
	private String nmNumero;
	private String complemento;
	private String codigoTribunal;
	
	private List<ProcessoProcedimentoOrigemDTO> processoProcedimentoOrigemList;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNrProcesso() {
		return nrProcesso;
	}
	public void setNrProcesso(String nrProcesso) {
		this.nrProcesso = nrProcesso;
	}
	public Date getDtLocalFato() {
		return dtLocalFato;
	}
	public void setDtLocalFato(Date dtLocalFato) {
		this.dtLocalFato = dtLocalFato;
	}
	public String getDsLocalFato() {
		return dsLocalFato;
	}
	public void setDsLocalFato(String dsLocalFato) {
		this.dsLocalFato = dsLocalFato;
	}
	public String getDsLongitude() {
		return dsLongitude;
	}
	public void setDsLongitude(String dsLongitude) {
		this.dsLongitude = dsLongitude;
	}
	public String getDsLatitude() {
		return dsLatitude;
	}
	public void setDsLatitude(String dsLatitude) {
		this.dsLatitude = dsLatitude;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public String getNmBairro() {
		return nmBairro;
	}
	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}
	public String getNmLogradouro() {
		return nmLogradouro;
	}
	public void setNmLogradouro(String nmLogradouro) {
		this.nmLogradouro = nmLogradouro;
	}
	public String getNmNumero() {
		return nmNumero;
	}
	public void setNmNumero(String nmNumero) {
		this.nmNumero = nmNumero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@JsonIgnore
	public String getCodigoTribunal() {
		return codigoTribunal;
	}
	
	@JsonProperty
	public void setCodigoTribunal(String codigoTribunal) {
		this.codigoTribunal = codigoTribunal;
	}
	
	@JsonIgnoreProperties("processo")
	public List<ProcessoProcedimentoOrigemDTO> getProcessoProcedimentoOrigemList() {
		return processoProcedimentoOrigemList;
	}
	public void setProcessoProcedimentoOrigemList(List<ProcessoProcedimentoOrigemDTO> processoProcedimentoOrigemList) {
		this.processoProcedimentoOrigemList = processoProcedimentoOrigemList;
	}

	

}
