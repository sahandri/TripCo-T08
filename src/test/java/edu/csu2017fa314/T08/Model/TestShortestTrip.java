package edu.csu2017fa314.T08.Model;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Before;
import org.junit.Test;

public class TestShortestTrip 
{
    private ShortestTrip st;
    private Destination d;

    @Before
    public void setUp() throws Exception 
    {
        st = new ShortestTrip();
        d = new Destination();

        URL url = this.getClass().getResource("/brews.csv"); 

        d.readFile(url.getPath());
    }

    @Test
    public void testShortestTrip() {
        ArrayList<String> stops = st.getShortestTrip();
        assertTrue(stops.size() > 0);
        assertTrue(stops.size() == d.getTotal() + 1);
    }

}
