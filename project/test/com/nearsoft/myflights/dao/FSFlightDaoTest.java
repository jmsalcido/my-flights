package com.nearsoft.myflights.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nearsoft.myflights.model.fs.FSConnection;
import com.nearsoft.myflights.util.JsonUtils;

public class FSFlightDaoTest {

    private FSFlightDao dao;

    @Before
    public void setUp() {
        dao = new FSFlightDao();
    }

    @Test
    public void testGetJson() throws Exception {
        String json = dao.getJson("TUS", "JFK", "2013", "11", "21");
        Assert.assertNotNull(json);
    }

    @Test
    public void testGetJsonWithNullFields() throws Exception {
        String json = dao.getJson(null, "JFK", null, null, null);
        Assert.assertNull(json);
    }

    @Test
    public void testFSConnectionFromJson() throws Exception {
        String json = JsonUtils.createJsonString(getClass(),
                "flights-json.txt");
        FSConnection connection = dao.getFSConnectionFromJson(json);
        Assert.assertNotNull(connection);
        Assert.assertNotNull(connection.getRequest());
        Assert.assertNotNull(connection.getAppendix());
        Assert.assertNotNull(connection.getFlights());
    }

}
