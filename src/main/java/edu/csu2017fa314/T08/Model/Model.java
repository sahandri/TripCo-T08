package edu.csu2017fa314.T08.Model;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    public static ArrayList<String> shortestTrip() {
        return TripManager.shortest();
    }

    public static ArrayList<String> search(String key) {
        return DataBase.getID(key);
    }

	public static ArrayList<String> shortestTrip(ArrayList<String> destList, int optLevel) {
        TripManager.clear();
        TripManager.setOptLevel(optLevel);
        return TripManager.shortest(destList);
    }

    public static HashMap getInfo(String id) {
        return DataBase.getInfo(id);
    }

    public static int getDistance(String id1, String id2, boolean useKm) {
        int distance;
        String lat1 = DataBase.getLatit(id1);
        String lon1 = DataBase.getLongit(id1);
        String lat2 = DataBase.getLatit(id2);
        String lon2 = DataBase.getLongit(id2);

        distance = Distance.distanceMi(lat1,lon1,lat2,lon2);

        return distance;
    }

    public static String getID(int idx) {
        return DataBase.getID(idx);
    }

    /*
    * Populates the distLookUp table by calculating the distance between every point.
    */
}
