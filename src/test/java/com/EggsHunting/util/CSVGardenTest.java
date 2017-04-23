package com.EggsHunting.util;

import static org.junit.Assert.*;


import org.junit.Test;

import com.EggsHunting.model.Garden;

public class CSVGardenTest {
	
	@Test
	public void testGetGarden(){
		Garden g = CSVGarden.getGarden("/csv/gardenTest.csv");
		assertEquals(g.getX(), 6);
		assertEquals(g.getY(), 5);
		assertEquals(g.getCell(4, 2).getNbEggs(), 1);
		assertEquals(g.getCell(1, 4).getNbEggs(), 3);
		assertTrue(g.getCell(5, 3).isRock());
		/*J 6 5
		C 4-2 1
		C 1-4 3
		R 5-3*/
	}

}
