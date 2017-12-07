package edu.csu2017fa314.T08.View;

import edu.csu2017fa314.T08.Model.Model;
import edu.csu2017fa314.T08.Model.Distance;
import edu.csu2017fa314.T08.Model.Destination;
import edu.csu2017fa314.T08.Model.DataBase;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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
	
	//Method returns String that represents SVG tag and the size of the svg
	public static String svgSizeTag(){
		return "<svg width=\"1280\" height=\"1024\" xmlns=\"http://www.w3.org/2000/svg\">";
	}
	
	//Method returns String that represents SVG tag
	public static String svgTag(){
		return "</svg>";
	}

	//Method returns String that represents the gTag to be used at the beginning of SVG
	public static String gTag1(){
		return"<g>";
	}
	
	//Method returns String that represents the gTag to be used at the end of SVG
	public static String gTag2(){
		return"</g>";
	}
	
	//Method returns String that represents the title to be used in the SVG
	public static String titleTag(String title){
		return "<title>"+ title + "</title>";
	}
	
	//Method used to create comments within an SVG
	public static String commentTag(String comment){
		return "<!-- " + comment + "-->";
	}
	
	//Method used to draw a line within an SVG
	public static String drawLine(String id, double x1, double y1, double x2, double y2, double strokeWidth, String color) {
		return "<line id=\"" + id + "\" y2=\"" + y2 + "\" x2=\"" + x2 + "\" y1=\"" + y1 + "\" x1=\"" + x1 + "\" stroke-width=\"" + strokeWidth + "\" stroke=\""+ color + "\"/>";
	}

	//Method used to draw a circle within an SVG	
	public static String drawStartPoint(double cx, double cy, double r, String strokeColor, String fill, double strokeWidth) {
		return "<circle cx=\"" + cx + "\" cy=\"" + cy + "\" r=\"" + r + "\" stroke=\"" + strokeColor + "\" fill=\"" + fill + "\" stroke-width=\"" + strokeWidth + "\"/>";
	}
	
	//Method used to draw text within an SVG
	public static String addText(double x, double y, String fontFamily, String size, String color, String actualText) {
		return "<text x=\"" + x + "\" y=\"" + y + "\" font-family=\"" + fontFamily + "\" font-size=\"" + size + "\" fill=\"" + color + "\">" + actualText + "</text>";
	}

	//New Method used to convert global latitude to local latitude on previous SVG
	public static double convertLatitude(double latitude, double y1, double y2){
		return ((y2 - y1) * ((85 - latitude) / (85 - (-85)))) + y1;
	}
	
	//New Method used to convert global longitude to local longitude on previous SVG
	public static double convertLongitude(double longitude, double x1, double x2){
		return ((x2 - x1) * ((-180 - longitude) / (-180 - 180))) + x1;
	}

	//Set up key
	public static String setupKey(){
		String key = "";
		//Create Key
		key += ("\n" + drawStartPoint(80, 380, 5, "red", "red", 8));
		key += ("\n"  + drawStartPoint(265, 382, 5, "blue", "blue", 1));
		key += ("\n" + addText(90, 390, "sans-serif", "20px", "red", " = Start/End"));
		key += ("\n" + addText(270, 390, "sans-serif", "20px", "blue", " = Other Destinations Along The Way"));
		//Draw Title
		key += ("\n" + addText(180, 360, "sans-serif", "30px", "black", "Showing Your World Trip!"));
		return key;
	}

	public static String drawInterpolationLines(double largerX, double largerY, double smallerX, double smallerY){
		String newLines = "";
		newLines += ("\n" + drawLine("path", largerX, largerY, 800, (((800-largerX)/((800-largerX)+smallerX)*(smallerY-largerY))+largerY), 1, "#000000"));
		newLines += ("\n" + drawLine("path", 0, (((800-largerX)/((800-largerX)+smallerX)*(smallerY-largerY))+largerY), smallerX, smallerY, 1, "#000000"));
		return newLines;
	}

	public static double getCoordinate(String s, int selector){
		//If selector = 0 return latitude else return longitude		
		double coordinate;
		if(selector == 0){
			coordinate = convertLongitude((edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(DataBase.getLongit(s))),0,800);
		}
		else {
			coordinate = convertLatitude((edu.csu2017fa314.T08.Model.Distance.degreesToDecimal(DataBase.getLatit(s))),0,400);
		}
		return coordinate;
	}

	public static String completeSvg(ArrayList<String> order){
		String allDestLines = "";
		double startX = 0;
		double startY = 0;
		double finishX = 0;
		double finishY = 0;		
		for(int i = 0; i < order.size(); i++) {
			if(i == 0) {
				//Sets first destination in trip to the destination at i = 0
				startX = getCoordinate(order.get(i),0);
				startY = getCoordinate(order.get(i),1);			
				//Draws the starting point given start data					
				allDestLines += ("\n" + drawStartPoint(startX, startY, 1, "red", "red", 1));					
			}
			else {
				finishX = startX;
				finishY = startY;
				startX = getCoordinate(order.get(i),0);
				startY = getCoordinate(order.get(i),1);
				allDestLines += ("\n" + drawStartPoint(startX, startY, 1, "blue", "blue", 1));
				if((startX - finishX) > 400){						
					allDestLines += drawInterpolationLines(startX, startY, finishX, finishY);
				}
				else if((finishX - startX) > 400){
					allDestLines += drawInterpolationLines(finishX, finishY, startX, startY);
				}
				else{						
					allDestLines += ("\n" + drawLine("path", startX, startY, finishX, finishY, 1, "#000000"));
				}
			}
		}
		allDestLines += ("\n" + gTag2());
		allDestLines += ("\n" + svgTag());
		return allDestLines;

	}
	
	
	//Method to modify premade SVG of the world 
	public static String getSvg(String searched, int optLevel) {
		System.out.println("Old svg method is being called");
		ArrayList<String> order = Model.search(searched);
		return getArraySvg(order, optLevel);			
	}
	
	public static String getArraySvg(ArrayList<String> destList, int optLevel) {
		System.out.println("Got a request for an svg given an array of strings");		
		BufferedReader br = null;
		BufferedWriter bw = null;		
		String svg = "";		
		try{
		//Create and open a writer for current .svg file
			br = new BufferedReader(new FileReader("data/World_location_map.svg"));
			bw = new BufferedWriter(new FileWriter("ArrayTest.svg"));
			//Copying File...
			String line = null;
			while((line = br.readLine()) != null) {
				svg += line + "\n";
			}
			//Writing to file...
			//Add key
			svg += setupKey() + "\n";
	        	//Draw trip
			ArrayList<String> order = Model.shortestTrip();
			svg += completeSvg(order);
			bw.write(svg);
			br.close();
			bw.close();
		} catch (IOException e) {
		   // do something
		}
	return svg;
	}
	
	public static String getBlankSvg() {
		System.out.println("Getting a blank svg");
		BufferedWriter bw = null;		
		BufferedReader br = null;	
		String svg = "";	
		try{
		//Create and open a writer for current .svg file
			br = new BufferedReader(new FileReader("data/BlankWorldExample.svg"));
			bw = new BufferedWriter(new FileWriter("BlankTest.svg"));			
			//Copying File...
			String line = null;
			while((line = br.readLine()) != null) {
				//system.out.println(line);
				svg += line + "\n";
			}			
			bw.write(svg);			
			br.close();
			bw.close();
		} catch (IOException e) {
		   // do something
		}
		return svg;
	}
}

