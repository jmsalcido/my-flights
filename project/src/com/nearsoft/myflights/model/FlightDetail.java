package com.nearsoft.myflights.model;

public class FlightDetail {

    // Flight info
    private Integer id;
    private String departure_airport;
    private String arrival_airport;
    private String departure_time;
    private String arrival_time;
    private int travel_time;
    private String flight_number;
    private String airline;
    private String equipment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDepartureTime() {
        return departure_time;
    }

    public void setDepartureTime(String departureTime) {
        this.departure_time = departureTime;
    }

    public String getArrivalTime() {
        return arrival_time;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrival_time = arrivalTime;
    }

    public int getTravelTime() {
        return travel_time;
    }

    public void setTravelTime(int travelTime) {
        this.travel_time = travelTime;
    }

    public String getFlightNumber() {
        return flight_number;
    }

    public void setFlightNumber(String flightNumber) {
        // emmm funciona... mal.
        try {
            this.setId(Integer.parseInt(flightNumber));
        } catch (NumberFormatException nfe) {
            this.setId(0);
        }
        this.flight_number = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
