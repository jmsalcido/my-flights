package com.nearsoft.myflights.model.fs;

import java.util.List;

/**
 * Javabean to deserialize an entire FlightStats JSON Object.
 * 
 * @author jsalcido
 * 
 */
public class FSFlight {

	private String departureAirportFsCode;
	private String arrivalAirportFsCode;
	private String departureDateFrom;
	private String departureDateTo;
	private List<Integer> departureDaysOfWeek;
	private int arrivalDateAdjustment;
	private String departureTime;
	private String arrivalTime;
	private int distanceMiles;
	private int flightDurationMinutes;
	private int layoverDurationMinutes;
	private String flightType;
	private String serviceType;
	private boolean online;
	private List<FSFlightLeg> flightLegs;

	public String getDepartureAirportFsCode() {
		return departureAirportFsCode;
	}

	public void setDepartureAirportFsCode(String departureAirportFsCode) {
		this.departureAirportFsCode = departureAirportFsCode;
	}

	public String getArrivalAirportFsCode() {
		return arrivalAirportFsCode;
	}

	public void setArrivalAirportFsCode(String arrivalAirportFsCode) {
		this.arrivalAirportFsCode = arrivalAirportFsCode;
	}

	public String getDepartureDateFrom() {
		return departureDateFrom;
	}

	public void setDepartureDateFrom(String departureDateFrom) {
		this.departureDateFrom = departureDateFrom;
	}

	public String getDepartureDateTo() {
		return departureDateTo;
	}

	public void setDepartureDateTo(String departureDateTo) {
		this.departureDateTo = departureDateTo;
	}

	public List<Integer> getDepartureDaysOfWeek() {
		return departureDaysOfWeek;
	}

	public void setDepartureDaysOfWeek(List<Integer> departureDaysOfWeek) {
		this.departureDaysOfWeek = departureDaysOfWeek;
	}

	public int getArrivalDateAdjustment() {
		return arrivalDateAdjustment;
	}

	public void setArrivalDateAdjustment(int arrivalDateAdjustment) {
		this.arrivalDateAdjustment = arrivalDateAdjustment;
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

	public int getDistanceMiles() {
		return distanceMiles;
	}

	public void setDistanceMiles(int distanceMiles) {
		this.distanceMiles = distanceMiles;
	}

	public int getFlightDurationMinutes() {
		return flightDurationMinutes;
	}

	public void setFlightDurationMinutes(int flightDurationMinutes) {
		this.flightDurationMinutes = flightDurationMinutes;
	}

	public int getLayoverDurationMinutes() {
		return layoverDurationMinutes;
	}

	public void setLayoverDurationMinutes(int layoverDurationMinutes) {
		this.layoverDurationMinutes = layoverDurationMinutes;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public List<FSFlightLeg> getFlightLegs() {
		return flightLegs;
	}

	public void setFlightLegs(List<FSFlightLeg> flightLegs) {
		this.flightLegs = flightLegs;
	}

	@Override
	public String toString() {
		return "FSFlight [departureAirportFsCode=" + departureAirportFsCode
				+ ", arrivalAirportFsCode=" + arrivalAirportFsCode
				+ ", departureDateFrom=" + departureDateFrom
				+ ", departureDateTo=" + departureDateTo
				+ ", departureDaysOfWeek=" + departureDaysOfWeek
				+ ", arrivalDateAdjustment=" + arrivalDateAdjustment
				+ ", departureTime=" + departureTime + ", arrivalTime="
				+ arrivalTime + ", distanceMiles=" + distanceMiles
				+ ", flightDurationMinutes=" + flightDurationMinutes
				+ ", layoverDurationMinutes=" + layoverDurationMinutes
				+ ", flightType=" + flightType + ", serviceType=" + serviceType
				+ ", online=" + online + ", flightLegs=" + flightLegs + "]";
	}
	
	
}