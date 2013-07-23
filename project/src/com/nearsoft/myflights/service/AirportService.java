package com.nearsoft.myflights.service;

import java.util.List;

import com.nearsoft.myflights.model.Airport;

public interface AirportService {

	public List<Airport> getAllAirports();

	public Airport getAirportByName(String name);

	public Airport getAirportByCode(String name);

	public List<Airport> getAirportsByKeyword(String keyword);

}
