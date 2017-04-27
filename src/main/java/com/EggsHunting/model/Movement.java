package com.EggsHunting.model;

public enum Movement {
	FORWARD("A"),
	RIGHT("D"),
	LEFT("G");
	
	private final String abbr;
	
	private Movement(String abbr){
		this.abbr =  abbr;
	}
	
	public String toString() {
		return this.abbr;
	}
}
