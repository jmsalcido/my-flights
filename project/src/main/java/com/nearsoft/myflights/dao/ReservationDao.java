package com.nearsoft.myflights.dao;

import com.nearsoft.myflights.model.Reservation;

/**
 * Created by
 * User: jms
 * Date: 8/25/13
 * Time: 9:21 PM
 */
public interface ReservationDao {

    public Reservation saveReservation(Reservation reservation) throws Exception;
    public Reservation retrieveReservation(long id) throws Exception;
    public Reservation updateReservation(Reservation reservation) throws Exception;
}
