
package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Level.state;
import dkeep.logic.Level1Map;
import dkeep.logic.DungeonLevel;

import dkeep.logic.Level;
import dkeep.logic.Map;


public class TestDungeonGameLogic {

	char[][] map = {{'X', 'X', 'X', 'X', 'X'},
					{'X', 'H', ' ', 'G', 'X'},
					{'I', ' ', ' ', ' ', 'X'},
					{'I', 'k', ' ', ' ', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void TestMoveHeroIntoToFreeCell()
	{
		
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap,2);
		assertEquals(1, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
	}
	
	@Test
	public void TestMoveToWall()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap,3);
		currentLevel.IstoMoveElements(false);
		currentLevel.updateGame('w');
		assertEquals(1, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
	}
	
	@Test
	public void TestAdjacentPositionGuard()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap,3);	
		currentLevel.IstoMoveElements(false);
		assertEquals(state.LOSE, currentLevel.updateGame('d')); 
		assertEquals(2, currentLevel.getHero().getY());
		assertEquals(1, currentLevel.getHero().getX());
		
	}
	
	@Test
	public void TestMoveGuard()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap,1);
		currentLevel.updateGame('w');
		assertEquals(1, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
		
	}
	
	@Test
	public void TestAdjacentunderGuard()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap,3);
		currentLevel.IstoMoveElements(false);
		currentLevel.updateGame('s');
		assertEquals(2, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
		currentLevel.updateGame('d');
		assertEquals(2, currentLevel.getHero().getX());
		assertEquals(2, currentLevel.getHero().getY());
		currentLevel.printMap();
		assertEquals(state.LOSE, currentLevel.updateGame('d'));
		currentLevel.printMap();
	}
	
	
	@Test
	public void TestCanNotLeave()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap,3);
		currentLevel.IstoMoveElements(false);			 
		currentLevel.updateGame('s'); 
		assertEquals(2, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
		currentLevel.updateGame('a');
		assertEquals(2, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
	}
	
	@Test
	public void TestOpenDoors()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap,3);
		currentLevel.IstoMoveElements(false);		
		currentLevel.updateGame('s');
		assertEquals(2, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
		currentLevel.updateGame('s');
		assertEquals(3, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
		currentLevel.getMap();
		assertEquals(true, testmap.DoorsAreOpened());
		
	}
	
	
	@Test
	public void TestNextLevel()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap,3);
		currentLevel.IstoMoveElements(false);
		currentLevel.updateGame('s');
		assertEquals(2, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
		currentLevel.updateGame('s');
		assertEquals(3, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
		assertEquals(state.NEXTLEVEL, currentLevel.updateGame('a'));
	
			
	}
	
/*	@Test
	public void TestMoveGuardIterator()
	{
		Map map2 = new Level1Map();
		Level currentLevel = new DungeonLevel(map2, 2);
		currentLevel.IstoMoveElements(true);
		for(int i=0; i<100; i++)
			currentLevel.updateGame('a');
		assertEquals(1, currentLevel.getHero().getY());
		assertEquals(1, currentLevel.getHero().getX());
	}
	
	@Test
	public void TestDrunkenGuard()
	{
		Map map2 = new Level1Map();
		Level currentLevel = new DungeonLevel(map2, 1);
		currentLevel.IstoMoveElements(true);
		for(int i=0; i<100; i++)
		{
			currentLevel.updateGame('a');
		assertEquals(1, currentLevel.getHero().getY());
		assertEquals(1, currentLevel.getHero().getX());
	}
	*/

	
	
}

