package edu.csu2017fa314.T08.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;

public class TestTripManager {
    private Trip t;
    private static Destination d;
    private static URL url = TestTrip.class.getResource("/sprint2airport.csv");

    @BeforeClass
    public static void setUp() throws Exception {
        d = new Destination();

        d.readFile(url.getPath());
        Model.setUp();
    }

    @Test
    public void testShortest() {
        Trip t = TripManager.shortest();
        assertTrue(t.length() < 4000);
    }

}
