package com.nearsoft.myflights.model;

import java.util.HashMap;
import java.util.Map;

public class Airport {

    public final static String FS_CODE = "fs";
    public final static String IATA_CODE = "iata";
    public final static String ICAO_CODE = "icao";
    public final static String FAA_CODE = "faa";

    private int id;
    //private Map<String, String> codes;
    private String code;
    private String name;
    private String city;
    private String country;

    public static Airport createAirportFromFSCode(String fsCode) {
        Airport airport = new Airport();
        Map<String, String> map = new HashMap<String, String>();
        map.put(Airport.FS_CODE, fsCode);
        airport.setCode(fsCode);
        //airport.setCodes(map);
        return airport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
