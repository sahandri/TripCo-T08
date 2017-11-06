package edu.csu2017fa314.T08.Model;


import java.sql.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;

public class DataBase {
    private static String myDriver = "com.mysql.jdbc.Driver";
    private static String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
    private static String user = "sahandri";
    private static String pass = "830564036";
    private static ResultSet rs;
    private static Statement st;
    private static Connection conn;
    private static ArrayList<String> list = new ArrayList<String>();

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

    public static boolean isConnected() {
        try {
            return conn.isValid(5);
        } catch (SQLException e) {
            return false;
        }
    }

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

    public static String getID(int index) {
        return list.get(index);
    }



    public static ArrayList getID(String key) {
        list.clear();
        String query = "SELECT airports.id FROM continents" +
                " INNER JOIN countries ON continents.code = countries.continent" +
                " INNER JOIN regions ON countries.code = regions.iso_country" +
                " INNER JOIN airports ON regions.code = airports.iso_region" +
                " WHERE countries.name LIKE '%" + key + "%' OR regions.name LIKE '%" + key + "%'" +
                " OR airports.name LIKE '%" + key + "%' OR airports.municipality LIKE '%" + key + "%'";
        try {
            rs = st.executeQuery(query);
            // iterate through the query results and return list of IDs
            while (rs.next()) {
                list.add(rs.getString("id"));
            }
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
        return list;
    }


    public static String getCountry(String id){
        String country = "";
        String query = "SELECT countries.name FROM continents" +
                " INNER JOIN countries ON continents.code = countries.continent" +
                " INNER JOIN regions ON countries.code = regions.iso_country" +
                " INNER JOIN airports ON regions.code = airports.iso_region" +
                " WHERE airports.id LIKE '" + id + "'";
        try {
            rs = st.executeQuery(query);
            // iterate through the query results and print selected columns
            rs.next();
            country = rs.getString("countries.name");
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
        return country;
    }


    public static String getRegion(String id){
        String region = "";
        String query = "SELECT regions.name FROM continents" +
                " INNER JOIN countries ON continents.code = countries.continent" +
                " INNER JOIN regions ON countries.code = regions.iso_country" +
                " INNER JOIN airports ON regions.code = airports.iso_region" +
                " WHERE airports.id LIKE '" + id + "'";
        try {
            rs = st.executeQuery(query);
            // iterate through the query results and print selected columns
            rs.next();
            region = rs.getString("regions.name");
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
        return region;
    }


    public static int getIndex(String ID){
        int index = 0;
        for(int i=0; i<list.size();i++) {
            String tgt = getID(i);
            if(ID.equalsIgnoreCase(tgt)) {
                index = i;
                return index;
            }
        }
        throw new EmptyStackException();
    }


    public static int getTotal() {
        return list.size();
    }



    public static String getLatit(String id) {
        String lotit = "";
        String query = "SELECT latitude FROM airports WHERE id LIKE '" + id + "'";
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


    public static String getName(String id){
        String name = "";
        String query = "SELECT name FROM airports WHERE id LIKE '" + id+ "'";
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


    public static String getLongit(String id) {
        String longit = "";
        String query = "SELECT longitude FROM airports WHERE id LIKE '" + id + "'";
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

//returns a pair of all data in a row
    public static  HashMap<String,String> getInfo(String id) {
        HashMap<String,String> info = new HashMap<String, String>();
        String query = "SELECT * FROM airports WHERE id LIKE '" + id + "'";
        try {
            rs = st.executeQuery(query);
            // iterate through the query results and return selected columns
            rs.next();
            info.put("id",rs.getString("id"));
            info.put("name",rs.getString("name"));
            info.put("latitude",rs.getString("latitude"));
            info.put("longitude",rs.getString("longitude"));
            info.put("elevation",rs.getString("elevation"));
            info.put("municipality",rs.getString("municipality"));
            info.put("home_link",rs.getString("home_link"));
            info.put("wikipedia_link",rs.getString("wikipedia_link"));
        } catch (Exception e) { // catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
        return info;
    }




    public static void print() {
        String query = "SELECT * FROM airports";
        try {
            rs = st.executeQuery(query);
            // iterate through the query results and print selected columns
            while (rs.next()) {
                String id = rs.getString("id");
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

        System.out.printf("%s\n", getLatit("7704"));
        System.out.printf("%s\n", getLongit("7704"));
        System.out.printf("%s\n", getID(1));
        System.out.printf("%s\n", getIndex("7704"));
        System.out.printf("%s\n", getName("7704"));
        System.out.printf("%s\n", getTotal());
        System.out.printf("get name of id: %s\n", getInfo("7704").get("name"));
        System.out.printf("get elevation of id: %s\n", getInfo("7704").get("wikipedia_link"));
        System.out.printf("country name: %s\n", getCountry("7704"));
        System.out.printf("region name: %s\n", getRegion("7704"));


        disconnect();
    }
}
