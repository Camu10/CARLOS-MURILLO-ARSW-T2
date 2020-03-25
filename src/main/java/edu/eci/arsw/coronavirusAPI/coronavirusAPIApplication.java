package edu.eci.arsw.coronavirusAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la Aplicacion covid19
 */
@SpringBootApplication
public class coronavirusAPIApplication
{
    /**
     * Metodo main de la clase principal
     * @param args Argumentos que este requiere
     */
    public static void main( String[] args )
    {
        SpringApplication.run(coronavirusAPIApplication.class, args);
    }
}
