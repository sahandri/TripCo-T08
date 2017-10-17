package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;

public class Trip implements Comparable<Trip> {
    private ArrayList<String> _stops;
    private int _length = 0;

    Trip() {
        _stops = new ArrayList<>();
    }
    Trip(int len) { _stops = new ArrayList<>(len); }

    public Trip(ArrayList<String> stops, int length) {
        _stops = stops;
        _length = length;
    }

    public void set(int i, String s) { _stops.set(i, s); }
    public void add(String s) {
        _stops.add(s);
    }

    public void setLength(int length) { _length = length; }

<<<<<<< HEAD

=======
>>>>>>> fa9939f4e67520f2e29c4ad5ddbda256eb96e41f
    int length() {
        return _length;
    }

    int size() { return _stops.size(); }

    String get(int i) {
        return _stops.get(i);
    }

    ArrayList<String> stops() { return _stops; }

    @Override
    public int compareTo(Trip t) {
        return(_length - t.length());
    }

}

