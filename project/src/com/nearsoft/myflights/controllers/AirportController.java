package com.nearsoft.myflights.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nearsoft.myflights.model.Airport;
import com.nearsoft.myflights.service.AirportService;

@Controller
@RequestMapping("/")
public class AirportController {

    @Autowired
    AirportService airportService;

    public AirportController() {
    }

    @RequestMapping("airportHi")
    @ResponseBody
    public Airport hi() {
        Airport airport = new Airport();
        airport.setId(1);
        return airport;
    }

    @RequestMapping(value = { "code" }, params = { "c" }, method = { RequestMethod.GET })
    @ResponseBody
    public Airport airportByCode(@RequestParam(value = "c") String name) {
        return airportService.getAirportByCode(name);
    }

    @RequestMapping(value = { "airports/{word}" }, method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, List<Airport>> listAirportsByKeyword(
            @PathVariable String word) {
        if (word.equalsIgnoreCase("")) {
            return null;
        }
        Map<String, List<Airport>> airports = airportService
                .getAirportsByKeyword(word);
        return airports;
    }

}
