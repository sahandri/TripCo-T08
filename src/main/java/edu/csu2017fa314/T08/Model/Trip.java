package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;

public class Trip {
    private ArrayList<String> _stops;
    private int _length = 0;

    public Trip() {
        _stops = new ArrayList<String>();
    }

    public Trip(ArrayList<String> stops) {
    }

    public void addStop(String id) {
        _stops.add(id);
        if(_stops.size() > 1) {
        }
    }

    public int length() {
        return _length;
    }

    public int size() { return _stops.size(); }

    public String get(int i) {
        return _stops.get(i);
    }

}

