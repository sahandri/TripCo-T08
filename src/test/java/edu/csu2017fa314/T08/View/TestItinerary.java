package edu.csu2017fa314.T08.View;
import static org.junit.Assert.*;

import org.json.simple.JSONObject;

import org.junit.Before;
import org.junit.Test;

public class TestItinerary {

    private Itinerary i;

    @Before
    public void setUp() throws Exception 
    {
        i = new Itinerary();
    }

    @Test 
    public void testCreateLeg() 
    {
        JSONObject leg = i.CreateLeg("TEST_START", "TEST_END");       
        System.out.println(leg);
        assertNotNull(leg);
    }
}
