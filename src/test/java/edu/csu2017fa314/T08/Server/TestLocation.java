package edu.csu2017fa314.T08.Server;
import org.junit.Before;
import org.junit.Test;

public class TestLocation {
    Location l;
    @Before
    public void setup() {
        l = new Location("1","abc");
    }

    @Test
    public void testTostring(){
        l.toString();
    }
}
