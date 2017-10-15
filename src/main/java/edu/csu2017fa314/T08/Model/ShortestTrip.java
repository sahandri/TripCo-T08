package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.Collections;

public class ShortestTrip {

    private ArrayList<String> stops = new ArrayList<>(); // Itinerary of shortest trip

    /*
     * Returns an ArrayList<String> containing the itinerary
     */
    public ArrayList<String> getShortestTrip() {
        int tripLength = -1;
        if(stops.size() == 0) {

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
     * Calculates the length of the shortest trip given the index of a starting point.
     */
    private int shortestTripLength(int start) {
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
}
