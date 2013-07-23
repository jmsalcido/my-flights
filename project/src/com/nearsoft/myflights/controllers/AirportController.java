package com.nearsoft.myflights.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nearsoft.myflights.model.Airport;
import com.nearsoft.myflights.service.AirportService;

@Controller
@RequestMapping("/airports")
public class AirportController {

	@Autowired
	AirportService airportService;
	
	public AirportController() {
		// TODO Auto-generated constructor stub
	}
//	public AirportController(AirportService airportService) {
//		this.airportService = airportService;
//	}
	
	@RequestMapping("index")
	@ResponseBody
	public Airport hi() {
		Airport airport = new Airport();
		airport.setId(1);
		return airport;
	}
	
	@RequestMapping(value = {"code"}, params = {"c"}, method = {RequestMethod.GET})
	@ResponseBody
	public Airport airportByCode(@RequestParam(value ="c")  String name) {
		return airportService.getAirportByCode(name);
	}
	
	@RequestMapping(value = {"word"}, params = {"w"}, method = {RequestMethod.GET})
	@ResponseBody
	public List<Airport> listAirportsByKeyword(@RequestParam(value ="w")  String word) {
		if(word.equalsIgnoreCase("")) {
			return null;
		} else {
			List<Airport> airports = airportService.getAirportsByKeyword(word);
			return airports;
		}
	}
	
}
