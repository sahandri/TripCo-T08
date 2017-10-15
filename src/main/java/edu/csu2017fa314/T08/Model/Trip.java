package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;

public class Trip implements Comparable<Trip> {
    private ArrayList<String> _stops;
    private int _length = 0;

    Trip() {
        _stops = new ArrayList<>();
    }

    public Trip(ArrayList<String> stops) {
        _stops = new ArrayList<>();
        for(String stop : stops) {
            add(stop);
        }
    }

    void add(String id) {
        _stops.add(id);
        if(_stops.size() > 1) {
            _length += Model.getDistance(_stops.get(size()-2), _stops.get(size()-1), false);
        }
    }

    int length() {
        return _length;
    }

    int size() { return _stops.size(); }

    String get(int i) {
        return _stops.get(i);
    }

    @Override
    public int compareTo(Trip t) {
        return(_length - t.length());
    }

}

