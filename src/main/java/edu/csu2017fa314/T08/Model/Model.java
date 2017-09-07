package edu.csu2017fa314.T08.Model;
import java.io.*;
import java.util.ArrayList;

public class Model
{
   private int[] numbers;
   String csvfile;
   BufferedReader br;
   String line;
   ArrayList<String[]> list = new ArrayList<String[]>();
   static int ID,Name,City,Latitude,Longitude,ElevationFt;  //index of string[]

   public Model () 
   {
      numbers = new int[] {0, 1, 2, 3, 4, 5};
      csvfile = "C:\\Users\\Sahand\\eclipse-workspace-2\\Tripco\\brews.csv";
      line = "";
   }

   public int[] getNumbers() 
   {
      return numbers;
   }
   
   //sets the order of input file and initializes index of string[]
   public void setOrder(String[] firstLine) {
	   for(int i=0;i<firstLine.length;i++) {
		   String order = firstLine[i];
		   switch(order) {
		   case "ID": ID = i;
		   case "Name": Name = i;
		   case "City": City = i;
		   case "Latitude": Latitude = i;
		   case "Longitude": Longitude = i;
		   case "ElevationFt": ElevationFt = i;
		   }
	   }
   }
   
   //read brews.csv file and stores it in an Arraylis of arrays of string
   public void readFile() {
	   try {
		   br = new BufferedReader(new FileReader(csvfile));
		   line = br.readLine();
		   String[] firstLine = line.split(",");
		   setOrder(firstLine);
		   while ((line = br.readLine()) != null) {
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

}
