package com.nearsoft.myflights.model.fs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.nearsoft.myflights.util.JsonUtils;

public class FSFlightTest {

    private FSFlight fsFlight;
    private Gson gson;

    String arrivalCode = "MEX";
    String departureCode = "CUL";
    int arrivalAdj = 0;
    String arrivalTime = "00:00:00.000";
    String departureDateFrom = "2013-10-10";
    String departureDateTo = "2013-10-30";
    List<Integer> listOfDays = new ArrayList<>();
    String departureTime = "06:05:00.000";
    int distanceMiles = 1000;
    int flightDurationMin = 100;
    String flightType = "NON_STOP";
    boolean online = true;
    String serviceType = "UNKNOWN";
    List<FSFlightLeg> listFlights = new ArrayList<>();
    int layoverDunno = 0;
    FSFlightLeg leg = new FSFlightLeg();

    @Before
    public void setUp() {
        this.fsFlight = new FSFlight();
        this.gson = new Gson();
        listOfDays.add(1);
        listFlights.add(leg);
    }

    @Test
    public void testGetAndSetMethods() {

        fsFlight.setArrivalAirportFsCode(arrivalCode);
        fsFlight.setDepartureAirportFsCode(departureCode);
        fsFlight.setArrivalTime(arrivalTime);
        fsFlight.setArrivalDateAdjustment(arrivalAdj);
        fsFlight.setDepartureDateFrom(departureDateFrom);
        fsFlight.setDepartureDateTo(departureDateTo);
        fsFlight.setDepartureDaysOfWeek(listOfDays);
        fsFlight.setDepartureTime(departureTime);
        fsFlight.setDistanceMiles(distanceMiles);
        fsFlight.setFlightDurationMinutes(flightDurationMin);
        fsFlight.setOnline(online);
        fsFlight.setServiceType(serviceType);
        fsFlight.setFlightLegs(listFlights);
        fsFlight.setLayoverDurationMinutes(layoverDunno);
        fsFlight.setFlightType(flightType);

        assertEquals(arrivalCode, fsFlight.getArrivalAirportFsCode());
        assertEquals(departureCode, fsFlight.getDepartureAirportFsCode());
        assertEquals(arrivalTime, fsFlight.getArrivalTime());
        assertEquals(arrivalAdj, fsFlight.getArrivalDateAdjustment());
        assertEquals(departureDateFrom, fsFlight.getDepartureDateFrom());
        assertEquals(departureDateTo, fsFlight.getDepartureDateTo());
        assertEquals(distanceMiles, fsFlight.getDistanceMiles());
        assertEquals(flightDurationMin, fsFlight.getFlightDurationMinutes());
        assertEquals(online, fsFlight.isOnline());
        assertEquals(serviceType, fsFlight.getServiceType());
        assertEquals(listFlights, fsFlight.getFlightLegs());
        assertEquals(layoverDunno, fsFlight.getLayoverDurationMinutes());
        assertEquals(flightType, fsFlight.getFlightType());

    }

    private void fillObject() {
        fsFlight.setArrivalAirportFsCode(arrivalCode);
        fsFlight.setDepartureAirportFsCode(departureCode);
        fsFlight.setArrivalTime(arrivalTime);
        fsFlight.setArrivalDateAdjustment(arrivalAdj);
        fsFlight.setDepartureDateFrom(departureDateFrom);
        fsFlight.setDepartureDateTo(departureDateTo);
        fsFlight.setDepartureDaysOfWeek(listOfDays);
        fsFlight.setDepartureTime(departureTime);
        fsFlight.setDistanceMiles(distanceMiles);
        fsFlight.setFlightDurationMinutes(flightDurationMin);
        fsFlight.setOnline(online);
        fsFlight.setServiceType(serviceType);
        fsFlight.setFlightLegs(listFlights);
        fsFlight.setLayoverDurationMinutes(layoverDunno);
        fsFlight.setFlightType(flightType);
    }

    @Test
    public void testObjectToJson() {
        fillObject();
        String json = gson.toJson(fsFlight);
        assertEquals(fsFlight, gson.fromJson(json, FSFlight.class));
    }

    @Test
    public void testParseFromJsonFile() throws IOException {
        String json = JsonUtils.createJsonString(getClass(),
                "flights-json.txt");
        fsFlight = gson.fromJson(json, FSFlight.class);
        assertNotNull(fsFlight);
    }

}
