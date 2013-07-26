package com.nearsoft.myflights.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nearsoft.myflights.model.Flight;
import com.nearsoft.myflights.service.FlightService;

@Controller
@RequestMapping("flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    public Flight flightByFsFlightId(int fsFlightId) {
        return null;
    }

    // TODO this method will change to be ember-data RESTAdapter friendly
    @RequestMapping(value = "search", params = { "f", "t", "d" }, method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> flightsByDepartureAndArrival(
            @RequestParam(value = "f") String departureAirportCode,
            @RequestParam(value = "t") String arrivalAirportCode,
            @RequestParam(value = "d") String date) {
        Map<String, Object> map = new HashMap<String, Object>();
        Object message = null;
        try {
            message = flightService.getFlights(departureAirportCode, arrivalAirportCode, date);
            map.put("flights", message);
        } catch (Exception e) {
            message = e.getMessage();
            map.put("error", message);
        }
        return map;
    }

}
