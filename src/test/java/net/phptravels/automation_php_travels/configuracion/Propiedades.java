/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.phptravels.automation_php_travels.configuracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drrodriguez
 */
public class Propiedades {
    private static final Logger LOGGER = Logger.getLogger(Propiedades.class.getName());
    private static Propiedades instancia = null;
    private Properties p = new Properties();
    private Properties datosPrueba;

    private Propiedades() {
        File directory;
        try {
            directory = new File(".");
            this.p.load(new FileInputStream(new File(String.valueOf(directory.getCanonicalPath()) + "/src/test/resources/pruebas.properties")));
        }
        catch (IOException e) {
            LOGGER.log(Level.SEVERE,e.getStackTrace().toString());
        }
    }

    public static Propiedades getInstancia() {
        if (instancia == null) {
            instancia = new Propiedades();
        }
        return instancia;
    }

    public String getPropiedad(String clave) {
        return this.p.getProperty(clave);
    }
}
