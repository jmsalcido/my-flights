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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((arrivalAirportFsCode == null) ? 0 : arrivalAirportFsCode
						.hashCode());
		result = prime * result + arrivalDateAdjustment;
		result = prime * result
				+ ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime
				* result
				+ ((departureAirportFsCode == null) ? 0
						: departureAirportFsCode.hashCode());
		result = prime
				* result
				+ ((departureDateFrom == null) ? 0 : departureDateFrom
						.hashCode());
		result = prime * result
				+ ((departureDateTo == null) ? 0 : departureDateTo.hashCode());
		result = prime
				* result
				+ ((departureDaysOfWeek == null) ? 0 : departureDaysOfWeek
						.hashCode());
		result = prime * result
				+ ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + distanceMiles;
		result = prime * result + flightDurationMinutes;
		result = prime * result
				+ ((flightType == null) ? 0 : flightType.hashCode());
		result = prime * result + layoverDurationMinutes;
		result = prime * result + (online ? 1231 : 1237);
		result = prime * result
				+ ((serviceType == null) ? 0 : serviceType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FSFlight other = (FSFlight) obj;
		if (arrivalAirportFsCode == null) {
			if (other.arrivalAirportFsCode != null)
				return false;
		} else if (!arrivalAirportFsCode.equals(other.arrivalAirportFsCode))
			return false;
		if (arrivalDateAdjustment != other.arrivalDateAdjustment)
			return false;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (departureAirportFsCode == null) {
			if (other.departureAirportFsCode != null)
				return false;
		} else if (!departureAirportFsCode.equals(other.departureAirportFsCode))
			return false;
		if (departureDateFrom == null) {
			if (other.departureDateFrom != null)
				return false;
		} else if (!departureDateFrom.equals(other.departureDateFrom))
			return false;
		if (departureDateTo == null) {
			if (other.departureDateTo != null)
				return false;
		} else if (!departureDateTo.equals(other.departureDateTo))
			return false;
		if (departureDaysOfWeek == null) {
			if (other.departureDaysOfWeek != null)
				return false;
		} else if (!departureDaysOfWeek.equals(other.departureDaysOfWeek))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (distanceMiles != other.distanceMiles)
			return false;
		if (flightDurationMinutes != other.flightDurationMinutes)
			return false;
		if (flightType == null) {
			if (other.flightType != null)
				return false;
		} else if (!flightType.equals(other.flightType))
			return false;
		if (layoverDurationMinutes != other.layoverDurationMinutes)
			return false;
		if (online != other.online)
			return false;
		if (flightLegs.size() != other.flightLegs.size())
			return false;
		if (serviceType == null) {
			if (other.serviceType != null)
				return false;
		} else if (!serviceType.equals(other.serviceType))
			return false;
		return true;
	}

}