package edu.csu2017fa314.T08.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;

public class TestTripManager {

    @BeforeClass
    public static void setUp() throws Exception {
    }

    @Ignore
    @Test
    public void testShortest() {
        DataBase.connect();
        System.out.println("Connected to DB");
        Model.setUp();
        System.out.println("Setup Model");
        TripManager.buildTripList();
        Trip t = TripManager.shortest();

        System.out.printf("Distance is: %d\n", t.length());
        System.out.printf("Stops: %d\n", t.size());

        //assertTrue(t.length() < 4000);
        //assertTrue(t.length() > 3000);
        assertEquals(t.get(0),t.get(t.size()-1));
        assertEquals(DataBase.getTotal()+1,t.size());
    }

}
