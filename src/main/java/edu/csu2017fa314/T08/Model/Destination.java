package edu.csu2017fa314.T08.Model;
import java.io.*;
import java.util.ArrayList;
public class Destination {
	   static String csvfile;
	   static int index;		//to use in getters by ID input
	   static BufferedReader br;
	   static String line;
	   static ArrayList<String[]> list = new ArrayList<String[]>();
	   static int ID,Name,City,Latitude,Longitude,ElevationFt;  //index of string[]

	   public Destination() 
	   {
	      csvfile = "brews.csv";
	      line = "";
	   }
	   
	   //sets the order of input file and initializes index of string[]
	   public static void setOrder(String[] firstLine) {
		   for(int i=0;i<firstLine.length;i++) {
			   String order = firstLine[i];
			   order = order.replaceAll(" ", "");
			   order = order.toLowerCase();
			   switch(order) {
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
	   
	   //read brews.csv file and stores it in an Arraylis of arrays of string
	   public static void readFile() {
		   try {
               InputStream is = Destination.class.getResourceAsStream(csvfile);
			   br = new BufferedReader(new InputStreamReader(is));
			   line = br.readLine();
			   String[] firstLine = line.split(",");
			   setOrder(firstLine);
			   while ((line = br.readLine()) != null) {
				   if(!line.equals("")) {
					   line = line.replaceAll(", ", ",");
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
	   
	   public static String getID(int index) {
		   return list.get(index)[ID];
	   }
	   
	   public static String getName(int index) {
		   return list.get(index)[Name];
	   }
	   
	   public static String getCity(int index) {
		   return list.get(index)[City];
	   }
	   
	   public static String getLatit(int index) {
		   return list.get(index)[Latitude];
	   }
	   
	   public static String getLongit(int index) {
		   return list.get(index)[Longitude];
	   }
	   
	   public static String getElevation(int index) {
		   return list.get(index)[ElevationFt];
	   }
	   
	   //find the index of an ID
	   public static void setIndex(String ID) {
		   for(int i=0; i<list.size();i++) {
			   if(ID.equalsIgnoreCase(getID(i))) {
				   index = i;
			   }
		   }
	   }
	   
	   
	   public static String getName(String ID) {
		   setIndex(ID);
		   return list.get(index)[Name];
	   }
	   
	   public static String getCity(String ID) {
		   setIndex(ID);
		   return list.get(index)[City];
	   }
	   
	   public static String getLatit(String ID) {
		   setIndex(ID);
		   return list.get(index)[Latitude];
	   }
	   
	   public static String getLongit(String ID) {
		   setIndex(ID);
		   return list.get(index)[Longitude];
	   }
	   
	   public static String getElevation(String ID) {
		   setIndex(ID);
		   return list.get(index)[ElevationFt];
	   }
	   
	   public static int getTotal() {
		   return list.size();
	   }



}
