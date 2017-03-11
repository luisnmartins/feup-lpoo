package dkeep.cli;



import dkeep.logic.DungeonLevel;
import dkeep.logic.Level;
import dkeep.logic.Level1Map;
import dkeep.logic.Level2Map;
import dkeep.logic.Map;
import dkeep.logic.OgreLevel;


import java.util.Scanner;


public class DungeonKeep {

	
	
	
	public enum state{ RUNNING, WIN, LOSE, END, NEXTLEVEL}
	
	private state gameState;
	
	private Level currentLevel;

	public void run(int level)
	{
		
		Map maptouse;
		
		gameState = state.RUNNING;
		
		if(level == 1)
		{
			maptouse = new Level1Map();
			currentLevel= new DungeonLevel(maptouse);
			
		}
		else
		{
			maptouse = new Level2Map();
			currentLevel = new OgreLevel(maptouse);
		}
			
		
		// hero's movement key
		char move; 
		Scanner s = new Scanner(System.in);
		
		//first print
		printMap();
		
		
		while(gameState == state.RUNNING)
		{
			
			System.out.print("Move: ");
			
			move = s.next().charAt(0);
			
			UpdateGame(move);
			printMap();
			
		}
		s.close();
		
		
	}
	
	public void UpdateGame(char move)
	{
		
		int verifyUpdate = currentLevel.updateGame(move);
		
		if(verifyUpdate == 1)
		{	
			gameState = state.LOSE;
			System.out.println("You Lose!! Try Again");
		}
		else if(verifyUpdate == 2)
		{
			
			Level nextlevel = currentLevel.nextLevel();
			
			if(nextlevel == null)
			{
				gameState = state.WIN;
				System.out.println("Congratzz!! You're a Hero!!");
			}
			else
			{
				
				currentLevel = nextlevel;
				System.out.println("Go, go, go!! You're in the next level");
				
				
				
			}
					
		}
	}



	
	
	public void printMap() {
		
		
		char[][] currentmap = currentLevel.getMap();
		

		for (int i = 0; i < currentmap.length; i++) {
			
			for (int j = 0; j < currentmap[i].length; j++) {
				System.out.print(currentmap[i][j]);
				System.out.print(" ");

			}
			System.out.print("\n");
		}
		System.out.print('\n');

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
