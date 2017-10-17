package edu.csu2017fa314.T08.View;

import edu.csu2017fa314.T08.Model.Destination;
import edu.csu2017fa314.T08.Model.Model;
import edu.csu2017fa314.T08.Model.Distance;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;

/* Itinerary: Produces a JSON file containing a travel itinerary given a set of location IDs.
 */
public class Itinerary {
    private static String path = ""; // Path to output the JSON file
    private static ArrayList<String> columns;

    // TODO: Handle i/o exceptions(?)

    // Writes the JSON itinerary to file.
    public static void createJSON(String fileName) throws IOException {
        JSONArray arr = createItinerary();

        BufferedWriter ob = new BufferedWriter(new FileWriter(fileName));
        // Write the JSON to a file, pretty-printed using 4-space indentation
        ob.write(arr.toString(4));
        ob.close();
    }

    // Prints the JSON to stdout for debugging purposes
    public static void printJSON() throws IOException {
        JSONArray arr = createItinerary();
        
        // Write the JSON to stdout, pretty-printed using 4-space indentation
        System.out.println(arr.toString(4));
    }

    // Creates a JSONArray of trip legs, i.e. the itinerary
    public static JSONArray createItinerary() {
        JSONArray arr = new JSONArray();

        ArrayList<String> stops = Model.shortestTrip();

        // Iterate through destinations, calculating the distance of each leg.
        columns = Destination.getFirstLine();
        ArrayList<ArrayList<String>> list = Destination.getList();

        // Iterate through destinations, calculating the distance of each leg.
        for(int i = 0; i < Destination.getTotal()-1; i++)
        {
            ArrayList<String> line = list.get(i);

            // Creates a JSON object with start, end, and the distance between them
            // and appends it to the end of the itinerary JSON array.
            int dist = Model.getDistance(stops.get(i), stops.get(i+1), false);
            arr.put(createLeg(line, dist));
        }

        return arr;


    }

    // Creates a JSONObject for a single leg of the trip from start to end
    public static JSONObject createLeg(ArrayList<String> line, int dist) {
        JSONObject leg = new JSONObject();
        for(int i = 0; i < line.size(); ++i) {
            leg.put(columns.get(i), line.get(i));
        }

        leg.put("distance", dist); //Change to DistanceMi for next sprint
        //leg.put("distanceKm", distk);
        return leg;
    }
}
