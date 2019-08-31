package io.carlosaltamirano.facturacion.core.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.carlosaltamirano.facturacion.core.exception.FacturacionWebException;
import io.carlosaltamirano.facturacion.core.exception.RucEmpresaExisteException;
import io.carlosaltamirano.facturacion.core.model.Empresa;
import io.carlosaltamirano.facturacion.core.repository.EmpresaRepository;
import io.carlosaltamirano.facturacion.core.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{
	
	@Autowired
	EmpresaRepository empresaRepository;

	public Empresa crear(Empresa empresa) throws RucEmpresaExisteException, FacturacionWebException {
		
		if( existeRucEmpresa(empresa.getRuc()) ) {
			
			throw new RucEmpresaExisteException();
			
		}
		
		return empresaRepository.save(empresa);
	}
	
	public void eliminar(Empresa empresa) throws FacturacionWebException{
		
		empresaRepository.deleteById(empresa.getId());
		
	}
	
	public List<Empresa> listarTodos() {
		
		return empresaRepository.findAll();
		
	}
	
	private boolean existeRucEmpresa(String ruc) throws FacturacionWebException {
			
		try {
			
			empresaRepository.findByRuc(ruc).get();
			
			return true;
			
		} catch (NoSuchElementException e) {
			
			return false;
		}
		
		
	}
	

}
