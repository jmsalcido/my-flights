package com.nearsoft.myflights.model.fs;

import java.util.List;

public class FSAppendix {
	private List<FSAirline> airlines;
	private List<FSEquipment> equipments;
	private List<FSFlight> flights;

	public List<FSAirline> getAirlines() {
		return airlines;
	}

	public void setAirlines(List<FSAirline> airlines) {
		this.airlines = airlines;
	}

	public List<FSEquipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<FSEquipment> equipments) {
		this.equipments = equipments;
	}

	public List<FSFlight> getFlights() {
		return flights;
	}

	public void setFlights(List<FSFlight> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "FSAppendix [airlines=" + airlines + ", equipments="
				+ equipments + ", flights=" + flights + "]";
	}
}
