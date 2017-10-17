package edu.csu2017fa314.T08;

import java.io.IOException;
import edu.csu2017fa314.T08.Model.Destination;
import edu.csu2017fa314.T08.Model.Model;
import edu.csu2017fa314.T08.View.Itinerary;
import edu.csu2017fa314.T08.View.makeSvg;
public class TripCo
<<<<<<< HEAD

=======
>>>>>>> fa9939f4e67520f2e29c4ad5ddbda256eb96e41f

{
    public static void main(String[] args) {
        System.out.println("Welcome to TripCo");
        try {
            Destination.readFile(args[0]);
            Model.setUp();
            Itinerary.createJSON(args[0].substring(5,args[0].length()-4) + "_Trip.json");
            //makeSvg.createTripFile(args[3]);
            //makeSvg.addTripFile(args[3]);
            makeSvg.addTripFile(args[0].substring(5,args[0].length()-4) + ".svg");
<<<<<<< HEAD
=======

>>>>>>> fa9939f4e67520f2e29c4ad5ddbda256eb96e41f
        }
        catch(IOException e) {
            System.err.println("Failed to generate JSON, caught IOException: " + e.getMessage());
        }
    }

}
