package com.nearsoft.myflights.model;

import java.util.Date;
import java.util.List;

public class Flight {

    public final static int NON_STOP = 0;
    public final static int CONNECTION = 1;

    private Long id;
    private Date date;
    private String departure_airport;
    private String arrival_airport;
    private int travel_time;
    private int flight_type;
    private List<FlightDetail> flight_details;

    public Flight() {}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDepartureAirport() {
        return departure_airport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departure_airport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrival_airport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrival_airport = arrivalAirport;
    }

    public int getTravelTime() {
        return travel_time;
    }

    public void setTravelTime(int travelTime) {
        this.travel_time = travelTime;
    }

    public int getFlightType() {
        return flight_type;
    }

    public void setFlightType(int flightType) {
        this.flight_type = flightType;
    }

    public List<FlightDetail> getFlightDetails() {
        return flight_details;
    }

    public void setFlightDetails(List<FlightDetail> flight_details) {
        this.flight_details = flight_details;
    }

}
