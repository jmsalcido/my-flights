package com.nearsoft.myflights.model.fs;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.nearsoft.myflights.util.JsonUtils;

public class FSConnectionTest {

    private Gson gson;
    private String json;

    @Before
    public void setUp() throws IOException {
        gson = new Gson();
        json = JsonUtils.createString(this.getClass(),
                "flights-json.txt");
    }

    @Test
    public void testParseFromJSONFile() {
        FSConnection conn = gson.fromJson(json, FSConnection.class);
        Assert.assertNotNull(conn);
    }

}
