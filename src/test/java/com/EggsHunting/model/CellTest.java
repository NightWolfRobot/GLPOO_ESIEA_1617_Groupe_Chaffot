package com.EggsHunting.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {


	@Test
	public void testHasEggs() {
		Cell cell1 = new Cell();

		Egg egg= new Egg();
		cell1.getItems().add(egg);	
		assertTrue("There is an egg",cell1.hasEggs() == true);		
		
		Cell cell2 = new Cell();
		assertTrue("there is no egg",cell2.hasEggs() == false);
		
		Cell cell3 = new Cell();
		Rock rock= new Rock();
		cell3.getItems().add(rock);	
		assertTrue("There is no egg",cell3.hasEggs() == false);			
	}

	@Test
	public void testGetNbEggs() {			
		Cell cell1 = new Cell();
		Egg egg= new Egg();
		cell1.getItems().add(egg);		
		assertTrue("There is 1 egg",cell1.getNbEggs() == 1);
		
		Cell cell2 = new Cell();	
		assertTrue("There is no egg",cell2.getNbEggs() == 0);
		
		Cell cell3 = new Cell();
		Rock rock= new Rock();
		cell3.getItems().add(rock);		
		assertTrue("There is no egg", cell3.getNbEggs() == 0);
	}

	@Test
	public void testRemoveItem() {
		Cell cell1 = new Cell();
		Egg egg= new Egg();
		cell1.getItems().add(egg);
		cell1.removeItem(egg);
		assertTrue("Object Removed", cell1.getItems().isEmpty() == true);
		
		Cell cell2 = new Cell();
		cell2.removeItem(egg);
		assertTrue("No Object", cell2.getItems().isEmpty() == true);
		
		Cell cell3 = new Cell();
		Rock rock= new Rock();
		cell3.getItems().add(rock);
		cell3.removeItem(egg);
		assertTrue("Object not Removed", cell3.getItems().isEmpty() == false);
		
	}

	@Test
	public void testRemoveEgg() {
		Cell cell1 = new Cell();
		Egg egg= new Egg();
		cell1.getItems().add(egg);		
		assertTrue("Egg Removed",cell1.removeEgg() == egg && cell1.getItems().isEmpty() == true);
	
		Cell cell2 = new Cell();		
		assertTrue("No Egg",cell2.removeEgg() == null && cell2.getItems().isEmpty() == true);
		
		Cell cell3 = new Cell();
		Rock rock= new Rock();
		cell3.getItems().add(rock);
		assertTrue("No Egg",cell3.removeEgg() == null && cell3.getItems().isEmpty() == false);
		
		Cell cell4 = new Cell();
		Teleporter tel = new Teleporter();
		cell4.getItems().add(tel);
		assertTrue("No Egg",cell4.removeEgg() == null && cell4.getItems().isEmpty() == false);
		
	}

	@Test
	public void testIsRock() {
		Cell cell1 = new Cell();
		Rock rock= new Rock();
		cell1.getItems().add(rock);
		assertTrue("There is a rock", cell1.isRock() == true);
		
		Cell cell2 = new Cell();
		assertTrue("There is no rock", cell2.isRock() == false);
		
		Cell cell3 = new Cell();
		Teleporter tel = new Teleporter();
		cell1.getItems().add(tel);
		assertTrue("There is no rock", cell3.isRock() == false);
		
		Cell cell4 = new Cell();
		Egg egg = new Egg();
		cell1.getItems().add(egg);
		assertTrue("There is no rock", cell4.isRock() == false);
	}
	

	@Test
	public void testGetSpecials() {
		Cell cell1 = new Cell();
		Teleporter tel = new Teleporter();
		cell1.getItems().add(tel);		
		assertTrue("There is a Special Items", cell1.getSpecials().isEmpty() == false);
		
		Cell cell2 = new Cell();		
		assertTrue("There is no Special Items", cell2.getSpecials().isEmpty() == true);
		
		Cell cell3 = new Cell();
		Egg egg = new Egg();
		cell1.getItems().add(egg);
		assertTrue("There is no Special Items", cell3.getSpecials().isEmpty() == true);
		
		Cell cell4 = new Cell();
		Rock rock = new Rock();
		cell1.getItems().add(rock);
		assertTrue("There is no Special Items", cell4.getSpecials().isEmpty() == true);
	}

}
