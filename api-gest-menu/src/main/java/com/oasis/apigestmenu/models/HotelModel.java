package com.oasis.apigestmenu.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tbl_hotel")
public class HotelModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private UUID id;
	
	@NotEmpty(message = "O Atributo NOME está Vazio!")
	@Size(min=2, 
	  max=64,
	  message = "O Numero de caracteres do atributo 'NOME' tem de estar entre 2 and 64!")
	@Column(name= "nome", length=64, nullable=false, unique=false, updatable=true)
	private String nome;
	
	@NotEmpty(message = "O Atributo Numero Fixo está Vazio!")
	@Size(min=2, 
	  max=64,
	  message = "O Numero de caracteres do atributo 'Numero Fixo' tem de estar entre 2 and 64!")
	@Column(name = "numero_fixo", length=64, nullable=false, unique=false, updatable=true)
	private String numeroFixo;
	
	
	@Column(name = "estado")
	//Boolean
	private String estado;
	
	
	@Column(name = "utilizador_reg")
	private String utilizadorReg;
	
	@FutureOrPresent(message="A Data de Criacão tem de ser uma Data Presente ou Futuro!!") 
	@NotNull(message="A Data de Criacao nao pode ser Null")
	@Column(name = "data_registo", nullable=false, updatable=false, unique=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime dataRegisto;
	

	@OneToMany(mappedBy = "hotelFk")
	@JsonIgnore
	private List<DepartamentoModel> departamentosModel = new ArrayList<>();
	

	public HotelModel() {
		super();
	}

	public HotelModel(UUID id, String nome, String numeroFixo, String estado, String utilizadorReg,
			LocalDateTime dataRegisto) {
		super();
		this.id = id;
		this.nome = nome;
		this.numeroFixo = numeroFixo;
		this.estado = estado;
		this.utilizadorReg = utilizadorReg;
		this.dataRegisto = dataRegisto;
	}

	public List<DepartamentoModel> getDepartamentosModel() {
		return departamentosModel;
	}

	public void setDepartamentosModel(List<DepartamentoModel> departamentosModel) {
		this.departamentosModel = departamentosModel;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroFixo() {
		return numeroFixo;
	}

	public void setNumeroFixo(String numeroFixo) {
		this.numeroFixo = numeroFixo;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotelModel other = (HotelModel) obj;
		return Objects.equals(id, other.id);
	}

}
