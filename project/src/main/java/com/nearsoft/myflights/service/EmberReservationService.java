package com.nearsoft.myflights.service;

import com.nearsoft.myflights.dao.ReservationDao;
import com.nearsoft.myflights.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Jose Salcido
 * User: jms
 * Date: 8/25/13
 * Time: 10:34 PM
 */
public class EmberReservationService implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @Override
    public Reservation saveReservation(Reservation reservation) throws Exception {
        try {
            return reservationDao.saveReservation(reservation);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Reservation retrieveReservation(Reservation reservation) throws Exception {
        try {
            return reservationDao.retrieveReservation(reservation.getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Reservation updateReservation(Reservation reservation) throws Exception {
        try {
            return reservationDao.updateReservation(reservation);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }
}
