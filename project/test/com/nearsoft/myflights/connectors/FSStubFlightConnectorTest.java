package com.nearsoft.myflights.connectors;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nearsoft.myflights.model.Flight;

public class FSStubFlightConnectorTest {

    private FSStubFlightConnector flightConnector;
    
    @Before
    public void setUp() {
        flightConnector = new FSStubFlightConnector();
    }
    
    @Test
    public void testGetFlightsWithParameters() throws Exception {
        List<Flight> flightList = flightConnector.getFlights("CUL", "MEX", new Date());
        assertNotNull(flightList);
        Flight flight = flightList.get(0);
        assertEquals("CUL", flight.getDepartureAirport());
        assertEquals("MEX", flight.getArrivalAirport());
        assertNotNull(flight.getDate());
    }
}
