package edu.csu2017fa314.T08.Server;

import java.util.ArrayList;
import java.util.Arrays;

public class ServerQueryResponse {
    private String response = "query";
    private ArrayList<Location> locations;

    public ServerQueryResponse(ArrayList locations) {
        this.locations = locations;
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "response='" + response + '\'' +
                ", locations=" + locations +
                '}';
    }
}
