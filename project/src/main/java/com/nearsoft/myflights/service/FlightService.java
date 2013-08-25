package com.nearsoft.myflights.service;

import java.util.Map;

public interface FlightService {

    public Map<String, Object> getFlights(String from, String to, String date) throws Exception;

}
