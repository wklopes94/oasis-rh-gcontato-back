package com.oasis.apigestmenu.dtos;

import javax.validation.constraints.NotBlank;

public class TipoColaboradorDto {
	
	@NotBlank
	private String tipoColaborador;

	public String getTipoColaborador() {
		return tipoColaborador;
	}

	public void setTipoColaborador(String tipoColaborador) {
		this.tipoColaborador = tipoColaborador;
	}
	
	

}
