package com.oasis.apigestmenu.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import com.oasis.apigestmenu.models.DepartamentoModel;
import com.oasis.apigestmenu.models.ExtensaoModel;
import com.oasis.apigestmenu.models.TipoColaboradorModel;



public class ColaboradorDto {
	
	@NotBlank
	private String nomeColab;
	@NumberFormat
	private String telefoneColab;
	@NotBlank
	@Email
	private String emailColab;
	@NotBlank
	private String enderecoColab;
	@NotBlank
	private String numeroRh;
	
	
	private ExtensaoModel extensaofk;
	
	private TipoColaboradorModel tipoColabFk;
	
	private DepartamentoModel departamentoFk;

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
	public ExtensaoModel getExtensaofk() {
		return extensaofk;
	}
	public void setExtensaofk(ExtensaoModel extensaofk) {
		this.extensaofk = extensaofk;
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
	public String getNumeroRh() {
		return numeroRh;
	}
	public void setNumeroRh(String numeroRh) {
		this.numeroRh = numeroRh;
	}
	public DepartamentoModel getDepartamentoFk() {
		return departamentoFk;
	}
	public void setDepartamentoFk(DepartamentoModel departamentoFk) {
		this.departamentoFk = departamentoFk;
	}
	public TipoColaboradorModel getTipoColabFk() {
		return tipoColabFk;
	}
	public void setTipoColabFk(TipoColaboradorModel tipoColabFk) {
		this.tipoColabFk = tipoColabFk;
	}
	@Override
	public String toString() {
		return "ColaboradorDto [nomeColab=" + nomeColab + ", telefoneColab=" + telefoneColab + ", emailColab="
				+ emailColab + ", enderecoColab=" + enderecoColab + ", numeroRh=" + numeroRh + ", extensaofk="
				+ extensaofk + ", tipoColabFk=" + tipoColabFk + ", departamentoFk=" + departamentoFk + "]";
	}
	
	

	

}
