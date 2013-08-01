package com.nearsoft.myflights.service;

import java.util.List;
import java.util.Map;

import com.nearsoft.myflights.model.Airport;

public interface AirportService {

    public List<Airport> getAllAirports();

    public Map<String, Object> getAirportsByKeyword(String keyword);

}
