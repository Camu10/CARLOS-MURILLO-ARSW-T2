package edu.eci.arsw.coronavirusAPI.connection;

import org.json.JSONArray;

/**
 * Interfaz para la conexion HTTP
 */
public interface HTTPConnectionService {
    /**
     * Obtiene una lista con las provincias y los casos de cada una
     * @param name Un string con el nombre del pais
     * @return Un JSONArray con las provincias y su numero de casos
     */
    JSONArray HTTPConnection(String name);
}
