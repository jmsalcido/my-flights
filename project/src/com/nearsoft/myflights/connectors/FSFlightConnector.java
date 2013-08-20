package com.nearsoft.myflights.connectors;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nearsoft.myflights.connectors.util.FlightConnectorUtil;
import com.nearsoft.myflights.model.Flight;
import com.nearsoft.myflights.model.fs.FSConnection;
import com.nearsoft.myflights.model.fs.FSFlight;

public class FSFlightConnector implements FlightConnector {

    public final static String FS_APPID = "fddf4ecf";
    public final static String FS_APPKEY = "d2a01014237e20af21d8c7a146e5bbf2";
    public final static String FS_URL = "https://api.flightstats.com/flex/connections/rest/v1/json/connecting/from/%s/to/%s/departing/%s/%s/%s?appId=%s&appKey=%s";

    private final static Log logger = LogFactory.getLog(FSFlightConnector.class);

    private Gson gson;

    public FSFlightConnector() {
        gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
    }

    @Override
    public List<Flight> getFlights(String fromAirportCode, String toAirportCode, Date date)
            throws ClientProtocolException, URISyntaxException, IOException,
            HttpException, NullPointerException {
        List<Flight> flightList = new ArrayList<>();
        String json = getJsonFromParameters(fromAirportCode, toAirportCode, date);
        FSConnection fsConn = getFSConnectionFromJson(json);
        
        Map<String, String> airlines = FlightConnectorUtil.convertFSAirlinesListToMap(fsConn.getAppendix().getAirlines());
        for (FSFlight fsFlight : fsConn.getFlights()) {
            Flight flight = FlightConnectorUtil.createFlightFromFSFlight(fsFlight, airlines, date);
            flightList.add(flight);
        }
        return flightList;
    }

    private FSConnection getFSConnectionFromJson(String json) {
        return gson.fromJson(json, FSConnection.class);
    }

    private String getJsonFromParameters(String fromAirport, String toAirport, Date date)
            throws URISyntaxException, IOException,
            HttpException {

    	if(fromAirport == null || toAirport == null || date == null) {
    		throw new NullPointerException("null is not allowed.");
    	}
        // get the date.
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        // create the url to request
        URL urlRequest = createtUrl(fromAirport, toAirport, year, month, day);
        
        // return the json
        return FlightConnectorUtil.getJsonFromUrl(urlRequest);
    }

    private URL createtUrl(String fromAirportCode, String toAirportCode, String year, String month,
            String day) throws MalformedURLException {
        URL url = new URL(String.format(FS_URL, fromAirportCode, toAirportCode, year, month, day,
                FS_APPID, FS_APPKEY));
        logger.info(url.toString());
        return url;
    }

}