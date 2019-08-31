package io.carlosaltamirano.facturacion.core.exception;

@SuppressWarnings("serial")
public class RucEmpresaExisteException extends FacturacionWebException {
	
	public static String MENSAJE_DEFAULT = "RUC de la empresa ya se encuentra registrado.";
	
	public RucEmpresaExisteException() {
		super(MENSAJE_DEFAULT);
	}

//	public RucEmpresaExisteException(String mensaje) {
//		super(mensaje);
//	}

}
