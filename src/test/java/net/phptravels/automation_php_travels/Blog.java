package net.phptravels.automation_php_travels;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import net.phptravels.automation_php_travels.configuracion.BasePruebas;
import net.phptravels.automation_php_travels.configuracion.Utilidades;

public class Blog extends BasePruebas {
	
	 JavascriptExecutor js;
	
	public void AddCategory() throws InterruptedException {
		seleccion = buscarYEsperarClick(XPATH, "//i[@class='w30 fa fa-columns']");
		click(seleccion);
		
		seleccion = buscarYEsperarClick(XPATH, "//a[contains(.,'Blog Categories')]");
		click(seleccion);
		
		seleccion = buscarYEsperarClick(XPATH, "//button[@type='button'][contains(.,'Add')]");
		click(seleccion);
		
		seleccion = buscarYEsperarClick(NOMBRE, "name");
		escribir(seleccion, Utilidades.dato("category.name"));
		
		seleccion = buscarYEsperarClick(NOMBRE, "status");
		new Select(seleccion).selectByVisibleText("Enable");
		
		seleccion = buscarYEsperarClick(XPATH, "(//input[@name='translated[vi][name]'])[1]");
		escribir(seleccion, Utilidades.dato("vietnam"));
		
		seleccion = buscarYEsperarClick(XPATH, "(//input[@name='translated[ru][name]'])[1]");
		escribir(seleccion, Utilidades.dato("ruso"));
		
		seleccion = buscarYEsperarClick(XPATH, "(//input[@name='translated[ar][name]'])[1]");
		escribir(seleccion, Utilidades.dato("arabe"));
		
		seleccion = buscarYEsperarClick(XPATH, "(//input[@name='translated[fa][name]'])[1]");
		escribir(seleccion, Utilidades.dato("persa"));
		
		seleccion = buscarYEsperarClick(XPATH, "(//input[@name='translated[tr][name]'])[1]");
		escribir(seleccion, Utilidades.dato("turco"));
		
		seleccion = buscarYEsperarClick(XPATH, "(//input[@name='translated[fr][name]'])[1]");
		escribir(seleccion, Utilidades.dato("frances"));
		
		seleccion = buscarYEsperarClick(XPATH, "(//input[@name='translated[es][name]'])[1]");
		escribir(seleccion, Utilidades.dato("español"));
		
		seleccion = buscarYEsperarClick(XPATH, "(//input[@name='translated[de][name]'])[1]");
		escribir(seleccion, Utilidades.dato("aleman"));
		
		utilidades.tomarImagenPantalla("Catefory");
		
		seleccion = buscar(XPATH, "//button[@type='submit'][contains(.,'Add')]");
		click(seleccion);
	}
	
	public void AddPost() throws InterruptedException {
		
		seleccion = buscarYEsperarClick(XPATH, "//i[@class='w30 fa fa-columns']");
		click(seleccion);
		
		seleccion = buscarYEsperarClick(XPATH, "//a[contains(.,'Post')]");
		click(seleccion);
		
		seleccion = buscarYEsperarClick(XPATH, "//button[@type='submit' and @class='btn btn-success']");
		click(seleccion);
		
		seleccion = buscarYEsperarClick(NOMBRE, "title");
		escribir(seleccion, Utilidades.dato("category.name"));
		
		seleccion = buscarYEsperarClick(NOMBRE, "slug");
		escribir(seleccion, Utilidades.dato("category.name"));
		
		seleccion = buscarYEsperarClick(NOMBRE, "category");
		new Select(seleccion).selectByVisibleText(Utilidades.dato("category.name"));
		
		seleccion = buscarYEsperarClick(NOMBRE, "relatedposts[]");
		new Select(seleccion).selectByVisibleText("Graphic Tour Sri Lanka");
	
		
		seleccion = buscar(NOMBRE, "keywords");
		escribir(seleccion, Utilidades.dato("category.name"));
		
		seleccion = buscar(NOMBRE, "metadesc");
		escribir(seleccion, Utilidades.dato("category.name"));
		
		seleccion = buscar(XPATH, "//button[@class='btn btn-primary'][contains(.,'Submit')]");
		click(seleccion);
		
		
	}

}
