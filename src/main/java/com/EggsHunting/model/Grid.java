package com.EggsHunting.model;

import org.apache.log4j.Logger;


public class Grid {
	private static Logger log = Logger.getLogger(Grid.class);
	
	private Cell[][] grid;
	private int x = 0, y = 0;
	
	public Grid(int x, int y){
		if(x>=1 && y >= 1){
			grid = new Cell[x][y];
			this.x = x;
			this.y = y;
		}
		
		initGrid();
	}
	
	private void initGrid(){
		
	}
	
	public Cell getCell(int x, int y){
		if(x >= 0 && x < this.x && y>=0 && y<this.y){
			return grid[x][y];
		}
		else{
			log.error("Les coordonnées sont invalides et ne peuvent retourner la bonne cellule");
			return grid[0][0];
		}
	}

}
