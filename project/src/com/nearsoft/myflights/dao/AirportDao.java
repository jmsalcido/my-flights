package com.nearsoft.myflights.dao;

import java.util.List;

import com.nearsoft.myflights.model.Airport;

public interface AirportDao {

    public List<Airport> getAllAirports();

    public List<Airport> getAirportsByKeyword(String keyword);

}
