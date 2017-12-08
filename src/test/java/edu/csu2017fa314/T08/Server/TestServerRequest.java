package edu.csu2017fa314.T08.Server;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestServerRequest {
    ServerRequest s;
    ServerRequest as;
    String[] array = new String[] {"a", "b"};
    @Before
    public void setup(){
        as = new ServerRequest("x" , array);
        s = new ServerRequest("x", "y");
        s.setQuery("a");
        s.setDescription("b");
    }

    @Test
    public void testGetRequest(){
        assertEquals("a",s.getRequest());
    }

    @Test
    public void testGetDescription(){
        assertEquals("b",s.getDescription());
    }

    @Test
    public void testToString(){
        s.toString();
    }
}
