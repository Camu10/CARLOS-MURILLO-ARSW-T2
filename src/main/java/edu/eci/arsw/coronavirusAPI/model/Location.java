package edu.eci.arsw.coronavirusAPI.model;

/**
 * Ubicacion en el mapa del mundo de un pais
 */
public class Location {
    private Double longitude;
    private Double latitude;

    /**
     * Obtiene la longitud
     * @return Un numero con la longitud
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Actucaliza la longitud
     * @param longitude Un numero con la nueva longitud
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Obtiene la latitud
     * @return Un numero con la latitud
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Actualiza la latitud
     * @param latitude Un numero con la nueva latitud
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}