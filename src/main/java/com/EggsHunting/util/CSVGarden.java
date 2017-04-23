package com.EggsHunting.util;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.EggsHunting.model.Cell;
import com.EggsHunting.model.Egg;
import com.EggsHunting.model.Garden;
import com.EggsHunting.model.Rock;
import com.opencsv.CSVReader;

public class CSVGarden {
	private static Logger log = Logger.getLogger(CSVGarden.class);
	private static CSVReader reader;
	
	public static Garden getGarden(String path){
		try{
			reader = new CSVReader(new InputStreamReader(CSVGarden.class.getResourceAsStream(path)), ' ');
		}
		catch(Exception e){
			log.error("Impossible to access the CSV file"+ e.toString());
		}
		Garden garden = new Garden(0,0);
		
		String [] nextLine;
		
	     try {
	    	log.debug("Debut du parsing");
			while ((nextLine = reader.readNext()) != null) {
				/*
				J 6 5
				C 4-2 1
				C 1-4 3
				R 5-3 */
				if(nextLine[0].equals("J")){
					garden = new Garden(Integer.parseInt(nextLine[1]), Integer.parseInt(nextLine[2]));
					log.debug("Create garden x="+ nextLine[1]+" y="+ nextLine[2]);
				}else if(nextLine[0].equals("R")){
					Point p = turnStringIntoCoordinates(nextLine[1]);
					Cell c = garden.getCell(p.x, p.y);
					c.getItems().add(new Rock());
				}else if(nextLine[0].equals("C")){
					Point p = turnStringIntoCoordinates(nextLine[1]);
					Cell c = garden.getCell(p.x, p.y);
					int nb = Integer.parseInt(nextLine[2]);
					for(int i=0; i< nb; i++){
						c.getItems().add(new Egg());
					}
				}
			 }
		} catch (IOException e) {
			log.fatal("Erreur lors du parsing du CSV");
			//e.printStackTrace();
		}
		
		log.info("Garden loaded from CSV");
		
		return garden;
	}
	
	
	private static Point turnStringIntoCoordinates(String str){
		Point p = new Point();
		p.x = Integer.parseInt(""+str.charAt(0)); 
		p.y = Integer.parseInt(""+str.charAt(2)); 
		return p;
	}
}
