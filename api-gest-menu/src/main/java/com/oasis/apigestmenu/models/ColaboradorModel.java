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
@Table(name = "tbl_colaborador")
public class ColaboradorModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private UUID id;

	@NotEmpty(message = "O Atributo NOME está Vazio!")
	@Size(min=2, 
	  max=64,
	  message = "O Numero de caracteres do atributo 'NOME' tem de estar entre 2 and 64!")
	@Column(name= "nome_colab", length=64, nullable=false, unique=false, updatable=true)
	private String nomeColab;
	
	@NotEmpty(message = "O Atributo Telefone está Vazio!")
	@Size(min=2, 
	  max=64,
	  message = "O Numero de caracteres do atributo 'Telefone' tem de estar entre 2 and 64!")
	@Column(name= "telefone_colab", length=64, nullable=false, unique=false, updatable=true)
	private String telefoneColab;
	
	
	@NotEmpty(message = "O Atributo Email está Vazio!")
	@Size(min=2, 
	  max=64,
	  message = "O Numero de caracteres do atributo 'Email' tem de estar entre 2 and 64!")
	@Column(name= "email_colab", length=64, nullable=false, unique=false, updatable=true)
	private String emailColab;
	
	@NotEmpty(message = "O Atributo Endereco está Vazio!")
	@Size(min=2, 
	  max=64,
	  message = "O Numero de caracteres do atributo 'Endereco' tem de estar entre 2 and 64!")
	@Column(name= "endereco_colab", length=64, nullable=false, unique=false, updatable=true)
	private String enderecoColab;
	
	
	
	@Column(name = "id_foto")
	private String idFoto;
	
	
	@Column(name = "cni")
	private String cni;
	
	
	@Column(name = "nif")
	private String nif;
	
	@NotEmpty(message = "O Atributo Numero RH está Vazio!")
	@Size(min=2, 
	  max=64,
	  message = "O Numero de caracteres do atributo 'Numero RH' tem de estar entre 2 and 64!")
	@Column(name= "numero_rh", length=64, nullable=false, unique=false, updatable=true)
	private String numeroRh;
	
	
	@Column(name = "utilizador")
	private String utilizador;
	
	@Column(name = "estado")
	private String estado;
	


	private LocalDateTime dataRegisto;
	
	


	
	
	@ManyToOne
	@JoinColumn(name = "tipo_colab_fk", nullable = false)
	private TipoColaboradorModel tipoColabFk;
	
	
	@ManyToOne
	@JoinColumn(name = "tbl_departamento_id", nullable = false)
	private DepartamentoModel departamentoFk;
	
	
	
	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public ColaboradorModel() {
		super();

	}

	public DepartamentoModel getDepartamentoFk() {
		return departamentoFk;
	}

	public void setDepartamentoFk(DepartamentoModel departamentoFk) {
		this.departamentoFk = departamentoFk;
	}

	@Column(name = "hotel")
	private String hotel;

	
	public String getNomeColab() {
		return nomeColab;
	}

	public void setNomeColab(String nomeColab) {
		this.nomeColab = nomeColab;
	}

	public String getTelefoneColab() {
		return telefoneColab;
	}

	public void setTelefoneColab(String telefoneColab) {
		this.telefoneColab = telefoneColab;
	}



	public String getEmailColab() {
		return emailColab;
	}

	public void setEmailColab(String emailColab) {
		this.emailColab = emailColab;
	}

	public String getEnderecoColab() {
		return enderecoColab;
	}

	public void setEnderecoColab(String enderecoColab) {
		this.enderecoColab = enderecoColab;
	}

	public String getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(String utilizador) {
		this.utilizador = utilizador;
	}
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getDataRegisto() {
		return dataRegisto;
	}

	public void setDataRegisto(LocalDateTime dataRegisto) {
		this.dataRegisto = dataRegisto;
	}



	public String getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(String idFoto) {
		this.idFoto = idFoto;
	}

	public String getCni() {
		return cni;
	}

	public void setCni(String cni) {
		this.cni = cni;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNumeroRh() {
		return numeroRh;
	}

	public void setNumeroRh(String numeroRh) {
		this.numeroRh = numeroRh;
	}

	public TipoColaboradorModel getTipoColabFk() {
		return tipoColabFk;
	}

	public void setTipoColabFk(TipoColaboradorModel tipoColabFk) {
		this.tipoColabFk = tipoColabFk;
	}

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cni, dataRegisto, departamentoFk, emailColab, enderecoColab, estado, hotel, id,
				idFoto, nif, nomeColab, numeroRh, telefoneColab, tipoColabFk, utilizador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColaboradorModel other = (ColaboradorModel) obj;
		return Objects.equals(cni, other.cni) && Objects.equals(dataRegisto, other.dataRegisto)
				&& Objects.equals(departamentoFk, other.departamentoFk) && Objects.equals(emailColab, other.emailColab)
				&& Objects.equals(enderecoColab, other.enderecoColab) && Objects.equals(estado, other.estado)
				&& Objects.equals(hotel, other.hotel)
				&& Objects.equals(id, other.id) && Objects.equals(idFoto, other.idFoto)
				&& Objects.equals(nif, other.nif) && Objects.equals(nomeColab, other.nomeColab)
				&& Objects.equals(numeroRh, other.numeroRh) && Objects.equals(telefoneColab, other.telefoneColab)
				&& Objects.equals(tipoColabFk, other.tipoColabFk) && Objects.equals(utilizador, other.utilizador);
	}

	@Override
	public String toString() {
		return "ColaboradorModel [id=" + id + ", nomeColab=" + nomeColab + ", telefoneColab=" + telefoneColab
				+ ", emailColab=" + emailColab + ", enderecoColab=" + enderecoColab + ", idFoto=" + idFoto + ", cni="
				+ cni + ", nif=" + nif + ", numeroRh=" + numeroRh + ", utilizador=" + utilizador + ", estado=" + estado
				+ ", dataRegisto=" + dataRegisto + ", tipoColabFk=" + tipoColabFk + ", departamentoFk=" + departamentoFk
				+ ", hotel=" + hotel + "]";
	}


	
	

}
