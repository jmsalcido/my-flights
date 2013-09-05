package com.nearsoft.myflights.dao;

import com.nearsoft.myflights.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jose Salcido
 * User: jms
 * Date: 8/25/13
 * Time: 9:37 PM
 */
@Repository
public class JdbcReservationDao implements ReservationDao {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Reservation saveReservation(final Reservation reservation) throws Exception {
        final String sql = "INSERT INTO reservations VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate = new JdbcTemplate(dataSource);

        // keyHolder will have the generated key from the database
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] params = new Object[] {
                reservation.getPrice(),
                reservation.getName(),
                reservation.getLast_name(),
                reservation.getTelephone(),
                reservation.getEmail(),
                reservation.getDeparture(),
                reservation.getArrival(),
                reservation.getDeparture_date(),
                reservation.getArrival_date(),
                reservation.getStatus()
        };
        PreparedStatementCreator statementCreator = returnPreparedStatementCreatorGeneratedKeys(sql, params);
        jdbcTemplate.update(statementCreator, keyHolder);
        Long id = (Long) keyHolder.getKey();
        reservation.setId(id);
        return reservation;
    }

    /**
     * return a PreparedStatementCreator that will return generated keys from the sql database.
     * @param sql
     * @param params
     * @return
     */
    private PreparedStatementCreator returnPreparedStatementCreatorGeneratedKeys(final String sql, final Object[] params) {
        PreparedStatementCreator statementCreator = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                for(int i = 0; i < params.length; i+=1) {
                    ps.setObject(i+1, params[i]);
                }
                return ps;
            }
        };
        return statementCreator;
    }

    @Override
    public Reservation retrieveReservation(long id, String email) throws Exception {
        final String sql = "SELECT * FROM reservations WHERE id = ? AND email = ?;";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] params = new Object[] { id, email};
        return jdbcTemplate.queryForObject(sql, new ReservationMapper(), params);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) throws Exception {
        throw new UnsupportedOperationException("This method is not yet supported");
    }

    private static class ReservationMapper implements
            ParameterizedRowMapper<Reservation> {

        private final static String COLUMN_ID = "id";
        private final static String COLUMN_PRICE = "price";
        private final static String COLUMN_NAME = "name";
        private final static String COLUMN_LASTNAME = "lastName";
        private final static String COLUMN_TELEPHONE = "telephone";
        private final static String COLUMN_EMAIL = "email";
        private final static String COLUMN_DEPARTURE = "departure";
        private final static String COLUMN_ARRIVAL = "arrival";
        private final static String COLUMN_DEPARTUREDATE = "departureDate";
        private final static String COLUMN_ARRIVALDATE = "arrivalDate";
        private final static String COLUMN_STATUS = "status";

        @Override
        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getLong(COLUMN_ID));
            reservation.setPrice(rs.getInt(COLUMN_PRICE));
            reservation.setName(rs.getString(COLUMN_NAME));
            reservation.setLast_name(rs.getString(COLUMN_LASTNAME));
            reservation.setTelephone(rs.getString(COLUMN_TELEPHONE));
            reservation.setEmail(rs.getString(COLUMN_EMAIL));
            reservation.setDeparture(rs.getString(COLUMN_DEPARTURE));
            reservation.setArrival(rs.getString(COLUMN_ARRIVAL));
            reservation.setDeparture_date(rs.getDate(COLUMN_DEPARTUREDATE));
            reservation.setArrival_date(rs.getDate(COLUMN_ARRIVALDATE));
            reservation.setStatus(rs.getShort(COLUMN_STATUS));
            return reservation;
        }

    }
}
