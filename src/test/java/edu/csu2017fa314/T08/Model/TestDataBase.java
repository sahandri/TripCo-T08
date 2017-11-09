package edu.csu2017fa314.T08.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

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
        assertEquals(d.getID(2),"7704" );
    }

    @Test
    public void testGetInfo() {
        assertEquals(d.getInfo("7704").get("id"),"7704" );
        assertEquals(d.getInfo("7704").get("name"),"Channel 7 Heliport" );
        assertEquals(d.getInfo("7704").get("latitude"),"39.72529983520508" );
        assertEquals(d.getInfo("7704").get("longitude"),"-104.98400115966797" );
        assertEquals(d.getInfo("7704").get("elevation"),"5300" );
        assertEquals(d.getInfo("7704").get("municipality"),"Denver" );
        assertEquals(d.getInfo("7704").get("home_link"),null );
        assertEquals(d.getInfo("7704").get("wikipedia_link"),null );

    }


    @Test
    public void testGetLongit() {
        assertEquals(d.getLongit("7704"),"-104.98400115966797" );
    }

    @Test
    public void testGetName() {
        assertEquals(d.getName("7704"),"Channel 7 Heliport" );
    }

    @Test
    public void testGetLatit() {
        assertEquals(d.getLatit("7704"),"39.72529983520508" );
    }


    @Test
    public void testGetTotal() {
        assertEquals(d.getTotal(),30 );
    }

    @Test
    public void testGetIndex() {
        assertEquals(d.getIndex("7704"),2 );
    }
}
