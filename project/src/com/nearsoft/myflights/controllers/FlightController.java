package com.nearsoft.myflights.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nearsoft.myflights.model.Flight;
import com.nearsoft.myflights.service.FlightService;

@Controller
@RequestMapping("flights")
public class FlightController {
	
	
	//FlightService flightService;
	
	public Flight flightByFsFlightId(int fsFlightId) {
		return null;
	}
	
	@RequestMapping(value = "search", params = {"f", "t", "d"}, method = {RequestMethod.GET})
	@ResponseBody
	public List<Flight> flightsByDepartureAndArrival(String departureAirportCode, String arrivalAirportCode, String date) {
		//return flightService.getFlights(departureAirportCode, arrivalAirportCode, date);
		return null;
	}
	
	@RequestMapping(value = "searchq", params = {"f", "t", "d"}, method = {RequestMethod.GET})
	@ResponseBody
	public List<Flight> flightsByDepartureAndArrivals(String departureAirportCode, String[] arrivalAirportCodes, String date) {
		return null;
	}
	
}
