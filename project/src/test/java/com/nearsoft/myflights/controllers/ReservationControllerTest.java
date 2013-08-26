package com.nearsoft.myflights.controllers;

import com.nearsoft.myflights.model.ReservationRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: jsalcido
 * Date: 8/23/13
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReservationControllerTest {


    @Test
    public void testSerialize() throws Exception {
        String str = "{\"reservation\":{\"price\":1000,\"name\":null,\"last_name\":null,\"telephone\":null,\"email\":null,\"departure\":null,\"arrival\":null,\"departure_date\":null,\"arrival_date\":null}}";

        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        ReservationRequest reservation = mapper.readValue(str, ReservationRequest.class);

        assertNotNull(reservation);
        assertEquals(new Integer(1000), reservation.getReservation().getPrice());

    }
}
