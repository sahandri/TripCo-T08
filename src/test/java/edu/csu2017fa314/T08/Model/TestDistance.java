package edu.csu2017fa314.T08.Model;
import static org.junit.Assert.*;

import org.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

public class TestDistance {

    private Distance d;

    @Before
    public void setUp() throws Exception 
    {
        d = new Distance();
    }
    
    @Test
    public void testDistanceKm() {
        assertEquals(d.distanceKm("50° 03' 59\" N", "5° 42' 53\" W", "58° 38' 38\" N", "3° 4' 12\" W"), 969, 0.000001);	
    }
	
    @Test
    public void testDistanceMi() {
        assertEquals(d.distanceMi("50° 03' 59\" N", "5° 42' 53\" W", "58° 38' 38\" N", "3° 4' 12\" W"), 602, 0.000001);
    }
	
    @Test
    //Method Used To Create JSON
    public void testDistance() {
	 
    }
	
    @Test
    //Method Used In Order To Calculate Greatest Circle Distance		
    public void testCalcGCD() {
		
    }
	
    @Test
    public void testDegreesToRadians() {
	//Test different possible strings 
	
	//Test Degree, Minute, Second Format	
	//Test format with Degrees, Minutes, and Seconds
	assertEquals(d.degreesToRadians("5° 30' 12\" N"),0.096051287,0.000000001);
	//Test format with Degrees, Minutes, and Seconds (Seconds has one decimal)
	assertEquals(d.degreesToRadians("5° 30' 12.5\" N"),0.096053711,0.000000001);
	//Test format with Degrees, Minutes, and Seconds (Seconds has more than one decimal)
	assertEquals(d.degreesToRadians("5° 30' 12.52\" N"),0.096053808,0.000000001);
	
	//Test Degree, Minute Format
	//Test format with just Degrees and Minutes
	assertEquals(d.degreesToRadians("10° 20' N"),0.180350689,0.000000001);
	//Test format with Degrees and Minutes (Minutes has one decimal)
	assertEquals(d.degreesToRadians("10° 20.8'  N"),0.1805834,0.0000001);
	//Test format with Degrees and Minutes (Minutes has more than one decimal)
	assertEquals(d.degreesToRadians("10° 20.83' N"),0.180592127,0.000000001);
	
	//Test Degree Format
	//Test format with just Degrees
	assertEquals(d.degreesToRadians("23° N"),0.401425728,0.000000001);
	//Test format with just Degrees (Degrees has one decimal)
	assertEquals(d.degreesToRadians("23.2° N"),0.404916386,0.000000001);
	//Test format with just Degrees (Degrees has more than one decimal)
	assertEquals(d.degreesToRadians("23.29° N"),0.406487183,0.000000001);

	//Test Input of a Double (negative or positive)
	//Test format with a Double (positive, no decimal)
	assertEquals(d.degreesToRadians("127"),2.21656815,0.00000001);
	//Test format with a Double (positive, one decimal)
	assertEquals(d.degreesToRadians("127.1"),2.218313479,0.000000001);
	//Test format with a Double (positive, more than one decimal)
	assertEquals(d.degreesToRadians("127.16"),2.219360677,0.000000001);
	//Test format with a Double (negative, no decimal)
	assertEquals(d.degreesToRadians("-127"),-2.21656815,0.00000001);
	//Test format with a Double (negative, one decimal)
	assertEquals(d.degreesToRadians("-127.1"),-2.218313479,0.000000001);
	//Test format with a Double (negative, more than one decimal)
	assertEquals(d.degreesToRadians("-127.16"),-2.219360677,0.000000001);
	

	//Test Directions
	//Test DMS format with North
	assertEquals(d.degreesToRadians("32° 18' 23.1\" N"),0.56385334,0.00000001);
	//Test DMS format with South
	assertEquals(d.degreesToRadians("32° 18' 23.1\" S"),-0.56385334,0.00000001);
	//Test DMS format with East
	assertEquals(d.degreesToRadians("32° 18' 23.1\" E"),0.56385334,0.00000001);
	//Test DMS format with West
	assertEquals(d.degreesToRadians("32° 18' 23.1\" W"),-0.56385334,0.00000001);
	//Test DM format with North
	assertEquals(d.degreesToRadians("32° 18' N"),0.563741348,0.000000001);
	//Test DM format with South
	assertEquals(d.degreesToRadians("32° 18' S"),-0.563741348,0.000000001);
	//Test DM format with East
	assertEquals(d.degreesToRadians("32° 18' E"),0.563741348,0.000000001);
	//Test DM format with West
	assertEquals(d.degreesToRadians("32° 18' W"),-0.563741348,0.000000001);
	//Test D format with North
	assertEquals(d.degreesToRadians("32° N"),0.558505361,0.000000001);
	//Test D format with South
	assertEquals(d.degreesToRadians("32° S"),-0.558505361,0.000000001);
	//Test D format with East
	assertEquals(d.degreesToRadians("32° E"),0.558505361,0.000000001);
	//Test D format with West
	assertEquals(d.degreesToRadians("32° W"),-0.558505361,0.000000001);	
    }
	
    @Test
    //Method Used to Convert a Decimal Value Representing Degrees to Radians
    public void testDecimalToRadians() {
	//Test positive double with no decimal
	assertEquals(d.decimalToRadians(90),1.570796327,0.000000001);
	//Test positive double with one decimal
	assertEquals(d.decimalToRadians(90.3),1.576032315,0.000000001);
	//Test positive double with more than one decimal
	assertEquals(d.decimalToRadians(90.34),1.576730446,0.000000001);
	//Test negative double with no decimal
	assertEquals(d.decimalToRadians(-90),-1.570796327,0.000000001);
	//Test negative double with one decimal
	assertEquals(d.decimalToRadians(-90.3),-1.576032315,0.000000001);
	//Test negative double with more than one decimal
	assertEquals(d.decimalToRadians(-90.34),-1.576730446,0.000000001);
	

    }
	
    //Method Used to Convert a String Representing Either Latitude or Longitude Into a Decimal 
    @Test
    public void testDegreesToDecimal() {
	//Test different possible strings 
	
	//Test Degree, Minute, Second Format	
	//Test format with Degrees, Minutes, and Seconds
	assertEquals(d.degreesToDecimal("5° 30' 12\" N"),5.50333333,0.00000001);
	//Test format with Degrees, Minutes, and Seconds (Seconds has one decimal)
	assertEquals(d.degreesToDecimal("5° 30' 12.5\" N"),5.50347222,0.00000001);
	//Test format with Degrees, Minutes, and Seconds (Seconds has more than one decimal)
	assertEquals(d.degreesToDecimal("5° 30' 12.52\" N"),5.503477778,0.000000001);
	
	//Test Degree, Minute Format
	//Test format with just Degrees and Minutes
	assertEquals(d.degreesToDecimal("10° 20' N"),10.33333333,0.00000001);
	//Test format with Degrees and Minutes (Minutes has one decimal)
	assertEquals(d.degreesToDecimal("10° 20.8'  N"),10.34666667,0.00000001);
	//Test format with Degrees and Minutes (Minutes has more than one decimal)
	assertEquals(d.degreesToDecimal("10° 20.83' N"),10.34716667,0.00000001);
	
	//Test Degree Format
	//Test format with just Degrees
	assertEquals(d.degreesToDecimal("23° N"),23.0,0.000);
	//Test format with just Degrees (Degrees has one decimal)
	assertEquals(d.degreesToDecimal("23.2° N"),23.2,0.000);
	//Test format with just Degrees (Degrees has more than one decimal)
	assertEquals(d.degreesToDecimal("23.29° N"),23.29,0.000);

	//Test Input of a Double (negative or positive)
	//Test format with a Double (positive, no decimal)
	assertEquals(d.degreesToDecimal("127"),127.0,0.000);
	//Test format with a Double (positive, one decimal)
	assertEquals(d.degreesToDecimal("127.1"),127.1,0.000);
	//Test format with a Double (positive, more than one decimal)
	assertEquals(d.degreesToDecimal("127.16"),127.16,0.000);
	//Test format with a Double (negative, no decimal)
	assertEquals(d.degreesToDecimal("-127"),-127.0,0.000);
	//Test format with a Double (negative, one decimal)
	assertEquals(d.degreesToDecimal("-127.1"),-127.1,0.000);
	//Test format with a Double (negative, more than one decimal)
	assertEquals(d.degreesToDecimal("-127.16"),-127.16,0.000);
	

	//Test Directions
	//Test DMS format with North
	assertEquals(d.degreesToDecimal("32° 18' 23.1\" N"),32.30641667,0.00000001);
	//Test DMS format with South
	assertEquals(d.degreesToDecimal("32° 18' 23.1\" S"),-32.30641667,0.00000001);
	//Test DMS format with East
	assertEquals(d.degreesToDecimal("32° 18' 23.1\" E"),32.30641667,0.00000001);
	//Test DMS format with West
	assertEquals(d.degreesToDecimal("32° 18' 23.1\" W"),-32.30641667,0.00000001);
	//Test DM format with North
	assertEquals(d.degreesToDecimal("32° 18' N"),32.3,0.00);
	//Test DM format with South
	assertEquals(d.degreesToDecimal("32° 18' S"),-32.3,0.00);
	//Test DM format with East
	assertEquals(d.degreesToDecimal("32° 18' E"),32.3,0.00);
	//Test DM format with West
	assertEquals(d.degreesToDecimal("32° 18' W"),-32.3,0.00);
	//Test D format with North
	assertEquals(d.degreesToDecimal("32° N"),32.0,0.00);
	//Test D format with South
	assertEquals(d.degreesToDecimal("32° S"),-32.0,0.00);
	//Test D format with East
	assertEquals(d.degreesToDecimal("32° E"),32.0,0.00);
	//Test D format with West
	assertEquals(d.degreesToDecimal("32° W"),-32.0,0.00);
	
	
    }
}
