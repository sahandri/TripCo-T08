package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;

public class Model
{
   public static ArrayList<String> shortestTrip() {
       ArrayList<String> order = new ArrayList<String>();

       order = ShortestTrip.getShortestTrip();

       return order;
   }
}
