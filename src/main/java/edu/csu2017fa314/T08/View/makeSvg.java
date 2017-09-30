package edu.csu2017fa314.T08.View;

import edu.csu2017fa314.T08.Model.Model;
import edu.csu2017fa314.T08.Model.Distance;
import edu.csu2017fa314.T08.Model.Destination;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class makeSvg {
	//Import array of destinations ids
	//Pass array of destination ids into constructLineSVG
	//SVG file looks up coordinate data for each destination
	//constructLineSVG calls coordinate converter from Lat/Long to local coordinates in file
	//constructSVG inserts line for each leg of trip and outputs svg file with total trip
	
	public static String xmlDec() {
		return "<?xml version=\"1.0\"?>";
	}
	
	public static String svgSizeTag(){
		return "<svg width=\"1280\" height=\"1024\" xmlns=\"http://www.w3.org/2000/svg\">";
	}
	
	public static String svgTag(){
		return "</svg>";
	}

	public static String gTag1(){
		return"<g>";
	}
	
	public static String gTag2(){
		return"</g>";
	}
	
	public static String titleTag(String title){
		return "<title>"+ title + "</title>";
	}
	
	public static String commentTag(String comment){
		return "<!-- " + comment + "-->";
	}
	
	public static String drawLine(String id, double x1, double y1, double x2, double y2, double strokeWidth, String color) {
		return "<line id=\"" + id + "\" y2=\"" + y2 + "\" x2=\"" + x2 + "\" y1=\"" + y1 + "\" x1=\"" + x1 + "\" stroke-width=\"" + strokeWidth + "\" stroke=\""+ color + "\"/>";
	}
	
	public static String drawStartPoint(double cx, double cy, double r, String strokeColor, String fill, double strokeWidth) {
		return "<circle cx=\"" + cx + "\" cy=\"" + cy + "\" r=\"" + r + "\" stroke=\"" + strokeColor + "\" fill=\"" + fill + "\" stroke-width=\"" + strokeWidth + "\"/>";
	}
	
	public static String addText(double x, double y, String fontFamily, String size, String color, String actualText) {
		return "<text x=\"" + x + "\" y=\"" + y + "\" font-family=\"" + fontFamily + "\" font-size=\"" + size + "\" fill=\"" + color + "\">" + actualText + "</text>";
	}
	
	public static double convertLatitude(double latitude){
		return ((974 - 50) * ((41 - latitude) / (41 - 37))) + 50;
	}
	
	public static double convertLongitude(double longitude){
		return ((1230 - 50) * ((-109 - longitude) / (-109 + 102))) + 50;
	}
	
	public static void createTripFile(String filename) {
		try{
		//Create and open a writer for a new .svg file
			BufferedWriter ob = new BufferedWriter(new FileWriter(filename));
			//Writing to file...
	        ob.write(xmlDec());
	        ob.write("\n" + svgSizeTag());
	        ob.write("\n" + gTag1());
	        ob.write("\n" + titleTag("Title"));
	        ob.write(commentTag("\nDrawing the state borders!"));
		//Create Key
		ob.write("\n" + drawStartPoint(30, 30, 5, "red", "red", 8));
		ob.write("\n" + drawStartPoint(200, 30, 5, "blue", "blue", 1));
		ob.write("\n" + addText(40, 35, "sans-serif", "20px", "red", " = Start/End"));
		ob.write("\n" + addText(210, 35, "sans-serif", "20px", "blue", " = Other Destinations Along The Way"));
	        //Draw State Borders
	        ob.write("\n" + drawLine("north", 50, 50, 1230, 50, 5, "#000000"));
	        ob.write("\n" + drawLine("north", 1230, 50, 1230, 974, 5, "#000000"));
	        ob.write("\n" + drawLine("north", 1230, 974, 50, 974, 5, "#000000"));
	        ob.write("\n" + drawLine("north", 50, 974, 50, 50, 5, "#000000"));
	        //Draw trip
			ob.write(commentTag("\nDrawing the trip path!"));
			//Test Conversions
			//ob.write("\n" + drawStartPoint(convertLongitude(-105.5), convertLatitude(39), 5, "red", "transparent", 20));
			//ob.write("\n" + drawStartPoint((edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(Destination.getLongit(1))) * -1, edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(Destination.getLatit(1)), 5, "red", "transparent", 20));
			//ob.write("\n" + drawStartPoint(convertLongitude((edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(Destination.getLongit(1))) * -1), convertLatitude(edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(Destination.getLatit(1))), 5, "red", "transparent", 20));
			double startX = 0;
			double startY = 0;
			double finishX = 0;
			double finishY = 0;
			ArrayList<String> order = Model.shortestTrip();
			for(int i = 0; i < order.size(); i++) {
				if(i == 0) {
					//Sets first destination in trip to the destination at i = 0
					startX = convertLongitude((edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(Destination.getLongit(order.get(i)))));
					startY = convertLatitude(edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(Destination.getLatit(order.get(i))));
					System.out.println("Getting For ID: " + order.get(i) + " Longitude = " + startX + " Latitude = " + startY);
					//For this part of the loop, sets the finish destination to the very last destination of the trip at i = Destination.getTotal() - 1					
					//finishX = convertLongitude((edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(Destination.getLongit(order.size() - 1))) * -1);
					//finishY = convertLatitude(edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(Destination.getLatit(order.size() - 1)));

					//Draws the starting point given start data					
					ob.write("\n" + drawStartPoint(startX, startY, 5, "red", "red", 8));
					//Draws the line from the starting point to the very last destination					
					//ob.write("\n" + drawLine("north", startX, startY, finishX, finishY, 5, "#000000"));
				}
				else {
					finishX = startX;
					finishY = startY;
					startX = convertLongitude((edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(Destination.getLongit(order.get(i)))));
					startY = convertLatitude(edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(Destination.getLatit(order.get(i))));
					System.out.println("Getting For ID: " + order.get(i) + " Longitude = " + startX + " Latitude = " + startY);
					ob.write("\n" + drawStartPoint(startX, startY, 5, "blue", "blue", 1));
					ob.write("\n" + drawLine("path", startX, startY, finishX, finishY, 2, "#000000"));
					//ob.write("\n" + addText(startX, startY - 5, "sans-serif", "20px", "red", "" + i));
				}
			}
			ob.write("\n" + gTag2());
			ob.write("\n" + svgTag());
	        ob.close();
		} catch (IOException e) {
		   // do something
		}
	}
}
