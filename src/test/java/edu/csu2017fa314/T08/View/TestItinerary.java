package edu.csu2017fa314.T08.View;
import static org.junit.Assert.*;

import edu.csu2017fa314.T08.Model.DataBase;
import edu.csu2017fa314.T08.Model.Model;
import edu.csu2017fa314.T08.Model.Trip;
import edu.csu2017fa314.T08.Model.TripManager;
import org.json.JSONObject;
import org.json.JSONArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;



public class TestItinerary {

    @Before
    public void setUp() throws Exception 
    {
        DataBase.connect();
        System.out.println("Connected to DB");
        System.out.println("Setup Model");
    }


    @Test
    public void testItinerary() {
        JSONArray arr = Itinerary.createJSON("denver");
        //System.out.println(arr.toString());
    }

}
