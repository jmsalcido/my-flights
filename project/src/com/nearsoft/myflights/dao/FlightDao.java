package com.nearsoft.myflights.dao;

import java.util.Date;
import java.util.List;

import com.nearsoft.myflights.model.Flight;

public interface FlightDao {

    public List<Flight> getFlights(String fromAirport, String toAirport, Date date) throws Exception;

}
