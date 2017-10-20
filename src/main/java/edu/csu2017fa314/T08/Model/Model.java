package edu.csu2017fa314.T08.Model;

import org.eclipse.jetty.server.session.JDBCSessionIdManager;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class Model {
    private static int[][] distLookUp;
    private static ArrayList<String> ids;

    public static void setUp() {
        ids = DataBase.getID("");
        buildDistLookUp();
        TripManager.buildTripList();
    }
    public static ArrayList<String> shortestTrip() {
        return TripManager.shortest().stops();
    }

    public static ArrayList<String> shortestTrip(String key) {
        ids = DataBase.getID(key);
        TripManager.buildTripList();
        return TripManager.shortest().stops();
    }

    public static int getNumStops() { return ids.size(); }


    public static HashMap getInfo(String id) {
        return DataBase.getInfo(id);
    }


    public static int getDistance(String id1, String id2, boolean useKm) {
        int distance;

        distance = getDist(ids.indexOf(id1),ids.indexOf(id2));

        return distance;
    }

    public static String getID(int idx) {
        return DataBase.getID(idx);
    }

    public static  ArrayList getStops() { return ids; }

    public static int getDistance(int idx1, int idx2, boolean useKm) {
        //String id1 = DataBase.getID(idx1);
        //String id2 = DataBase.getID(idx2);
        //String lat1 = DataBase.getLatit(id1);
        //String lon1 = DataBase.getLongit(id1);
        //String lat2 = DataBase.getLatit(id2);
        //String lon2 = DataBase.getLongit(id2);

        int distance;

        distance = getDist(idx1, idx2);

        return distance;

    }

    /*
    * Populates the distLookUp table by calculating the distance between every point.
    */
    private static void buildDistLookUp() {
        int destTtl = DataBase.getTotal();
        distLookUp = new int[destTtl][];

        ArrayList<String> lats = new ArrayList<>(destTtl);
        ArrayList<String> longs = new ArrayList<>(destTtl);

        for (int i = 0; i < destTtl; i++) {
            String id = ids.get(i);
            lats.add(DataBase.getLatit(id));
            longs.add(DataBase.getLongit(id));
        }

        for (int i = 0; i < destTtl; i++) {
            distLookUp[i] = new int[destTtl - i];
            for(int j = 0; j < destTtl-i-1; j++) {
                distLookUp[i][j] = Distance.distanceMi( lats.get(i), longs.get(i),
                                                        lats.get(j), longs.get(j));
            }
        }
    }

    /*
     * The distLookUp indices are a little different from the csv indices, so some math
     * is done to get them correct. Each row i in the lookup table has n-i entries, n being
     * the size of the dataset. The distance to a destination j is the entry j-i-1 when i<j
     * because the distance from i to destinations 0..i-1 is already stored in the previous rows.
     */
    private static int getDist(int i, int j) {
        if(i < j) { return distLookUp[i][j-i-1]; }
        else if(j < i) { return distLookUp[j][i-j-1]; }
        else { return distLookUp[i][0]; }
    }
}
