package com.nearsoft.myflights.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nearsoft.myflights.service.AirportService;

@Controller
@RequestMapping("/")
public class AirportController {

    @Autowired
    AirportService airportService;

    public AirportController() {
    }

    @RequestMapping(value = { "airports/{word}" }, method = { RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> listAirportsByKeyword(
            @PathVariable String word) {
        Map<String, Object> map = new HashMap<String, Object>(); 
        if(StringUtils.isEmpty(word)) {
            map.put("error", "'word' can't be empty");
        } else {
            map.put("airports", airportService.getAirportsByKeyword(word));
        }
        return map;
    }

}
