package com.nearsoft.myflights.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jsalcido
 * Date: 8/23/13
 * Time: 2:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReservationRequest implements Serializable {

    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
