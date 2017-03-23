
package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Level.state;
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
		currentLevel.printMap();
		currentLevel.updateGame('s');
		currentLevel.printMap();
		currentLevel.updateGame('d');
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
		currentLevel.updateGame('s');
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
		currentLevel.updateGame('s');
		assertEquals(state.NEXTLEVEL, currentLevel.updateGame('a'));
		
		
		
	}
	
	
}

