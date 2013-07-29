package com.nearsoft.myflights.connectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nearsoft.myflights.connectors.FSFlightConnector;
import com.nearsoft.myflights.model.fs.FSConnection;
import com.nearsoft.myflights.util.JsonUtils;

public class FSFlightConnectorTest {

    private FSFlightConnector connector;

    @Before
    public void setUp() {
        connector = new FSFlightConnector();
    }

    @Test
    public void testFSConnectionFromJson() throws Exception {
        String json = JsonUtils.createJsonString(getClass(),
                "flights-json.txt");
        FSConnection connection = connector.getFSConnectionFromJson(json);
        Assert.assertNotNull(connection);
        Assert.assertNotNull(connection.getRequest());
        Assert.assertNotNull(connection.getAppendix());
        Assert.assertNotNull(connection.getFlights());
    }

}
