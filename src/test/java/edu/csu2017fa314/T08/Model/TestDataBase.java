package edu.csu2017fa314.T08.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;

public class TestDataBase {

    private DataBase d;

    @Before
    public void setUp() throws Exception
    {
        d = new DataBase();
        d.connect();
        d.getID("denver");
    }

    @Ignore
    @Test
    public void testConnect() {
        DataBase.connect();
        assertTrue(DataBase.isConnected());
    }

    @Test
    public void testGetID() {
        assertEquals("11CO",d.getID(2));
    }

    @Test
    public void testGetInfo() {
        HashMap info = d.getInfo("11CO");
        assertEquals("11CO", info.get("id"));
        assertEquals("Channel 7 Heliport", info.get("name"));
        assertEquals("39.72529983520508", info.get("latitude"));
        assertEquals("-104.98400115966797", info.get("longitude"));
        assertEquals("5300", info.get("elevation"));
        assertEquals("Denver", info.get("municipality"));
        assertEquals(null, info.get("home_link"));
        assertEquals(null, info.get("wikipedia_link"));

    }


    @Test
    public void testGetLongit() {
        assertEquals("-104.98400115966797", d.getLongit("11CO"));
    }

    @Test
    public void testGetName() {
        assertEquals("Channel 7 Heliport", d.getName("11CO"));
    }

    @Test
    public void testGetLatit() {
        assertEquals("39.72529983520508", d.getLatit("11CO"));
    }


    @Test
    public void testGetTotal() {
        assertEquals(30, d.getTotal());
    }

    @Test
    public void testGetIndex() {
        assertEquals(2, d.getIndex("11CO"));
    }
}
