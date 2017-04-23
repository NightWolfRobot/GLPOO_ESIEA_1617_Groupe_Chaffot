package com.EggsHunting.util;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.EggsHunting.model.Child;
import com.EggsHunting.model.Movement;
import com.EggsHunting.model.Orientation;
import com.opencsv.CSVReader;


public class CSVChild {
	private static Logger log = Logger.getLogger(CSVChild.class);
	private CSVReader reader;
	
	public CSVChild(){
		try{
			reader = new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/csv/children.csv")), ' ');
		}
		catch(Exception e){
			log.error("Impossible to access the CSV file"+ e.toString());
		}
		
	}
	
	public ArrayList<Child> getChildren(){
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
	
	private ArrayList<Movement> turnStringIntoPath(String str){
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
	
	private Point turnStringIntoCoordinates(String str){
		Point p = new Point();
		p.x = Integer.parseInt(""+str.charAt(0)); 
		p.y = Integer.parseInt(""+str.charAt(2)); 
		return p;
	}
	
	private Orientation turnStringIntoOrientation(String str){
		switch(str.charAt(0)){
			case 'N': return Orientation.NORTH;
			case 'E': return Orientation.EAST;
			case 'W': return Orientation.WEST;
			case 'S': return Orientation.SOUTH;
			default: return Orientation.NORTH;
		}
	}
}
