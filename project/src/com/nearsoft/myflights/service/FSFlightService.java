package com.nearsoft.myflights.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nearsoft.myflights.dao.FlightDao;
import com.nearsoft.myflights.model.Flight;

@Service
public class FSFlightService implements FlightService {

    @Autowired
    FlightDao flightDao;

    private static Log logger = LogFactory.getLog(FSFlightService.class);

    public void setFlightDao(FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    @Override
    public List<Flight> getFlights(String fromAirportCode, String toAirportCode, String date)
            throws Exception {
        try {
            Date dateObject = createDateFromString(date);
            return flightDao.getFlights(fromAirportCode, toAirportCode, dateObject);
        } catch (ParseException e) {
            throw new Exception("[DATE d] " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private Date createDateFromString(String dateString) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = formatter.parse(dateString);
        logger.info(String.format("%s produced: %s", dateString, date.toString()));
        return date;
    }

}
