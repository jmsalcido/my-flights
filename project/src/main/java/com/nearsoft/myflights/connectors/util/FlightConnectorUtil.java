package com.nearsoft.myflights.connectors.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class FlightConnectorUtil {
    
    public static String formatTimeAMPM(String time) {
        StringBuilder sb = new StringBuilder(time);
        int hours = Integer.parseInt(sb.substring(0, 2));
        String minutes = sb.substring(3,4);
        int minutesInt = Integer.parseInt(minutes);
        String ampm = hours >= 12 ? "pm" : "am";
        hours = hours % 12;
        hours = hours != 0 ? hours : 12;
        minutes = minutesInt < 10 ? '0' + minutes : minutes;
        sb = new StringBuilder();
        sb.append(hours).append(":").append(minutes).append(" ").append(ampm);
        return sb.toString();
    }
    
    public static String removeCharacters(String string) {
        int digits = 5;
        StringBuilder sb = new StringBuilder(string);
        return sb.substring(0, digits);
    }

    /**
     * obtain the JSON file from the specified URL
     * @param url
     * @return
     * @throws java.net.URISyntaxException
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     * @throws org.apache.http.HttpException
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
        
        json = readStringFromBufferedReader(reader);
        
        reader.close();

        // shutdown httpclient
        client.getConnectionManager().shutdown();

        return json;
    }
    
    public static String getJsonFromInputStream(InputStream in) throws IOException {
        String json;
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(in));
        json = readStringFromBufferedReader(reader);
        reader.close();
        return json;
    }
    
    private static String readStringFromBufferedReader(BufferedReader reader) throws IOException {
        // read all the string obtained
        StringBuilder builder = new StringBuilder();
        String aux = "";

        while ((aux = reader.readLine()) != null) {
            builder.append(aux);
        }
        return builder.toString();
    }

}
