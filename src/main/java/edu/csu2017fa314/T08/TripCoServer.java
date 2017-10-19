package edu.csu2017fa314.T08;

import edu.csu2017fa314.T08.Model.DataBase;
import edu.csu2017fa314.T08.Model.Model;
import edu.csu2017fa314.T08.Server.Server;

public class TripCoServer {
    public static void main(String[] args) {
        System.out.println("Welcome to TripCo");
        Server s = new Server();
        DataBase.connect();
        Model.setUp();
        s.serve();
    }
}
