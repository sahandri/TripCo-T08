package edu.csu2017fa314.T08.Model;

import static org.junit.Assert.*;

import edu.csu2017fa314.T08.View.Itinerary;
import edu.csu2017fa314.T08.View.makeSvg;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;

public class TestTripManager {

    @BeforeClass
    public static void setUp() throws Exception {
    }

    @Test
    public void testShortest() {
        DataBase.connect();

        ArrayList<String> arr = Model.search("denver");

        Model.shortestTrip(arr, 1);
        Trip t = TripManager.trips.get(0);

        System.out.printf("Distance is: %d\n", t.length());
        System.out.printf("Stops: %d\n", t.size());

        //assertTrue(t.length() < 4000);
        //assertTrue(t.length() > 3000);
        assertEquals(t.get(0),t.get(t.size()-1));
        //assertEquals(DataBase.getTotal()+1,t.size());
        Model.shortestTrip(new ArrayList<>(arr.subList(0,10)),2);
        Trip t2 = TripManager.trips.get(0);
        System.out.printf("Shorter distance is: %d\n", t2.length());
        System.out.printf("Less stops: %d\n", t2.size());
        assertTrue(t.size() > t2.size());
    }

}
