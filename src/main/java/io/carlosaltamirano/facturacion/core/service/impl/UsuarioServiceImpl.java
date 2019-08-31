package io.carlosaltamirano.facturacion.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.carlosaltamirano.facturacion.core.exception.FacturacionWebException;
import io.carlosaltamirano.facturacion.core.model.Usuario;
import io.carlosaltamirano.facturacion.core.repository.UsuarioRepository;
import io.carlosaltamirano.facturacion.core.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public Usuario crear(Usuario usuario) throws FacturacionWebException {
		return usuarioRepository.save(usuario);
	}
	
	public void eliminar(Usuario usuario) throws FacturacionWebException{
		
		usuarioRepository.deleteById(usuario.getId());
		
	}
	
	public List<Usuario> listarTodos() {
		
		return usuarioRepository.findAll();
		
	}
	
	

}
