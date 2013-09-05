package com.nearsoft.myflights.service;

import com.nearsoft.myflights.dao.ReservationDao;
import com.nearsoft.myflights.model.Reservation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Jose Salcido
 * User: jms
 * Date: 8/25/13
 * Time: 10:34 PM
 */
public class EmberReservationService implements ReservationService {

    private final static Log logger = LogFactory.getLog(EmberReservationService.class.getClass());

    @Autowired
    private ReservationDao reservationDao;

    @Override
    public Reservation saveReservation(Reservation reservation) throws Exception {
        try {
            reservation = reservationDao.saveReservation(reservation);
            reservation.setReservation_number(reservation.getId());
            return reservation;
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new Exception("There was a problem connecting to the database", e.getCause());
        }
    }

    @Override
    public Reservation retrieveReservation(Reservation reservation) throws Exception {
        try {
            return reservationDao.retrieveReservation(reservation.getId(), reservation.getEmail());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }
}
