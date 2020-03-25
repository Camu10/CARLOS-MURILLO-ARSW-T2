package edu.eci.arsw.coronavirusAPI.service;

import edu.eci.arsw.coronavirusAPI.model.Country;
import edu.eci.arsw.coronavirusAPI.model.Province;

import java.util.List;

public interface Covid19Services {
    List<Country> getAllCovid19();
    List<Province> getCovid19ByCountry(String name);
}
