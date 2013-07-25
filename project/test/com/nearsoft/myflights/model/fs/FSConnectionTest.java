package com.nearsoft.myflights.model.fs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class FSConnectionTest {

	public static String createJsonString(Class<?> clazz, String fileName) throws IOException {
		InputStream stream = clazz.getClassLoader().getResourceAsStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
	
	private FSConnection conn;
	private Gson gson;
	private String json;
	
	@Before
	public void setUp() throws IOException {
		gson = new Gson();
		json = FSConnectionTest.createJsonString(this.getClass(), "flights-json.txt");
	}
	
	@Test
	public void testParseFromJSONFile() {
		conn = gson.fromJson(json, FSConnection.class);
		Assert.assertNotNull(conn);
	}
	
}
