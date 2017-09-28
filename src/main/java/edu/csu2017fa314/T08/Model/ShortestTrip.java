package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.Collections;

public class ShortestTrip {

    private static int[][] distLookUp;
    private static ArrayList<String> stops = new ArrayList<String>();
    private static ArrayList<String> tripCandidate;

    public static ArrayList<String> getShortestTrip() {

        if(stops.size() == 0) {
            buildDistLookUp();

            int tripLength = -1;
            for(int i = 0; i < Destination.getTotal(); i++) {
                // Get the shortest possible trip from i
                int currTrip = shortestTripLength(i);

                // Save that trip if it's the shortest we've found.
                if(currTrip < tripLength || tripLength < 0) {
                    tripLength = currTrip;
                    stops = tripCandidate;
                }
            }
        }

        System.out.println("Shortest Trip: " + Integer.toString(tripLength));

        return stops;
    }

    private static void buildDistLookUp() {
        int destTtl = Destination.getTotal();
        distLookUp = new int[destTtl][destTtl];

        for(int i = 0; i < destTtl; i++) {
            String d_i = Destination.getID(i);
            for(int j = 0; j < destTtl; j++) {
            String d_j = Destination.getID(j);

                distLookUp[i][j] = Itinerary.distanceMi(d_i,d_j);
            }
        }
    }

    private static int shortestTripLength(int start) {
        tripCandidate = new ArrayList<String>(); // TODO: Delete old ones XXX

        for(int i = 0; i < Destination.getTotal(); i++) {
            tripCandidate.add(Destination.getID(i));
        }

        // Move the start destination to the top of the list
        Collections.swap(tripCandidate,0,start);

        int len = 0;
        // Iterate through tripCandidate to sort into shortest trip
        for(int i = 1; i < tripCandidate.size()-1; i++) {

            int idxi = Destination.getIndex(tripCandidate.get(i));
            int idxn = idxi; // index of distance to nearest neighbor of i
            int nn = i;

            // Iterate remainder of trip to find next closest location
            for(int j=i+1; j < tripCandidate.size(); j++) {
                int idxj = Destination.getIndex(tripCandidate.get(j));

                if(distLookUp[idxi][idxj] < distLookUp[idxi][idxn] || idxn==idxi) {
                    idxn = idxj;
                    nn = j;
                }
            }

            if(nn != i) {
                // Add the closest distance
                len += distLookUp[idxi][idxn];

                // Swap the array entries
                Collections.swap(tripCandidate,i+1,nn);
            }
        }

        int idx1 = Destination.getIndex(tripCandidate.get(tripCandidate.size()-1));
        int idx2 = Destination.getIndex(tripCandidate.get(0));

        tripCandidate.add(tripCandidate.get(0));

        len += distLookUp[idx1][idx2];

        return len;
    }
}
