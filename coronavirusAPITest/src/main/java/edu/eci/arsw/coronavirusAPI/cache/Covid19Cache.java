package edu.eci.arsw.coronavirusAPI.cache;

import edu.eci.arsw.coronavirusAPI.model.Province;

import java.time.LocalDateTime;
import java.util.List;

public interface Covid19Cache {
    List<Province> getCovid19ByName(String name);
    void saveCovid19(String name, List<Province> airport);
    LocalDateTime getTime(String name);
}
