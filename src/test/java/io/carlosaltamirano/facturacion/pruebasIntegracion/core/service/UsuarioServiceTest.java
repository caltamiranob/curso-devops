package io.carlosaltamirano.facturacion.pruebasIntegracion.core.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.carlosaltamirano.facturacion.core.exception.FacturacionWebException;
import io.carlosaltamirano.facturacion.core.model.Usuario;
import io.carlosaltamirano.facturacion.core.service.UsuarioService;
import io.carlosaltamirano.facturacion.test.categoria.PruebaIntegracion;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Category(PruebaIntegracion.class)
public class UsuarioServiceTest {

	@Autowired
	UsuarioService usuarioService;
	
	private Usuario usuario;
	
	@Before
	public void setUp() {
		usuario = new Usuario("carlos", "altamirano", "alt.carlos@gmail.com");
	}
	
	@After
	public void destroy() {
		
		try {
			
			usuarioService.eliminar(usuario);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void a_creacionUsuarioConDatosValidos() {
		
		//TODO: completar prueba de integración
		
	}
	
	
}
