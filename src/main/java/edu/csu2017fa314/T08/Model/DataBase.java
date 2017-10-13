package edu.csu2017fa314.T08.Model;

import java.sql.Connection; // https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html
import java.sql.DriverManager; // https://www.tutorialspoint.com/jdbc/
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class DataBase {
public static void main(String[] args){ // command line args contain username and password
String myDriver = "com.mysql.jdbc.Driver"; // add dependencies in pom.xml
String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314";
try { // connect to the database
Class.forName(myDriver);
Connection conn = DriverManager.getConnection(myUrl, "sahandri", "830564036");
try { // create a statement
Statement st = conn.createStatement();
try { // submit a query
String query = "SELECT * FROM airports LIMIT 10";
ResultSet rs = st.executeQuery(query);
try { // iterate through the query results and print selected columns
while (rs.next()) {
String id = rs.getString("id");
String name = rs.getString("name");
System.out.printf("%s,%s\n", id, name);
}
} finally { rs.close(); }
} finally { st.close(); }
} finally { conn.close(); }
} catch (Exception e) { // catches all exceptions in the nested try's
System.err.printf("Exception: ");
System.err.println(e.getMessage());
}}}