package com.nearsoft.myflights.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nearsoft.myflights.dao.AirportDao;
import com.nearsoft.myflights.model.Airport;

@Service
public class JdbcAirportService implements AirportService {

    @Autowired
    private AirportDao airportDao;

    public void setAirportDao(AirportDao airportDao) {
        this.airportDao = airportDao;
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportDao.getAllAirports();
    }

    @Override
    public Map<String,Object> getAirportsByKeyword(String keyword) {
        Map<String, Object> filtered_map = new LinkedHashMap<String, Object>();
        List<Airport> airportList = airportDao.getAirportsByKeyword(keyword);
        List<Integer> idAirportList = new ArrayList<>();
        Map<String, Object> search_map = new HashMap<>();
        for(Airport a : airportList) {
            idAirportList.add(a.getId());
        }
        search_map.put("airport_ids", idAirportList);
        filtered_map.put("search_airport", search_map);
        filtered_map.put("airports", airportList);
        return filtered_map;
    }

}
