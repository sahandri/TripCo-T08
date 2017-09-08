package edu.csu2017fa314.T08;

import java.io.IOException;
import edu.csu2017fa314.T08.Model.Destination;
import edu.csu2017fa314.T08.View.Itinerary;
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
        System.out.println("Unwelcome to TripCo");

        try {
            Destination.readFile(args[0]);
//            Itinerary.createJSON();
            Itinerary.printJSON();
        }
        catch(IOException e) {
            System.err.println("Failed to generate JSON, caught IOException: " + e.getMessage());
        }
    }

}
