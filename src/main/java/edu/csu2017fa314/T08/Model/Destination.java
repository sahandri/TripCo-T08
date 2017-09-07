package edu.csu2017fa314.T08.Model;
import java.io.*;
import java.util.ArrayList;
public class Destination {
	   String csvfile;
	   int index;		//to use in getters by ID input
	   BufferedReader br;
	   String line;
	   ArrayList<String[]> list = new ArrayList<String[]>();
	   static int ID,Name,City,Latitude,Longitude,ElevationFt;  //index of string[]

	   public Destination() 
	   {
	      csvfile = "brews.csv";
	      line = "";
	   }
	   
	   //sets the order of input file and initializes index of string[]
	   public void setOrder(String[] firstLine) {
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
	   public void readFile() {
		   try {
               InputStream is = Destination.class.getResourceAsStream(csvfile);
			   br = new BufferedReader(new InputStreamReader(is));
			   line = br.readLine();
			   String[] firstLine = line.split(",");
			   setOrder(firstLine);
			   while ((line = br.readLine()) != null) {
				   line = line.replaceAll(", ", ",");
				   String[] data = line.split(",");
				   list.add(data);
			   }
		   }catch (IOException e) {
			   e.printStackTrace();
		   }finally {
			   try {
				   br.close();
			   }catch(IOException e) {
				   e.printStackTrace();
			   }
			   
		   }
		   
	   }
	   
	   
	   
	   
	   public void print() {
		   for(int i=0;i< list.size();i++) {
			   String[] temp = list.get(i);
			   for(int j=0;j<temp.length;j++) {
				   System.out.print(temp[j]);
				   System.out.print(",");
			   }
			   System.out.print("\n");
		   }
	   }
	   
	   public String getID(int index) {
		   return list.get(index)[ID];
	   }
	   
	   public String getName(int index) {
		   return list.get(index)[Name];
	   }
	   
	   public String getCity(int index) {
		   return list.get(index)[City];
	   }
	   
	   public String getLatit(int index) {
		   return list.get(index)[Latitude];
	   }
	   
	   public String getLongit(int index) {
		   return list.get(index)[Longitude];
	   }
	   
	   public String getElevation(int index) {
		   return list.get(index)[ElevationFt];
	   }
	   
	   //find the index of an ID
	   public void setIndex(String ID) {
		   for(int i=0; i<list.size();i++) {
			   if(ID.equalsIgnoreCase(getID(i))) {
				   index = i;
			   }
		   }
	   }
	   
	   
	   public String getName(String ID) {
		   setIndex(ID);
		   return list.get(index)[Name];
	   }
	   
	   public String getCity(String ID) {
		   setIndex(ID);
		   return list.get(index)[City];
	   }
	   
	   public String getLatit(String ID) {
		   setIndex(ID);
		   return list.get(index)[Latitude];
	   }
	   
	   public String getLongit(String ID) {
		   setIndex(ID);
		   return list.get(index)[Longitude];
	   }
	   
	   public String getElevation(String ID) {
		   setIndex(ID);
		   return list.get(index)[ElevationFt];
	   }


}
