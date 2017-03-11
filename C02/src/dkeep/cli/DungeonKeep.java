package dkeep.cli;



import dkeep.logic.DungeonLevel;
import dkeep.logic.GameState;
import dkeep.logic.GameState.state;
import dkeep.logic.Level;
import dkeep.logic.Level1Map;
import dkeep.logic.Level2Map;
import dkeep.logic.Map;
import dkeep.logic.OgreLevel;


import java.util.Scanner;


public class DungeonKeep {

	private GameState gs;
	

	public void run(int level)
	{
		Map maptouse;
		Level currentLevel;
		
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
			
		
		
		gs = new GameState(currentLevel);
		
		// hero's movement key
		char move; 
		Scanner s = new Scanner(System.in);
		
		//first print
		gs.printMap();
		
		
		while(gs.getgameRunning()== state.RUNNING)
		{
			
			System.out.print("Move: ");
			
			move = s.next().charAt(0);
			
			gs.UpdateGame(move);
			gs.printMap();
			
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
	
}
