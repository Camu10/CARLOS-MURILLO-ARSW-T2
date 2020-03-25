package edu.eci.arsw.coronavirusAPI.connection.impl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronavirusAPI.connection.HTTPConnectionService;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Clase que implementa la interfaz HTTPConnectionService para obtener las provincias que presentan casos de covid19
 */
@Service
public class HTTPConnectionServiceImpl implements HTTPConnectionService {
    private String headerHostValue;
    private String headerKeyValue;
    private String url;

    /**
     * Constructor de la clase HTTPConnectionServiceImpl
     */
    public HTTPConnectionServiceImpl() {
        url = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=";
        headerHostValue = "covid-19-coronavirus-statistics.p.rapidapi.com";
        headerKeyValue = "ecd92ce78fmsha41e1754a0b09e1p1b709bjsna4f3e9e91296";
    }

    /**
     * Obtiene una lista con las provincias y los casos de cada una
     * @param name Un string con el nombre del pais
     * @return Un JSONArray con las provincias y su numero de casos
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
}
