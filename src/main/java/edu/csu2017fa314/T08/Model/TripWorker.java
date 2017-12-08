package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TripWorker implements Callable<Trip> {

    private int _idx;
    private int[] _order;
    private int _optLevel = 0;
    private int _units = 0;
    private int _tripLength = 0;
    private int _numStops = TripManager.total.get();

    public TripWorker(int idx, int optLevel, int units) {
        this._idx = idx;
        this._optLevel = optLevel;
	this._units = units;
	
    }

    @Override
    public Trip call() {
        System.out.println("Starting trip " + Integer.toString(_idx));
        _order = new int[_numStops+1];

        _order[0] = _idx;
        _order[_numStops] = _idx;

        for(int i = 1; i < _numStops; i++) {
            _order[i] = i;
        }

        _order[_idx] = 0;

        switch(_optLevel) {
            case 0: calcLength(); break;
            case 1: doNN(); break;
            case 2: doNN(); do2Opt(); break;
            case 3: {
                doNN();
                do3Opt();
                break;
            }
        }

        // Turn the _ordering into a trip
        Trip t = new Trip();
        for(int i = 0; i < _numStops+1; i++) {
            t.add(DataBase.getID(_order[i]));
        }
        t.setLength(_tripLength);

        return t;
    }

    private void calcLength() {
        for(int i = 0; i < _numStops; i++) {
            _tripLength+=TripManager.getDist(_order[i],_order[i+1]);
        }
    }


    private void doNN() {
        for(int i = 0; i < _numStops-1; i++) {
            // For each location, find the nearest neighbor
            int nn = i+1;
            int d1;
            int d2;

            d1 = TripManager.getDist(_order[i], _order[nn]);

            for(int j = i+1; j < _numStops; j++) {

                d2 = TripManager.getDist(_order[i], _order[j]);

                // If j is closer than nn, nn becomes j
                if(d2 < d1) {
                    nn = j;
                    d1 = d2;
                }
            }

            // Swap nn w/ i+1
            int tmp = _order[i+1];
            _order[i+1] = _order[nn];
            _order[nn] = tmp;

            _tripLength += d1;
        }

        _tripLength += TripManager.getDist(_order[_numStops], _order[_numStops-1]);

    }

    private void do2Opt() {
        boolean improved = true;
        while(improved) {
            improved = false;
            for(int i = 0; i <= _numStops-3; i++) {
                for(int k =i+2; k < _numStops-1; k++) {
                    int delta = -TripManager.getDist(_order[i],_order[i+1])-TripManager.getDist(_order[k],_order[k+1])
                            +TripManager.getDist(_order[i],_order[k])+TripManager.getDist(_order[i+1],_order[k+1]);

                    if (delta < 0) {
                        twoOptSwap(i+1, k);
                        _tripLength += delta;
                        improved = true;
                    }
                }
            }
        }
    }

    private void do3Opt() {
        boolean improved = true;
        // Preallocating to avoid allocations in the loop
        int cDiff=0;
        int minPattern=0;
        int[] deltas = new int[7];
        while(improved) {
            improved = false;
            for(int i = 0; i <= _numStops-5; i++) {
                for(int k =i+2; k < _numStops-3; k++) {
                    for(int j =k+2; j < _numStops-1; j++) {
                        minPattern=0;

                        cDiff =0- TripManager.getDist(_order[i], _order[i + 1])
                                    - TripManager.getDist(_order[k], _order[k + 1])
                                    - TripManager.getDist(_order[j], _order[j + 1]);

                        // 2-opt cases
                        deltas[0] = -TripManager.getDist(_order[i],_order[i+1])-TripManager.getDist(_order[k],_order[k+1])
                                +TripManager.getDist(_order[i],_order[k])+TripManager.getDist(_order[i+1],_order[k+1]);

                        deltas[1] = -TripManager.getDist(_order[i],_order[i+1])-TripManager.getDist(_order[j],_order[j+1])
                                +TripManager.getDist(_order[i],_order[j])+TripManager.getDist(_order[i+1],_order[j+1]);

                        deltas[2] = -TripManager.getDist(_order[j],_order[j+1])-TripManager.getDist(_order[k],_order[k+1])
                                +TripManager.getDist(_order[j],_order[k])+TripManager.getDist(_order[j+1],_order[k+1]);

                        // 3-opt cases
                        deltas[3] = cDiff
                                + TripManager.getDist(_order[i], _order[j])
                                + TripManager.getDist(_order[i + 1], _order[k])
                                + TripManager.getDist(_order[i + 1], _order[k + 1]);

                        deltas[4] = cDiff
                            + TripManager.getDist(_order[i], _order[k])
                            + TripManager.getDist(_order[j + 1], _order[i + 1])
                            + TripManager.getDist(_order[j], _order[k + 1]);

                        deltas[5] = cDiff
                                + TripManager.getDist(_order[i], _order[j+1])
                                + TripManager.getDist(_order[k], _order[j])
                                + TripManager.getDist(_order[i+1], _order[k + 1]);

                        deltas[6] = cDiff
                                + TripManager.getDist(_order[i], _order[j+1])
                                + TripManager.getDist(_order[k], _order[i+1])
                                + TripManager.getDist(_order[j], _order[k + 1]);

                        int delta = 0;
                        for(int index = 0; i < 7; i++) {
                            if(deltas[index] < delta) {
                                delta = deltas[index];
                                minPattern = index;
                            }
                        }

                        if(delta < 0) {
                            switch (minPattern) {
                                case 0: {
                                    twoOptSwap(i + 1, k);
                                    _tripLength += delta;
                                    improved = true;
                                    break;
                                }
                                case 1: {
                                    twoOptSwap(i+ 1, j);
                                    _tripLength += delta;
                                    improved = true;
                                    break;
                                }
                                case 2: {
                                    twoOptSwap(j + 1, k);
                                    _tripLength += delta;
                                    improved = true;
                                    break;
                                }
                                case 3: {
                                    twoOptSwap(i + 1, j);
                                    twoOptSwap(j + 1, k);
                                    _tripLength += delta;
                                    improved = true;
                                    break;
                                }

                                case 4: {
                                    twoOptSwap(i + 1, j);
                                    twoOptSwap(i + 1, k);
                                    _tripLength += delta;
                                    improved = true;
                                    break;
                                }

                                case 5: {
                                    twoOptSwap(j + 1, k);
                                    twoOptSwap(i + 1, k);
                                    _tripLength += delta;
                                    improved = true;
                                    break;
                                }

                                case 6: {
                                    twoOptSwap(i + 1, j);
                                    twoOptSwap(j + 1, k);
                                    twoOptSwap(i + 1, k);
                                    _tripLength += delta;
                                    improved = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Swap the _order of trip for destination i..j
    private void twoOptSwap(int i, int k) {
        while(i<k) {
            int tmp = _order[i];
            _order[i] = _order[k];
            _order[k] = tmp;
            i++; k--;
        }
    }
}
