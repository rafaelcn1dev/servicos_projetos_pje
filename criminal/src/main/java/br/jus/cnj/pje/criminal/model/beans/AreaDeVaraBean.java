package br.jus.cnj.pje.criminal.model.beans;

public class AreaDeVaraBean extends BaseBean{

	private static final long serialVersionUID = 1L;

	private Long codigo; // <- Código da area de varas no SEEU
	private Integer competencia; //<- Valores possíveis: 16 regime fechado/semiaberto; 18 aberto; 3 execução de multa
	
	public AreaDeVaraBean() {
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

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Integer competencia) {
		this.competencia = competencia;
	}
}