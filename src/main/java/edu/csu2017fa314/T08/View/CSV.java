package edu.csu2017fa314.T08.View;

import edu.csu2017fa314.T08.Model.Destination;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;

/* Itinerary: Produces a JSON file containing a travel itinerary given a set of location IDs.
 */
public class CSV {
    private static String path = ""; // Path to output the JSON file

    // TODO: Handle i/o exceptions(?)

    // Writes the JSON itinerary to file.
    public static void createJSON(String fileName) throws IOException {
        JSONArray arr = createCSV();

        BufferedWriter ob = new BufferedWriter(new FileWriter(fileName));
        // Write the JSON to a file, pretty-printed using 4-space indentation
        ob.write(arr.toString(4));
        ob.close();
    }

    // Prints the JSON to stdout for debugging purposes
    public static void printJSON() throws IOException {
        JSONArray arr = createCSV();
        
        // Write the JSON to stdout, pretty-printed using 4-space indentation
        System.out.println(arr.toString(4));
    }

    // Creates a JSONArray of trip legs, i.e. the itinerary
    public static JSONArray createCSV() {
        JSONArray arr = new JSONArray();
		
		String[] firstLine = Destination.getFirstLine();
		ArrayList<String[]> list = Destination.getList();
		
        // Iterate through destinations, calculating the distance of each leg.
        for(int i = 0; i < Destination.getTotal()-1; i++)
        {
            String[] line = new String[line[i].length];
            System.arraycopy(line[i],0,line,0,line[i].length);
            
            // Creates a JSON object with start, end, and the distance between them
            // and appends it to the end of the itinerary JSON array.
            arr.put(createLeg(line, firstLine));
        }

        return arr;
    }

    // Creates a JSONObject for a single leg of the trip from start to end
    public static JSONObject createLeg(String[] line, String[] firstLine) {
        JSONObject leg = new JSONObject();
        for(int i = 0; i < line.length; ++i) {
			leg.put(firstLine[i], line[i]);
		}
        return leg;
    }
}
