package edu.csu2017fa314.T08.View;

import edu.csu2017fa314.T08.Model.Destination;
import edu.csu2017fa314.T08.Model.Model;
import edu.csu2017fa314.T08.Model.Distance;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONArray;

/* Itinerary: Produces a JSON file containing a travel itinerary given a set of location IDs.
 */
public class Itinerary {
    private static String path = ""; // Path to output the JSON file
    private static ArrayList<String> columns;

    // TODO: Handle i/o exceptions(?)

    // Writes the JSON itinerary to file.
    public static JSONArray createJSON(String search) {
        JSONArray arr = createItinerary(search);
        return arr;
    }

	public static JSONArray createJSON(ArrayList<String> destList, int optLevel) {
        ArrayList<String> stops = Model.shortestTrip(destList, optLevel);
	    JSONArray arr = createItinerary(stops);
        return arr;
    }

    // Creates a JSONArray of trip legs, i.e. the itinerary
    public static JSONArray createItinerary(String search) {
        JSONArray arr = new JSONArray();

        ArrayList<String> stops = Model.search(search);

        // Iterate through destinations, calculating the distance of each leg.

        // Iterate through destinations, calculating the distance of each leg.
        for(int i = 0; i < stops.size()-1; i++)
        {
            // Creates a JSON object with start, end, and the distance between them
            // and appends it to the end of the itinerary JSON array.
            int dist = Model.getDistance(stops.get(i), stops.get(i+1), false);
            arr.put(createLeg(stops.get(i), dist));
        }

        return arr;


    }
	
	// Creates a JSONArray of trip legs, i.e. the itinerary
    public static JSONArray createItinerary(ArrayList<String> stops) {
        JSONArray arr = new JSONArray();


        // Iterate through destinations, calculating the distance of each leg.

        // Iterate through destinations, calculating the distance of each leg.
        for(int i = 0; i < stops.size()-1; i++)
        {
            // Creates a JSON object with start, end, and the distance between them
            // and appends it to the end of the itinerary JSON array.
            int dist = Model.getDistance(stops.get(i), stops.get(i+1), false);
            arr.put(createLeg(stops.get(i), dist));
        }

        return arr;


    }

    // Creates a JSONObject for a single leg of the trip from start to end
    public static JSONObject createLeg(String id, int dist) {
        JSONObject leg = new JSONObject();
        HashMap<String,String> info = Model.getInfo(id);
        for(Map.Entry<String,String> kv: info.entrySet()) {
            leg.put(kv.getKey(), kv.getValue());
        }

        leg.put("distance", dist); //Change to DistanceMi for next sprint
        //leg.put("distanceKm", distk);
        return leg;
    }
}
