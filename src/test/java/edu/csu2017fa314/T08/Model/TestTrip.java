package edu.csu2017fa314.T08.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;

public class TestTrip {
    private Trip t;
    private static Destination d;
    private static URL url = TestTrip.class.getResource("/brews.csv");

    @BeforeClass
    public static void setUp() throws Exception {
        d = new Destination();

        d.readFile(url.getPath());
    }

    @Before
    public void clean() throws Exception {
        t = new Trip();
    }

    @Test
    public void testEnsureEmpty() {
        assertEquals(0, t.length());
        assertEquals(0, t.size());
    }

    @Test
    public void testTripStart() {
        t.add("acwatson");
        assertEquals(0, t.length());
        assertEquals(1, t.size());
    }

    @Test
    public void testSingleLeg() {
        t.add("rcox");
        t.add("mjgaffne");

        assertEquals(Model.getDistance("rcox", "mjgaffne",false), t.length());
        assertEquals(2, t.size());
    }

    @Test
    public void testShortTrip() {
        t.add("rcox");
        t.add("mjgaffne");
        t.add("rcox");

        assertEquals(Model.getDistance("rcox", "mjgaffne",false)*2, t.length());
        assertEquals(3, t.size());
        assertEquals("rcox", t.get(0));
        assertEquals("mjgaffne", t.get(1));
        assertEquals("rcox", t.get(2));
    }

    @Test
    public void testArrayConstructor() {
        ArrayList<String> testArr = new ArrayList<>();
        testArr.add("rcox");
        testArr.add("mjgaffne");
        testArr.add("sahandri");
        testArr.add("acwatson");
        testArr.add("rcox");
        t = new Trip(testArr);

        assertEquals("rcox", testArr.get(0));
        assertEquals("mjgaffne", testArr.get(1));
        assertEquals("sahandri", testArr.get(2));
        assertEquals("acwatson", testArr.get(3));
        assertEquals("rcox", testArr.get(4));
    }

    @Test
    public void testLongTrip() {

    }
}

