package edu.csu2017fa314.T08.Model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;


public class TestModel 
{
    private Model m;

    @Before
    public void setUp() throws Exception 
    {
        m = new Model();
        DataBase.connect();
        m.search("denver");
    }

    @Test
    public void testGetInfo() {
        HashMap info = m.getInfo("11CO");
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
    public void testGetDistance() {
        assertEquals(182, m.getDistance("FZCP", "FZCR", 0));
    }

    @Test
    public void testGetID() {
        assertEquals("11CO",m.getID(2));
    }

}
