package com.nearsoft.myflights.controllers;

import com.nearsoft.myflights.model.Reservation;
import com.nearsoft.myflights.model.ReservationRequest;
import com.nearsoft.myflights.service.ReservationService;
import com.nearsoft.myflights.util.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ReservationController {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value = "reservations", method = {RequestMethod.POST})
    @ResponseBody
    public String createReservation(@RequestBody ReservationRequest reservationRequest) {
        Reservation reservation = null;
        logger.info(reservationRequest);
        String reservationKey = "reservation";
        Map<String, Object> mapResponse = new HashMap<>();
        try {
            reservation = reservationService.saveReservation(reservationRequest.getReservation());
            mapResponse.put(reservationKey, reservation);
        } catch(Exception e) {
            mapResponse.put("error", e.getMessage());
            logger.warn(e.getMessage());
        }
        logger.info(mapResponse);
        return JsonUtils.convertObjectToJSON(mapResponse);
    }

    @RequestMapping(value = "reservations", method = {RequestMethod.GET})
    @ResponseBody
    public String getReservation(@RequestParam(value = "id") long id,
                                 @RequestParam(value = "email") String email) {
        Map<String, Reservation> reservationMapResponse;
        Map<String, String> errorMapResponse;
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setEmail(email);
        try {
            reservationMapResponse = new HashMap<>();
            reservationMapResponse.put("reservation", reservationService.retrieveReservation(reservation));
            return JsonUtils.convertObjectToJSON(reservationMapResponse);
        } catch(Exception ex) {
            errorMapResponse = new HashMap<>();
            errorMapResponse.put("error", ex.getMessage());
            return JsonUtils.convertObjectToJSON(errorMapResponse);
        }
    }
}
