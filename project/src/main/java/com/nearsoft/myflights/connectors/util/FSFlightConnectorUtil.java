package com.nearsoft.myflights.connectors.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.nearsoft.myflights.model.Flight;
import com.nearsoft.myflights.model.FlightDetail;
import com.nearsoft.myflights.model.fs.FSAirline;
import com.nearsoft.myflights.model.fs.FSConnection;
import com.nearsoft.myflights.model.fs.FSFlight;
import com.nearsoft.myflights.model.fs.FSFlightLeg;

public class FSFlightConnectorUtil {

    /**
     * create a Flight object from a FSFlight object parsed from JSON
     * @param fsFlight
     * @param fsAirlines
     * @param date
     * @return
     */
    public static Flight createFlightFromFSFlight(FSFlight fsFlight,
            Map<String, String> fsAirlines, Date date) {
        
        Flight flight = new Flight();
        flight.setDate(date);
        flight.setDepartureAirport(fsFlight.getDepartureAirportFsCode());
        flight.setArrivalAirport(fsFlight.getArrivalAirportFsCode());
        flight.setTravelTime(fsFlight.getFlightDurationMinutes());
        flight.setFlightType(fsFlight.getFlightType()
                .equalsIgnoreCase("DIRECT") ? Flight.NON_STOP
                : Flight.CONNECTION);
        
        List<FSFlightLeg> fsFlightDetails = fsFlight.getFlightLegs();
        flight.setFlightDetails(new ArrayList<FlightDetail>());
        
        StringBuilder sb = new StringBuilder();
        for (FSFlightLeg fsFlightLeg : fsFlightDetails) {
            FlightDetail flightDetail = createFlightDetailFromFSFlightLeg(fsFlightLeg, fsAirlines);
            flight.getFlightDetails().add(flightDetail);
            sb.append(flightDetail.getFlightNumber());
        }
        
        // Id is set at last because is the concatenation of all the flight numbers
        flight.setId(Long.parseLong(sb.toString()));
        return flight;
    }
    
    /**
     * return a FSConnection object from a JSON String
     * @param gson
     * @param json
     * @return
     */
    public static FSConnection getFSConnectionFromJson(Gson gson, String json) {
        return gson.fromJson(json, FSConnection.class);
    }

    /**
     * create a FlightDetail object from a FSFlightLeg object parsed from JSON
     * @param flightLeg
     * @param fsAirlines
     * @return
     */
    public static FlightDetail createFlightDetailFromFSFlightLeg(
            FSFlightLeg flightLeg, Map<String, String> fsAirlines) {
        
        FlightDetail flightDetail = new FlightDetail();
        flightDetail.setArrivalAirport(flightLeg.getArrivalAirportFsCode());
        flightDetail.setDepartureAirport(flightLeg.getDepartureAirportFsCode());
        String departureTime = FlightConnectorUtil.removeCharacters(flightLeg.getDepartureTime());
        String arrivalTime = FlightConnectorUtil.removeCharacters(flightLeg.getArrivalTime());
        flightDetail.setDepartureTime(departureTime);
        flightDetail.setArrivalTime(arrivalTime);
        flightDetail.setTravelTime(flightLeg.getFlightDurationMinutes());
        
        String airline_code = flightLeg.getCarrierFsCode();
        String airline_name = fsAirlines.get(airline_code);
        
        flightDetail.setAirlineName(airline_name);
        flightDetail.setAirlineCode(airline_code);
        flightDetail.setFlightNumber(flightLeg.getFlightNumber());
        return flightDetail;
    }
    
    /**
     * convert a FSAirline list into a Map<String, String> where the key is the code value of the airline and the value is the airline name
     * @param fsAirlines
     * @return
     */
    public static Map<String, String> convertFSAirlinesListToMap(
            List<FSAirline> fsAirlines) {
        Map<String, String> airlinesMap = new HashMap<String, String>();
        for (FSAirline fsAirline : fsAirlines) {
            airlinesMap.put(fsAirline.getFs(), fsAirline.getName());
        }
        return airlinesMap;
    }
    
}
