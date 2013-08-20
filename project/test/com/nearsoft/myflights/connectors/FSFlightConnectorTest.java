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
    
    @Test(expected = NullPointerException.class)
    public void testGetFlightsWithNullParameters() throws Exception{
		connector.getFlights(null, null, null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetFlightsWithNullAirportCodes() throws Exception {
    	connector.getFlights(null, null, new Date());
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetFlightsWithNullDate() throws Exception {
    	connector.getFlights("CUL", "MEX", null);
    }
    
    public void testGetFlightsWithTheSameAirportCode() throws Exception {
    	List<Flight> flightList = connector.getFlights("CUL", "MEX", new Date());
    	assertNotNull(flightList);
    }
    
    public void testGetFlightsWithAirportCodeWithNoRoutes() throws Exception {
    	
    }
    
    public void testGetFlightsWithNonExistentAirportCodes() throws Exception {
    	
    }
    
    public void testGetflightsWithNonExistentParameters() throws Exception {
    	
    }

}
