package com.nearsoft.myflights.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.nearsoft.myflights.model.fs.FSFlight;
import com.nearsoft.myflights.model.fs.FSFlightLeg;

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
	
	public Flight(FSFlight fsFlight, Date date) {
		List<FSFlightLeg> fsFlightDetails = fsFlight.getFlightLegs();
		this.flightDetail = new ArrayList<>();
		for(FSFlightLeg fsFlightLeg  : fsFlightDetails) { 
			FlightDetail flightDetail = new FlightDetail(fsFlightLeg);
			this.flightDetail.add(flightDetail);
		}
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
