package com.EggsHunting.model;

import java.awt.Point;
import java.util.ArrayList;

import org.apache.log4j.Logger;


public class Garden {
	private static Logger log = Logger.getLogger(Garden.class);
	
	private Cell[][] grid;
	private int x = 0, y = 0;
	private ArrayList<Child> children;
	
	public Garden(int x, int y, ArrayList<Child> children){
		if(x>=1 && y >= 1){
			grid = new Cell[x][y];
			this.x = x;
			this.y = y;
			this.children = children;
		}
		
		initGrid();
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	public Garden(int x, int y){
		if(x>=1 && y >= 1){
			grid = new Cell[x][y];
			this.x = x;
			this.y = y;
		}
		
		initGrid();
	}
	
	private void initGrid(){
		for(int i =0; i<x; i++){
			for(int j =0; j< y; j++){
				grid[i][j] = new Cell();
			}
		}
		for(Child child : children){
			Point currentPosition = child.getPosition();
			int posX = (int) currentPosition.getX(), posY = (int) currentPosition.getY();
			grid[posX][posY].childSteppingIn();
		}
	}
	
	public Cell getCell(int x, int y){
		if(x >= 0 && x < this.x && y>=0 && y<this.y){
			return grid[x][y];
		}
		else{
			log.error("Les coordonn�es sont invalides et ne peuvent pas retourner la bonne cellule");
			return grid[0][0];
		}
	}

	/**
	 * @return the children
	 */
	public ArrayList<Child> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(ArrayList<Child> children) {
		this.children = children;
	}
	
	public Cell[][] getGrid(){
		return this.grid;
	}



}
