package com.EggsHunting.model;

public enum Orientation {
	NORTH("N"),
	EAST("E"),
	SOUTH("S"),
	WEST("W");
	
	private final String name;
	
	private Orientation(String name){
		this.name =  name;
	}
	
	public String toString() {
		return this.name;
	}
}
