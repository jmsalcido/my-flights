package com.nearsoft.myflights.model.fs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class FSAppendixTest {
	
	private FSAppendix appendix;
	private Gson gson;
	
	@Before
	public void setUp() {
		gson = new Gson();
		appendix = new FSAppendix();
		List<FSAirline> airlines = new ArrayList<>();
		List<FSEquipment> equipments = new ArrayList<>();
		
		FSEquipment equipment = new FSEquipment();
		equipments.add(equipment);
		
		FSAirline airline = new FSAirline();
		airlines.add(airline);
		
		appendix.setAirlines(airlines);
		appendix.setEquipments(equipments);
	}
	
	@Test
	public void testToJsonFromGson() {
		String json = gson.toJson(appendix);
		FSAppendix test = gson.fromJson(json, FSAppendix.class);
		assertEquals(appendix, test);
	}
	
	@Test
	public void testParseFromJsonFile() throws IOException {
		String json = FSConnectionTest.createJsonString(getClass(), "flights-json.txt");
		FSAppendix test = gson.fromJson(json, FSAppendix.class);
		assertNotNull(test);
	}
	
}
