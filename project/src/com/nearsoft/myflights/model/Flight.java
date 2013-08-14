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
    private String airline;
    private List<FlightDetail> flight_detail;

    public Flight() {}
    
    public Flight(List<FlightDetail> flightDetail) {
        this.flight_detail = flightDetail;
    }
    
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

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public List<FlightDetail> getFlightDetail() {
        return flight_detail;
    }

    public void setFlightDetail(List<FlightDetail> flightDetail) {
        this.flight_detail = flightDetail;
    }

}
