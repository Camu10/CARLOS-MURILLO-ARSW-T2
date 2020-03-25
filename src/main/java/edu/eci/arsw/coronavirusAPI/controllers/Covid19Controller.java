package edu.eci.arsw.coronavirusAPI.controllers;


import edu.eci.arsw.coronavirusAPI.model.Country;
import edu.eci.arsw.coronavirusAPI.model.Province;
import edu.eci.arsw.coronavirusAPI.service.Covid19Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlodor API de la aplicacion de covid19
 */
@RestController
@RequestMapping("v1")
public class Covid19Controller {
    @Autowired
    public Covid19Services covid19Services;

    /**
     * Obtiene los casos de covid19 en todos los paises
     * @return Una entidad dependiendo del la respuesta del servicio
     */
    @GetMapping("/stats")
    public ResponseEntity<?> getAllCovid19(){
        List<Country> provinceListData = null;
        try{
            provinceListData = covid19Services.getAllCovid19();
            return new ResponseEntity<>(provinceListData, HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("ERROR 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene el numero de casos de covid19 por pais
     * @param name Un String con el nombre del pais
     * @return Una entidad dependiendo del la respuesta del servicio
     */
    @GetMapping("/stats/{name}")
    public ResponseEntity<?> getCovid19ByCountry(@PathVariable(name = "name") String name){
        List<Province> provinceListData = null;
        try{
            provinceListData = covid19Services.getCovid19ByCountry(name);
            return new ResponseEntity<>(provinceListData, HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("ERROR 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
