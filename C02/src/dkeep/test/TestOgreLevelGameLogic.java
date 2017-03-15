package dkeep.test;


import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.cli.DungeonKeep;
import dkeep.cli.DungeonKeep.state;
import dkeep.logic.OgreLevel;

import dkeep.logic.Level;
import dkeep.logic.Map;
import dkeep.logic.Ogre;



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
		OgreLevel currentLevel = new OgreLevel(testmap);
		currentLevel.NotMoveElements();	
		assertEquals(state.LOSE, currentLevel.updateGame('d'));
	}
	
	@Test
	public void testHeroCatchKey()
	{
		Map testmap = new Map(map);
		Level currentLevel = new OgreLevel(testmap);
		currentLevel.NotMoveElements();	
		currentLevel.updateGame('s');
		currentLevel.updateGame('s');
		assertTrue(currentLevel.getKey().getFound());
		assertEquals('K',currentLevel.getHero().getElement());
		
	}
	
	@Test
	public void testHeroFailOpenDoor()
	{
		Map testmap = new Map(map);
		Level currentLevel = new OgreLevel(testmap);
		currentLevel.NotMoveElements();	
		currentLevel.updateGame('s');
		currentLevel.updateGame('d');
		assertFalse(currentLevel.DoorsAreOpened());
	}
	
	@Test
	public void testHeroOpensKey()
	{
		Map testmap = new Map(map);
		Level currentLevel = new OgreLevel(testmap);
		currentLevel.NotMoveElements();
		currentLevel.updateGame('s');
		currentLevel.updateGame('s');
		assertTrue(currentLevel.getKey().getFound());
		currentLevel.updateGame('w');
		currentLevel.updateGame('a');
		currentLevel.getMap();
		assertTrue(currentLevel.DoorsAreOpened()); 
		
	}
	
	@Test
	public void testHeroWins()
	{
		Map testmap = new Map(map);
		Level currentLevel = new OgreLevel(testmap);
		currentLevel.NotMoveElements();
		currentLevel.updateGame('s');
		currentLevel.updateGame('s');
		currentLevel.updateGame('w');
		currentLevel.updateGame('a');
		assertEquals(state.NEXTLEVEL, currentLevel.updateGame('a'));
		assertNull(currentLevel.nextLevel());
		
				
	}
	
	@Test
	public void testHeroLoss()
	{
		Map testmap = new Map(map);
		OgreLevel currentLevel = new OgreLevel(testmap);
		currentLevel.NotMoveElements();
		Ogre myOgre = currentLevel.getFirstOgre();
		
		myOgre.setClub(2, 3);
		currentLevel.updateGame('s');
		assertEquals(state.LOSE,currentLevel.updateGame('d')); 
	}
}
