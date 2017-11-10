package edu.csu2017fa314.T08.Server;

public class ServerRequest {
    private String request = "";
    private String description = "";
	private String[] arrayDescription;

    public ServerRequest(String request, String description) {
        this.request = request;
        this.description = description;
    }

	public void ServerArrayRequest(String request, String[] description) {
        this.request = request;
        this.arrayDescription = description;
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

	public String[] getArrayDescription() {
        return arrayDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Request{" +
                "request='" + request + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
