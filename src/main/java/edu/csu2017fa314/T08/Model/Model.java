package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;

public class Model {
    private static int[][] distLookUp;

    public static void init() {
        buildDistLookUp();
    }

    public static Trip shortestTrip() {
        return TripManager.shortest();
    }

    public static Trip shortestTrip(String id) {
        return TripManager.get(id);
    }

    public static int getDistance(String id1, String id2, boolean useKm) {
        String lat1 = Destination.getLatit(id1);
        String lon1 = Destination.getLongit(id1);
        String lat2 = Destination.getLatit(id2);
        String lon2 = Destination.getLongit(id2);

        int distance;

        if (useKm) {
            distance = Distance.distanceKm(lat1, lon1, lat2, lon2);
        } else {
            distance = getDist(Destination.getIndex(id1), Destination.getIndex(id2));
        }

        return distance;
    }

    /*
    * Populates the distLookUp table by calculating the distance between every point.
    */
    private static void buildDistLookUp() {
        int destTtl = Destination.getTotal();
        distLookUp = new int[destTtl][];

        String lat1 = "";
        String long1 = "";
        String lat2 = "";
        String long2 = "";
        for (int i = 0; i < destTtl; i++) {
            distLookUp[i] = new int[destTtl - i];
            String d_i = Destination.getID(i);

            for (int j = 0; j < destTtl - i - 1; j++) {
                String d_j = Destination.getID(i + j + 1);
                lat1 = Destination.getLatit(d_i);
                long1 = Destination.getLongit(d_i);
                lat2 = Destination.getLatit(d_j);
                long2 = Destination.getLongit(d_j);
                distLookUp[i][j] = Distance.distanceMi(lat1, long1, lat2, long2);
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
