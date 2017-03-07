package dkeep.cli;


import dkeep.logic.GameMap;
import dkeep.logic.GameState;
import dkeep.logic.Level1Map;

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
	
		GameMap firstmap = new Level1Map();
		gs = new GameState(firstmap);
		
		// hero's movement key
		char move; 
		gs.printMap();
		
		while(gs.getgameRunning()==0)
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
		if(dk.gs.getgameRunning() == 1){
			System.out.println("You WIN!\n");
			
		}
		else 
			System.out.println("Game Over!");
		
		
		return;

	}
	
}
