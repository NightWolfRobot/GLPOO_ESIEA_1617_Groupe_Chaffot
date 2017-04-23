package com.EggsHunting.util;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.EggsHunting.model.Child;

public class CSVChildTest {
	
	@Test
	public void testGetChildren(){
		CSVChild csv = new CSVChild();
		ArrayList<Child> children = csv.getChildren("/csv/childrenTest.csv");
		assertEquals(children.get(1).getName(), "Jenny");
		assertEquals(children.get(1).getNbEggs(), 0);
	}

}
