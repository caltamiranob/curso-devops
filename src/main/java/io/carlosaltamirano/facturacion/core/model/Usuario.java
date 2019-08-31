package io.carlosaltamirano.facturacion.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private Integer id;

	@NotEmpty(message="El campo NOMBRES es obligatorio.")
	@Size(max=100, message="El campo Nombres no debe exceder de 100 caracteres.")
	@Column(length=100, nullable=false)
	private String nombres;
	
	@NotEmpty(message="El campo APELLIDOS es obligatorio.")
	@Size(max=100, message="El campo Apellidos no debe exceder de 100 caracteres.")
	@Column(length=11, nullable=false)
	private String apellidos;
	
	@NotEmpty(message="El campo Email es obligatorio.")
	@Size(max=100, message="El campo Email no debe exceder de 100 caracteres.")
	@Column(length=100, nullable=false)
	private String email;
	
	
	public Usuario() {
	}

	public Usuario(String nombres, String apellidos, String email) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
