package com.oasis.apigestmenu.dtos;


import java.util.Objects;
import javax.validation.constraints.NotBlank;
import com.oasis.apigestmenu.models.DepartamentoModel;

public class ExtensaoDto {
	
	@NotBlank
	private String numero;

	private DepartamentoModel departamentoFk;
	
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public DepartamentoModel getDepartamentoFk() {
		return departamentoFk;
	}
	public void setDepartamentoFk(DepartamentoModel departamentoFk) {
		this.departamentoFk = departamentoFk;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(departamentoFk, numero);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtensaoDto other = (ExtensaoDto) obj;
		return Objects.equals(departamentoFk, other.departamentoFk) && Objects.equals(numero, other.numero);
	}
	@Override
	public String toString() {
		return "ExtensaoDto [numero=" + numero + ", departamentoFk=" + departamentoFk + ", getNumero()=" + getNumero()
				+ ", getDepartamentoFk()=" + getDepartamentoFk() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}


	

}
