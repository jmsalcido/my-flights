package com.nearsoft.myflights.controllers;

import java.util.List;

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

    @RequestMapping(value = "search", params = { "f", "t", "d" }, method = { RequestMethod.GET })
    @ResponseBody
    public List<Flight> flightsByDepartureAndArrival(
            @RequestParam(value = "f") String departureAirportCode,
            @RequestParam(value = "t") String arrivalAirportCode,
            @RequestParam(value = "d") String date) {
        return flightService.getFlights(departureAirportCode,
                arrivalAirportCode, date);
    }

    // TODO will change to become easier to develop in Ember.
    @RequestMapping(value = "searchq", params = { "f", "t", "d" }, method = { RequestMethod.GET })
    @ResponseBody
    public List<Flight> flightsByDepartureAndArrivals(
            String departureAirportCode, String[] arrivalAirportCodes,
            String date) {
        return null;
    }

}
