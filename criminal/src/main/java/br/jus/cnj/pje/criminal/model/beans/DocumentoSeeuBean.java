package br.jus.cnj.pje.criminal.model.beans;

/**
 * Representa um documento a ser encaminhado ao SEEU.
 * @author adriano.silva
 *
 */
public class DocumentoSeeuBean extends BaseBean{

	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private Boolean binario;
	private String linkDownload;

	
	public DocumentoSeeuBean() {
		super();
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLinkDownload() {
		return linkDownload;
	}

	public void setLinkDownload(String linkDownload) {
		this.linkDownload = linkDownload;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.descricao;
	}

	public Boolean isBinario() {
		return binario;
	}

	public void setBinario(Boolean binario) {
		this.binario = binario;
	}
	
}
