package dkeep.cli;


import dkeep.logic.DungeonLevel;
import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.GameState.state;
import dkeep.logic.Level;

import java.util.concurrent.ThreadLocalRandom;

import java.util.Scanner;

/**
 * @author luismartins
 *
 */
public class DungeonKeep {

	private GameState gs;
	

	public void run()
	{
	
		Level currentLevel = new DungeonLevel();
		gs = new GameState(currentLevel);
		
		// hero's movement key
		char move; 
		gs.printMap();
		
		while(gs.getgameRunning()== state.RUNNING)
		{
			
			System.out.print("Move: ");
			Scanner s = new Scanner(System.in);
			move = s.next().charAt(0);
			
			
			gs.UpdateGame(move);
			gs.printMap();
			
		}
		
		
	}
	

	public static void main(String[] args) {

		
		DungeonKeep dk = new DungeonKeep();
		
		dk.run();
		if(dk.gs.getgameRunning() == state.WIN){
			System.out.println("You WIN!\n");
			
		}
		else 
			System.out.println("Game Over!");
		
		
		return;

	}
	
}
