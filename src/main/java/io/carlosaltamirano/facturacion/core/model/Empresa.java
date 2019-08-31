package io.carlosaltamirano.facturacion.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Constraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Empresa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private Integer id;

	@NotEmpty(message="El campo RUC es obligatorio.")
	@Size(min=11, max=11, message="El campo RUC debe tener 11 caracteres.")
	@Column(length=11, nullable=false)
	private String ruc;
	
	@NotEmpty(message="El campo Razon Social es obligatorio.")
	@Size(max=100, message="El campo Razón Social no debe exceder de 100 caracteres.")
	@Column(length=100, nullable=false)
	private String razonSocial;
	
	@Column(length=100)
	@Size(max=100, message="El campo Razon Social Comercial no debe exceder de 100 caracteres.")
	private String razonSocialComercial;
	
	@Column(length=255)
	@Size(max=255, message="El campo Direccion no debe exceder de 255 caracteres.")
	private String direccion;
	
	@Column(length=9)
	@Size(min=9, max=9, message="El campo Celular debe tener 9 caracteres.")
	private String celular;


	public Empresa() {
	}

	public Empresa(String ruc, String razonSocial) {
		super();
		this.ruc = ruc;
		this.razonSocial = razonSocial;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRazonSocialComercial() {
		return razonSocialComercial;
	}

	public void setRazonSocialComercial(String razonSocialComercial) {
		this.razonSocialComercial = razonSocialComercial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public String toString() {
		return "Empresa(id=" + id + ", ruc=" + ruc + ", razonSocial=" + razonSocial + ", razonSocialComercial="
				+ razonSocialComercial + ", direccion=" + direccion + ", celular=" + celular + ")";
	}
	
	
}
