package edu.csu2017fa314.T08.View;

import java.io.StringWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import edu.csu2017fa314.T08.Model.Destination;

/* Itinerary: Produces a JSON file containing a travel itinerary given a set of location IDs.
 */
public class Itinerary {
    private static String path = ""; // Path to output the JSON file

    // TODO: Handle i/o exceptions(?)

    // Writes the JSON itinerary to file.
    public static void CreateJSON() throws IOException {
        JSONArray arr = CreateItinerary();
        StringWriter os = new StringWriter();
        arr.writeJSONString(os);

    }

    // Prints the JSON to stdout for debugging purposes
    public static void PrintJSON() throws IOException {
        JSONArray arr = CreateItinerary();
        StringWriter os = new StringWriter();

        arr.writeJSONString(os);
        String json = os.toString();

        System.out.println(json);
    }

    // Creates a JSONArray of trip legs, i.e. the itinerary
    public static JSONArray CreateItinerary() {
        JSONArray arr = new JSONArray();

        for(int i = 0; i < 3; i++)
        {
            String start = dests.getID(i);
            String end = dests.getID(i+1);

            arr.add(CreateLeg(start, end));
        }

        return arr;
    }

    // Creates a JSONObject for a single leg of the trip from start to end
    public static JSONObject CreateLeg(String start, String end) {
        JSONObject leg = new JSONObject();
        leg.put("start", start);
        leg.put("end", end);

        // XXX needs implementation
        double dist = edu.csu2017fa314.T08.Model.Itinerary.distance(start, end);

        leg.put("distance", dist);
        return leg;
    }
}
