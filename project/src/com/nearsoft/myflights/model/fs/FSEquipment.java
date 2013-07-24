package com.nearsoft.myflights.model.fs;

public class FSEquipment {
	
	private String fs;
	private String iata;
	private String icao;
	private String name;
	private boolean turboProp;
	private boolean jet;
	private boolean widebody;
	private boolean regional;

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTurboProp() {
		return turboProp;
	}

	public void setTurboProp(boolean turboProp) {
		this.turboProp = turboProp;
	}

	public boolean isJet() {
		return jet;
	}

	public void setJet(boolean jet) {
		this.jet = jet;
	}

	public boolean isWidebody() {
		return widebody;
	}

	public void setWidebody(boolean widebody) {
		this.widebody = widebody;
	}

	public boolean isRegional() {
		return regional;
	}

	public void setRegional(boolean regional) {
		this.regional = regional;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public String getFs() {
		return fs;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}

}
