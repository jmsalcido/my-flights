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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.nearsoft.myflights.service.AirportService;

@Controller
@RequestMapping("search_airports")
public class AirportController {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    AirportService airportService;

    public AirportController() {
    }

    @RequestMapping(value = { "{word}" }, method = { RequestMethod.GET })
    @ResponseBody
    public ResponseEntity<String> listAirportsByKeyword(@PathVariable String word,
            HttpServletRequest request) {
        // TODO filter words.
        logger.info(String.format("Incoming request from %s :)",request.getLocalAddr()));
        logger.info(String.format("Word received: %s", word));
        Map<String, Object> responseMap = new HashMap<String, Object>();
        if (StringUtils.isEmpty(word)) {
            responseMap.put("error", "'word' can't be empty");
        } else {
            responseMap = airportService.getAirportsByKeyword(word);
        }

        // this could be on an util class for every controller that returns JSON.
        HttpHeaders responseHeaders = new HttpHeaders();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String nextElement = (String) headerNames.nextElement(); 
            responseHeaders.set(nextElement, request.getHeader(nextElement));
        }

        // populating the header required for CORS
        responseHeaders.set("Access-Control-Allow-Origin", "*");

        Gson gson = new Gson();        
        return new ResponseEntity<String>(gson.toJson(responseMap), responseHeaders, HttpStatus.OK);
    }

}
