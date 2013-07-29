package com.nearsoft.myflights.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nearsoft.myflights.model.Flight;
import com.nearsoft.myflights.model.fs.FSConnection;
import com.nearsoft.myflights.model.fs.FSFlight;

public class FSFlightDao implements FlightDao {

    private final static String FS_APPID = "fddf4ecf";
    private final static String FS_APPKEY = "d2a01014237e20af21d8c7a146e5bbf2";
    private final static String FS_URL = "https://api.flightstats.com/flex/connections/rest/v1/json/connecting/from/%s/to/%s/departing/%s/%s/%s?appId=%s&appKey=%s";

    private final static Log logger = LogFactory.getLog(FSFlightDao.class);

    private Gson gson;

    public FSFlightDao() {
        gson = new GsonBuilder()
                .setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
    }

    @Override
    public List<Flight> getFlights(String fromAirportCode, String toAirportCode, Date date)
            throws ClientProtocolException, URISyntaxException, IOException,
            HttpException {
        List<Flight> flightList = new ArrayList<>();
        String json = getJsonFromParameters(fromAirportCode, toAirportCode, date);
        if (json == null) {
            return null;
        }
        FSConnection fsConn = getFSConnectionFromJson(json);
        for (FSFlight fsFlight : fsConn.getFlights()) {
            Flight flight = new Flight(fsFlight, date);
            flightList.add(flight);
        }
        return flightList;
    }

    public FSConnection getFSConnectionFromJson(String json) {
        // logger.info(json); // ...
        return gson.fromJson(json, FSConnection.class);
    }

    public String getJson(String codeFrom, String codeTo, String year,
            String month, String day) throws ClientProtocolException,
            URISyntaxException, IOException, HttpException {
        URL url = createtUrl(codeFrom, codeTo, year, month, day);
        return getJsonFromUrl(url);
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
        return getJsonFromUrl(urlRequest);
    }

    private URL createtUrl(String fromAirportCode, String toAirportCode, String year, String month,
            String day) throws MalformedURLException {
        URL url = new URL(String.format(FS_URL, fromAirportCode, toAirportCode, year, month, day,
                FS_APPID, FS_APPKEY));
        logger.info(url.toString());
        return url;
    }

    private String getJsonFromUrl(URL url) throws URISyntaxException,
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

        reader = new BufferedReader(new InputStreamReader(response
                .getEntity().getContent()));

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