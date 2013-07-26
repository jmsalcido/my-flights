package com.nearsoft.myflights.service;

import java.util.List;

import com.nearsoft.myflights.model.Flight;

public interface FlightService {

    public List<Flight> getFlights(String from, String to, String date);

}
