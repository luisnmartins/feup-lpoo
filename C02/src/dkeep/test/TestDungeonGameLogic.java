package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.GameState;
import dkeep.logic.Level;
import dkeep.logic.TestLevel;

//import dkeep.logic.CellPosition;

public class TestDungeonGameLogic {

	char[][] map = {{'X', 'X', 'X', 'X', 'X'},
					{'X', 'H', ' ', 'G', 'X'},
					{'I', ' ', ' ', ' ', 'X'},
					{'I', 'k', ' ', ' ', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void TestMoveHeroIntoToFreeCell()
	{
		Level currentLevel = new TestLevel();
		GameState gs = new GameState(currentLevel);
		assertEquals(1, currentLevel.getHero().getX());
		assertEquals(1, currentLevel.getHero().getY());
	}
	
}
