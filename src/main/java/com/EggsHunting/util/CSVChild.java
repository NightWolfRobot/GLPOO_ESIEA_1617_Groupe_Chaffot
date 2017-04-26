package com.EggsHunting.util;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.EggsHunting.model.Child;
import com.EggsHunting.model.Movement;
import com.EggsHunting.model.Orientation;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


public class CSVChild {
	private static Logger log = Logger.getLogger(CSVChild.class);
	private static CSVReader reader;
	private static CSVWriter writer;

	
	public static ArrayList<Child> getChildren(String path){ //  /csv/children.csv
		try{
			reader = new CSVReader(new InputStreamReader(CSVChild.class.getResourceAsStream(path)), ' ');
		}
		catch(Exception e){
			log.error("Impossible to access the CSV file"+ e.toString());
		}
		
		ArrayList<Child> children = new ArrayList<>();
		
		String [] nextLine;
	     try {
	    	log.debug("Debut du parsing");
			while ((nextLine = reader.readNext()) != null) {
				//log.debug(nextLine[0]);
			    // nextLine[] is an array of values from the line
			    log.debug(nextLine[0] + " "+ nextLine[1] +" "+ nextLine[2] +" "+nextLine[3] +" "+ nextLine[4]); 
			    children.add(new Child(turnStringIntoCoordinates(nextLine[1]), turnStringIntoOrientation(nextLine[2]), turnStringIntoPath(nextLine[3]), nextLine[4]));
			    
			 }
		} catch (IOException e) {
			log.fatal("Erreur lors du parsing du CSV");
			//e.printStackTrace();
		}
		
		log.info("Children loaded from CSV");
		return children;
	}
	
	private static ArrayList<Movement> turnStringIntoPath(String str){
		ArrayList<Movement> mvt = new ArrayList<>();
		for(int i=0; i< str.length(); i++){
			switch(str.charAt(i)){
				case 'G': 
					mvt.add(Movement.LEFT);
					break;
				case 'D':
					mvt.add(Movement.RIGHT);
					break;
				case 'A':
					mvt.add(Movement.FORWARD);
					break;
			}
		}
		return mvt;
	}
	
	private static Point turnStringIntoCoordinates(String str){
		Point p = new Point();
		p.x = Integer.parseInt(""+str.charAt(0)); 
		p.y = Integer.parseInt(""+str.charAt(2)); 
		return p;
	}
	
	private static Orientation turnStringIntoOrientation(String str){
		switch(str.charAt(0)){
			case 'N': return Orientation.NORTH;
			case 'E': return Orientation.EAST;
			case 'W': return Orientation.WEST;
			case 'S': return Orientation.SOUTH;
			default: return Orientation.NORTH;
		}
	}
	
	public static void saveChildren(String filename, ArrayList<Child> children){
		String outputFile = filename+".csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
		if(alreadyExists){
			log.info("The file to save in already exists");
		}
		try{
			writer = new CSVWriter(new FileWriter(outputFile), '\t', CSVWriter.NO_QUOTE_CHARACTER);
		}catch(IOException e){
			log.error("Impossible to access the CSV file to save"+ e.toString());
		}

	    String[] line; 
	    Child child;
	    for(int i=0; i<children.size(); i++){
	    	child = children.get(i);
	    	line = new String[5];
	    	line[0] = "E"; //Child
	    	Point p = child.getPosition();
	    	line[1] = String.valueOf((int)p.getX())+"-"+String.valueOf((int)p.getY()); //position
	    	line[2] = child.getOrientation().toString(); //orientation
	    	ArrayList<Movement> path = child.getPath();
	    	line[3] = pathToString(path); //movements
	    	line[4] = child.getName(); //name
	    	writer.writeNext(line);
	    }
	    
		
	    try{
	    	writer.close();
	    }catch(IOException e){
	    	log.error("Impossible to close CSV writer"+ e.toString());
	    }
	    
		//return true;
	}
	
	private static String pathToString(ArrayList<Movement> path){
		String res = "";
		for(Movement move: path){
			res += move.toString();
		}
		return res;
	}
}
