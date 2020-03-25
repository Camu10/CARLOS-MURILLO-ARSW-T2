package edu.eci.arsw.coronavirusAPI.model;

/**
 * Provincia en la cual se registraron casos de covid19
 */
public class Province {
    private String city;
    private String province;
    private String country;
    private String lastUpdate;
    private String keyId;
    private int confirmed;
    private int deaths;
    private int recovered;
    private Location location;

    /**
     * Contructor de la clase Province
     */
    public Province(){}

    /**
     *  Obtine la cuidad en la que se encuentra la provincia
     * @return Un String con el nombre de la ciudad
     */
    public String getCity() {
        return city;
    }

    /**
     * Actualiza la cuidad de la provincia
     * @param city Un String con el nombre de la nueva ciudad
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Obtiene el nombre de la provincia
     * @return Un String con el nombre de la provincia
     */
    public String getProvince() {
        return province;
    }

    /**
     * Actualiza el nombre de la provincia
     * @param province Un String con el nombre de la nueva provincia
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Obtiene el nombre del pais de la provincia
     * @return Un String con el nombre del pais de la provincia
     */
    public String getCountry() {
        return country;
    }

    /**
     * Actualiza el pais de la provincia
     * @param country Un String con el nombre del pais de la provincia
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Obtiene la ultima fecha de actualizacion de los casos de covid19
     * @return Un String con la ultima fecha de actualizacion de los casos de covid19
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Actualiza la ultima fecha de actualizacion de los casos de covid19
     * @param lastUpdate Un String con la nueva ultima fecha de actualizacion de los casos de covid19
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Obtiene el id de la provincia
     * @return Un String con el id
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * Actualiza la id de la provincia
     * @param keyId Un string con la nueva id
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * Obtiene los casos confirmados con covid19
     * @return Un entero con el numero de casos confirmados con covid19
     */
    public int getConfirmed() {
        return confirmed;
    }

    /**
     *  Actualiza el numero de casos confirmados con covid19
     * @param confirmed Un entero con el nuevo numero de casos confirmados con covid19
     */
    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    /**
     * Obtiene el numero de casos de muertes por causa del covid19
     * @return Un entero con el numero de casos de muertes por causa del covid19
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Actualiza el numero de casos de muertes por causa del covid19
     * @param deaths Un entero con el nuevo numero de casos de muertes por causa del covid19
     */
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    /**
     * Obtiene el numero de casos recuperados del covid19
     * @return Un entero con el numero de casos recuperados del covid19
     */
    public int getRecovered() {
        return recovered;
    }

    /**
     * Actualiza el numero de casos recuperados del covid19
     * @param recovered Un entero con el nuevo numero de casos recuperados del covid19
     */
    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    /**
     * Obtiene la ubicacion en el planeta
     * @return Una Location con la ubicacion en el planeta
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Actualiza la ubicacion en el planeta
     * @param location Una Location con la nueva ubicacion en el planeta
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}