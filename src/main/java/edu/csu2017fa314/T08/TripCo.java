package edu.csu2017fa314.T08;

import edu.csu2017fa314.T08.Model.DataBase;
import edu.csu2017fa314.T08.Model.Model;
import edu.csu2017fa314.T08.Model.TripManager;
import edu.csu2017fa314.T08.View.Itinerary;
import edu.csu2017fa314.T08.View.makeSvg;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static edu.csu2017fa314.T08.View.Itinerary.createJSON;

public class TripCo

{
    public static void main(String[] args) {
        System.out.println("Welcome to TripCo");
        DataBase.connect();
        JSONArray trip = Itinerary.createJSON("");
        try {
            BufferedWriter ob = new BufferedWriter(new FileWriter("airport.json"));
            // Write the JSON to a file, pretty-printed using 4-space indentation
            ob.write(trip.toString(2));
            ob.close();
        } catch(IOException e) {
            System.err.println(e.toString());
            return;
        }
        String svg = makeSvg.getSvg("",0);

    }

}
