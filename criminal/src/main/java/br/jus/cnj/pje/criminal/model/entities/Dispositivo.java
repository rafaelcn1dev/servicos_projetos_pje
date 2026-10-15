package br.jus.cnj.pje.criminal.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import br.jus.cnj.pje.criminal.enums.DispositivoEnum;


@Entity
@Table(name = "tb_dispositivo")
@SequenceGenerator(allocationSize = 1, name = "gen_dispositivo", sequenceName = "sq_tb_dispositivo")
public class Dispositivo extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "gen_dispositivo")
	@Column(name = "id_dispositivo", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "id_tipo_dispositivo")
	private TipoDispositivo tipoDispositivo;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "id_norma")
	private Norma norma;

	@ManyToOne
	@JoinColumn(name = "id_dispositivo_pai")
	private Dispositivo dispositivoPai;

	@Column(name = "ds_simbolo")
	private String simbolo;

	@Column(name = "ds_identificador")
	private String identificador;

	@Column(name = "ds_texto")
	private String texto;

	@Column(name = "dt_inicio_vigencia", nullable = false)
	private Date inicioVigencia;

	@Column(name = "dt_fim_vigencia")
	private Date fimVigencia;

	@Column(name = "in_ativo", nullable = false)
	private Boolean ativo;
	
	@Transient
	private String textoFinal;
	
	@Transient
	private String textoFinalResumido;
	
	@Transient
	private String identificadorPena = null;
	
	@Transient
	private String artigo = null;
	
	@Transient
	private String paragrafo = null;
	
	@Transient
	private String inciso = null;

	public Dispositivo(Long id, TipoDispositivo tipoDispositivo, Norma norma, Dispositivo dispositivoPai,
			String simbolo, String identificador, String texto, Date inicioVigencia, Date fimVigencia, Boolean ativo) {
		super();
		this.id = id;
		this.tipoDispositivo = tipoDispositivo;
		this.norma = norma;
		this.dispositivoPai = dispositivoPai;
		this.simbolo = simbolo;
		this.identificador = identificador;
		this.texto = texto;
		this.inicioVigencia = inicioVigencia;
		this.fimVigencia = fimVigencia;
		this.ativo = ativo;
	}
	
	public Dispositivo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoDispositivo getTipoDispositivo() {
		return tipoDispositivo;
	}

	public void setTipoDispositivo(TipoDispositivo tipoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
	}

	public Norma getNorma() {
		return norma;
	}

	public void setNorma(Norma norma) {
		this.norma = norma;
	}

	public Dispositivo getDispositivoPai() {
		return dispositivoPai;
	}

	public void setDispositivoPai(Dispositivo dispositivoPai) {
		this.dispositivoPai = dispositivoPai;
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
		return this.getTextoFinalDispositivo(this);
	}

	public String getTextoFinalResumido() {
		return getTextoFinalDispositivoResumido(this);
	}
	
	private String getTextoFinalDispositivoResumido(Dispositivo dispositivo) {
		String texto = "";
		if(dispositivo.getDispositivoPai() != null) {
			texto = getTextoFinalDispositivoResumido(dispositivo.getDispositivoPai()) + ", ";
		}
		if(dispositivo.getSimbolo() == null) {
			dispositivo.setSimbolo("");
		}
		texto = texto + dispositivo.getSimbolo() + " " + dispositivo.getIdentificador();
		return texto;
	}

	private String getTextoFinalDispositivo(Dispositivo dispositivo) {
		String texto = (dispositivo.getNorma().getSigla() == null ? "" : dispositivo.getNorma().getSigla()) 
				+ " " + dispositivo.getNorma().getNumeroNorma() + ", ";
		
		if(dispositivo.getDispositivoPai() != null) {
			texto = this.getTextoFinalDispositivo(dispositivo.getDispositivoPai()) + ", ";
		}
		
		if(dispositivo.getSimbolo() == null) {
			dispositivo.setSimbolo("");
		}
		
		texto = texto + dispositivo.getSimbolo() + " " + dispositivo.getIdentificador();
		
		return texto;
	}	
	
	@Override
	@Transient
	@JsonIgnore
	public String getCodigoTribunal() {
		return null;
	}
	
	@Override
	@JsonProperty
	public void setCodigoTribunal(String codigoTribunal) {
	}
	
	/**
	 * Identificador do dispositivo penal no formato "lei.ano.artigo.paragrafo.inciso".
	 * Utilizado precipuamente pelo pelo SEEU.
	 * @return
	 */
	public String getIdentificadorPena() {
		if (this.norma!=null && this.identificadorPena==null) {
			SimpleDateFormat sdfAno = new SimpleDateFormat("YYYY");
			String lei = this.norma.getNumeroNorma();
			String ano = sdfAno.format(this.norma.getInicioVigencia());
			String artigo = getArtigo();
			String paragrafo = getParagrafo();
			String inciso = getInciso();
			identificadorPena = lei+"."+ano+"."+artigo+"."+paragrafo+"."+inciso;
		}
		return identificadorPena;
	}
	
	public String getArtigo() {
		if (this.artigo==null) {
			atualizaHierarquiaDispositivos();
		}
		return artigo;
	}
	
	public String getParagrafo() {
		if (this.paragrafo==null) {
			atualizaHierarquiaDispositivos();
		}
		return paragrafo;
	}
	
	public String getInciso() {
		if (this.inciso==null) {
			atualizaHierarquiaDispositivos();
		}
		return inciso;
	}
	
	
	/**
	 * Atualiza a hierarquia de dispositivos (campos artigo, paragrafo e inciso), partindo dos seguintes pressupostos:
	 * 1) Um parágrafo é sempre filho de um artigo; 2) Um inciso pode ser filho de um parágrafo ou de um artigo.
	 * Obs: como não existe um campo dispositivoFilhos, só é possível saber a hierarquia ascendente, e não a descendente.
	 * Em tese, isso não é um problema porque o dispositivo utilizado nos eventos criminais será sempre
	 * aquele mais específico aplicável ao caso, não fazendo sentido saber se o dispositivo escolhido tem filhos, porque
	 * ele já é o que o usuário escolheu como incidente no caso concreto.
	 */
	private void atualizaHierarquiaDispositivos() {
		if (this.tipoDispositivo != null) {
			if (this.tipoDispositivo.isArtigo()) {
				this.artigo = this.getIdentificador();
				this.paragrafo = "0";
				this.inciso = "0";
			} else if (this.tipoDispositivo.isParagrafo()) {
				this.artigo = this.dispositivoPai != null ? this.dispositivoPai.getIdentificador() : "0";
				this.paragrafo = this.getIdentificador();
				this.inciso = "0";
			} else if (this.tipoDispositivo.isInciso()) {
				this.inciso = this.getIdentificador();
				// Um inciso pode ter como pai um artigo ou um parágrafo
				if (this.dispositivoPai != null) {
					if (this.dispositivoPai.getTipoDispositivo().isArtigo()) {
						this.artigo = this.dispositivoPai.getIdentificador();
						this.paragrafo = "0";
					} else {
						this.paragrafo = this.dispositivoPai.getIdentificador();
						if (this.dispositivoPai.getDispositivoPai() != null) {
							this.artigo = this.dispositivoPai.getDispositivoPai().getIdentificador();
						} else {
							this.artigo = "0";
						}
					}
				}
				// O inciso deveria ter um pai! Não deveria nunca cair aqui!
				else {
					this.artigo = "0";
					this.paragrafo = "0";
				}
			}
		}
	}

}
