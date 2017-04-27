package com.EggsHunting.model;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.junit.Test;

public class ChildTest {




	@Test
	public void testGetNbEggs() {
		Child child1 = new Child();
		Egg egg = new Egg();
		Egg egg2 =new Egg();
		Egg egg3 =new Egg();
		child1.getItems().add(egg);
		child1.getItems().add(egg2);
		child1.getItems().add(egg3);		
		assertTrue("The child have 3 eggs",child1.getNbEggs() == 3);
		
		Child child2 = new Child();
		assertTrue("The child have 3 eggs",child2.getNbEggs() == 0);
		
	}

	@Test
	public void testIsPickingUpItem() {
		Child child1 = new Child();
		assertTrue("Child isPickingUpItem",child1.isPickingUpItem() == false);
		
	}

	@Test
	public void testPickUpItem() {
		Child child1 = new Child();
		child1.pickUpItem();	
		assertTrue("Child1 is picking up item",child1.isPickingUpItem() == true);
		assertFalse("Child1 should picking up item",child1.isPickingUpItem() == false);
	}

	@Test
	public void testDonePickingUpItem() {
		Child child1 = new Child();
		child1.donePickingUpItem();
		assertTrue("Child1 is done picking up item",child1.isPickingUpItem() == false);
		assertFalse("Child1 should not picking up item",child1.isPickingUpItem() == true);
		
	}

	/*@Test						// not implemented
	public void testUpdateMovements() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testAddItem() {
		Child child1 = new Child();
		Egg egg = new Egg();
		child1.addItem(egg);
		
		assertTrue("Object Added to Child",child1.getItems().isEmpty() == false);
		assertFalse("Object Added to Child",child1.getItems().isEmpty() == true);
	}

	@Test
	public void testRemoveItem() {
		Child child1 = new Child();
		Egg egg = new Egg();
		child1.getItems().add(egg);
		child1.removeItem(egg);
		
		assertTrue("Object removed to Child",child1.getItems().isEmpty() == true);
		assertFalse("Object removed to Child",child1.getItems().isEmpty() == false);
		

	}

	@Test
	public void testMove() {
		ArrayList<Movement> path1 = new ArrayList<>();
		path1.add(Movement.RIGHT);
		path1.add(Movement.LEFT);
		Child child1 = new Child();
		child1.setPath(path1);
		child1.move();
		assertTrue("Got Next Position",child1.getPath().get(0) == Movement.LEFT);
		
	}

	@Test
	public void testGetNextPosition() {
		ArrayList<Movement> path1 = new ArrayList<>();
		path1.add(Movement.RIGHT);		
		Child child1 = new Child();
		child1.setPath(path1);
		assertTrue("Got Next Position",child1.getNextPosition().getY() == child1.getPosition().getY()+1);
		
		ArrayList<Movement> path2 = new ArrayList<>();
		path2.add(Movement.LEFT);		
		Child child2 = new Child();
		child2.setPath(path2);
		assertTrue("Got Next Position",child2.getNextPosition().getY() == child2.getPosition().getY()-1);
		
		ArrayList<Movement> path3 = new ArrayList<>();
		path3.add(Movement.FORWARD);		
		Child child3 = new Child();
		child3.setPath(path3);
		assertTrue("Got Next Position",child3.getNextPosition().getX() == child3.getPosition().getX()-1);	
		
		
		
	}

	@Test
	public void testTurn() {
		ArrayList<Movement> path = new ArrayList<>();
		path.add(Movement.RIGHT);		
		Child child1 = new Child();
		child1.setOrientation(Orientation.SOUTH);
		child1.setPath(path);
		child1.turn();
		assertTrue("Child turned",child1.getOrientation() == Orientation.WEST);
		
	}

}
