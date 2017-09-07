package edu.csu2017fa314.T08.View;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/* Itinerary: Produces a JSON file containing a travel itinerary given a set of location IDs.
 */
public class Itinerary {
    private String path = ""; // Path to output the JSON file

    // Writes the JSON itinerary to file.
    public void CreateJSON() {
    }

    private JSONArray CreateItinerary() {
        JSONArray arr = new JSONArray();
        return arr;
    }

    private JSONObject CreateLeg(String start, String end) {
        JSONObject leg = new JSONObject();
        leg.put("start", start);
        leg.put("end", end);

        // XXX needs implementation
        // double dist = Model.CalculateDistance(start, end);

        leg.put("distance", "nill" /* dist */);
        return leg;
    }
}
