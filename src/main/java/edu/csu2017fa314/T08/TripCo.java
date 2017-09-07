package edu.csu2017fa314.T08;
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
        System.out.println("Welcome to TripCo");
        Destination.readFile();
        Itinerary.createJSON();
    }

}
