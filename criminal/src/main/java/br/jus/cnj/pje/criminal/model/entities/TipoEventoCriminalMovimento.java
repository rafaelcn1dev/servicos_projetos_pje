package br.jus.cnj.pje.criminal.model.entities;

import javax.persistence.*;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.jus.cnj.pje.criminal.base.AbstractEntity;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tb_tipo_ec_movimento")
@SequenceGenerator(allocationSize = 1, name = "gen_tipo_ec_movimento", sequenceName = "sq_tb_tipo_ec_movimento")
public class TipoEventoCriminalMovimento extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private TipoEventoCriminal tipoEventoCriminal;
	private String codMovimento;

	@Id
	@GeneratedValue(generator = "gen_tipo_ec_movimento")
	@Column(name = "id_tipo_ec_movimento", unique = true, nullable = false)
	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "id_tipo_ec")
	public TipoEventoCriminal getTipoEventoCriminal() {
		return tipoEventoCriminal;
	}

	public void setTipoEventoCriminal(TipoEventoCriminal tipoEventoCriminal) {
		this.tipoEventoCriminal = tipoEventoCriminal;
	}


	@Column(name = "ds_cod_movimento", unique = true)
	@NotNull
	@Length(max = 30)
	public String getCodMovimento() {
		return codMovimento;
	}

	public void setCodMovimento(String codMovimento) {
		this.codMovimento = codMovimento;
	}
	
	@Override
	@Transient
	public String getCodigoTribunal() {
		return null;
	}
	
	@Override
	public void setCodigoTribunal(String codigoTribunal) {
	}	

	@Override
	@Transient
	public Boolean getAtivo() {
		return null;
	}

	@Override
	public void setAtivo(Boolean ativo) {

	}


}
