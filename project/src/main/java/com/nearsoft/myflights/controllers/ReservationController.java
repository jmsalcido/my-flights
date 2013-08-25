package com.nearsoft.myflights.controllers;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nearsoft.myflights.model.ReservationRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.nearsoft.myflights.model.Reservation;

@Controller
public class ReservationController {
    
    private final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "reservations", method = {RequestMethod.POST})
    public String sayHi(@RequestBody ReservationRequest reservation, HttpServletRequest request) {
        logger.info(reservation);
        return "";
    }
}
