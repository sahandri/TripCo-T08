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
        assertEquals(d.getID(1),"KDEN" );
    }

    @Test
    public void testGetInfo() {
        assertEquals(d.getInfo("KDEN").get("id"),"KDEN" );
        assertEquals(d.getInfo("KDEN").get("name"),"Denver International Airport" );
        assertEquals(d.getInfo("KDEN").get("latitude"),"39.861698150635" );
        assertEquals(d.getInfo("KDEN").get("longitude"),"-104.672996521" );
        assertEquals(d.getInfo("KDEN").get("elevation"),"5431" );
        assertEquals(d.getInfo("KDEN").get("municipality"),"Denver" );
        assertEquals(d.getInfo("KDEN").get("home_link"),"http://www.flydenver.com/" );
        assertEquals(d.getInfo("KDEN").get("wikipedia_link"),"http://en.wikipedia.org/wiki/Denver_International_Airport" );

    }


    @Test
    public void testGetLongit() {
        assertEquals(d.getLongit("KDEN"),"-104.672996521" );
    }

    @Test
    public void testGetName() {
        assertEquals(d.getName("KDEN"),"Denver International Airport" );
    }

    @Test
    public void testGetLatit() {
        assertEquals(d.getLatit("KDEN"),"39.861698150635" );
    }


    @Test
    public void testGetTotal() {
        assertEquals(d.getTotal(),26 );
    }

    @Test
    public void testGetIndex() {
        assertEquals(d.getIndex("KDEN"),1 );
    }
}
