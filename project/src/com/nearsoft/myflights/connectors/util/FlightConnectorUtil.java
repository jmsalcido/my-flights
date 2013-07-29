package com.nearsoft.myflights.connectors.util;

import java.io.BufferedReader;
import java.io.IOException;
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
