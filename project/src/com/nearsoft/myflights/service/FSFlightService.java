package com.nearsoft.myflights.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nearsoft.myflights.dao.FlightDao;
import com.nearsoft.myflights.model.Airport;
import com.nearsoft.myflights.model.Flight;

public class FSFlightService implements FlightService {

	private final static String SEPARATOR = "-";
	private final static int ARRAY_YEAR = 0;
	private final static int ARRAY_MONTH = 1;
	private final static int ARRAY_DAY = 2;
	
	
	FlightDao flightDao;
	
	@Override
	public List<Flight> getFlights(String from, String to, String date) {
		
		Airport airportFrom = createAirportFromCode(from);
		Airport airportTo = createAirportFromCode(to);
		Date dateObject = createDateFromString(date);
		
		return flightDao.getFlights(airportFrom, airportTo, dateObject);
	}
	
	private Airport createAirportFromCode(String code) {
		Map<String, String> map = new HashMap<String, String>();
		Airport airport = new Airport();
		map.put(Airport.FS, code);
		airport.setCodes(map);
		return airport;
	}
	
	private Date createDateFromString(String dateString) {
		String[] dateStringArray = dateString.split(SEPARATOR);
		int year = Integer.parseInt(dateStringArray[ARRAY_YEAR]);
		int month = Integer.parseInt(dateStringArray[ARRAY_MONTH]);
		int day = Integer.parseInt(dateStringArray[ARRAY_DAY]);
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1); // is -1 needed?
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

}
