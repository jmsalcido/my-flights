package com.nearsoft.myflights.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.nearsoft.myflights.model.Airport;

@Repository
public class JdbcAirportDao implements AirportDao {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Airport> getAllAirports() {
        List<Airport> airportList = new ArrayList<>();
        final String sql = "SELECT id, fs, name, city FROM airports;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        airportList = jdbcTemplate.query(sql, new AirportMapper());
        return airportList;
    }

    @Override
    public List<Airport> getAirportsByKeyword(String keyword) {
        final String sql = "SELECT id, fs, iata, icao, faa, name, city, countryName FROM airports WHERE fs LIKE ? OR name LIKE ? OR city LIKE ? ORDER BY classification LIMIT 5;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        
        // create the '%param%' string for LIKE statement
        StringBuilder sb = new StringBuilder();
        sb.append("%").append(keyword).append("%");
        String param = sb.toString();
        
        Object[] params = new Object[] { param, param, param };
        return jdbcTemplate.query(sql, params, new AirportMapper());
    }

    private static class AirportMapper implements
            ParameterizedRowMapper<Airport> {

        private final static String COLUMN_ID = "id";
        private final static String COLUMN_FS = "fs"; // only flightsats code will be used.
        private final static String COLUMN_NAME = "name";
        private final static String COLUMN_CITY = "city";
        private final static String COLUMN_COUNTRY = "countryName";

        @Override
        public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {

            // ICAO, IATA and FAA are the most important.
            String fs = rs.getString(COLUMN_FS);

            Airport airport = new Airport();
            airport.setId(rs.getInt(COLUMN_ID));
            airport.setName(rs.getString(COLUMN_NAME));
            airport.setCity(rs.getString(COLUMN_CITY));
            airport.setCountry(rs.getString(COLUMN_COUNTRY));
            airport.setCode(fs);//createCodesMap(fs, iata, icao, faa));
            return airport;
        }

    }

}
