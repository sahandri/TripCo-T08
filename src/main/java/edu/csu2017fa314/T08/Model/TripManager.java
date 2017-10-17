package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.*;


public class TripManager {
    public static ArrayList<Trip> trips;
    public static final int total = Destination.getTotal();
    public static String[] stops;

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
        System.out.printf("Generating %d trips", total);
        trips = new ArrayList<>(total);
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        stops = new String[total];

        for(int i = 0; i < total; i++) {
            stops[i] = Destination.getID(i);
        }

        for(int i = 0; i < total; i++) {
            TripWorker tw = new TripWorker(i);
            pool.execute(tw);
        }
        pool.shutdown();
        while(!pool.isTerminated()) { }
        Collections.sort(trips);
    }

}
