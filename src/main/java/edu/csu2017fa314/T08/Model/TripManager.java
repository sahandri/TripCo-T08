package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class TripManager {
    private static int[][] distLookUp;
    private static String _key = "";
    public static ArrayList<Trip> trips = new ArrayList<>();
    public static AtomicInteger total;
    static Trip shortest() {
        return trips.get(0);
    }

    public static int size() { return trips.size(); }

    public static Trip get(String id) {
        for(Trip t: trips) {
            if(t.get(0).equals(id)) {
                return t;
            }
        }

        return trips.get(0);
    }

    public static void buildTripList(String key) {
        if(key.equals(_key) && trips.size() > 0) { return; }
        _key = key;
        DataBase.getID(key);
        total = new AtomicInteger(DataBase.getTotal());
        trips = new ArrayList<>();

        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        ArrayList<Future<Trip>> results = new ArrayList<>();

        buildDistLookUp();

        for(int i = 0; i < total.get(); i++) {
            TripWorker tw = new TripWorker(i);
            Future<Trip> res = pool.submit(tw);
            results.add(res);

        }
        pool.shutdown();
        while(!pool.isTerminated()) { }

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

    private static void buildDistLookUp() {
        int destTtl = DataBase.getTotal();
        distLookUp = new int[destTtl][];

        ArrayList<String> lats = new ArrayList<>(destTtl);
        ArrayList<String> longs = new ArrayList<>(destTtl);

        for (int i = 0; i < destTtl; i++) {
            String id = DataBase.getID(i);
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
        if(i < j) { return distLookUp[i][j-i-1]; }
        else if(j < i) { return distLookUp[j][i-j-1]; }
        else { return distLookUp[i][0]; }
    }
}
