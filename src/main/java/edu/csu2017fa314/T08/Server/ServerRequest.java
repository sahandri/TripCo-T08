package edu.csu2017fa314.T08.Server;

public class ServerRequest {
    private String request = "";
    private int units = 0; 
    private String description = "";

    public ServerRequest(String request, String description, int units) {
        this.request = request;
        this.description = description;
    }

    public String getRequest() {
        return request;
    }

    public void setQuery(String request) {
        this.request = request;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Request{" +
                "request='" + request + '\'' +
                ", description='" + description + '\'' + 
		", units='" + units + '\'' +
                '}';
    }
}
