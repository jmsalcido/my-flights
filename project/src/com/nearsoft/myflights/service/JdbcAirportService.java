package com.nearsoft.myflights.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nearsoft.myflights.dao.AirportDao;
import com.nearsoft.myflights.model.Airport;

@Service
public class JdbcAirportService implements AirportService {

	@Autowired
	private AirportDao airportDao;
	
	public void setAirportDao(AirportDao airportDao) {
		this.airportDao = airportDao;
	}
	
	@Override
	public List<Airport> getAllAirports() {
		return airportDao.getAllAirports();
	}

	@Override
	public Airport getAirportByName(String name) {
		return airportDao.getAirportByName(name);
	}

	@Override
	public Airport getAirportByCode(String name) {
		return airportDao.getAirportByCode(name);
	}

	@Override
	public List<Airport> getAirportsByKeyword(String keyword) {
		// Just return the airports by keyword.
		return airportDao.getAirportsByKeyword(keyword);
	}

}
