package com.nearsoft.myflights.model;

import java.sql.Date;
import java.util.List;

public class Flight {

	public final static int NON_STOP = 0;
	public final static int CONNECTION = 1;

	private Date date;
	private Airport departureAirport;
	private Airport arrivalAirport;
	private int travelTime;
	private int flightType;
	private List<FlightDetail> flightDetail;

	public Flight(List<FlightDetail> flightDetail) {
		this.flightDetail = flightDetail;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public int getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}

	public int getFlightType() {
		return flightType;
	}

	public void setFlightType(int flightType) {
		this.flightType = flightType;
	}

	public List<FlightDetail> getFlightDetail() {
		return flightDetail;
	}

	public void setFlightDetail(List<FlightDetail> flightDetail) {
		this.flightDetail = flightDetail;
	}

}
