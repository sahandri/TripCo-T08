package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.Collections;


public class TripManager {
    private static ArrayList<Trip> trips;
    private static ArrayList<String> locations; // Hack

    static {
        buildTripList();
    }

    public static Trip shortest() {
        return trips.get(0);
    }

    public static Trip get(String id) {
        for(Trip t: trips) {
            if(t.get(0).equals(id)) {
                return t;
            }
        }

        return trips.get(0);
    }

    private static void buildTripList() {

        for(int i = 0; i < Destination.getTotal(); i++) {
            locations.add(Destination.getID(i));
        }
        for(String s: locations) {
            trips.add(buildTrip(s));
        }
        Collections.sort(trips);
    }

    private static Trip buildTrip(String start) {
        ArrayList<String> locs = new ArrayList<>(locations);
        Trip t = new Trip();
        t.add(start);

        return t;
    }
}
