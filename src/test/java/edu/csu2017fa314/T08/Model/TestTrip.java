package edu.csu2017fa314.T08.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestTrip {
    private Trip t;

    @Before
    public void setUp() throws Exception {
        t = new Trip();
    }

    @Test
    public void testEnsureEmpty() {
        assertEquals(0, t.length());
        assertEquals(0, t.size());
    }

    @Test
    public void testTripStart() {
        t.addStop("ABC");
        assertEquals(0, t.length());
        assertEquals(1, t.size());
    }

    @Ignore("Need to figure out what setup is needed.")
    @Test
    public void testSingleLeg() {
        t.addStop("KDRI");
        t.addStop("KDNR");
    }

    @Ignore("Need to figure out what setup is needed.")
    @Test
    public void testShortTrips() {

    }

    @Ignore("Need to figure out what setup is needed.")
    @Test
    public void testLongTrip() {

    }
}

