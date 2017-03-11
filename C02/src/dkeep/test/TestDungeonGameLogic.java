package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.cli.DungeonKeep.state;
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
		Level currentLevel = new DungeonLevel(testmap);
		assertEquals(1, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
	}
	
	@Test
	public void TestMoveToWall()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap);
		currentLevel.updateGame('w');
		assertEquals(1, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
	}
	
	@Test
	public void TestAdjacentPositionGuard()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap);	
		currentLevel.updateGame('d');
		assertEquals(state.LOSE, currentLevel.getgameRunning());
	
		
	}
	
	
	@Test
	public void TestCanNotLeave()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap);
		currentLevel.NotMoveElements();
		GameState gs = new GameState(currentLevel);			
		gs.UpdateGame('s');
		gs.UpdateGame('a');
		assertEquals(2, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
	}
	
	@Test
	public void TestOpenDoors()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap);
		currentLevel.NotMoveElements();
		GameState gs = new GameState(currentLevel);			
		gs.UpdateGame('s');
		gs.UpdateGame('s');
		assertEquals(true, currentLevel.DoorsAreOpened());
		
	}
	
	@Test
	public void TestNextLevel()
	{
		Map testmap = new Map(this.map);
		Level currentLevel = new DungeonLevel(testmap);
		currentLevel.NotMoveElements();
		GameState gs = new GameState(currentLevel);			
		gs.UpdateGame('s');
		gs.UpdateGame('s');
		gs.UpdateGame('a');
		assertEquals(state.WIN, gs.getgameRunning());
		
		
	}
	
	
}
