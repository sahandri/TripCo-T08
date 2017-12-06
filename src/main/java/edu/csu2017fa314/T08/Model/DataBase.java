package edu.csu2017fa314.T08.Model;


import java.sql.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private static String myDriver = "com.mysql.jdbc.Driver";
    private static String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
    private static String user = "sahandri";
    private static String pass = "830564036";
    private static ResultSet rs;
    private static Statement st;
    private static Connection conn;
    private static ArrayList<String> list = new ArrayList<String>();

    //A method to connect to the database
    public static void connect() { // command line args contain username and password
        try { // connect to the database
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, user, pass);
            // create a statement
            st = conn.createStatement();
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
    }

    //checking if database is connected
    public static boolean isConnected() {
        try {
            return conn.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }

    //Disconnect from database after we're done
    public static void disconnect() {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
    }

    //returns id of the desired index in the saved list
    public static String getID(int index) {
            return list.get(index);
    }


    //Querrey the the database and search a key in it, save possible IDs in list, and return the list
    public static ArrayList getID(String key) {
        list.clear();
        String query = "SELECT airports.code FROM continents" +
                " INNER JOIN countries ON continents.code = countries.continent" +
                " INNER JOIN regions ON countries.code = regions.iso_country" +
                " INNER JOIN airports ON regions.code = airports.iso_region" +
                " WHERE countries.name LIKE '%" + key + "%' OR continents.name LIKE '%" + key + "%' OR regions.name LIKE '%" + key + "%'" +
                " OR airports.name LIKE '%" + key + "%' OR airports.municipality LIKE '%" + key + "%'";
        try {
            rs = st.executeQuery(query);
            // iterate through the query results and return list of IDs
            while (rs.next()) {
                list.add(rs.getString("code"));
            }
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
        return list;
    }


    //return index of ID in list
    public static int getIndex(String ID){
        return list.indexOf(ID);
    }

    //return total number of IDs in list
    public static int getTotal() {
        return list.size();
    }


    //Returns Latitude of an ID
    public static String getLatit(String id) {
        String lotit = "";
        String query = "SELECT latitude FROM airports WHERE code LIKE '" + id + "'";
        try {
            rs = st.executeQuery(query);
            // iterate through the query results and print selected columns
            rs.next();
            lotit = rs.getString("latitude");
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
        return lotit;
    }

    //Returns name of an ID
    public static String getName(String id){
        String name = "";
        String query = "SELECT name FROM airports WHERE code LIKE '" + id+ "'";
        try {
            rs = st.executeQuery(query);
            // iterate through the query results and get name
            rs.next();
            name = rs.getString("name");
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
        return name;
    }

    //Returns Longitude of an ID
    public static String getLongit(String id) {
        String longit = "";
        String query = "SELECT longitude FROM airports WHERE code LIKE '" + id + "'";
        try {
            rs = st.executeQuery(query);
            // iterate through the query results and get longitude
            rs.next();
            longit = rs.getString("longitude");
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
        return longit;
    }

    //returns a pair of all data of an ID
    public static  HashMap<String,String> getInfo(String id) {
        HashMap<String,String> info = new HashMap<String, String>();
        //String query = "SELECT * FROM airports WHERE code LIKE '" + id + "'";
        String query = "SELECT * FROM continents" +
                " INNER JOIN countries ON continents.code = countries.continent" +
                " INNER JOIN regions ON countries.code = regions.iso_country" +
                " INNER JOIN airports ON regions.code = airports.iso_region" +
                " WHERE airports.code LIKE '%" + id + "%'";
        try {
            rs = st.executeQuery(query);
            // iterate through the query results and return selected columns
            rs.next();
            info.put("id",rs.getString("airports.code"));
            info.put("name",rs.getString("airports.name"));
            info.put("latitude",rs.getString("airports.latitude"));
            info.put("longitude",rs.getString("airports.longitude"));
            info.put("elevation",rs.getString("airports.elevation"));
            info.put("municipality",rs.getString("airports.municipality"));
            info.put("region",rs.getString("regions.name"));
            info.put("country",rs.getString("countries.name"));
            info.put("continent",rs.getString("continents.name"));
            info.put("home_link",rs.getString("airports.home_link"));
            info.put("wikipedia_link",rs.getString("airports.wikipedia_link"));
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
        return info;
    }



/*
    public static void print() {
        String query = "SELECT * FROM airports";
        try {
            rs = st.executeQuery(query);
            // iterate through the query results and print selected columns
            while (rs.next()) {
                String id = rs.getString("code");
                String name = rs.getString("name");
                System.out.printf("%s,%s\n", id, name);
            }
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
    }



    public static void main(String[] args) {
        ArrayList<String> list;


        connect();

        //print();

        list = getID("denver");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%s\n", list.get(i));
        }

        System.out.printf("%s\n", getLatit("CND7"));
        System.out.printf("%s\n", getLongit("CND7"));
        System.out.printf("%s\n", getID(1));
        System.out.printf("%s\n", getIndex("CND7"));
        System.out.printf("%s\n", getName("CND7"));
        System.out.printf("%s\n", getTotal());
        System.out.printf("get name of id: %s\n", getInfo("CND7").get("name"));
        System.out.printf("get country name of id: %s\n", getInfo("CND7").get("country"));
        //System.out.printf("country name: %s\n", getCountry("CND7"));
        //System.out.printf("region name: %s\n", getRegion("CND7"));

        HashMap<String,String> info = getInfo("CND7");
        for(Map.Entry<String,String> kv: info.entrySet()) {
            System.out.println(kv.getKey()+" "+ kv.getValue());
        }


        disconnect();
    }
    */
}
