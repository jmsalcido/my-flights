package com.nearsoft.myflights.connectors.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.nearsoft.myflights.model.Flight;
import com.nearsoft.myflights.model.FlightDetail;
import com.nearsoft.myflights.model.fs.FSAirline;
import com.nearsoft.myflights.model.fs.FSFlight;
import com.nearsoft.myflights.model.fs.FSFlightLeg;

public class FlightConnectorUtil {

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
        flightDetail.setDepartureTime(flightLeg.getDepartureTime());
        flightDetail.setArrivalTime(flightLeg.getArrivalTime());
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
        Map<String, String> returnMap = new HashMap<String, String>();
        for (FSAirline fsAirline : fsAirlines) {
            returnMap.put(fsAirline.getFs(), fsAirline.getName());
        }
        return returnMap;
    }

    /**
     * obtain the JSON file from the specified URL
     * @param url
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     * @throws HttpException
     */
    public static String getJsonFromUrl(URL url) throws URISyntaxException,
            ClientProtocolException, IOException, HttpException {

        DefaultHttpClient client = null;
        HttpGet getRequest = null;
        HttpResponse response = null;
        BufferedReader reader = null;
        String json = null;

        client = new DefaultHttpClient();
        getRequest = new HttpGet(url.toURI());
        getRequest.addHeader("accept", "application/json");

        response = client.execute(getRequest);

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new HttpException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        reader = new BufferedReader(new InputStreamReader(response.getEntity()
                .getContent()));

        // read all the json obtained
        StringBuilder builder = new StringBuilder();
        String aux = "";

        while ((aux = reader.readLine()) != null) {
            builder.append(aux);
        }
        json = builder.toString();

        // shutdown httpclient
        client.getConnectionManager().shutdown();

        return json;
    }

}
