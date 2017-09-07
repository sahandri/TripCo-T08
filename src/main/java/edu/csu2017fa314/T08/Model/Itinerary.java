package edu.csu2017fa314.T08.Model;
import java.lang.Math;

public class Itinerary {
	
	//Static Global Variables 
	static double r_earth_km = 6371.0088; //kilometers
	static double r_earth_mi = 3958.7613; //miles
	
	//Method Used To Create JSON
	public static double distance(String id_1, String id_2) {
		double distance  = 0;
		return distance;
	}
	
	//Method Used In Order To Calculate Greatest Circle Distance		
	public static double calcGCD(double phi_1, double lambda_1, double phi_2, double lambda_2) {
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
		//Final Distance Computation
		return r_earth_km * centralAngle;
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
		int numStart = 0;
		int numEnd = 0;
		int newInt = 0;
		String deg = "0";
		String min = "0";
		String sec = "0";
		for(int i = 0; i < coordinate.length(); i++) {
			//System.out.println("i = " + i + " char: " + coordinate.charAt(i));
			if((coordinate.charAt(i) >= '0' && coordinate.charAt(i) <= '9') | (coordinate.charAt(i) == '.') | (coordinate.charAt(i) == '-')) {
				if(newInt == 0) {
					numStart = i;
				}
				newInt = 1;
			}
			else { 
				if(newInt == 1) {
					numEnd = i;
					if(deg == "0") deg = coordinate.substring(numStart,numEnd);
					else if(min == "0") min = coordinate.substring(numStart, numEnd);
					else sec = coordinate.substring(numStart, numEnd);
				}
				newInt = 0;
			}
		}
		if(deg == "0") deg = coordinate;
		//System.out.println("Degrees: " + deg + ", Minutes: " + min + ", Seconds: " + sec );
		
		
		return Double.parseDouble(deg) + (Double.parseDouble(min)/60) + (Double.parseDouble(sec)/(60*60)) ;
	}
}
