package com.nearsoft.myflights.connectors;

import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nearsoft.myflights.connectors.util.FSFlightConnectorUtil;
import com.nearsoft.myflights.connectors.util.FlightConnectorUtil;
import com.nearsoft.myflights.model.Flight;
import com.nearsoft.myflights.model.fs.FSConnection;
import com.nearsoft.myflights.model.fs.FSFlight;

public class FSStubFlightConnector implements FlightConnector {

    private Gson gson;
    
    public FSStubFlightConnector() {
        gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
    }
    
    @Override
    public List<Flight> getFlights(String fromAirport, String toAirport,
            Date date) throws Exception {
        List<Flight> flightList = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource("flights-json.txt");
        InputStream inputStream = resource.getInputStream();
        String json = FlightConnectorUtil.getJsonFromInputStream(inputStream);
        inputStream.close();
        FSConnection fsConnection = FSFlightConnectorUtil.getFSConnectionFromJson(gson, json);
        
        Map<String, String> airlines = FSFlightConnectorUtil.convertFSAirlinesListToMap(fsConnection.getAppendix().getAirlines());
        for (FSFlight fsFlight : fsConnection.getFlights()) {
            Flight flight = FSFlightConnectorUtil.createFlightFromFSFlight(fsFlight, airlines, date);
            flightList.add(flight);
        }
        
        return flightList;
    }

}
