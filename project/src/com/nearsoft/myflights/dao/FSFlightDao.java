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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nearsoft.myflights.model.Airport;
import com.nearsoft.myflights.model.Flight;
import com.nearsoft.myflights.model.FlightDetail;
import com.nearsoft.myflights.model.fs.FSConnection;
import com.nearsoft.myflights.model.fs.FSFlight;
import com.nearsoft.myflights.model.fs.FSFlightLeg;

public class FSFlightDao implements FlightDao {

	// =================================
	// FLIGHTSTATS API ID & KEY
	// =================================
	private final static String appId = "fddf4ecf";
	private final static String appKey = "d2a01014237e20af21d8c7a146e5bbf2";
	private final static String FS_URL = "https://api.flightstats.com/flex/connections/rest/v1/json/connecting/from/%s/to/%s/departing/%s/%s/%s?appId=%s&appKey=%s";

	private final static Log logger = LogFactory.getLog(FSFlightDao.class);
	
	private Gson gson;

	public FSFlightDao() {
		gson = new GsonBuilder()
		   .setDateFormat(DateFormat.FULL, DateFormat.FULL).create();
	}

	@Override
	public List<Flight> getFlights(Airport from, Airport to, Date date) {
		List<Flight> flightList = new ArrayList<>();
		String json = getJsonFromParameters(from, to, date);
		if (json == null) {
			return null;
		}
		FSConnection fsConn = getFSConnectionFromJson(json);
		for(FSFlight fsFlight : fsConn.getAppendix().getFlights()) {
			Flight flight = new Flight(fsFlight);
			flightList.add(flight);
		}
		return flightList;
	}
	
	/**
	 * Use {@link getFSConnectionFromJson}
	 * @param json
	 * @return
	 */
	@Deprecated
	public Map<String, Object> getMapFromJson(String json) {
		Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
		return map;
	}
	
	public FSConnection getFSConnectionFromJson(String json) {
		return gson.fromJson(json, FSConnection.class);
	}
	
	public String getJson(String codeFrom, String codeTo, String year, String month, String day) {
		URL url = createtUrl(codeFrom, codeTo, year, month, day);
		return getJsonFromUrl(url);
	}

	private String getJsonFromParameters(Airport from, Airport to, Date date) {
		Map<String, String> codesFrom = from.getCodes();

		// get only the FS aiport codes.
		String codeFrom = codesFrom.get(Airport.FS);
		Map<String, String> codesTo = to.getCodes();
		String codeTo = codesTo.get(Airport.FS);

		// get the date.
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

		// create the url to request
		URL urlRequest = createtUrl(codeFrom, codeTo, year, month, day);
		return getJsonFromUrl(urlRequest);
	}
	
	private URL createtUrl(String from, String to, String year, String month,
			String day) {
		try {
			URL url = new URL(String.format(FS_URL, from, to, year, month, day,
					appId, appKey));
			return url;
		} catch (MalformedURLException e) {
			
			logger.error(e.getLocalizedMessage());
			
			// if the url is malformed return null.
			return null;
		}
	}
	
	private String getJsonFromUrl(URL url) {

		DefaultHttpClient client = null;
		HttpGet getRequest = null;
		HttpResponse response = null;
		BufferedReader reader = null;
		String json = null;

		if (url == null) {
			return null;
		} else {
			try {
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

			} catch (IOException e) {
				logger.error(e.getLocalizedMessage());
				return null;
			} catch (URISyntaxException e) {
				logger.error(e.getLocalizedMessage());
				return null;
			} catch (HttpException e) {
				logger.error(e.getLocalizedMessage());
				return null;
			}
		}

		return json;
	}

}