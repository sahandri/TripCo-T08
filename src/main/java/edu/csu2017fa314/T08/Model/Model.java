package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;

public class Model
{
   public static ArrayList<String> shortestTrip() {
       ShortestTrip trip = new ShortestTrip();
       return trip.getShortestTrip();
   }

   public static int getDistance(String id1, String id2, boolean useKm) {
       String lat1 = Destination.getLatit(id1);
       String lon1 = Destination.getLongit(id1);
       String lat2 = Destination.getLatit(id2);
       String lon2 = Destination.getLongit(id2);
       return 0;
   }
}
