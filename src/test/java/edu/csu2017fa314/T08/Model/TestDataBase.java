package edu.csu2017fa314.T08.Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDataBase {

    @Test
    public void testConnect() {
        DataBase.connect();
        assertTrue(DataBase.isConnected());
    }
}
