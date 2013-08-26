package com.nearsoft.myflights.dao;

import com.nearsoft.myflights.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
    public Reservation saveReservation(Reservation reservation) throws Exception {
        final String sql = "INSERT INTO reservations VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] params = new Object[] {
                reservation.getPrice(),
                reservation.getName(),
                reservation.getLast_name(),
                reservation.getTelephone(),
                reservation.getEmail(),
                reservation.getDeparture(),
                reservation.getArrival(),
                reservation.getDeparture_date(),
                reservation.getArrival_date()
        };
        long id = jdbcTemplate.update(sql, params);
        reservation.setId(id);
        return reservation;
    }

    @Override
    public Reservation retrieveReservation(long id) throws Exception {
        throw new UnsupportedOperationException("This method is not yet supported");
    }

    @Override
    public Reservation updateReservation(Reservation reservation) throws Exception {
        throw new UnsupportedOperationException("This method is not yet supported");
    }
}
