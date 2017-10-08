package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.Collections;

public class ShortestTrip {

    private static int[][] distLookUp; // Lookup table of all distances between two locations
    private static ArrayList<String> stops = new ArrayList<String>(); // Itinerary of shortest trip
    private static ArrayList<String> tripCandidate; // Intermediate variable for storing the current shortest trip during calculations.

    public static void printTrip() {
        for(int i = 0; i < stops.size(); i++) {
            System.out.println(stops.get(i));
        }
    }

    /*
     * Returns an ArrayList<String> containing the itinerary
     */
    public static ArrayList<String> getShortestTrip() {
        int tripLength = -1;
        if(stops.size() == 0) {
            buildDistLookUp();

            for(int i = 0; i < Destination.getTotal(); i++) {
                // Get the shortest possible trip from i
                int currTrip = shortestTripLength(i);

                // Save that trip if it's the shortest we've found.
                if(currTrip < tripLength || tripLength < 0) {
                    tripLength = currTrip;
                    stops = new ArrayList<String>(tripCandidate);
                }
            }
        }
        
        return stops;
    }

    /*
     * Populates the distLookUp table by calculating the distance between every point.
     */
    private static void buildDistLookUp() {
        int destTtl = Destination.getTotal();
        distLookUp = new int[destTtl][];

        for(int i = 0; i < destTtl; i++) {
            distLookUp[i] = new int[destTtl - i];
            String d_i = Destination.getID(i);

            for(int j = 0; j < destTtl-i-1; j++) {
                String d_j = Destination.getID(i+j+1);
                distLookUp[i][j] = Distance.distanceMi(d_i,d_j);
            }
        }
    }

    private static void printDistLookUp() {
        int destTtl = Destination.getTotal();
        for(int i = 0; i < destTtl; i++) {
            for(int j = 0; j < destTtl-i-1; j++) {
                System.out.println(Destination.getID(i) + "->" + Destination.getID(i+j+1) + "=" + distLookUp[i][j]);
            }
        }
    }

    /*
     * Calculates the length of the shortest trip given the index of a starting point.
     */
    private static int shortestTripLength(int start) {
        tripCandidate = new ArrayList<String>(); // TODO: This is bad XXX

        for(int i = 0; i < Destination.getTotal(); i++) {
            tripCandidate.add(Destination.getID(i));
        }

        // Move the start destination to the top of the list
        Collections.swap(tripCandidate,0,start);

        int len = 0;
        // Iterate through tripCandidate to sort into shortest trip
        for(int i = 0; i < tripCandidate.size()-1; i++) {

            int idxi = Destination.getIndex(tripCandidate.get(i));
            int idxn = idxi; // index of distance to nearest neighbor of i
            int nn = i;

            // Iterate remainder of trip to find next closest location
            for(int j=i+1; j < tripCandidate.size(); j++) {
                int idxj = Destination.getIndex(tripCandidate.get(j));

                if(getDist(idxi,idxj) < getDist(idxi,idxn) || idxn==idxi) {
                    idxn = idxj;
                    nn = j;
                }
            }

            // Add the closest distance
            len += getDist(idxi,idxn);

            // Swap the array entries
            Collections.swap(tripCandidate,i+1,nn);
        }

        // Add the start location to the end of the list to complete the trip
        int idx1 = Destination.getIndex(tripCandidate.get(tripCandidate.size()-1));
        int idx2 = Destination.getIndex(tripCandidate.get(0));
        tripCandidate.add(tripCandidate.get(0));
        len += getDist(idx1,idx2);

        return len;
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
