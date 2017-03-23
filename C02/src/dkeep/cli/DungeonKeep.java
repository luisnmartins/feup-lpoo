
package dkeep.cli;



import dkeep.logic.DungeonLevel;
import dkeep.logic.Level;
import dkeep.logic.Level.state;
import dkeep.logic.Level1Map;
import dkeep.logic.Level2Map;
import dkeep.logic.Map;
import dkeep.logic.OgreLevel;


import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class DungeonKeep {

	
	
	
	private state gameState;
	
	private Level currentLevel;

	public void run(int level)
	{
		
		Map maptouse;
		
		gameState = state.RUNNING;
		
		if(level == 1)
		{
			maptouse = new Level1Map();
			int randGuard= ThreadLocalRandom.current().nextInt(1,4);
			currentLevel= new DungeonLevel(maptouse, randGuard);
			
		}
		else
		{
			maptouse = new Level2Map();
			int randOgre = ThreadLocalRandom.current().nextInt(1,4);
			currentLevel = new OgreLevel(maptouse, randOgre);
		}
			
		
		// hero's movement key
		char move; 
		Scanner s = new Scanner(System.in);
		
		//first print
		currentLevel.printMap();
		
		
		while(gameState == state.RUNNING)
		{
			
			System.out.print("Move: ");
			
			move = s.next().charAt(0);
			gameState = currentLevel.updateGameStatus(move);
			if(gameState == state.CHANGELEVEL)
			{
				currentLevel = currentLevel.nextLevel(-1);
				gameState = state.RUNNING;
			}
			currentLevel.printMap();
			
		}
		s.close();
		
		
	}
	

	
	public static void main(String[] args) {

		
		DungeonKeep dk = new DungeonKeep();
		
		System.out.println("Welcome aboard!!");
		System.out.println("Choose your Level: ");
		Scanner s = new Scanner(System.in);
		int level = s.nextInt();
		
		dk.run(level);
		
		s.close();
		return;

	}
	
	public state getgameRunning()
	{
		return gameState; 
	}
	
	
	
}
