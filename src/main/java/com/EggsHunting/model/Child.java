package com.EggsHunting.model;

import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

import com.EggsHunting.util.CSVChild;

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
	private int color;
	private boolean isPickingUpItem;
	private static Logger log = Logger.getLogger(Child.class);
	
	public Child(){
		orientation = NORTH;
		items = new ArrayList<Item>();
		path = new ArrayList<Movement>();
		position = new Point(0,0);
		Random random = new Random();
		color = random.nextInt(3) + 1;
		isPickingUpItem = false;
		log.info("New child orientation "+ orientation+"; color "+ color); 
		
	}
	
	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}

	public Child(Point position, Orientation orientation, ArrayList<Movement> path, String name){
		this.orientation = orientation;
		this.path = path;
		this.position = position;
		this.name = name;
		this.items = new ArrayList<Item>();
		Random random = new Random();
		color = random.nextInt(3) + 1;
		log.info("New child orientation "+ orientation+"; color "+ color); 
	}
	
	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public ArrayList<Movement> getPath() {
		return path;
	}

	public void setPath(ArrayList<Movement> path) {
		this.path = path;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
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
	
	public boolean isPickingUpItem(){
		return isPickingUpItem;
	}
	
	public void pickUpItem(){
		isPickingUpItem = true;
	}
	
	public void donePickingUpItem(){
		isPickingUpItem = false;
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
		//turn the child
		this.turn();
		//moves the child
		position = this.getNextPosition(); //new Point((int)position.getX()+1, (int)position.getY()); 
		//removes the movement from the path
		path.remove(0);
	}
	
	public Point getNextPosition(){
		Movement  nextMove;
		Point nextPosition= new Point((int)position.getX(), (int)position.getY());
		if(!path.isEmpty()){
			nextMove = path.get(0);
		} else {
			return null;
		}
		
		switch(orientation){
		case NORTH:
			if(nextMove == FORWARD){
				nextPosition = new Point((int)position.getX(), (int)position.getY()-1); 
			} else if(nextMove == RIGHT){
				nextPosition = new Point((int)position.getX()+1, (int)position.getY());
			} else if(nextMove == LEFT){
				nextPosition = new Point((int)position.getX()-1, (int)position.getY());
			}
			break;
		case EAST:
			if(nextMove == FORWARD){
				nextPosition = new Point((int)position.getX()+1, (int)position.getY());
			} else if(nextMove == RIGHT){
				nextPosition = new Point((int)position.getX(), (int)position.getY()+1);
			} else if(nextMove == LEFT){
				nextPosition = new Point((int)position.getX(), (int)position.getY()-1);
			}
			break;
		case SOUTH:
			if(nextMove == FORWARD){
				nextPosition = new Point((int)position.getX(), (int)position.getY()+1);
			} else if(nextMove == RIGHT){
				nextPosition = new Point((int)position.getX()-1, (int)position.getY());
			} else if(nextMove == LEFT){
				nextPosition = new Point((int)position.getX()+1, (int)position.getY());
			}
			break;
		case WEST:
			if(nextMove == FORWARD){
				nextPosition = new Point((int)position.getX()-1, (int)position.getY());
			} else if(nextMove == RIGHT){
				nextPosition = new Point((int)position.getX(), (int)position.getY()-1);
			} else if(nextMove == LEFT){
				nextPosition = new Point((int)position.getX(), (int)position.getY()+1);
			}
			break;
		default:
			break;
		}
		return nextPosition;
	}
	
	public void turn(){
		Movement  nextMove;
		if(!path.isEmpty()){
			nextMove = path.get(0);
		} else {
			return ;
		}
		
		switch(orientation){
		case NORTH:
			if(nextMove == RIGHT){
				orientation = EAST;
			} else if(nextMove == LEFT){
				orientation = WEST;
			}
			break;
		case EAST:
			if(nextMove == RIGHT){
				orientation = SOUTH;
			} else if(nextMove == LEFT){
				orientation = NORTH;
			}
			break;
		case SOUTH:
			if(nextMove == RIGHT){
				orientation = WEST;
			} else if(nextMove == LEFT){
				orientation = EAST;
			}
			break;
		case WEST:
			if(nextMove == RIGHT){
				orientation = NORTH;
			} else if(nextMove == LEFT){
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
