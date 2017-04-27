package com.EggsHunting.util;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.EggsHunting.model.Cell;
import com.EggsHunting.model.Egg;
import com.EggsHunting.model.Garden;
import com.EggsHunting.model.Rock;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVGarden {
	private static Logger log = Logger.getLogger(CSVGarden.class);
	private static CSVReader reader;
	private static CSVWriter writer;
	
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
		String[] parts = str.split("-");
		p.x = Integer.parseInt(""+parts[0])-1; 
		p.y = Integer.parseInt(""+parts[1])-1; 
		return p;
	}
	
	public static void saveGarden(String filename, Cell[][] grid){
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
		Cell cell;
		//Save the garden
		line = new String[3];
		line[0] = "J";
		line[1] = String.valueOf(grid.length);
		line[2] = String.valueOf(grid[0].length);
		writer.writeNext(line);
		//go through the garden
	    for(int i=0; i<grid.length; i++){
	    	for(int j=0; j<grid[i].length; j++){
	    		
	    		cell = grid[i][j];
	    		
		    	if(cell.isRock()){
		    		line = new String[2];
		    		line[0] = "R";
		    		line[1] = String.valueOf(i)+"-"+String.valueOf(j);
		    	} else if(cell.hasEggs()) { 
		    		line = new String[3];
		    		line[0] = "C";
		    		line[1] = String.valueOf(i)+"-"+String.valueOf(j);
		    		line[2] = String.valueOf(cell.getNbEggs());
		    	} else {
		    		continue;
		    	}
		    	
		    	writer.writeNext(line);
	    	}
	    }
	    
		
	    try{
	    	writer.close();
	    }catch(IOException e){
	    	log.error("Impossible to close CSV writer"+ e.toString());
	    }
	    
		//return true;
	}
}
