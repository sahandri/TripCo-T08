package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.Collections;


public class TripManager {
    private static ArrayList<Trip> trips;

    static {
        buildTripList();
    }

    static Trip shortest() {
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
        trips = new ArrayList<>();
        for(int i = 0; i < Destination.getTotal(); i++) {
            trips.add(buildTrip(i));
        }
        Collections.sort(trips);
    }

    private static Trip buildTrip(int idx) {
        int len = Destination.getTotal(); // Number of locations
        int[] order = new int[len];

        order[0] = idx;

        for(int i = 1; i < len; i++) {
            order[i] = i;
        }

        order[idx] = 0;

        for(int i = 0; i < len-1; i++) {
            // For each location, find the nearest neighbor
            int nn = i+1;
            int d1;
            int d2;

            for(int j = i+2; j < len; j++) {

                String idi = Destination.getID(order[i]);
                String idn = Destination.getID(order[nn]);
                String idj = Destination.getID(order[j]);

                d1 = Model.getDistance(idi, idn, false);
                d2 = Model.getDistance(idi, idj, false);

                // If j is closer than nn, nn becomes j
                if(d2 < d1) {
                    nn = j;
                }
            }

            // Swap nn w/ i+1
            int tmp = order[i+1];
            order[i+1] = order[nn];
            order[nn] = tmp;
        }

        Trip t = new Trip();
        for(int i : order) {
            t.add(Destination.getID(i));
        }
        t.add(Destination.getID(order[0]));

        return t;
    }
}
