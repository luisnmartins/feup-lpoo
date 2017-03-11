package dkeep.test;


import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.cli.DungeonKeep;
import dkeep.cli.DungeonKeep.state;
import dkeep.logic.OgreLevel;

import dkeep.logic.Level;
import dkeep.logic.Map;



public class TestOgreLevelGameLogic {
	char[][] map = {{'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', 'O', 'X'},
			{'I', ' ', ' ', ' ', 'X'},
			{'X', 'k', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testHeroNextToOgre()
	{
		
		Map testmap = new Map(map);
		Level currentLevel = new OgreLevel(testmap);
		currentLevel.updateGame('d');
		//assertEquals(state.LOSE,)
	}
	
	@Test
	public void testHeroCatchKey()
	{
		Map testmap = new Map(map);
		Level currentLevel = new OgreLevel(testmap);
		currentLevel.updateHero('s');
		currentLevel.updateHero('s');
		assertEquals('K',currentLevel.getHero().getElement());
		
	}
	
	@Test
	public void testHeroFailOpenDoor()
	{
		Map testmap = new Map(map);
		Level currentLevel = new OgreLevel(testmap);
		currentLevel.updateHero('s');
		currentLevel.updateHero('d');
		assertFalse(currentLevel.DoorsAreOpened());
	}
	
	@Test
	public void testHeroOpensKey()
	{
		Map testmap = new Map(map);
		Level currentLevel = new OgreLevel(testmap);
		currentLevel.updateHero('s');
		currentLevel.updateHero('s');
		assertTrue(currentLevel.getKey().getFound());
		currentLevel.updateHero('w');
		currentLevel.updateHero('a');
		assertTrue(currentLevel.DoorsAreOpened()); 
		
	}
	
	@Test
	public void testHeroWins()
	{
		
	}
}
