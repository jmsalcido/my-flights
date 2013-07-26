package com.nearsoft.myflights.dao;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nearsoft.myflights.model.fs.FSConnection;
import com.nearsoft.myflights.model.fs.FSConnectionTest;

public class FSFlightDaoTest {

    private FSFlightDao dao;

    @Before
    public void setUp() {
        dao = new FSFlightDao();
    }

    @Test
    public void testGetJson() {
        String json = dao.getJson("TUS", "JFK", "2013", "11", "21");
        Assert.assertNotNull(json); // Just wanted to be sure it didnt come
                                    // null.
    }

    @Test
    public void testGetJsonWithNullFields() {
        String json = dao.getJson(null, "JFK", null, null, null);
        Assert.assertNull(json); // Just wanted to be sure it did come null.
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testMapFromJson() throws Exception {
        String json = FSConnectionTest.createJsonString(getClass(),
                "flights-json.txt");
        Map<String, Object> map = dao.getMapFromJson(json);
        Assert.assertEquals(true, map.containsKey("appendix"));
    }

    @Test
    public void testFSConnectionFromJson() throws Exception {
        String json = FSConnectionTest.createJsonString(getClass(),
                "flights-json.txt");
        FSConnection connection = dao.getFSConnectionFromJson(json);
        Assert.assertNotNull(connection);
        Assert.assertNotNull(connection.getRequest());
        Assert.assertNotNull(connection.getAppendix());
        Assert.assertNotNull(connection.getFlights());
    }

}
