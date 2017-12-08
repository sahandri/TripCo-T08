package edu.csu2017fa314.T08.Server;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestServerRequest {
    ServerRequest s;
    ServerRequest as;
    @Before
    public void setup(){
        s = new ServerRequest("x", "y" , 0);
        s.setQuery("a");
        s.setDescription("b");
	s.setUnits(0);
    }

    @Test
    public void testGetRequest(){
        assertEquals("a",s.getRequest());
    }

    @Test
    public void testGetDescription(){
        assertEquals("b",s.getDescription());
    }

    @Test
    public void testGetUnits(){
        assertEquals(0,s.getUnits());
    }	

    @Test
    public void testToString(){
        s.toString();
    }
}
