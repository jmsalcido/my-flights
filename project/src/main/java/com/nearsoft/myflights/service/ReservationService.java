package com.nearsoft.myflights.service;

import com.nearsoft.myflights.model.Reservation;

/**
 * Created by
 * User: jms
 * Date: 8/25/13
 * Time: 9:19 PM
 */
public interface ReservationService {

    public Reservation saveReservation(Reservation reservation);
    public Reservation retrieveReservation(Reservation reservation);
    public Reservation updateReservation(Reservation reservation);
}
