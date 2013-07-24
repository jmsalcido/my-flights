package com.nearsoft.myflights.dao;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nearsoft.myflights.model.fs.FSConnection;

public class FSFlightDaoTest {
	
	private FSFlightDao dao;
	
	@Before
	public void setUp() {
		dao = new FSFlightDao();
	}
	
	@Test
	public void testGetJson() {
		String json = dao.getJson("TUS", "JFK", "2013", "11", "21");
		Assert.assertNotNull(json); // Just wanted to be sure it didnt come null.
	}
	
	@Test
	public void testGetJsonWithNullFields() {
		String json = dao.getJson(null, "JFK", null, null, null);
		Assert.assertNull(json); // Just wanted to be sure it did come null.
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMapFromJson() {
		String json = dao.getJson("TUS", "JFK", "2013", "11", "21");
		Map<String, Object> map = dao.getMapFromJson(json);
		Assert.assertEquals(true, map.containsKey("appendix"));
		System.out.println(map.get("appendix"));
	}
	
	@Test
	public void testFSConnectionFromJson() {
		String json = dao.getJson("TUS", "JFK", "2013", "11", "21");
		FSConnection connection = dao.getFSConnectionFromJson(json);
		Assert.assertNotNull(connection);
	}
	
}
