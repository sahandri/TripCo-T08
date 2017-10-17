package edu.csu2017fa314.T08.Model;

import java.util.ArrayList;
import java.util.Collections;
<<<<<<< HEAD

=======
>>>>>>> fa9939f4e67520f2e29c4ad5ddbda256eb96e41f
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class TripManager {
    public static ArrayList<Trip> trips;
    public static AtomicInteger total;
    public static String[] stops;
<<<<<<< HEAD
=======

>>>>>>> fa9939f4e67520f2e29c4ad5ddbda256eb96e41f
    static Trip shortest() {
        return trips.get(0);
    }

    public static Trip get(String id) {
        for(Trip t: trips) {
            if(t.get(0).equals(id)) {
                return t;
            }
        }

        return trips.get(0);
    }

<<<<<<< HEAD

=======
>>>>>>> fa9939f4e67520f2e29c4ad5ddbda256eb96e41f
    public static void buildTripList() {
        total = new AtomicInteger(Destination.getTotal());
        trips = new ArrayList<>();
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        ArrayList<Future<Trip>> results = new ArrayList<>();

        stops = new String[total.get()];

        for(int i = 0; i < total.get(); i++) {
            stops[i] = Destination.getID(i);
        }

        for(int i = 0; i < total.get(); i++) {
            TripWorker tw = new TripWorker(i);
            Future<Trip> res = pool.submit(tw);
            results.add(res);

        }
        pool.shutdown();
        while(!pool.isTerminated()) { }

        try {
            for(Future<Trip> ft : results) {
                    trips.add(ft.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Collections.sort(trips);
    }

}
