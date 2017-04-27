package com.EggsHunting.model;

import java.util.ArrayList;

public class Cell {
	private ArrayList<Item> items;
	
	public Cell (){
		items = new ArrayList<>();
	}
	
	public boolean hasEggs(){
		for(Item i : items){
			if(i instanceof Egg){
				return true;
			}
		}
		return false;
	}
	
	public int getNbEggs(){
		int cmpt = 0;
		for(Item i : items){
			if(i instanceof Egg){
				cmpt++;
			}
		}
		return cmpt;
	}
	
	public void removeItem(Item i){
		items.remove(i);
	}
	
	public Egg removeEgg(){
		for(Item i : items){
			if(i instanceof Egg){
				return (Egg) i;
			}
		}
		return null;
	}
	
	public boolean isRock(){
		for(Item i: items){
			if(i instanceof Rock){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Item> getSpecials(){
		ArrayList<Item> tmpList = new ArrayList<>();
		for(Item i: items){
			if(! (i instanceof Rock) && !(i instanceof Egg)){
				tmpList.add(i);
			}
		}
		return tmpList;
	}

	/**
	 * @return the items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
