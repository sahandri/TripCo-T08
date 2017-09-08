package edu.csu2017fa314.T08.View;
import static org.junit.Assert.*;

import org.json.simple.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

public class TestItinerary {

    private Itinerary i;

    @Before
    public void setUp() throws Exception 
    {
        i = new Itinerary();
    }

    @Ignore("Completing P1 implementation made this test invalid.")
    @Test 
    public void testCreateLeg() 
    {
        JSONObject leg = i.createLeg("TEST_START", "TEST_END");       
        System.out.println(leg);
        assertNotNull(leg);
    }
}
