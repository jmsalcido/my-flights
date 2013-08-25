package com.nearsoft.myflights.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nearsoft.myflights.connectors.FlightConnector;
import com.nearsoft.myflights.model.Flight;

@Service
public class FSFlightService implements FlightService {

    @Autowired
    FlightConnector flightConnector;

    private static Log logger = LogFactory.getLog(FSFlightService.class);

    public void setFlightDao(FlightConnector flightConnector) {
        this.flightConnector = flightConnector;
    }

    @Override
    public Map<String, Object> getFlights(String fromAirportCode, String toAirportCode, String date)
            throws Exception {
        Map<String, Object> filtered_map = new LinkedHashMap<>();
        try {
            Date dateObject = createDateFromString(date);
            List<Flight> flightList = flightConnector.getFlights(fromAirportCode, toAirportCode, dateObject);
            List<Long> flightIdList = new LinkedList<>();
            for(Flight f : flightList) {
                flightIdList.add(f.getId());
            }
            Map<String, Object> search_map = new LinkedHashMap<>();
            search_map.put("flight_ids", flightIdList);
            filtered_map.put("search_flight", search_map);
            filtered_map.put("flights", flightList);
            return filtered_map;
        } catch (ParseException e) {
            throw new Exception("[DATE d] " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private Date createDateFromString(String dateString) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = formatter.parse(dateString);
        logger.info(String.format("%s produced: %s", dateString, date.toString()));
        return date;
    }

    public FlightConnector getFlightConnector() {
        return flightConnector;
    }

    public void setFlightConnector(FlightConnector flightConnector) {
        this.flightConnector = flightConnector;
    }

}
