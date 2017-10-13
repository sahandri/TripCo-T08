package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;

public class Trip {
    private ArrayList<String> _stops;
    private int _length = 0;

    public Trip() {
    }

    public Trip(ArrayList<String> stops) {
    }

    public void addStop(String id) {
    }

    public int length() {
        return _length;
    }

    public String get(int i) {
        return _stops.get(i);
    }

}

