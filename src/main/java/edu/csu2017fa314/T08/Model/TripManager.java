package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class TripManager {
    private static int[][] distLookUp; // Lookup table of distances between every location in the search.
    private static int _optLevel = 1; // Optimization level
    private static String _key = ""; // Key used in most recent search.
    static ArrayList<String> ids = new ArrayList<>();
    static ArrayList<Trip> trips = new ArrayList<>();
    static AtomicInteger total;

    // Returns the shortest trip based on the current key. Calculates if necessary.
    static ArrayList<String> shortest() {
        if(trips.isEmpty()) {
            if (ids.isEmpty()) {
                return new ArrayList<>();
            } else {
                buildTripList();
            }
        }
        return trips.get(0).stops();
    }

    // Returns the shortest trip to all destinations with the key. Calculates if necessary.
    static ArrayList<String> shortest(String key) {
        if(trips.isEmpty()) { buildTripList();}
        return trips.get(0).stops();
    }

    /*
    1. Generate index list from stops
    2. Pass list to TripWorker instead of generating list in TripWorker
    3. Change buildTripList to call shortest
     */

    static void addStops(ArrayList<String> stops) {
        for(String s : stops) {
            if(!ids.contains(s)) { ids.add(s); }
        }

    }

    static ArrayList<String> shortest(ArrayList<String> stops) {
        addStops(stops);
        buildTripList();
        return trips.get(0).stops();
    }

    // XXX: Web endpoint not implementeed yet.
    /*
    String id: id of starting location for desired shortest trip.
    Returns the shortest trip starting at id.
     */
    static Trip get(String id) {
        if(trips.isEmpty()) { buildTripList(); }
        for(Trip t: trips) {
            if(t.get(0).equals(id)) {
                return t;
            }
        }
        return trips.get(0);
    }

    /*
    int level: Desired optimization level.
    Levels:
        0 - Raw data
        1 - Nearest neighbor
        2 - 2-opt
        3 - 3-opt
    */
    static void setOptLevel(int level) {
        _optLevel = level;
    }

    /*
    Calculates the shortest trips using all destinations found using key.
    Stores the shortest trip from every starting location in ArrayList trips ordered by Trip length.
     */
    private static void buildTripList() {
        if(ids.size() == 0){ System.err.println("ERROR: no destinations to make trip from."); return; }
        total = new AtomicInteger(ids.size());
        trips = new ArrayList<>();

        buildDistLookUp();
        // Runtime.getRuntime().availableProcessors()
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        ArrayList<Future<Trip>> results = new ArrayList<>();

        // Generate the trip creation tasks and store their futures
        for(int i = 0; i < total.get(); i++) {
            TripWorker tripWorker = new TripWorker(i, _optLevel);
            Future<Trip> tripResult = pool.submit(tripWorker);
            results.add(tripResult);
        }

        pool.shutdown();
        while(!pool.isTerminated()) { }

        // Get the results from each task's future
        try {
            for(Future<Trip> ft : results) {
                trips.add(ft.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Collections.sort(trips);
    }

    // Calculates all the distances between destinations.
    private static void buildDistLookUp() {
        int destTtl = ids.size();
        distLookUp = new int[destTtl][];

        ArrayList<String> lats = new ArrayList<>(destTtl);
        ArrayList<String> longs = new ArrayList<>(destTtl);

        // Collect the coordinates of all locations
        for (String id : ids) {
            lats.add(DataBase.getLatit(id));
            longs.add(DataBase.getLongit(id));
        }

        for (int i = 0; i < destTtl; i++) {
            distLookUp[i] = new int[destTtl - i];
            for(int j = 0; j < destTtl-i-1; j++) {
                distLookUp[i][j] = Distance.distanceMi( lats.get(i), longs.get(i),
                                                    lats.get(j+i+1), longs.get(j+i+1));
            }
        }
    }

    /*
     * The distLookUp indices are a little different from the csv indices, so some math
     * is done to get them correct. Each row i in the lookup table has n-i entries, n being
     * the size of the dataset. The distance to a destination j is the entry j-i-1 when i<j
     * because the distance from i to destinations 0..i-1 is already stored in the previous rows.
     */
    static int getDist(int i, int j) {
        if(i < trips.size() || j < trips.size()) { return -1; } // TODO: Properly handle error
        if(i < j) { return distLookUp[i][j-i-1]; }
        else if(j < i) { return distLookUp[j][i-j-1]; }
        else { return distLookUp[i][0]; }
    }
}
