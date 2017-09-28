package edu.csu2017fa314.T08;

import java.io.IOException;
import edu.csu2017fa314.T08.Model.Destination;
import edu.csu2017fa314.T08.View.Itinerary;
import edu.csu2017fa314.T08.View.makeSvg;
public class TripCo
{

    private String name = "";

    public String getName()
    {
        return name;
    }

    public String getMessage()
    {
        if (name == "")
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
            Itinerary.createJSON(args[1]);
            Itinerary.printJSON();
            makeSvg.createTripFile(args[2]);
        }
        catch(IOException e) {
            System.err.println("Failed to generate JSON, caught IOException: " + e.getMessage());
        }
    }

}
