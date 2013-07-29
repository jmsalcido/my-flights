package com.nearsoft.myflights.connectors;

import java.util.Date;
import java.util.List;

import com.nearsoft.myflights.model.Flight;

public interface FlightConnector {

    public List<Flight> getFlights(String fromAirport, String toAirport, Date date) throws Exception;

}
