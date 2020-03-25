package edu.eci.arsw.coronavirusAPI.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.eci.arsw.coronavirusAPI.cache.Covid19Cache;
import edu.eci.arsw.coronavirusAPI.connection.HTTPConnectionLocationService;
import edu.eci.arsw.coronavirusAPI.connection.HTTPConnectionService;
import edu.eci.arsw.coronavirusAPI.model.Country;
import edu.eci.arsw.coronavirusAPI.model.Province;
import edu.eci.arsw.coronavirusAPI.service.Covid19Services;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase que implementa la interfaz Covid19Services para consultar los casos de covid19
 */
@Service
public class Covid19ServicesImpl implements Covid19Services {
    private Gson gson;

    @Autowired
    private Covid19Cache covid19Cache;

    @Autowired
    private HTTPConnectionService httpConnectionService;

    @Autowired
    private HTTPConnectionLocationService httpConnectionLocationService;

    /**
     * Constructor de la clase Covid19ServicesImpl
     */
    public Covid19ServicesImpl(){
        gson = new GsonBuilder().create();
    }

    /**
     * Obtiene todos los casos de covid19 en todos los paises
     * @return Una lista con los paises infectados
     */
    @Override
    public List<Country> getAllCovid19() {
        JSONArray stats = httpConnectionService.HTTPConnection("");
        List<Province> res = null;
        res = gson.fromJson(stats.toString(),new TypeToken<List<Province>>(){}.getType());
        HashMap<String, ArrayList<Integer>> countries = new HashMap<String, ArrayList<Integer>>();
        for(Province pr:res){
            if(!countries.containsKey(pr.getCountry())){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(pr.getDeaths());temp.add(pr.getConfirmed());temp.add(pr.getRecovered());
                countries.put(pr.getCountry(),temp);
            }else{
                ArrayList<Integer> aux = new ArrayList<Integer>();
                ArrayList<Integer> temp = countries.get(pr.getCountry());
                countries.remove(pr.getCountry());
                aux.add(temp.get(0)+pr.getDeaths());aux.add(temp.get(1)+pr.getConfirmed());aux.add(temp.get(2)+pr.getRecovered());
                countries.put(pr.getCountry(),aux);
            }
        }
        List<Country> res1 = new ArrayList<Country>();
        for (HashMap.Entry<String, ArrayList<Integer>> entry : countries.entrySet()) {
            res1.add(new Country(entry.getKey(),entry.getValue().get(0),entry.getValue().get(1),entry.getValue().get(2)));
        }

        return res1;
    }

    /**
     * Obtiene todos lo casos de covid19 en las provincias de un pais en especifico
     * @param name El nombre del pais que se quiere consultar
     * @return Una lista con las provincias que presentaron casos de covid19
     */
    @Override
    public List<Province> getCovid19ByCountry(String name) {
        JSONArray stats = httpConnectionService.HTTPConnection(name);
        JSONArray coordenadas = httpConnectionLocationService.HTTPConnection(name);
        for(int i = 0; i<stats.length();i++) {
            JSONObject temp = (JSONObject) stats.get(i);
            temp.put("location", new JSONObject("{\"latitude\":\""+coordenadas.get(0)+"\",\"longitude\":\""+coordenadas.get(1)+"\"}"));
        }
        List<Province> res = null;
        if(covid19Cache.getCovid19ByName(name) == null){
            System.out.println("no esta en cache");
            res = gson.fromJson(stats.toString(),new TypeToken<List<Province>>(){}.getType());
            covid19Cache.saveCovid19(name,res);
        }else{
            LocalDateTime time = covid19Cache.getTime(name);
            if(LocalDateTime.now().isAfter(time.plusMinutes(5))){
                System.out.println("cache 5 minutos");
                res = gson.fromJson(stats.toString(),new TypeToken<List<Province>>(){}.getType());
                covid19Cache.saveCovid19(name,res);
            }else{
                System.out.println("esta en cache");
                res = covid19Cache.getCovid19ByName(name);
            }
        }
        return res;
    }
}
