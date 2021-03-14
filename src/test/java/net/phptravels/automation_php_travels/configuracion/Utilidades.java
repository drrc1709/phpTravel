/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.phptravels.automation_php_travels.configuracion;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



/**
 *
 * @author drrodriguez
 */
public class Utilidades {

    private static final Logger LOGGER = Logger.getLogger(Utilidades.class.getName());
    private static WebDriver driver;
    private static Utilidades instance;
    private static String carpetaImagenes;
    private int contadorImagenes = 0;
    private static String formatoImagen;
    private static String tomaImagenes;

    static {
        formatoImagen = ".png";
    }

    private Utilidades() {
    	tomaImagenes = Propiedades.getInstancia().getPropiedad("tomarImagenes");
    }

    public static Utilidades getUtilidades() {
        if (instance == null) {
            instance = new Utilidades();
            if (tomaImagenes.equals("si")) {
                try {
                    carpetaImagenes = "target/Imagenes/Imagenes_" + Utilidades.getDateTime();
                    File f = new File(carpetaImagenes);
                    f.mkdirs();
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error crear carpeta de im\u00e1genes");
                    LOGGER.log(Level.SEVERE, e.toString());
                }
            }
        }
        return instance;
    }

    public String tomarImagenPantalla(String nombre) {
        if (tomaImagenes.equals("si")) {
            try {
                driver.switchTo().alert().dismiss();
                return "No se genera imagen por presencia de Alert";
            } catch (Exception e) {
                try {
                    File directory = new File(".");
                    ++this.contadorImagenes;
                    String NewFileNamePath = String.valueOf(directory.getCanonicalPath()) + "/" + carpetaImagenes + "/" + this.contadorImagenes + "_" + nombre + formatoImagen;
                    File scrFile = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile((File) scrFile, (File) new File(NewFileNamePath));
                   // escenarioactual.attach(Files.readAllBytes(scrFile.toPath()), "image/png");
                    return NewFileNamePath;
                } catch (IOException i) {
                    LOGGER.log(Level.SEVERE, i.toString());
                }
            }
        }
        return "";
    }

    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void setDriver(WebDriver driver) {
        Utilidades.driver = driver;
    }

    public String fecha_hora_actual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String numero_fecha_hora_actual() {
        Date date = new Date();
        return Long.toString(date.getTime());
    }


    public static String dato(String clave) {

        String dato = Propiedades.getInstancia().getPropiedad(clave);
        if (dato == null) {
            LOGGER.log(Level.WARNING, "Dato: {0} - No encontrado", clave);
            return "";
        }
        return dato;
    }
}
