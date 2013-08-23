package com.nearsoft.myflights.controllers;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.nearsoft.myflights.model.Reservation;

@Controller
@RequestMapping("reservations")
public class ReservationController {
    
    private final Log logger = LogFactory.getLog(getClass());
    
    @RequestMapping(headers ={"Accept=application/json"},
                    method = { RequestMethod.POST },
                    value = "/")
    @ResponseBody
    public ResponseEntity<String> createReservation(@RequestBody final Reservation reservation, HttpServletRequest request, HttpServletResponse response) {
        logger.info(reservation);
     // this could be on an util class for every controller that returns
        // JSON.
        HttpHeaders responseHeaders = new HttpHeaders();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String nextElement = (String) headerNames.nextElement();
            responseHeaders.set(nextElement, request.getHeader(nextElement));
        }

        // populating the header required for CORS
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        return new ResponseEntity<String>(gson.toJson(reservation), responseHeaders,
                HttpStatus.OK);
    }

}
