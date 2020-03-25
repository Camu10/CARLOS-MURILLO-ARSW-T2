package edu.eci.arsw.coronavirusAPI.connection.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronavirusAPI.connection.HTTPConnectionLocationService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Clase que implemeta la interfaz HTTPConnectionLocationService para obtener las ubicacion de los paises
 */
@Service
public class HTTPConnectionLocationServiceImpl implements HTTPConnectionLocationService {
    private String headerHostValue;
    private String headerKeyValue;
    private String url;

    /**
     * Constructor de la clase HTTPConnectionLocationServiceImpl
     */
    public HTTPConnectionLocationServiceImpl(){
        url = "https://restcountries-v1.p.rapidapi.com/name/";
        headerHostValue = "restcountries-v1.p.rapidapi.com";
        headerKeyValue = "ecd92ce78fmsha41e1754a0b09e1p1b709bjsna4f3e9e91296";
    }

    /**
     * Obtiene una lista con la ubicacion de cada pais
     * @param name Un string con el nombre del pais
     * @return Un JSONArray con  ubicacion de cada pais
     */
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
        String stats = apiResponse.getBody();
        String objeto = new JSONArray(stats).get(0).toString();
        String location = new JSONObject(objeto).get("latlng").toString();
        JSONArray res = new JSONArray(location);
        return res;
    }
}
