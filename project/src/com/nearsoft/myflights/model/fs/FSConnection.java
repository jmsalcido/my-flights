package com.nearsoft.myflights.model.fs;

import java.util.List;

public class FSConnection {

	private Object request;
	private FSAppendix appendix;
	private List<FSFlight> flights;

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	public FSAppendix getAppendix() {
		return appendix;
	}

	public void setAppendix(FSAppendix appendix) {
		this.appendix = appendix;
	}
	
	public List<FSFlight> getFlights() {
		return flights;
	}

	public void setFlights(List<FSFlight> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "FSConnection [request=" + request + ", appendix=" + appendix
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((appendix == null) ? 0 : appendix.hashCode());
		result = prime * result + ((flights == null) ? 0 : flights.hashCode());
		result = prime * result + ((request == null) ? 0 : request.hashCode());
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
		FSConnection other = (FSConnection) obj;
		if (appendix == null) {
			if (other.appendix != null)
				return false;
		} else if (!appendix.equals(other.appendix))
			return false;
		if (flights == null) {
			if (other.flights != null)
				return false;
		} else if (!flights.equals(other.flights))
			return false;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		return true;
	}
}
