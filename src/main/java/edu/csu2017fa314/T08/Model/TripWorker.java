package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TripWorker implements Runnable {

    private int _idx;

    public TripWorker(int idx) {
        this._idx = idx;
    }

    @Override
    public void run() {
        buildTrip(_idx);
    }

    private void buildTrip(int idx) {
        int len = TripManager.total; // Number of locations
        int tripLength = 0;
        int[] order = new int[len];
        System.out.printf("Thread %d: Generating trip...", _idx);

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

            d1 = Model.getDistance(i, nn, false);

            for(int j = i+2; j < len; j++) {

                d2 = Model.getDistance(i, j, false);

                // If j is closer than nn, nn becomes j
                if(d2 < d1) {
                    nn = j;
                    d1 = d2;
                }
            }

            // Swap nn w/ i+1
            int tmp = order[i+1];
            order[i+1] = order[nn];
            order[nn] = tmp;

            tripLength += d1;
        }


        System.out.printf("Thread %d: Calculated trip of length %d.", _idx, tripLength);

        for(int i = 0; i < len; i++) {
            TripManager.trips.get(_idx).set(i , TripManager.stops[order[i]]);
        }
        TripManager.trips.get(_idx).setLength(tripLength);
    }
}
