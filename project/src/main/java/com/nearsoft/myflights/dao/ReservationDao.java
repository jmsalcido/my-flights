package com.nearsoft.myflights.dao;

import com.nearsoft.myflights.model.Reservation;

/**
 * Created by
 * User: jms
 * Date: 8/25/13
 * Time: 9:21 PM
 */
public interface ReservationDao {

    public Reservation saveReservation(Reservation reservation);
    public Reservation retrieveReservation(long id);
    public Reservation updateReservation(Reservation reservation);
}
