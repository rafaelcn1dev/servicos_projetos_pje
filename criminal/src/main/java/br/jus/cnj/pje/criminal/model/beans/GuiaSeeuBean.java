package br.jus.cnj.pje.criminal.model.beans;

public class GuiaSeeuBean extends BaseBean{

	private static final long serialVersionUID = 1L;

	private String numeroProcessoSeeu;
	private AreaDeVaraBean areaDeVara;
	private String aguardandoPrisao; // "S" ou "N"

	public GuiaSeeuBean() {
		super();
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getNumeroProcessoSeeu() {
		return numeroProcessoSeeu;
	}

	public void setNumeroProcessoSeeu(String numeroProcessoSeeu) {
		this.numeroProcessoSeeu = numeroProcessoSeeu;
	}

	public AreaDeVaraBean getAreaDeVara() {
		return areaDeVara;
	}

	public void setAreaDeVara(AreaDeVaraBean areaDeVara) {
		this.areaDeVara = areaDeVara;
	}

	public String getAguardandoPrisao() {
		return aguardandoPrisao;
	}

	public void setAguardandoPrisao(String aguardandoPrisao) {
		this.aguardandoPrisao = aguardandoPrisao;
	}
}