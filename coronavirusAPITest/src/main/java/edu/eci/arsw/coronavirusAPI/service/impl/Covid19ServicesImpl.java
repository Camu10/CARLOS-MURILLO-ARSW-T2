package edu.eci.arsw.coronavirusAPI.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronavirusAPI.cache.Covid19Cache;
import edu.eci.arsw.coronavirusAPI.model.Country;
import edu.eci.arsw.coronavirusAPI.model.Province;
import edu.eci.arsw.coronavirusAPI.service.Covid19Services;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class Covid19ServicesImpl implements Covid19Services {

    private String headerHostValue;
    private String headerKeyValue;
    private String url;
    private Gson gson;

    @Autowired
    private Covid19Cache covid19Cache;

    public Covid19ServicesImpl() {
        url = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=";
        headerHostValue = "covid-19-coronavirus-statistics.p.rapidapi.com";
        headerKeyValue = "ecd92ce78fmsha41e1754a0b09e1p1b709bjsna4f3e9e91296";
        gson = new GsonBuilder().create();
    }

    public JSONArray HTTPConnection(String name){
        String encodedUrlName = null;
        try {
            encodedUrlName = URLEncoder.encode(name,java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder apiUrl = new StringBuilder();
        apiUrl.append(url+encodedUrlName);

        HttpResponse<JsonNode> apiResponse = null;
        try{
            apiResponse = Unirest.get(apiUrl.toString())
                    .header("x-rapidapi-host",headerHostValue)
                    .header("x-rapidapi-key",headerKeyValue)
                    .asJson();
        }catch (UnirestException e){
            e.printStackTrace();
        }
        JSONArray stats = apiResponse.getBody().getObject().getJSONObject("data").getJSONArray("covid19Stats");
        return stats;
    }

    @Override
    public List<Country> getAllCovid19() {
        JSONArray stats = HTTPConnection("");
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

    @Override
    public List<Province> getCovid19ByCountry(String name) {
        JSONArray stats = HTTPConnection(name);
        //System.out.println(stats);
        /*for(int i = 0; i<stats.length();i++) {
            JSONObject superJson = (JSONObject) stats.get(i);
            superJson.put("Probando", new JSONObject("{\"latitud\":\""+i+"\",\"longitud\":\""+(i+1)+"\"}"));
        }*/
        //System.out.println(stats);
        List<Province> res = null;
        res = gson.fromJson(stats.toString(),new TypeToken<List<Province>>(){}.getType());
        //System.out.println(res);
        return res;
    }
}
