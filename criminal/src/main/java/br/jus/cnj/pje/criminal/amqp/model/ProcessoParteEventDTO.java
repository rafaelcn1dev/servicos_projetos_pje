package br.jus.cnj.pje.criminal.amqp.model;

import java.io.Serializable;

import br.jus.cnj.pje.criminal.enums.SituacaoParteEnum;

public class ProcessoParteEventDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String numeroProcesso;
	private Long idProcessoPje;
	private Long idProcessoPartePje;
	private Long idPessoaPje;
	private String rji;
	private SituacaoParteEnum situacaoParte = SituacaoParteEnum.A;

	public ProcessoParteEventDTO(String numeroProcesso, Long idProcessoPje, Long idProcessoPartePje, Long idPessoaPje, String rji,
			SituacaoParteEnum situacaoParte) {
		super();
		this.numeroProcesso = numeroProcesso;
		this.idProcessoPje = idProcessoPje;
		this.idProcessoPartePje = idProcessoPartePje;
		this.idPessoaPje = idPessoaPje;
		this.rji = rji;
		this.situacaoParte = situacaoParte;
	}
	
	public ProcessoParteEventDTO() {
		super();
	}
	
	public String getNumeroProcesso() {
		return numeroProcesso;
	}
	
	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public Long getIdProcessoPje() {
		return idProcessoPje;
	}

	public void setIdProcessoPje(Long idProcessoPje) {
		this.idProcessoPje = idProcessoPje;
	}

	public Long getIdProcessoPartePje() {
		return idProcessoPartePje;
	}

	public void setIdProcessoPartePje(Long idProcessoPartePje) {
		this.idProcessoPartePje = idProcessoPartePje;
	}

	public Long getIdPessoaPje() {
		return idPessoaPje;
	}

	public void setIdPessoaPje(Long idPessoaPje) {
		this.idPessoaPje = idPessoaPje;
	}
	
	public String getRji() {
		return rji;
	}

	public void setRji(String rji) {
		this.rji = rji;
	}

	public SituacaoParteEnum getSituacaoParte() {
		return situacaoParte;
	}

	public void setSituacaoParte(SituacaoParteEnum situacaoParte) {
		this.situacaoParte = situacaoParte;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPessoaPje == null) ? 0 : idPessoaPje.hashCode());
		result = prime * result + ((idProcessoPartePje == null) ? 0 : idProcessoPartePje.hashCode());
		result = prime * result + ((idProcessoPje == null) ? 0 : idProcessoPje.hashCode());
		result = prime * result + ((numeroProcesso == null) ? 0 : numeroProcesso.hashCode());
		result = prime * result + ((rji == null) ? 0 : rji.hashCode());
		result = prime * result + ((situacaoParte == null) ? 0 : situacaoParte.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ProcessoParteEventDTO other = (ProcessoParteEventDTO) obj;
		if (idPessoaPje == null) {
			if (other.idPessoaPje != null) {
				return false;
			}
		} else if (!idPessoaPje.equals(other.idPessoaPje)) {
			return false;
		}
		if (idProcessoPartePje == null) {
			if (other.idProcessoPartePje != null) {
				return false;
			}
		} else if (!idProcessoPartePje.equals(other.idProcessoPartePje)) {
			return false;
		}
		if (idProcessoPje == null) {
			if (other.idProcessoPje != null) {
				return false;
			}
		} else if (!idProcessoPje.equals(other.idProcessoPje)) {
			return false;
		}
		if (numeroProcesso == null) {
			if (other.numeroProcesso != null) {
				return false;
			}
		} else if (!numeroProcesso.equals(other.numeroProcesso)) {
			return false;
		}
		if (rji == null) {
			if (other.rji != null) {
				return false;
			}
		} else if (!rji.equals(other.rji)) {
			return false;
		}
		if (situacaoParte != other.situacaoParte) {
			return false;
		}
		return true;
	}

}