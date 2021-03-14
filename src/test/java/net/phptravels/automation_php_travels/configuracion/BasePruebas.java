/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.phptravels.automation_php_travels.configuracion;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 *
 * @author drrodriguez
 */
public class BasePruebas {

	protected static final Logger LOGGER = Logger.getLogger(BasePruebas.class.getName());
	protected static Utilidades utilidades;
	protected static int numeroError;
	protected static Scenario escenarioactual;
	protected static WebDriver driver;
	protected WebElement seleccion;
	protected static StringWriter escritorLogWS;
	protected static PrintStream capturaLogWS;
	protected RequestSpecification request;
	protected Response response;

	//private static String urlBasePruebas;

	public static final String XPATH = "xpath";
	public static final String CSS = "css";
	public static final String NOMBRE = "nombre";
	public static final String ID = "id";
	public static final String TEXTOLINK = "textolink";
	public static final String ZK = "zk";

	public static void iniciarPruebasNavegador() throws Exception {
		driver = getDriver();
		utilidades = Utilidades.getUtilidades();
		Utilidades.setDriver(driver);
		numeroError = 1;
		//urlBasePruebas = Propiedades.getInstancia().getPropiedad("urlBasePruebas");
	}

	public static WebDriver getDriver() {
		try {
			if (Propiedades.getInstancia().getPropiedad("navegador").equals("chrome")) {
				File directory = new File(".");
				File file = new File(String.valueOf(directory.getCanonicalPath()) + System.getProperty("file.separator")
						+ "lib" + System.getProperty("file.separator") + "chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				ChromeOptions options = new ChromeOptions();
				options.addArguments(new String[] { "--start-maximized" });
				driver = new ChromeDriver(options);
			} else if (Propiedades.getInstancia().getPropiedad("navegador").equals("firefox")) {
				File directory = new File(".");
				File file = new File(String.valueOf(directory.getCanonicalPath()) + System.getProperty("file.separator")
						+ "lib" + System.getProperty("file.separator") + "geckodriver.exe");
				System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
				driver = new FirefoxDriver();
			}
			driver.get(Utilidades.dato("urlBasePruebas"));
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error al obtener driver");
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		return driver;
	}

	protected WebElement buscar(String buscarPor, String ubicacion) {
		try {

			By by = null;

			switch (buscarPor) {
			case XPATH:
				by = By.xpath(ubicacion);
				break;

			case TEXTOLINK:
				by = By.linkText(ubicacion);
				break;

			case CSS:
				by = By.cssSelector(ubicacion);
				break;

			case NOMBRE:
				by = By.name(ubicacion);
				break;

			case ID:
				by = By.id(ubicacion);
				break;

			default:
				break;
			}

			return driver.findElement(by);

		} catch (Exception e) {
			fail(e.toString());
			return null;
		}
	}
	
	public void escribir (WebElement element, String texto) {
		try {
			element.click();
			element.clear();
			element.sendKeys(new CharSequence[] { texto });
			
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	public void click(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	protected WebElement buscarYEsperarClick(String buscarPor, String ubicacion) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			By by = null;

			switch (buscarPor) {
			case XPATH:
				by = By.xpath(ubicacion);
				break;

			case TEXTOLINK:
				by = By.linkText(ubicacion);
				break;

			case CSS:
				by = By.cssSelector(ubicacion);
				break;

			case NOMBRE:
				by = By.name(ubicacion);
				break;

			case ID:
				by = By.id(ubicacion);
				break;

			default:
				break;
			}
			return wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			fail("Excepci\u00f3n al intentar esperar y dar click a un elemento..." + e.toString());
			e.printStackTrace();
			return null;
		}
	}

	public static void assertTrue(boolean condition) {
		Assertions.assertTrue((boolean) condition);
	}

	public static void assertTrue(boolean condition, String message) {
		Assertions.assertTrue((boolean) condition, (String) message);
	}

	public static void assertFalse(boolean condition) {
		Assertions.assertFalse((boolean) condition);
	}

	public static void assertFalse(boolean condition, String message) {
		Assertions.assertFalse((boolean) condition, (String) message);
	}

	public static void assertEquals(boolean actual, boolean expected) {
		Assertions.assertEquals((Object) actual, (Object) expected);
	}

	public static void assertEquals(Object actual, Object expected) {
		Assertions.assertEquals((Object) actual, (Object) expected);
	}

	public static void assertEquals(Object[] actual, Object[] expected) {
		Assertions.assertEquals((Object) actual, (Object) expected);
	}

	public static void assertEquals(Object actual, Object expected, String message) {
		Assertions.assertEquals((Object) actual, (Object) expected, (String) message);
	}

	public static void fail(String message) {
		try {
			LOGGER.log(Level.WARNING, message);
			Assertions.fail((String) message);
		} catch (Throwable e) {
			String m = "Error_" + numeroError + "_" + Thread.currentThread().getStackTrace()[1].getMethodName();
			utilidades.tomarImagenPantalla(m);
			LOGGER.log(Level.WARNING, m);
			++numeroError;
		}
	}

	public static void cerrarNavegador() {
		driver.quit();
	}
}