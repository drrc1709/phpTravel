package net.phptravels.automation_php_travels;

import net.phptravels.automation_php_travels.configuracion.BasePruebas;
import net.phptravels.automation_php_travels.configuracion.Utilidades;

public class Login extends BasePruebas {
	
	public void Ingresar() {
		
		seleccion = buscar(NOMBRE, "email");
		escribir(seleccion, Utilidades.dato("email"));
		
		seleccion = buscar(NOMBRE, "password");
		escribir(seleccion, Utilidades.dato("password"));
		
		seleccion = buscar(XPATH, "//button[contains(.,'Login')]");
		click(seleccion);
		
		utilidades.tomarImagenPantalla("Login");
	}

}
