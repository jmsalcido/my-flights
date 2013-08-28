package com.nearsoft.myflights.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nearsoft.myflights.model.Reservation;
import com.nearsoft.myflights.model.ReservationRequest;
import com.nearsoft.myflights.service.ReservationService;
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
    public String createReservation(@RequestBody ReservationRequest reservationRequest,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {
        Reservation reservation = null;
        logger.info(reservationRequest);
        String reservationKey = "reservation";
        Map<String, Object> mapResponse = new HashMap<>();
        Gson gson = new Gson();
        try {
            reservation = reservationService.saveReservation(reservationRequest.getReservation());
            mapResponse.put(reservationKey, reservation);
        } catch(Exception e) {
            mapResponse.put("error", e.getMessage());
            logger.warn(e.getMessage());
        }
        logger.info(mapResponse);
        return gson.toJson(mapResponse);
    }
}
