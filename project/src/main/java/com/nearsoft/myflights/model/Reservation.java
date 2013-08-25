package com.nearsoft.myflights.model;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6322475916639593551L;

    private Integer id;
    private Integer price;
    private String name;
    private String last_name;
    private String telephone;
    private String email;
    private String departure;
    private String arrival;
    private Date departure_date;
    private Date arrival_date;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public Date getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Date arrival_date) {
        this.arrival_date = arrival_date;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", price=" + price + ", name=" + name
                + ", last_name=" + last_name + ", telephone=" + telephone
                + ", email=" + email + ", departure=" + departure
                + ", arrival=" + arrival + ", departure_date=" + departure_date
                + ", arrival_date=" + arrival_date + "]";
    }
    
    

}
