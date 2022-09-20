package com.oasis.apigestmenu.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tbl_extensao")
public class ExtensaoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private UUID id;
	
	@NotEmpty(message = "O Atributo Numero está Vazio!")
	@Size(min=2, 
	  max=64,
	  message = "O Numero de caracteres do atributo 'Numero' tem de estar entre 2 and 64!")
	@Column(name = "numero", length=64, nullable=false, unique=false, updatable=true)
	private String numero;
	
	@Column(name = "estado")
	//Boolean
	private String estado;
	
	
	@Column(name = "utilizador_reg")
	private String utilizadorReg;
	
	@FutureOrPresent(message="A Data de Criacão tem de ser uma Data Presente ou Futuro!!") 
	@NotNull(message="A Data de Criacao nao pode ser Null")
	@Column(name = "data_registo", nullable=false, updatable=false, unique=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime dataRegisto;
	
	
	
	@ManyToOne
	@JoinColumn(name = "tbl_departamento_id")
	private DepartamentoModel departamentoFk;



	public ExtensaoModel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ExtensaoModel(UUID id,
			@NotEmpty(message = "O Atributo Numero está Vazio!") @Size(min = 2, max = 64, message = "O Numero de caracteres do atributo 'Numero' tem de estar entre 2 and 64!") String numero,
			String estado, String utilizadorReg,
			@FutureOrPresent(message = "A Data de Criacão tem de ser uma Data Presente ou Futuro!!") @NotNull(message = "A Data de Criacao nao pode ser Null") LocalDateTime dataRegisto,
			DepartamentoModel departamentoFk) {
		super();
		this.id = id;
		this.numero = numero;
		this.estado = estado;
		this.utilizadorReg = utilizadorReg;
		this.dataRegisto = dataRegisto;
		this.departamentoFk = departamentoFk;
	}



	public UUID getId() {
		return id;
	}



	public void setId(UUID id) {
		this.id = id;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getUtilizadorReg() {
		return utilizadorReg;
	}



	public void setUtilizadorReg(String utilizadorReg) {
		this.utilizadorReg = utilizadorReg;
	}



	public LocalDateTime getDataRegisto() {
		return dataRegisto;
	}



	public void setDataRegisto(LocalDateTime dataRegisto) {
		this.dataRegisto = dataRegisto;
	}



	public DepartamentoModel getDepartamentoFk() {
		return departamentoFk;
	}



	public void setDepartamentoFk(DepartamentoModel departamentoFk) {
		this.departamentoFk = departamentoFk;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public int hashCode() {
		return Objects.hash(dataRegisto, departamentoFk, estado, id, numero, utilizadorReg);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtensaoModel other = (ExtensaoModel) obj;
		return Objects.equals(dataRegisto, other.dataRegisto) && Objects.equals(departamentoFk, other.departamentoFk)
				&& Objects.equals(estado, other.estado) && Objects.equals(id, other.id)
				&& Objects.equals(numero, other.numero) && Objects.equals(utilizadorReg, other.utilizadorReg);
	}



	@Override
	public String toString() {
		return "ExtensaoModel [id=" + id + ", numero=" + numero + ", estado=" + estado + ", utilizadorReg="
				+ utilizadorReg + ", dataRegisto=" + dataRegisto + ", departamentoFk=" + departamentoFk + "]";
	}
	
	
	



}
