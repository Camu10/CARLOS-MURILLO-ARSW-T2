package edu.eci.arsw.coronavirusAPI.connection;

import org.json.JSONArray;

/**
 * Interfaz para la conexion HTTP
 */
public interface HTTPConnectionLocationService {
    /**
     * Obtiene una lista con la ubicacion de cada pais
     * @param name Un string con el nombre del pais
     * @return Un JSONArray con  ubicacion de cada pais
     */
    JSONArray HTTPConnection(String name);
}
