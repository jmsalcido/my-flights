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
import com.nearsoft.myflights.connectors.util.FSFlightConnectorUtil;
import com.nearsoft.myflights.connectors.util.FlightConnectorUtil;
import com.nearsoft.myflights.model.Flight;
import com.nearsoft.myflights.model.fs.FSConnection;
import com.nearsoft.myflights.model.fs.FSFlight;

public class FSFlightConnector implements FlightConnector {

    public final static String FS_APPID = "c2eb128c";
    public final static String FS_APPKEY = "298a8715d0c24aab56b97850f2403a31";
    public final static String FS_URL = "https://api.flightstats.com/flex/connections/rest/v1/json/connecting/from/%s/to/%s/departing/%s/%s/%s?appId=%s&appKey=%s";

    private final static Log logger = LogFactory.getLog(FSFlightConnector.class);

    private Gson gson;

    public FSFlightConnector() {
        gson = new GsonBuilder().setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
    }

    @Override
    public List<Flight> getFlights(String fromAirportCode, String toAirportCode, Date date)
            throws URISyntaxException, IOException, HttpException, IllegalArgumentException {
        if(fromAirportCode == null || toAirportCode == null || date == null) {
            throw new IllegalArgumentException("null is not allowed.");
        }
        if(fromAirportCode.equalsIgnoreCase(toAirportCode)) {
            throw new IllegalArgumentException(String.format("from airport: %s is the same as to airport: %s, there was something wrong.", fromAirportCode, toAirportCode));
        }
        List<Flight> flightList = new ArrayList<>();
        String json = getJsonFromParameters(fromAirportCode, toAirportCode, date);
        FSConnection fsConn = FSFlightConnectorUtil.getFSConnectionFromJson(gson, json);
        
        Map<String, String> airlines = FSFlightConnectorUtil.convertFSAirlinesListToMap(fsConn.getAppendix().getAirlines());
        for (FSFlight fsFlight : fsConn.getFlights()) {
            Flight flight = FSFlightConnectorUtil.createFlightFromFSFlight(fsFlight, airlines, date);
            flightList.add(flight);
        }
        return flightList;
    }

    private String getJsonFromParameters(String fromAirport, String toAirport, Date date)
            throws URISyntaxException, IOException,
            HttpException {
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