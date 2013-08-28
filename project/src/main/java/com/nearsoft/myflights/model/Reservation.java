package com.nearsoft.myflights.model;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable{

    private Long id;
    private Integer price;
    private String name;
    private String last_name;
    private String telephone;
    private String email;
    private String departure;
    private String arrival;
    private Date departure_date;
    private Date arrival_date;
    private Long reservation_number;
    private short status;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getPrice() {
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


    public Long getReservation_number() {
        return reservation_number;
    }

    public void setReservation_number(Long reservation_number) {
        this.reservation_number = reservation_number;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (status != that.status) return false;
        if (arrival != null ? !arrival.equals(that.arrival) : that.arrival != null) return false;
        if (arrival_date != null ? !arrival_date.equals(that.arrival_date) : that.arrival_date != null) return false;
        if (departure != null ? !departure.equals(that.departure) : that.departure != null) return false;
        if (departure_date != null ? !departure_date.equals(that.departure_date) : that.departure_date != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (last_name != null ? !last_name.equals(that.last_name) : that.last_name != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (reservation_number != null ? !reservation_number.equals(that.reservation_number) : that.reservation_number != null)
            return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (departure_date != null ? departure_date.hashCode() : 0);
        result = 31 * result + (arrival_date != null ? arrival_date.hashCode() : 0);
        result = 31 * result + (reservation_number != null ? reservation_number.hashCode() : 0);
        result = 31 * result + (int) status;
        return result;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departure_date=" + departure_date +
                ", arrival_date=" + arrival_date +
                ", reservation_number=" + reservation_number +
                ", status=" + status +
                '}';
    }
}
