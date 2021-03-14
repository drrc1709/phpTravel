package net.phptravels.automation_php_travels;

import io.cucumber.java.Scenario;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.phptravels.automation_php_travels.configuracion.BasePruebas;

public class AppTest extends BasePruebas {
	
	@Before()
	public void iniciarPrueba1() throws Exception {
		super.iniciarPruebasNavegador();
	}
	
	@Given("que entro a la página de php travels con las credenciales correctas")
	public void que_entro_a_la_página_de_php_travels_con_las_credenciales_correctas(){
		Login login = new Login();
		login.Ingresar();
	}

	@When("creo una nueva categoría de blog y agrego un nuevo post")
	public void creo_una_nueva_categoría_de_blog_y_agrego_un_nuevo_post() throws InterruptedException {
		Blog category = new Blog();
		category.AddCategory();
		//category.AddPost();
	}

	@Then("veo el post agregado")
	public void veo_el_post_agregado() {
	    
	}

	@After()
	public void finalizarPrueba1() throws Exception {
		super.cerrarNavegador();
	}
}
