package edu.csu2017fa314.T08;

import java.io.IOException;
import edu.csu2017fa314.T08.Model.Destination;
import edu.csu2017fa314.T08.Model.Model;
import edu.csu2017fa314.T08.View.Itinerary;
import edu.csu2017fa314.T08.View.makeSvg;
import edu.csu2017fa314.T08.View.CSV;
public class TripCo
{

    private String name = "";

    public String getName()
    {
        return name;
    }

    String getMessage()
    {
        if (name.equals(""))
        {
            return "Hello!";
        }
        else
        {
            return "Hello " + name + "!";
        }
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to TripCo");

        try {
            Destination.readFile(args[0]);
            //Itinerary.createJSON(args[1]);
	    Itinerary.createJSON(args[0].substring(5,args[0].length()-4) + "_ShortTrip.json");
            Itinerary.printJSON();
            //CSV.createJSON(args[2]);
	    CSV.createJSON(args[0].substring(5,args[0].length()-4) + "_Csv.json");
            //CSV.printJSON();
            //makeSvg.createTripFile(args[3]);
	    //makeSvg.addTripFile(args[3]);
	    makeSvg.addTripFile(args[0].substring(5,args[0].length()-4) + ".svg");
        }
        catch(IOException e) {
            System.err.println("Failed to generate JSON, caught IOException: " + e.getMessage());
        }
    }

}
