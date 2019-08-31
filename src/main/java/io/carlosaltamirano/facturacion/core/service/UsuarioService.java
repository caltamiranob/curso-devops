package io.carlosaltamirano.facturacion.core.service;

import java.util.List;

import io.carlosaltamirano.facturacion.core.exception.FacturacionWebException;
import io.carlosaltamirano.facturacion.core.model.Usuario;

public interface UsuarioService {

	
	Usuario crear(Usuario usuario) throws FacturacionWebException;
	
	void eliminar(Usuario usuario) throws FacturacionWebException;
	
	List<Usuario> listarTodos();
	
}
