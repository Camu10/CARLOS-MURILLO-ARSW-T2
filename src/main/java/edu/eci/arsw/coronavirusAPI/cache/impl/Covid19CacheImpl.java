package edu.eci.arsw.coronavirusAPI.cache.impl;

import edu.eci.arsw.coronavirusAPI.cache.Covid19Cache;
import edu.eci.arsw.coronavirusAPI.model.Province;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class Covid19CacheImpl implements Covid19Cache {

    private ConcurrentHashMap<String, List<Province>> province;
    private ConcurrentHashMap<String, LocalDateTime> time;

    public Covid19CacheImpl(){
        province = new ConcurrentHashMap<>();
        time = new ConcurrentHashMap<>();
    }

    @Override
    public List<Province> getCovid19ByName(String name) {
        return province.get(name);

    }

    @Override
    public void saveCovid19(String name, List<Province> airport) {
        province.put(name,airport);
        time.put(name, LocalDateTime.now());
    }


    @Override
    public LocalDateTime getTime(String name){
        return time.get(name);
    }
}
