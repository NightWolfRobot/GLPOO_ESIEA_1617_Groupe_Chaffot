package com.EggsHunting.model;

public enum Orientation {
	NORTH("N"),
	EAST("E"),
	SOUTH("S"),
	WEST("W");
	
	private final String abbr;
	
	private Orientation(String abbr){
		this.abbr =  abbr;
	}
	
	public String toString() {
		return this.abbr;
	}
}
