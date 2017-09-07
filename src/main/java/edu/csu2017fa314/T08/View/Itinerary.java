package edu.csu2017fa314.T08.View;

import java.io.StringWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/* Itinerary: Produces a JSON file containing a travel itinerary given a set of location IDs.
 */
public class Itinerary {
    private String path = ""; // Path to output the JSON file

    // TODO: Handle i/o exceptions(?)

    // Writes the JSON itinerary to file.
    public void CreateJSON() throws IOException {
        JSONArray arr = CreateItinerary();
        StringWriter os = new StringWriter();
        arr.writeJSONString(os);

    }

    // Prints the JSON to stdout for debugging purposes
    public void PrintJSON() throws IOException {
        JSONArray arr = CreateItinerary();
        StringWriter os = new StringWriter();

        arr.writeJSONString(os);
        String json = os.toString();

        System.out.println(json);
    }

    // Creates a JSONArray of trip legs, i.e. the itinerary
    public JSONArray CreateItinerary() {
        JSONArray arr = new JSONArray();
        return arr;
    }

    // Creates a JSONObject for a single leg of the trip from start to end
    public JSONObject CreateLeg(String start, String end) {
        JSONObject leg = new JSONObject();
        leg.put("start", start);
        leg.put("end", end);

        // XXX needs implementation
        // double dist = Model.CalculateDistance(start, end);

        leg.put("distance", "nill" /* dist */);
        return leg;
    }
}
