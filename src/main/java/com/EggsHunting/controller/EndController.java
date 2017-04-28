package com.EggsHunting.controller;

import java.util.HashMap<K,V>;

public class EndController {
	
	/**
	 * @param lap the lap you want the state of
	 * @param result all the events of the simulation
	 * adds the children with no eggs at the end of the simulation
	 */
	public HashMap<String,int> eggsAtLap(int lap, ArrayList<ArrayList<Child>> result, ArrayList<Child> children){
	    HashMap<String,int> list = new HashMap<>();
	    for(int i=0; i<lap; i++){
	        ArrayList<Child> lapList = result.get(i);
	        for(int j=0; j<lapList.size(); j++){
	            Child child = listTour.get(i);
	            String name = child.getName();
	            if(list.containsKey(name)){
	                int oldValue = list.get(name);
	                list.put(name, ++oldValue);
	            } else { 
	                list.put(name, 1);
	            }
	        }
	    }
	    //adds all the losers to the list
	    addLosers(list, children);
	    return list;
	}

	/**
	 * @param list of children with eggs at the end of the simulation
	 * @param children list of all the children
	 * adds the children with no eggs at the end of the simulation
	 */
	public void addLosers(HashMap<String,int> winners, ArrayList<Child> children){
	    for(Child child : children){
	        String name = child.getName();
	        if(!list.containsKey(name)){
	            list.put(name, 0);
	        }
	    }
	}
	
}
