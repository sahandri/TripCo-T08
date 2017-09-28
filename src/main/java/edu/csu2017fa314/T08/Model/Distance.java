package edu.csu2017fa314.T08.Model;
import java.lang.Math;

public class Distance {
	
	//Static Global Variables 
	
	//Radius of the Earth
	static double r_earth_km = 6371.0088; //kilometers
	static double r_earth_mi = 3958.7613; //miles
    static double radius = 0.0;

    public static int distanceKm(String id_1, String id_2) {
        radius = r_earth_km;
        return distance(id_1, id_2);
    }

    public static int distanceMi(String id_1, String id_2) {
        radius = r_earth_mi;
        return distance(id_1, id_2);
    }
	
	//Method Used To Create JSON
	public static int distance(String id_1, String id_2) {
		//Create doubles for latitude and longitude given each ID
        String lat1 = Destination.getLatit(id_1);
        String lon1 = Destination.getLongit(id_1);
        String lat2 = Destination.getLatit(id_2);
        String lon2 = Destination.getLongit(id_2);

		double latitude1 = degreesToRadians(lat1);
		double longitude1 = degreesToRadians(lon1);
		double latitude2 = degreesToRadians(lat2);
		double longitude2 = degreesToRadians(lon2);

		
		//Use given latitude and longitudes in order to compute Greatest Circle Distance between two points
		return calcGCD(latitude1,longitude1,latitude2,longitude2);
	}
	
	//Method Used In Order To Calculate Greatest Circle Distance		
	public static int calcGCD(double phi_1, double lambda_1, double phi_2, double lambda_2) {
		//Setting Up Variables Within Equation
		double delta_phi = Math.abs(phi_1 - phi_2);
		double delta_lambda = Math.abs(lambda_1 - lambda_2);
		double cosPhi_1 = Math.cos(phi_1);
		double cosPhi_2 = Math.cos(phi_2);
		double sinPhi_1 = Math.sin(phi_1);
		double sinPhi_2 = Math.sin(phi_2);
		double cosDeltaLambda = Math.cos(delta_lambda);
		double sinDeltaLambda = Math.sin(delta_lambda);
		//Actual Equation
		double top = Math.sqrt(Math.pow((cosPhi_2 * sinDeltaLambda),2) + Math.pow(((cosPhi_1 * sinPhi_2) - (sinPhi_1 * cosPhi_2 * cosDeltaLambda)), 2)); 
		double bottom = (sinPhi_1 * sinPhi_2) + (cosPhi_1 * cosPhi_2 * cosDeltaLambda);
		double centralAngle = Math.atan2(top, bottom);
		//Final Distance Computation (rounds from double to float, and then from float to int)
		return Math.round(Math.round(radius * centralAngle));
	}
	
	//Method Used to Convert Degree Format To Radians
	public static double degreesToRadians(String coordinates) {
		return decimalToRadians(degreesToDecimal(coordinates));
	}
	
	//Method Used to Convert a Decimal Value Representing Degrees to Radians
	public static double decimalToRadians(double decimal) {
		//Convert from degrees (decimal) to radians
		return decimal * (Math.PI / 180);
	}
	
	//Method Used to Convert a String Representing Either Latitude or Longitude Into a Decimal 
	public static double degreesToDecimal(String coordinate) {
		//Takes coordinate in form of a String -> 40° 42' 51.6708" N
		//Define ints used to track the beginning and end of a coordinate value
		int numStart = 0;
		int numEnd = 0;
		//Define int to keep track of whether the for loop is reading a new coordinate value
		int newInt = 0;
		//Strings storing the degree, minute, and second values
		String deg = "0";
		String min = "0";
		String sec = "0";
		int neg = 1;
		//For loop used to run through entire coordinate string and extract values
		for(int i = 0; i < coordinate.length(); i++) {
			//System.out.println("i = " + i + " char: " + coordinate.charAt(i));
			//If the char is between zero and nine, a deciaml, or a minus
			if((coordinate.charAt(i) >= '0' && coordinate.charAt(i) <= '9') | (coordinate.charAt(i) == '.') | (coordinate.charAt(i) == '-')) {
				//if the char is between zero and nine, a deciaml, or a minus and newInt is zero, then this is a new value
				if(newInt == 0) {
					//Because it is a new value (newInt = 0), set numStart equal to the current value for i
					numStart = i;
				}
				//Change newInt to 1 to show that the next value is not the start of an int
				newInt = 1;
			}
			else if((coordinate.charAt(i) == 'S') | (coordinate.charAt(i) == 'W')){
				neg = -1;
				System.out.println("The coordinate is either West or South so negs value is: " + neg);
			}
			//If the char is not between zero and nine, a deciaml, or a minus then we have reached the end of a value, store value into either degree, minute, or second
			else { 
				if(newInt == 1) {
					numEnd = i;
					//If degree is 0 then it has not been set, store new value into degree
					if(deg == "0") deg = coordinate.substring(numStart,numEnd);
					//If minute is 0 but degree has been set then minute has not been set, store new value into minute
					else if(min == "0") min = coordinate.substring(numStart, numEnd);
					//Otherwise, store the new value into second
					else sec = coordinate.substring(numStart, numEnd);
				}
				//Reset newInt to 0 to indicate the next value between zero and nine, or a decimal, or a minus sign will start a new int
				newInt = 0;
			}
		}
		//If we loop through the whole coordinate string without finding a non integer value (0-9, '.' , '-') assign coordinate to degree
		if(deg == "0") deg = coordinate;
		//System.out.println("Degrees: " + deg + ", Minutes: " + min + ", Seconds: " + sec );
		
		//Return the computation of decimal degrees by parsing each string to a double and using the formula below
		System.out.println("The converted coordinate is: " + ((Double.parseDouble(deg) + (Double.parseDouble(min)/60) + (Double.parseDouble(sec)/(60*60))) * neg));
		return ((Double.parseDouble(deg) + (Double.parseDouble(min)/60) + (Double.parseDouble(sec)/(60*60))) * neg);
	}
}
