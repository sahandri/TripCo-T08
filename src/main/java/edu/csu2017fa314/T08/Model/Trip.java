package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;

public class Trip {
    private ArrayList<String> _stops;
    private int _length = 0;

    Trip() {
        _stops = new ArrayList<>();
    }

    public Trip(ArrayList<String> stops) {
    }

    void addStop(String id) {
        _stops.add(id);
        if(_stops.size() > 1) {
            _length += 1;
        }
    }

    int length() {
        return _length;
    }

    int size() { return _stops.size(); }

    String get(int i) {
        return _stops.get(i);
    }

}

