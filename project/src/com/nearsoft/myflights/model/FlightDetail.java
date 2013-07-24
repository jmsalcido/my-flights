package com.nearsoft.myflights.model;

import com.nearsoft.myflights.model.fs.FSFlightLeg;

public class FlightDetail {

	// Flight info
	private Airport departureAirport;
	private Airport arrivalAirport;
	private String departureTime;
	private String arrivalTime;
	private int travelTime;
	private Airline airline; // here.
	private String equipment;
	
	public FlightDetail(FSFlightLeg flightLeg) {
		this.arrivalAirport = Airport.createAirportFromFSCode(flightLeg.getArrivalAirportFsCode());
		this.departureAirport = Airport.createAirportFromFSCode(flightLeg.getDepartureAirportFsCode());
		this.departureTime = flightLeg.getDepartureTime();
		this.arrivalTime = flightLeg.getArrivalTime();
		this.travelTime = flightLeg.getFlightDurationMinutes();
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
}
