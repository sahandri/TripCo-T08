package edu.csu2017fa314.T08.Model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestModel 
{
    private Model m;

    @Before
    public void setUp() throws Exception 
    {
        m = new Model();
    }

    @Test 
    public void testGetNumbers() 
    {
        assertArrayEquals(m.getNumbers(), new int[] {0, 1, 2, 3, 4, 5});
    }

}
