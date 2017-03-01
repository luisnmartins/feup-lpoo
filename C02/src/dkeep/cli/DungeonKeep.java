package dkeep.cli;


import dkeep.logic.GameState;
import java.util.concurrent.ThreadLocalRandom;

import java.util.Scanner;

public class DungeonKeep {

	private GameState gs = new GameState();
	
	
	
	
	public void run()
	{
		gs.startGame();
		int randomInd;
		char move; // hero's movement key
		gs.printMap();
		while(true)
		{
			Scanner s = new Scanner(System.in);
			move = s.next().charAt(0);
			
			gs.UpdateGame(move);
			gs.printMap();
			randomInd = ThreadLocalRandom.current().nextInt(1,10);
			if(randomInd >= 7)
			{
				gs.getGuard().setCharateristic();
			
			}
	
			
			
		}
	}
	

	public static void main(String[] args) {

		
		DungeonKeep dk = new DungeonKeep();
		
		dk.run();
		return;
		
		
		

	}
	
}
