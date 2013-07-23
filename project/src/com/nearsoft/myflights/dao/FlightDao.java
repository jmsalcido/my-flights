package com.nearsoft.myflights.dao;

import java.util.Date;
import java.util.List;

import com.nearsoft.myflights.model.Airport;
import com.nearsoft.myflights.model.Flight;

public interface FlightDao {
	
	public List<Flight> getFlights(Airport from, Airport to, Date date);

}
