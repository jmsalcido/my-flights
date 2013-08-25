package com.nearsoft.myflights.model.fs;

import java.util.List;

public class FSFlightLeg {

    private String departureAirportFsCode;
    private String arrivalAirportFsCode;
    private String departureTime;
    private String arrivalTime;
    private int departureDateAdjustment;
    private int arrivalDateAdjustment;
    private String departureTerminal;
    private String carrierFsCode;
    private String flightNumber;
    private boolean codeshare;
    private List<String> equipmentCodes;
    private int distanceMiles;
    private int flightDurationMinutes;
    private int layoverDurationMinutes;

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

    public int getDepartureDateAdjustment() {
        return departureDateAdjustment;
    }

    public void setDepartureDateAdjustment(int departureDateAdjustment) {
        this.departureDateAdjustment = departureDateAdjustment;
    }

    public int getArrivalDateAdjustment() {
        return arrivalDateAdjustment;
    }

    public void setArrivalDateAdjustment(int arrivalDateAdjustment) {
        this.arrivalDateAdjustment = arrivalDateAdjustment;
    }

    public String getDepartureTerminal() {
        return departureTerminal;
    }

    public void setDepartureTerminal(String departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    public String getCarrierFsCode() {
        return carrierFsCode;
    }

    public void setCarrierFsCode(String carrierFsCode) {
        this.carrierFsCode = carrierFsCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public boolean isCodeshare() {
        return codeshare;
    }

    public void setCodeshare(boolean codeshare) {
        this.codeshare = codeshare;
    }

    public List<String> getEquipmentCodes() {
        return equipmentCodes;
    }

    public void setEquipmentCodes(List<String> equipmentCodes) {
        this.equipmentCodes = equipmentCodes;
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

    @Override
    public String toString() {
        return "FSFlightLeg [departureAirportFsCode=" + departureAirportFsCode
                + ", arrivalAirportFsCode=" + arrivalAirportFsCode
                + ", departureTime=" + departureTime + ", arrivalTime="
                + arrivalTime + ", departureDateAdjustment="
                + departureDateAdjustment + ", arrivalDateAdjustment="
                + arrivalDateAdjustment + ", departureTerminal="
                + departureTerminal + ", carrierFsCode=" + carrierFsCode
                + ", flightNumber=" + flightNumber + ", codeshare=" + codeshare
                + ", equipmentCodes=" + equipmentCodes + ", distanceMiles="
                + distanceMiles + ", flightDurationMinutes="
                + flightDurationMinutes + ", layoverDurationMinutes="
                + layoverDurationMinutes + "]";
    }

}
