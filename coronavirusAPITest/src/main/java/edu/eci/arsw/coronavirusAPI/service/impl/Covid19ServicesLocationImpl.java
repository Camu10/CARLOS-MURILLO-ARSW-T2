package edu.eci.arsw.coronavirusAPI.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronavirusAPI.cache.Covid19Cache;
import edu.eci.arsw.coronavirusAPI.service.Covid19Services;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class Covid19ServicesLocationImpl {

    private String headerHostValue;
    private String headerKeyValue;
    private String url;
    private Gson gson;

    @Autowired
    private Covid19Cache covid19Cache;

    public Covid19ServicesLocationImpl(){
        url = "https://restcountries-v1.p.rapidapi.com/name/";
        headerHostValue = "restcountries-v1.p.rapidapi.com";
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

        HttpResponse<String> apiResponse = null;
        try{
            apiResponse = Unirest.get(apiUrl.toString())
                    .header("x-rapidapi-host",headerHostValue)
                    .header("x-rapidapi-key",headerKeyValue)
                    .asString();
        }catch (UnirestException e){
            e.printStackTrace();
        }
        String  stats = apiResponse.getBody();
        String a = new JSONArray(stats).get(0).toString();
        String b = new JSONObject(a).get("latlng").toString();
        JSONArray c = new JSONArray(b);
        return c;
    }
}
