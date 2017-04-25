package com.EggsHunting.model;

public enum Movement {
	FORWARD("A"),
	RIGHT("D"),
	LEFT("G");
	
	private final String name;
	
	private Movement(String name){
		this.name =  name;
	}
	
	public String toString() {
		return this.name;
	}
}
