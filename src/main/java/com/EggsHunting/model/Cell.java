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
	
	public void removeItem(Item i){
		items.remove(i);
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
}
