package br.jus.cnj.pje.criminal.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import br.jus.cnj.pje.criminal.listeners.CriminalMultiTenancyListener;


@Entity
@Table(name = "tb_seeu_tribunal")
@EntityListeners(CriminalMultiTenancyListener.class)
public class SeeuTribunal extends AbstractEntity<Long> {
		

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_seeu_tribunal", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "ds_tribunal") 			private String dsTribunal;
	@Column(name = "ds_parte_autora_seeu")	private String dsParteAutoraSeeu;
	@Column(name = "cd_seeu")				private Integer cdSeeu;
	@Column(name = "ds_identificador")		private String dsIdentificador;
	@Column(name = "in_ativo")				private Boolean ativo;
	@Column(name = "cd_tribunal")			private String codigoTribunal;
	@Column(name = "nr_cnpj")				private String cnpj;
	@Column(name = "ds_municipio")			private String dsMunicipio;
	@Column(name = "ds_estado")				private String dsEstado;
	@Column(name = "ds_login_pje_mni")		private String dsLoginPjeMni;
	@Column(name = "ds_senha_pje_mni")		private String dsSenhaPjeMni;
	@Column(name = "ds_url_pje_mni")		private String dsUrlPjeMni;
	@Column(name = "ds_codigo_tipo_documento_pje")	private String dsCodigoTipoDocumentoPje;
	

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getCodigoTribunal() {
		return this.codigoTribunal;
	}
	
	public void setCodigoTribunal(String codigoTribunal) {
		this.codigoTribunal = codigoTribunal;
	}

	public String getDsIdentificador() {
		return dsIdentificador;
	}

	public void setDsIdentificador(String dsIdentificador) {
		this.dsIdentificador = dsIdentificador;
	}

	public Integer getCdSeeu() {
		return cdSeeu;
	}

	public void setCdSeeu(Integer cdSeeu) {
		this.cdSeeu = cdSeeu;
	}

	
	public String getDsTribunal() {
		return dsTribunal;
	}

	public void setDsTribunal(String dsTribunal) {
		this.dsTribunal = dsTribunal;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}	

	public String getDsParteAutoraSeeu() {
		return dsParteAutoraSeeu;
	}

	public void setDsParteAutoraSeeu(String dsParteAutoraSeeu) {
		this.dsParteAutoraSeeu = dsParteAutoraSeeu;
	}

	public String getDsLoginPjeMni() {
		return dsLoginPjeMni;
	}

	public void setDsLoginPjeMni(String dsLoginPjeMni) {
		this.dsLoginPjeMni = dsLoginPjeMni;
	}

	public String getDsSenhaPjeMni() {
		return dsSenhaPjeMni;
	}

	public void setDsSenhaPjeMni(String dsSenhaPjeMni) {
		this.dsSenhaPjeMni = dsSenhaPjeMni;
	}

	public String getDsUrlPjeMni() {
		return dsUrlPjeMni;
	}

	public void setDsUrlPjeMni(String dsUrlPjeMni) {
		this.dsUrlPjeMni = dsUrlPjeMni;
	}
	
	public String getDsMunicipio() {
		return dsMunicipio;
	}

	public void setDsMunicipio(String dsMunicipio) {
		this.dsMunicipio = dsMunicipio;
	}

	public String getDsEstado() {
		return dsEstado;
	}

	public void setDsEstado(String dsEstado) {
		this.dsEstado = dsEstado;
	}
	
	public String getDsCodigoTipoDocumentoPje() {
		return dsCodigoTipoDocumentoPje;
	}

	public void setDsCodigoTipoDocumentoPje(String dsCodigoTipoDocumentoPje) {
		this.dsCodigoTipoDocumentoPje = dsCodigoTipoDocumentoPje;
	}


}
