package edu.eci.arsw.coronavirusAPI.model;

/**
 * Pais en el que se encuentra las provincias con casos de covid19
 */
public class Country {
    private String name;
    private int confirmed;
    private int deaths;
    private int recovered;

    /**
     * Constructor de la clase Country
     * @param name Un String con el nombre del pais
     * @param deaths Un numero con la cantidad de muertes causadas por el covid19
     * @param confirmed Un numero con la cantidad de casos confirmados de contagio por el covid19
     * @param recovered Un numero con la cartidad de casos que se han recuperado del covid19
     */
    public Country(String name,int deaths,int confirmed,int recovered){
        this.name=name;
        this.confirmed=confirmed;
        this.deaths=deaths;
        this.recovered=recovered;
    }

    /**
     * Obtiene el nombre del pais
     * @return Un String con el nombre del pais
     */
    public String getName() {
        return name;
    }

    /**
     * Actualiza el nombre del pais
     * @param name Un String con el nuevo nombre del pais
     */
    public void setName(String name) {
        this.name = name;
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
}
