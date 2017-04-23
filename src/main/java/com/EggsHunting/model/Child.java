package com.EggsHunting.model;

import java.util.ArrayList;

import static com.EggsHunting.model.Movement.FORWARD;
import static com.EggsHunting.model.Movement.LEFT;
import static com.EggsHunting.model.Movement.RIGHT;
import static com.EggsHunting.model.Orientation.EAST;
import static com.EggsHunting.model.Orientation.NORTH;
import static com.EggsHunting.model.Orientation.SOUTH;
import static com.EggsHunting.model.Orientation.WEST;

import java.awt.Point;

public class Child {
	private Orientation orientation;
	private ArrayList<Item> items;
	private ArrayList<Movement> path;
	private Point position; //X is horizontal and Y is vertical
	private String name;
	
	public Child(){
		orientation = NORTH;
		items = new ArrayList<Item>();
		path = new ArrayList<Movement>();
		position = new Point(0,0);
	}
	
	public Child(Point position, Orientation orientation, ArrayList<Movement> path, String name){
		this.orientation = orientation;
		this.path = path;
		this.position = position;
		this.name = name;
		this.items = new ArrayList<Item>();
	}
	
	public int getNbEggs(){
		int nbEggs = 0;
		for(Item i : items){
			if(i instanceof Egg){
				nbEggs ++;
			}
		}
		return nbEggs;
	}
	
	public void updateMovements(){
		
	}
	
	public void addItem(Item newItem){
		items.add(newItem);
	}
	
	public void removeItem(Item item){
		items.remove(item);
	}
	
	public void move(){
		//need to be careful about the limits of the Grid!
		//either in the controller directly or here with the size
		//of the garden
		Movement  nextMove;
		if(path.isEmpty()){
			nextMove = path.remove(0);
		} else {
			return ;
		}
		
		switch(orientation){
		case NORTH:
			if(nextMove == FORWARD){
				position.setLocation(position.getX(), position.getY()-1); 
			} else if(nextMove == RIGHT){
				position.setLocation(position.getX()+1, position.getY());
				orientation = EAST;
			} else if(nextMove == LEFT){
				position.setLocation(position.getX()-1, position.getY());
				orientation = WEST;
			}
			break;
		case EAST:
			if(nextMove == FORWARD){
				position.setLocation(position.getX()+1, position.getY());
			} else if(nextMove == RIGHT){
				position.setLocation(position.getX(), position.getY()+1);
				orientation = SOUTH;
			} else if(nextMove == LEFT){
				position.setLocation(position.getX(), position.getY()-1);
				orientation = NORTH;
			}
			break;
		case SOUTH:
			if(nextMove == FORWARD){
				position.setLocation(position.getX(), position.getY()+1);
			} else if(nextMove == RIGHT){
				position.setLocation(position.getX()-1, position.getY());
				orientation = WEST;
			} else if(nextMove == LEFT){
				position.setLocation(position.getX()+1, position.getY());
				orientation = EAST;
			}
			break;
		case WEST:
			if(nextMove == FORWARD){
				position.setLocation(position.getX()-1, position.getY());
			} else if(nextMove == RIGHT){
				position.setLocation(position.getX(), position.getY()-1);
				orientation = NORTH;
			} else if(nextMove == LEFT){
				position.setLocation(position.getX(), position.getY()+1);
				orientation = SOUTH;
			}
			break;
		default:
			break;
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
