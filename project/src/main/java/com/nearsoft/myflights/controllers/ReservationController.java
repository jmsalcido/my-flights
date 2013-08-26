package com.nearsoft.myflights.controllers;

import javax.servlet.http.HttpServletRequest;

import com.nearsoft.myflights.model.ReservationRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {

    private final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "reservations", method = {RequestMethod.POST})
    @ResponseBody
    public ReservationRequest createReservation(@RequestBody ReservationRequest reservation,
                                                HttpServletRequest request) {
        logger.info(reservation);
        // TODO ReservationServiceSave
        return reservation;
    }
}
