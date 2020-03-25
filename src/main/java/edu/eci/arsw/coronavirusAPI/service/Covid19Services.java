package edu.eci.arsw.coronavirusAPI.service;

import edu.eci.arsw.coronavirusAPI.model.Country;
import edu.eci.arsw.coronavirusAPI.model.Province;

import java.util.List;

/**
 *  Interfaz que maneja consulta los casos de covid19
 */
public interface Covid19Services {
    /**
     * Obtiene todos los casos de covid19 en todos los paises
     * @return Una lista con los paises infectados
     */
    List<Country> getAllCovid19();

    /**
     * Obtiene todos lo casos de covid19 en las provincias de un pais en especifico
     * @param name El nombre del pais que se quiere consultar
     * @return Una lista con las provincias que presentaron casos de covid19
     */
    List<Province> getCovid19ByCountry(String name);
}
