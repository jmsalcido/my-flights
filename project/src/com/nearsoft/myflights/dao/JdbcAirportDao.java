package com.nearsoft.myflights.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
//	public JdbcAirportDao(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
//	
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
	public Airport getAirportByName(String name) {
		return null;
	}

	@Override
	public Airport getAirportByCode(String name) {
		final String sql = "SELECT id, fs, iata, icao, faa, name, city, countryName FROM airports WHERE fs = ?;";
		jdbcTemplate = new JdbcTemplate(dataSource);
		Object[] params = new Object[] {name};
		List<Airport> airports = jdbcTemplate.query(sql,params, new AirportMapper());
		if(airports.size() > 0) {
			return airports.get(0);
		}
		return null;
	}

	@Override
	public List<Airport> getAirportsByKeyword(String keyword) {
		List<Airport> airportList = new ArrayList<>();
		final String sql = "SELECT id, fs, iata, icao, faa, name, city, countryName FROM airports WHERE fs LIKE ? OR name LIKE ? OR city LIKE ? ORDER BY classification LIMIT 5;";
		jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder sb = new StringBuilder();
		sb.append("%").append(keyword).append("%");
		String param = sb.toString();
		Object[] params = new Object[] {param, param, param};
		airportList = jdbcTemplate.query(sql,params, new AirportMapper());
		return airportList;
	}

	private static class AirportMapper implements ParameterizedRowMapper<Airport> {

		private final static String COLUMN_ID 		= "id";
		private final static String COLUMN_FS 		= "fs"; // only flightsats?
		private final static String COLUMN_IATA 	= "iata";
		private final static String COLUMN_ICAO 	= "icao";
		private final static String COLUMN_FAA 		= "faa";
		private final static String COLUMN_NAME 	= "name";
		private final static String COLUMN_CITY 	= "city";
		private final static String COLUMN_COUNTRY 	= "countryName";
		
		public Map<String, String> createCodesMap(String fs, String iata, String icao, String faa) {
			Map<String, String> map = new HashMap<String, String>();
			map.put(COLUMN_FS, fs);
			map.put(COLUMN_IATA, iata);
			map.put(COLUMN_ICAO, icao);
			map.put(COLUMN_FAA, faa);
			return map;
		}
		
		@Override
		public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			// ICAO, IATA and FAA are the most important.
			String fs = rs.getString(COLUMN_FS);
			String iata = rs.getString(COLUMN_IATA);
			String icao = rs.getString(COLUMN_ICAO);
			String faa = rs.getString(COLUMN_FAA);
			
			Airport airport = new Airport();
			airport.setId(rs.getInt(COLUMN_ID));
			airport.setName(rs.getString(COLUMN_NAME));
			airport.setCity(rs.getString(COLUMN_CITY));
			airport.setCountry(rs.getString(COLUMN_COUNTRY));
			airport.setCodes(createCodesMap(fs, iata, icao, faa));
			return airport;
		}
		
	}

}
