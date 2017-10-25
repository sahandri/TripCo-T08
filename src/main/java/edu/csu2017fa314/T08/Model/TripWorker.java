package edu.csu2017fa314.T08.Model;

import java.util.concurrent.Callable;

public class TripWorker implements Callable<Trip> {

    private int _idx;
    private int[] order;
    private int _optLevel = 0;
    private int _tripLength = 0;
    private int _numStops = TripManager.total.get();

    public TripWorker(int idx, int optLevel) {
        this._idx = idx;
        this._optLevel = optLevel;
    }

    @Override
    public Trip call() {
        order = new int[_numStops+1];

        order[0] = _idx;
        order[_numStops] = _idx;

        for(int i = 1; i < _numStops; i++) {
            order[i] = i;
        }

        order[_idx] = 0;

        switch(_optLevel) {
            case 1: doNN(); break;
            case 2: doNN(); do2Opt(); break;
            case 3: doNN(); do2Opt(); do3Opt(); break;
        }

        // Turn the ordering into a trip
        Trip t = new Trip();
        for(int i = 0; i < _numStops+1; i++) {
            t.add(DataBase.getID(order[i]));
        }
        t.setLength(_tripLength);

        return t;
    }


    private void doNN() {
        for(int i = 0; i < _numStops-1; i++) {
            // For each location, find the nearest neighbor
            int nn = i+1;
            int d1;
            int d2;

            d1 = TripManager.getDist(order[i], order[nn]);

            for(int j = i+1; j < _numStops; j++) {

                d2 = TripManager.getDist(order[i], order[j]);

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

            _tripLength += d1;
        }

        _tripLength += TripManager.getDist(order[_numStops], order[_numStops-1]);

    }

    private void do2Opt() {
        boolean improved = true;
        while(improved) {
            improved = false;
            for(int i = 0; i <= _numStops-3; i++) {
                for(int k =i+2; k < _numStops-1; k++) {
                    int delta = -TripManager.getDist(order[i],order[i+1])-TripManager.getDist(order[k],order[k+1])
                            +TripManager.getDist(order[i],order[k])+TripManager.getDist(order[i+1],order[k+1]);

                    if (delta < 0) {
                        optSwap(i+1, k);
                        _tripLength += delta;
                        improved = true;
                    }
                }
            }
        }
    }

    private void do3Opt() {

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
