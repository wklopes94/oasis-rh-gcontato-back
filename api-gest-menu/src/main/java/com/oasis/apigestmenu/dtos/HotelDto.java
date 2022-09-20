package com.oasis.apigestmenu.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class HotelDto {
	


	@NotEmpty
	private String nome;
	@NotEmpty
	private String numeroFixo;

	

	
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

	

}
