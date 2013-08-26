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
    public Reservation saveReservation(Reservation reservation) {

        return null;
    }

    @Override
    public Reservation retrieveReservation(long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
