package edu.csu2017fa314.T08;

import edu.csu2017fa314.T08.Model.DataBase;
import edu.csu2017fa314.T08.Model.Model;
import edu.csu2017fa314.T08.View.makeSvg;

public class TripCo

{
    public static void main(String[] args) {
        System.out.println("Welcome to TripCo");
        DataBase.connect();
        DataBase.getID("");
        Model.setUp();
        makeSvg.addTripFile(args[0].substring(5,args[0].length()-4) + ".svg");
        String svg = makeSvg.getSvg();

    }

}
