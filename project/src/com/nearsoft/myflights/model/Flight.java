package com.nearsoft.myflights.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.nearsoft.myflights.model.fs.FSFlight;
import com.nearsoft.myflights.model.fs.FSFlightLeg;

public class Flight {

    public final static int NON_STOP = 0;
    public final static int CONNECTION = 1;

    private Long id;
    private Date date;
    private String departure_airport;
    private String arrival_airport;
    private int travel_time;
    private int flight_type;
    private List<FlightDetail> flight_detail;

    public Flight(List<FlightDetail> flightDetail) {
        this.flight_detail = flightDetail;
    }

    public Flight(FSFlight fsFlight, Date date) {
        this.setDate(date);
        this.setDepartureAirport(fsFlight.getDepartureAirportFsCode());
        this.setArrivalAirport(fsFlight.getArrivalAirportFsCode());
        this.setTravelTime(fsFlight.getFlightDurationMinutes());
        this.setFlightType(fsFlight.getFlightType().equalsIgnoreCase("DIRECT") ? NON_STOP
                : CONNECTION);
        List<FSFlightLeg> fsFlightDetails = fsFlight.getFlightLegs();
        this.flight_detail = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (FSFlightLeg fsFlightLeg : fsFlightDetails) {
            FlightDetail flightDetail = new FlightDetail(fsFlightLeg);
            this.flight_detail.add(flightDetail);
            sb.append(flightDetail.getFlightNumber());
        }
        this.setId(Long.parseLong(sb.toString()));   
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

    public List<FlightDetail> getFlightDetail() {
        return flight_detail;
    }

    public void setFlightDetail(List<FlightDetail> flightDetail) {
        this.flight_detail = flightDetail;
    }

}
