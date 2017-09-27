package edu.csu2017fa314.T08.Model;
import java.io.*;
/*
* this class reads input .csv file and stores each line in an array then adds that array to an arraylist
* we will have all the data in arraylist of array.
* to access the data there are getters by ID or index
*/
import java.util.ArrayList;
public class Destination {
	   static int index;		//to use in getters by ID input
	   static BufferedReader br;
	   static String line;
	   static ArrayList<String[]> list = new ArrayList<String[]>(); //arraylist to store arrays
	   static int ID,Name,City,Latitude,Longitude,ElevationFt;  //index of string[]

	   //constructor
	   public Destination() 
	   {
	      line = "";
	   }
	   
	  
	   /*
	   * read input file to buffer, then read the first line which includes the order of iput file,
	   * seperate them by "'", then calls setOrde() to determine the order of data
	   * then reads the rest of the lines, seperate them by "'", and store each line in an array,
	   * then add each array in to the arraylist
	   */
	   public static void readFile(String filename) {
		   try {
			   br = new BufferedReader(new FileReader(filename));
			   line = br.readLine();
			   String[] firstLine = line.split(",");
			   setOrder(firstLine);
			   while ((line = br.readLine()) != null) {
				   if(!line.equals("")) {  // if line is not empty stores it, otherwise ignores it
					   line = line.replaceAll(", ", ","); //if input data has space after comma, removes space
					   String[] data = line.split(",");
					   list.add(data);
				   }
			   }
		   }catch (IOException e) {
			   e.printStackTrace();
		   }catch(NullPointerException e) {
				   e.printStackTrace();
           }finally {
			   try {
				   br.close();
			   }catch(IOException e) {
				   e.printStackTrace();
			   }catch(NullPointerException e) {
				   e.printStackTrace();
			   }

			   
		   }
		   
	   }
	   
	   /*
	    * sets the order of input file by getting the first line, 
	    * and initializes index of each element in string[], which makes it easy to call them
	    */
	   public static void setOrder(String[] firstLine) {
		   for(int i=0;i<firstLine.length;i++) {
			   String order = firstLine[i];
			   order = order.replaceAll(" ", ""); //removes all the spaces
			   order = order.toLowerCase();	//makes it case insensitive
			   switch(order) {		//chacking index of each element
			   case "id": ID = i;
			   	break;
			   case "name": Name = i;
			   	break;
			   case "city": City = i;
			   	break;
			   case "latitude": Latitude = i;
			   	break;
			   case "longitude": Longitude = i;
			   	break;
			   case "elevation": ElevationFt = i;
			   	break;
			   default:
			   }
		   }
	   }
	   
	   
	   
	   //get ID by index of arraylist
	   public static String getID(int index) {
		   return list.get(index)[ID];
	   }
	   
	   //get Name by index of arraylist
	   public static String getName(int index) {
		   return list.get(index)[Name];
	   }
	   
	   //get City by index of arraylist
	   public static String getCity(int index) {
		   return list.get(index)[City];
	   }
	   
	   //get Latitude by index of arraylist
	   public static String getLatit(int index) {
		   return list.get(index)[Latitude];
	   }
	   
	   //get Longitude by index of arraylist
	   public static String getLongit(int index) {
		   return list.get(index)[Longitude];
	   }
	   
	   //get Elevation by index of arraylist
	   public static String getElevation(int index) {
		   return list.get(index)[ElevationFt];
	   }
	   
	   

	   //get Name by ID
	   public static String getName(String ID) {
		   setIndex(ID);
		   return list.get(index)[Name];
	   }
	   
	   //get City by ID
	   public static String getCity(String ID) {
		   setIndex(ID);
		   return list.get(index)[City];
	   }
	   
	   //get Latitude by ID
	   public static String getLatit(String ID) {
		   setIndex(ID);
		   return list.get(index)[Latitude];
	   }
	   
	   //get Longitude by ID
	   public static String getLongit(String ID) {
		   setIndex(ID);
		   return list.get(index)[Longitude];
	   }
	   
	   //get Elevation by ID
	   public static String getElevation(String ID) {
		   setIndex(ID);
		   return list.get(index)[ElevationFt];
	   }
	   
	   
	   //returns total number of destinations
	   public static int getTotal() {
		   return list.size();
	   }
	   
	   //find the index of an ID
	   public static void setIndex(String ID) {
		   for(int i=0; i<list.size();i++) {
			   if(ID.equalsIgnoreCase(getID(i))) {
				   index = i;
			   }
		   }
	   }
	   
	   
	   //prints the whole input file for testing case
	   public static void print() {
		   for(int i=0;i< list.size();i++) {
			   String[] temp = list.get(i);
			   for(int j=0;j<temp.length;j++) {
				   System.out.print(temp[j]);
				   System.out.print(",");
			   }
			   System.out.print("\n");
		   }
	   }



}