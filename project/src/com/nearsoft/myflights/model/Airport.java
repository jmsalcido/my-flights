package com.nearsoft.myflights.model;

import java.util.HashMap;
import java.util.Map;

public class Airport {

    public final static String FS = "fs"; // only flightsats?
    public final static String IATA = "iata";
    public final static String ICAO = "icao";
    public final static String FAA = "faa";

    private int id;
    private Map<String, String> codes;
    private String name;
    private String city;
    private String country;

    public static Airport createAirportFromFSCode(String fsCode) {
        Airport airport = new Airport();
        Map<String, String> map = new HashMap<String, String>();
        map.put(Airport.FS, fsCode);
        airport.setCodes(map);
        return airport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, String> getCodes() {
        return codes;
    }

    public void setCodes(Map<String, String> codes) {
        this.codes = codes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
