package com.nearsoft.myflights.connectors;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nearsoft.myflights.connectors.FSFlightConnector;
import com.nearsoft.myflights.model.Flight;

public class FSFlightConnectorTest {

    private FSFlightConnector connector;

    @Before
    public void setUp() {
        connector = new FSFlightConnector();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetFlightsWithNullParameters() throws Exception{
		connector.getFlights(null, null, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetFlightsWithNullAirportCodes() throws Exception {
    	connector.getFlights(null, null, new Date());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetFlightsWithNullDate() throws Exception {
    	connector.getFlights("CUL", "MEX", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFlightsWithTheSameAirportCode() throws Exception {
    	List<Flight> flightList = connector.getFlights("CUL", "CUL", new Date());
    }

    public void testGetFlightsWithAirportCodeWithNoRoutes() throws Exception {

    }
    
    public void testGetFlightsWithNonExistentAirportCodes() throws Exception {
    	
    }
    
    public void testGetflightsWithNonExistentParameters() throws Exception {
    	
    }

}
