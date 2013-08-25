package com.nearsoft.myflights.controllers;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.nearsoft.myflights.service.FlightService;

@Controller
@RequestMapping("search_flights")
public class FlightController {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    FlightService flightService;
    
    @RequestMapping(value = "search", method = { RequestMethod.GET })
    @ResponseBody
    public ResponseEntity<String> flightsByDepartureAndArrival(
            @RequestParam(value = "f") String departureAirportCode,
            @RequestParam(value = "t") String arrivalAirportCode,
            @RequestParam(value = "d") String date, HttpServletRequest request) {
        logger.info(String.format("Incoming request from %s :)",
                request.getLocalAddr()));
        logger.info(String.format("Airport Code from received: %s",
                departureAirportCode));
        logger.info(String.format("Airport Code to received: %s",
                arrivalAirportCode));
        logger.info(String.format("Date received: %s", date));

        Map<String, Object> map = new HashMap<String, Object>();

        try {
            map = flightService.getFlights(departureAirportCode,
                    arrivalAirportCode, date);
        } catch (Exception e) {
            String message = e.getMessage();
            map.put("error", message);
        }
        
        // this could be on an util class for every controller that returns
        // JSON.
        HttpHeaders responseHeaders = new HttpHeaders();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String nextElement = (String) headerNames.nextElement();
            responseHeaders.set(nextElement, request.getHeader(nextElement));
        }

        // populating the header required for CORS
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        
        Gson gson = new Gson();
        return new ResponseEntity<String>(gson.toJson(map), responseHeaders,
                HttpStatus.OK);
    }
    
}
