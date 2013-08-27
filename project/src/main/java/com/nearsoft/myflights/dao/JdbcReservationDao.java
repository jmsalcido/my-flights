package com.nearsoft.myflights.dao;

import com.nearsoft.myflights.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        final String sql = "INSERT INTO reservations VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
                reservation.getArrival_date()
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
    public Reservation retrieveReservation(long id) throws Exception {
        throw new UnsupportedOperationException("This method is not yet supported");
    }

    @Override
    public Reservation updateReservation(Reservation reservation) throws Exception {
        throw new UnsupportedOperationException("This method is not yet supported");
    }
}
