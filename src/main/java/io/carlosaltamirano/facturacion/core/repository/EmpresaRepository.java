package io.carlosaltamirano.facturacion.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.carlosaltamirano.facturacion.core.exception.FacturacionWebException;
import io.carlosaltamirano.facturacion.core.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	Optional<Empresa> findByRuc(String ruc) throws FacturacionWebException;
	
}
