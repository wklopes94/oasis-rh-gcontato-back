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
@Table(name = "tbl_tipo_colaborador")
public class TipoColaboradorModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
 	private UUID id;
	
	@NotEmpty(message = "O Atributo Tipo Colaborador está Vazio!")
	@Size(min=2, 
	  max=64,
	  message = "O Numero de caracteres do atributo 'Tipo Colaborador' tem de estar entre 2 and 64!")
	@Column(name= "tipo_colaborador", length=64, nullable=false, unique=false, updatable=true)
	private String tipoColaborador;
	
	
	@Column(name = "utilizador")
	private String utilizador;
	
	@Column(name = "estado")
	private String estado;
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public TipoColaboradorModel(String estado) {
		super();
		this.estado = estado;
	}

	@Column(name = "data_registo")
	private LocalDateTime dataRegisto;
	
	
	@OneToMany(mappedBy = "tipoColabFk")
	@JsonIgnore
	private List<ColaboradorModel> colaboradorModel = new ArrayList<>();
	
	public TipoColaboradorModel(UUID id,
			@NotEmpty(message = "O Atributo Tipo Colaborador está Vazio!") @Size(min = 2, max = 64, message = "O Numero de caracteres do atributo 'Tipo Colaborador' tem de estar entre 2 and 64!") String tipoColaborador,
			String utilizador, String estado, LocalDateTime dataRegisto, List<ColaboradorModel> colaboradorModel) {
		super();
		this.id = id;
		this.tipoColaborador = tipoColaborador;
		this.utilizador = utilizador;
		this.estado = estado;
		this.dataRegisto = dataRegisto;
		this.colaboradorModel = colaboradorModel;
	}
	
	public TipoColaboradorModel() {
		super();
		
	}
	public List<ColaboradorModel> getColaboradorModel() {
		return colaboradorModel;
	}
	public void setColaboradorModel(List<ColaboradorModel> colaboradorModel) {
		this.colaboradorModel = colaboradorModel;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTipoColaborador() {
		return tipoColaborador;
	}
	public void setTipoColaborador(String tipoColaborador) {
		this.tipoColaborador = tipoColaborador;
	}
	public String getUtilizador() {
		return utilizador;
	}
	public void setUtilizador(String utilizador) {
		this.utilizador = utilizador;
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
		TipoColaboradorModel other = (TipoColaboradorModel) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
