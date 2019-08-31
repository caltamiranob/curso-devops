package io.carlosaltamirano.facturacion.pruebasUnitarias.core.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import io.carlosaltamirano.facturacion.core.model.Empresa;
import io.carlosaltamirano.facturacion.test.categoria.PruebaUnitaria;

@Category(PruebaUnitaria.class)
public class EmpresaTest {

	private static Validator validator;
	
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void creacionEmpresaConRucyRazonSocialValidos() {
		Empresa empresa = new Empresa();
		empresa.setRuc("20440578921");
		empresa.setRazonSocial("JB ENTERPRISE GROUP");
		empresa.setRazonSocialComercial("JB GROUP");
		empresa.setDireccion("SAN ISIDRO");
		empresa.setCelular("123456789");
		
		Set<ConstraintViolation<Empresa>> constraintViolations =
				validator.validate(empresa);
		
		assertThat(constraintViolations.size(), is(0));
	}
	
	@Test
	public void validacionCreacionEmpresaCuandoRucEsVacio() {
		Empresa empresa = new Empresa();
		empresa.setRuc("");
		empresa.setRazonSocial("JB ENTERPRISE GROUP");
		
		Set<ConstraintViolation<Empresa>> constraintViolations = validator.validate(empresa);
		assertThat(constraintViolations.size(), is(2));
	}
	
	@Test
	public void validacionCreacionEmpresaCuandoRucNoTieneOnceDigitos() {
		Empresa empresa = new Empresa();
		empresa.setRuc("123");
		empresa.setRazonSocial("JB Group Enterprise");
		Set<ConstraintViolation<Empresa>> constraintViolations = validator.validate(empresa);
		assertThat(constraintViolations.size(), is(1));
	}
	
	@Test
	public void validacionCreacionEmpresaCuandoRazonSocialEsVacio() {
		Empresa empresa = new Empresa();
		empresa.setRuc("12345678901");
		
		Set<ConstraintViolation<Empresa>> constraintViolations = validator.validate(empresa);
		assertThat(constraintViolations.size(), is(1));
	}
	
	@Test
	public void validacionCreacionEmpresaCuandoCelularNoTieneNueveDigitos() {
		Empresa empresa = new Empresa();
		empresa.setRuc("12345678901");
		empresa.setRazonSocial("Mi Empresa S.A.C.");
		empresa.setCelular("333");
		
		Set<ConstraintViolation<Empresa>> constraintViolations = validator.validate(empresa);
		assertThat(constraintViolations.size(), is(1));
	}
	
	@Test
	public void validacionMetodosGetterAndSetter() {
		
		Integer id = 10;
		String ruc = "20440158877";
		String razonSocial = "JB ENTERPRISE GROUP";
		String razonSocialComercial = "JB GROUP";
		String direccion = "Av. Aramburú 800";
		String celular = "999999911";
		
		Empresa empresa = new Empresa();
		empresa.setId(id);
		empresa.setRuc(ruc);
		empresa.setRazonSocial(razonSocial);
		empresa.setRazonSocialComercial(razonSocialComercial);
		empresa.setDireccion(direccion);
		empresa.setCelular(celular);
		
		Set<ConstraintViolation<Empresa>> constraintViolations = validator.validate(empresa);
		
		assertThat(constraintViolations.size(), is(0));
		Assert.assertEquals(id, empresa.getId());
		Assert.assertEquals(ruc, empresa.getRuc());
		Assert.assertEquals(razonSocial, empresa.getRazonSocial());
		Assert.assertEquals(razonSocialComercial, empresa.getRazonSocialComercial());
		Assert.assertEquals(direccion, empresa.getDireccion());
		Assert.assertEquals(celular, empresa.getCelular());
		
	}
	
	@Test
	public void validacionMetodoToString() {
		
		Integer id = 10;
		String ruc = "20440158877";
		String razonSocial = "JB ENTERPRISE GROUP";
		String razonSocialComercial = "JB GROUP";
		String direccion = "Av. Aramburú 800";
		String celular = "999999911";
		
		Empresa empresa = new Empresa();
		empresa.setId(id);
		empresa.setRuc(ruc);
		empresa.setRazonSocial(razonSocial);
		empresa.setRazonSocialComercial(razonSocialComercial);
		empresa.setDireccion(direccion);
		empresa.setCelular(celular);
		
		String valorEperado = "Empresa(id=10, ruc=20440158877, razonSocial=JB ENTERPRISE GROUP, razonSocialComercial=JB GROUP, direccion=Av. Aramburú 800, celular=999999911)";
		String valorObtenido = empresa.toString();
		
		Assert.assertEquals(valorEperado, valorObtenido);
		
	}
	
}
