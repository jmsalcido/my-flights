package com.nearsoft.myflights.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.nearsoft.myflights.model.Airport;
import com.nearsoft.myflights.model.Flight;

public class FSFlightDao implements FlightDao {

	// =================================
	//    FLIGHTSTATS API ID & KEY
	// =================================
	private final static String appId = "fddf4ecf";
	private final static String appKey = "d2a01014237e20af21d8c7a146e5bbf2";
	
	private Gson gson;
	
	public FSFlightDao() {
		gson = new Gson();
	}
	
	@Override
	public List<Flight> getFlights(Airport from, Airport to, Date date) {
		List<Flight> flightList = null;
		Map<String, String> codesFrom = from.getCodes();
		
		// Get only the FS aiport codes.
		String codeFrom = codesFrom.get(Airport.FS);
		Map<String, String> codesTo = to.getCodes();
		String codeTo = codesTo.get(Airport.FS);
		
		
		
		
		return flightList;
	}
	
}