package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TripWorker implements Callable<Trip> {

    private int _idx;
    private int[] order;

    public TripWorker(int idx) {
        this._idx = idx;
    }

    @Override
    public Trip call() {
        int len = TripManager.total.get(); // Number of locations
        int tripLength = 0;
        order = new int[len+1];

        order[0] = _idx;
        order[len] = _idx;

        for(int i = 1; i < len; i++) {
            order[i] = i;
        }

        order[_idx] = 0;

        for(int i = 0; i < len-1; i++) {
            // For each location, find the nearest neighbor
            int nn = i+1;
            int d1;
            int d2;

            d1 = Model.getDistance(order[i], order[nn], false);

            for(int j = i+1; j < len; j++) {

                d2 = Model.getDistance(order[i], order[j], false);

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

        // 2-opt
        boolean improved = true;
        while(improved) {
            improved = false;
            for(int i = 0; i <= len-3; i++) {
                for(int k =i+2; k < len-1; k++) {
                    int delta = 0; // Calculate delta
                    if (delta < 0) {
                        optSwap(i+1, k);
                        improved = true;
                    }
                }
            }

        }

        // Turn the ordering into a trip
        tripLength += Model.getDistance(order[len], order[len-1], false);
        Trip t = new Trip();
        for(int i = 0; i < len+1; i++) {
            t.add(TripManager.stops[order[i]]);
        }
        t.setLength(tripLength);

        return t;
    }

    private void optSwap(int i, int k) {
        while(i<k) {
            int tmp = order[i];
            order[i] = order[k];
            order[k] = tmp;
            i++; k--;
        }
    }

}
